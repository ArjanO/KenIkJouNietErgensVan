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
package com.dare2date.kenikjounietergensvan.webservice;

import com.dare2date.businessservice.FacebookService;
import com.dare2date.businessservice.LastfmService;
import com.dare2date.domein.facebook.FacebookData;
import com.dare2date.domein.facebook.FacebookEducationHistory;
import com.dare2date.domein.facebook.FacebookEvent;
import com.dare2date.domein.facebook.FacebookWorkHistory;
import com.dare2date.domein.lastfm.LastfmData;
import com.dare2date.domein.lastfm.LastfmEvent;
import junit.framework.Assert;
import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("spring-ws-servlet.xml")
public class KenIkJouNietErgensVanEndpointTest {
    @Autowired
    private KenIkJouNietErgensVanEndpoint endpoint;

    @Test
    public void testAgreements() {
        OvereenkomstenUsersInput input = new OvereenkomstenUsersInput();
        input.setUsers(new OvereenkomstenUsersInput.Users());
        input.users.user = new ArrayList<String>();

        String username1 = "Theus";
        String username2 = "Klaas";

        FacebookService fbService = createFacebookServiceMock(username1, username2);
        LastfmService lfmService = createLastfmServiceMock(username1, username2);

        EasyMock.replay((fbService));
        EasyMock.replay(lfmService);

        endpoint.setFacebookService(fbService);
        endpoint.setLastFmService(lfmService);

        input.users.user.add(username1);
        input.users.user.add(username2);

        OvereenkomstenUsersRequest request = new OvereenkomstenUsersRequest();
        request.setInput(input);

        OvereenkomstenUsersResponse response = endpoint.agreements(request);

        Assert.assertTrue(response.result.getData().getItems().contains("Test"));

        EasyMock.verify(fbService);
        EasyMock.verify(lfmService);
    }

    private FacebookService createFacebookServiceMock(String username1, String username2) {
        FacebookService fbService = EasyMock.createMock(FacebookService.class);

        FacebookData fbDataUser1 = new FacebookData();
        fbDataUser1.setWorkHistory(new ArrayList<FacebookWorkHistory>());
        fbDataUser1.setEducationHistory(new ArrayList<FacebookEducationHistory>());
        fbDataUser1.setEvents(new ArrayList<FacebookEvent>());

        FacebookData fbDataUser2 = new FacebookData();
        fbDataUser2.setWorkHistory(new ArrayList<FacebookWorkHistory>());
        fbDataUser2.setEducationHistory(new ArrayList<FacebookEducationHistory>());
        fbDataUser2.setEvents(new ArrayList<FacebookEvent>());

        EasyMock.expect(fbService.getFacebookMatch(EasyMock.eq(username1))).andReturn(fbDataUser1).once();
        EasyMock.expect(fbService.getFacebookMatch(EasyMock.eq(username2))).andReturn(fbDataUser2).once();

        return fbService;
    }

    public LastfmService createLastfmServiceMock(String username1, String username2) {
        LastfmData lfmDataUser1 = new LastfmData();
        lfmDataUser1.addEvent(new LastfmEvent(1, "Test"));

        LastfmData lfmDataUser2 = new LastfmData();
        lfmDataUser2.addEvent(new LastfmEvent(1, "Test"));

        LastfmService lfmService = EasyMock.createMock(LastfmService.class);

        EasyMock.expect(lfmService.getLastfmGegevens(EasyMock.eq(username1))).andReturn(lfmDataUser1).once();
        EasyMock.expect(lfmService.getLastfmGegevens(EasyMock.eq(username2))).andReturn(lfmDataUser2).once();

        return lfmService;
    }
}
