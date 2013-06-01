/**
 * Copyright (c) 2013 HAN University of Applied Sciences
 * Arjan Oortgiese
 * Joëll Portier
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the "Software"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package com.dare2date.externeservice.facebook;

import com.dare2date.domein.facebook.FacebookEvent;
import com.dare2date.utility.IHttpClient;
import junit.framework.Assert;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FacebookAPITest {
    private FacebookAPI facebook;
    private IHttpClient httpClientMock;

    @Before
    public void before() {
        facebook = new FacebookAPI();
        facebook.setAppId("0000000");
        facebook.setAppSecret("11111111");

        httpClientMock = EasyMock.createMock(IHttpClient.class);

        facebook.setHttpClient(httpClientMock);
    }

    @After
    public void after() {
        EasyMock.verify(httpClientMock);
    }

    @Test
    public void testGetUsersEventsEmpty() {
        EasyMock.expect(httpClientMock.get(EasyMock.isA(String.class)))
                .andReturn("{ \"data\": []\n }").once();

        EasyMock.replay(httpClientMock);

        List<FacebookEvent> result = facebook.getUsersEvents("abc");

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testGetUsersEvents2Events() {
        EasyMock.expect(httpClientMock.get(EasyMock.isA(String.class)))
                .andReturn("{\n" +
                        "   \"data\": [\n" +
                        "      {\n" +
                        "         \"name\": \"Test.\",\n" +
                        "         \"start_time\": \"2013-06-28\",\n" +
                        "         \"timezone\": \"Europe/Amsterdam\",\n" +
                        "         \"location\": \"Rotterdam, Zuid-Holland\",\n" +
                        "         \"id\": \"123456\",\n" +
                        "      },\n" +
                        "      {\n" +
                        "         \"name\": \"Paintball\",\n" +
                        "         \"start_time\": \"2013-06-01\",\n" +
                        "         \"id\": \"234567\",\n" +
                        "      }\n" +
                        "   ]\n" +
                        "}").once();

        EasyMock.replay(httpClientMock);

        List<FacebookEvent> result = facebook.getUsersEvents("abc");

        Assert.assertEquals(2, result.size());
    }

    @Test
    public void testGetUsersEventsFields() {
        EasyMock.expect(httpClientMock.get(EasyMock.isA(String.class)))
                .andReturn("{\n" +
                        "   \"data\": [\n" +
                        "      {\n" +
                        "         \"name\": \"Test.\",\n" +
                        "         \"start_time\": \"2013-06-28\",\n" +
                        "         \"timezone\": \"Europe/Amsterdam\",\n" +
                        "         \"location\": \"Rotterdam, Zuid-Holland\",\n" +
                        "         \"id\": \"123456\",\n" +
                        "      }\n" +
                        "   ]\n" +
                        "}").once();

        EasyMock.replay(httpClientMock);

        List<FacebookEvent> result = facebook.getUsersEvents("abc");

        Assert.assertEquals(1, result.size());
        Assert.assertEquals("Test.", result.get(0).getName());
        Assert.assertEquals("123456", result.get(0).getId());
    }

    @Test
    public void testGetUsersEventsError() {
        EasyMock.expect(httpClientMock.get(EasyMock.isA(String.class)))
                .andReturn("{\n" +
                        "   \"error\": {\n" +
                        "      \"message\": \"An active access token must be used to query information about the current user.\",\n" +
                        "      \"type\": \"OAuthException\",\n" +
                        "      \"code\": 2500\n" +
                        "   }\n" +
                        "}").once();

        EasyMock.replay(httpClientMock);

        List<FacebookEvent> result = facebook.getUsersEvents("abc");

        Assert.assertEquals(0, result.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetUserEventsNullAccessToken() {
        EasyMock.replay(httpClientMock);

        facebook.getUsersEvents(null);
    }
}
