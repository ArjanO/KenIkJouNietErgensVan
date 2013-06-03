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
package com.dare2date.domein.facebook;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FacebookDataTest {
    private FacebookData data;

    @Before
    public void before() {
        data = new FacebookData();
    }

    @Test
    public void testSetEvents() {
        List<FacebookEvent> events = new ArrayList<FacebookEvent>();
        events.add(new FacebookEvent("1", "Test"));

        data.setEvents(events);

        Assert.assertEquals(events, data.getEvents());
    }

    @Test
    public void testSetWorkHistory() throws Exception {
        List<FacebookWorkHistory> work = new ArrayList<FacebookWorkHistory>();
        work.add(new FacebookWorkHistory());

        data.setWorkHistory(work);

        Assert.assertEquals(work, data.getWorkHistory());
    }

    @Test
    public void testSetEducationHistory() throws Exception {
        List<FacebookEducationHistory> education = new ArrayList<FacebookEducationHistory>();
        education.add(new FacebookEducationHistory());

        data.setEducationHistory(education);

        Assert.assertEquals(education, data.getEducationHistory());
    }

    @Test
    public void testMatchWorkHistory() {
        FacebookData other = new FacebookData();
        other.setWorkHistory(new ArrayList<FacebookWorkHistory>());

        data.setWorkHistory(new ArrayList<FacebookWorkHistory>());

        FacebookWorkHistory workHistory1 = new FacebookWorkHistory();
        workHistory1.setId("1");
        workHistory1.setName("Test");

        FacebookWorkHistory workHistory2 = new FacebookWorkHistory();
        workHistory2.setId("2");
        workHistory2.setName("Test2");

        FacebookWorkHistory workHistory3 = new FacebookWorkHistory();
        workHistory3.setId("1");
        workHistory3.setName("Test");

        other.getWorkHistory().add(workHistory1);
        other.getWorkHistory().add(workHistory2);

        data.getWorkHistory().add(workHistory3);

        FacebookData result = data.match(other);

        Assert.assertEquals(1, result.getWorkHistory().size());
        Assert.assertEquals("1", result.getWorkHistory().get(0).getId());
    }

    @Test
    public void testMatchEducationHistory() {
        FacebookData other = new FacebookData();
        other.setEducationHistory(new ArrayList<FacebookEducationHistory>());

        data.setEducationHistory(new ArrayList<FacebookEducationHistory>());

        FacebookEducationHistory educationHistory1 = new FacebookEducationHistory();
        educationHistory1.setId("1");
        educationHistory1.setName("Test");

        FacebookEducationHistory educationHistory2 = new FacebookEducationHistory();
        educationHistory2.setId("2");
        educationHistory2.setName("Test2");

        FacebookEducationHistory educationHistory3 = new FacebookEducationHistory();
        educationHistory3.setId("1");
        educationHistory3.setName("Test");

        other.getEducationHistory().add(educationHistory1);
        other.getEducationHistory().add(educationHistory2);

        data.getEducationHistory().add(educationHistory3);

        FacebookData result = data.match(other);

        Assert.assertEquals(1, result.getEducationHistory().size());
        Assert.assertEquals("1", result.getEducationHistory().get(0).getId());
    }

    @Test
    public void testMatchEvents() {
        FacebookData other = new FacebookData();
        other.setEvents(new ArrayList<FacebookEvent>());

        data.setEvents(new ArrayList<FacebookEvent>());

        FacebookEvent event1 = new FacebookEvent("1", "Test");
        FacebookEvent event2 = new FacebookEvent("2", "Test 2");
        FacebookEvent event3 = new FacebookEvent("1", "Test");

        other.getEvents().add(event1);
        other.getEvents().add(event2);

        data.getEvents().add(event3);

        FacebookData result = data.match(other);

        Assert.assertEquals(1, result.getEvents().size());
        Assert.assertEquals("1", result.getEvents().get(0).getId());
    }
}
