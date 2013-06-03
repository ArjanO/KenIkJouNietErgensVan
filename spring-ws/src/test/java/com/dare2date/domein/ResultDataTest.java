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
package com.dare2date.domein;

import com.dare2date.domein.facebook.FacebookData;
import com.dare2date.domein.facebook.FacebookEducationHistory;
import com.dare2date.domein.facebook.FacebookEvent;
import com.dare2date.domein.facebook.FacebookWorkHistory;
import com.dare2date.domein.lastfm.LastfmData;
import com.dare2date.domein.lastfm.LastfmEvent;
import com.dare2date.externeservice.lastfm.LastfmAPI;
import com.dare2date.kenikjounietergensvan.webservice.Data;
import com.dare2date.utility.HttpClient;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.transform.sax.SAXSource;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RunWith(BlockJUnit4ClassRunner.class)
public class ResultDataTest {


    @Test
    public void testResultDataToStringList() {
        ResultData rsdata = new ResultData();
        FacebookData fbdata1 = new FacebookData();
        FacebookData fbdata2 = new FacebookData();

        List<FacebookEvent> events1 = new ArrayList<FacebookEvent>();
        List<FacebookEvent> events2 = new ArrayList<FacebookEvent>();
        events1.add(new FacebookEvent("123","fe"));
        events1.add(new FacebookEvent("456","DEF"));
        events2.add(new FacebookEvent("123","fe"));
        events2.add(new FacebookEvent("321","ABC"));
        fbdata1.setEvents(events1);
        fbdata2.setEvents(events2);

        List<FacebookWorkHistory> workHistories1 = new ArrayList<FacebookWorkHistory>();
        List<FacebookWorkHistory> workHistories2 = new ArrayList<FacebookWorkHistory>();
        FacebookWorkHistory ws1 = new FacebookWorkHistory();
        ws1.setId("741");
        ws1.setName("ws");
        workHistories1.add(ws1);
        FacebookWorkHistory ws2 = new FacebookWorkHistory();
        ws2.setId("852");
        ws2.setName("ws2");
        workHistories1.add(ws2);
        FacebookWorkHistory ws3 = new FacebookWorkHistory();
        ws3.setId("963");
        ws3.setName("ws3");
        workHistories2.add(ws3);
        FacebookWorkHistory ws4 = new FacebookWorkHistory();
        ws4.setId("741");
        ws4.setName("ws");
        workHistories2.add(ws4);
        fbdata1.setWorkHistory(workHistories1);
        fbdata2.setWorkHistory(workHistories2);

        List<FacebookEducationHistory> educationHistories1 = new ArrayList<FacebookEducationHistory>();
        List<FacebookEducationHistory> educationHistories2 = new ArrayList<FacebookEducationHistory>();
        FacebookEducationHistory edu1 = new FacebookEducationHistory();
        edu1.setId("987");
        edu1.setName("edu");
        educationHistories1.add(edu1);
        FacebookEducationHistory edu2 = new FacebookEducationHistory();
        edu2.setId("852");
        edu2.setName("edu2");
        educationHistories2.add(edu2);
        FacebookEducationHistory edu3 = new FacebookEducationHistory();
        edu3.setId("987");
        edu3.setName("edu");
        educationHistories2.add(edu3);
        fbdata1.setEducationHistory(educationHistories1);
        fbdata2.setEducationHistory(educationHistories2);

        rsdata.setFacebookData1(fbdata1);
        rsdata.setFacebookData2(fbdata2);

        LastfmData lfmdata1 = new LastfmData();
        LastfmData lfmdata2 = new LastfmData();
        lfmdata1.getEvents().add(new LastfmEvent(123,"lfme"));
        lfmdata1.getEvents().add(new LastfmEvent(456,"ABC"));
        lfmdata2.getEvents().add(new LastfmEvent(123,"lfme"));
        rsdata.setLastfmData1(lfmdata1);
        rsdata.setLastfmData2(lfmdata2);

        Data data = rsdata.getMatchingData();

        Assert.assertEquals(4,data.getItems().size());
        Assert.assertEquals("fe",data.getItems().get(0));
        Assert.assertEquals("edu",data.getItems().get(1));
        Assert.assertEquals("ws",data.getItems().get(2));
        Assert.assertEquals("lfme",data.getItems().get(3));
    }
}