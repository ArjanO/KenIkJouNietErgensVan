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
package com.dare2date.domein.lastfm;

import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Calendar;

@RunWith(BlockJUnit4ClassRunner.class)
public class LastfmDataTest {


    @Test
    public void testLastfmDataCompare() {
        LastfmData lfmData1 = new LastfmData();
        lfmData1.addEvent(new LastfmEvent(123456, "ASD", Calendar.getInstance(), "Baak", "Nederland", "Hoofdstraat"));
        lfmData1.addEvent(new LastfmEvent(236547, "SFM", Calendar.getInstance(), "Baak", "Nederland", "Hoofdstraat"));
        lfmData1.addEvent(new LastfmEvent(743125, "WVF", Calendar.getInstance(), "Baak", "Nederland", "Hoofdstraat"));
        LastfmData lfmData2 = new LastfmData();
        lfmData2.addEvent(new LastfmEvent(546104, "ASD", Calendar.getInstance(), "Baak", "Nederland", "Hoofdstraat"));
        lfmData2.addEvent(new LastfmEvent(743125, "SVM", Calendar.getInstance(), "Baak", "Nederland", "Hoofdstraat"));
        lfmData2.addEvent(new LastfmEvent(456465, "KLMK", Calendar.getInstance(), "Baak", "Nederland", "Hoofdstraat"));
        ArrayList<LastfmEvent> matchedEvents = lfmData1.getMatchingEvents(lfmData2);
        Assert.assertEquals(1,matchedEvents.size());
        Assert.assertEquals(743125,matchedEvents.get(0).getId());
    }
}