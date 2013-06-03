/**
 * Copyright (c) 2013 HAN University of Applied Sciences
 * Arjan Oortgiese
 * Joëll Portier
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation
 * files (the \"Software\"), to deal in the Software without
 * restriction, including without limitation the rights to use,
 * copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the
 * Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
 * OTHER DEALINGS IN THE SOFTWARE.
 */
package com.dare2date.externeservice.lastfm;

import com.dare2date.domein.facebook.FacebookEvent;
import com.dare2date.domein.lastfm.LastfmData;
import com.dare2date.domein.lastfm.LastfmEvent;
import com.dare2date.externeservice.facebook.FacebookAPI;
import com.dare2date.utility.HttpClient;
import com.dare2date.utility.IHttpClient;
import junit.framework.Assert;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.TreeSet;

public class LastfmAPITest {

    @Test
    public void testGetUserEventHistory() {
        LastfmAPI lastfm = new LastfmAPI();
        IHttpClient httpClientMock = EasyMock.createMock(HttpClient.class);
        lastfm.setHttpClient(httpClientMock);
        EasyMock.expect(httpClientMock.get(EasyMock.isA(String.class))).andReturn("{\n" +
                "    \"events\": {\n" +
                "        \"event\": [\n" +
                "            {\n" +
                "                \"id\": \"1813450\",\n" +
                "                \"title\": \"De Jeugd van Tegenwoordig\",\n" +
                "                \"artists\": {\n" +
                "                    \"artist\": \"De Jeugd van Tegenwoordig\",\n" +
                "                    \"headliner\": \"De Jeugd van Tegenwoordig\"\n" +
                "                },\n" +
                "                \"venue\": {\n" +
                "                    \"id\": \"8782978\",\n" +
                "                    \"name\": \"Doornroosje\",\n" +
                "                    \"location\": {\n" +
                "                        \"geo:point\": {\n" +
                "                            \"geo:lat\": \"51.830253\",\n" +
                "                            \"geo:long\": \"5.860249\"\n" +
                "                        },\n" +
                "                        \"city\": \"Nijmegen\",\n" +
                "                        \"country\": \"Netherlands\",\n" +
                "                        \"street\": \"Groenewoudseweg 322\",\n" +
                "                        \"postalcode\": \"6525 EL\"\n" +
                "                    },\n" +
                "                    \"url\": \"http://www.last.fm/venue/8782978+Doornroosje\",\n" +
                "                    \"website\": \"http://www.doornroosje.nl\",\n" +
                "                    \"phonenumber\": \"+31-(0)24-3554243\",\n" +
                "                    \"image\": [\n" +
                "                        {\n" +
                "                            \"#text\": \"http://userserve-ak.last.fm/serve/34/293150.jpg\",\n" +
                "                            \"size\": \"small\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"#text\": \"http://userserve-ak.last.fm/serve/64/293150.jpg\",\n" +
                "                            \"size\": \"medium\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"#text\": \"http://userserve-ak.last.fm/serve/126/293150.jpg\",\n" +
                "                            \"size\": \"large\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"#text\": \"http://userserve-ak.last.fm/serve/252/293150.jpg\",\n" +
                "                            \"size\": \"extralarge\"\n" +
                "                        },\n" +
                "                        {\n" +
                "                            \"#text\": \"http://userserve-ak.last.fm/serve/_/293150/Doornroosje.jpg\",\n" +
                "                            \"size\": \"mega\"\n" +
                "                        }\n" +
                "                    ]\n" +
                "                },\n" +
                "                \"startDate\": \"Wed, 23 Mar 2011 20:00:00\",\n" +
                "                \"description\": \"\",\n" +
                "                \"image\": [\n" +
                "                    {\n" +
                "                        \"#text\": \"http://userserve-ak.last.fm/serve/34/2601453.jpg\",\n" +
                "                        \"size\": \"small\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"#text\": \"http://userserve-ak.last.fm/serve/64/2601453.jpg\",\n" +
                "                        \"size\": \"medium\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"#text\": \"http://userserve-ak.last.fm/serve/126/2601453.jpg\",\n" +
                "                        \"size\": \"large\"\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"#text\": \"http://userserve-ak.last.fm/serve/252/2601453.jpg\",\n" +
                "                        \"size\": \"extralarge\"\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"attendance\": \"16\",\n" +
                "                \"reviews\": \"0\",\n" +
                "                \"tag\": \"lastfm:event=1813450\",\n" +
                "                \"url\": \"http://www.last.fm/event/1813450+De+Jeugd+van+Tegenwoordig+at+Doornroosje+on+23+March+2011\",\n" +
                "                \"website\": \"\",\n" +
                "                \"cancelled\": \"0\",\n" +
                "                \"@attr\": {\n" +
                "                    \"status\": \"0\"\n" +
                "                }\n" +
                "            }\n" +
                "        ],\n" +
                "        \"@attr\": {\n" +
                "            \"user\": \"ArjansZusje\",\n" +
                "            \"url\": \"http://www.last.fm/user/ArjansZusje/events\",\n" +
                "            \"usertimezone\": \"UTC\",\n" +
                "            \"page\": \"1\",\n" +
                "            \"perPage\": \"50\",\n" +
                "            \"totalPages\": \"1\",\n" +
                "            \"total\": \"2\"\n" +
                "        }\n" +
                "    }\n" +
                "}");
        EasyMock.replay(httpClientMock);
        TreeSet<LastfmEvent> events = lastfm.getUserEventHistory("Name");
        Assert.assertEquals(1,events.size());
        Assert.assertEquals(1813450,events.first().getId());

    }
}
