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
import org.junit.Test;

public class FacebookEventTest {
    @Test(expected = IllegalArgumentException.class)
    public void testIdNull() {
        new FacebookEvent(null, "Event 9");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNameNull() {
        new FacebookEvent("222", null);
    }

    @Test
    public void testGetId() {
        FacebookEvent event = new FacebookEvent("2", "Event 1");

        Assert.assertEquals("2", event.getId());
    }

    @Test
    public void testGetName() {
        FacebookEvent event = new FacebookEvent("2", "Event 1");

        Assert.assertEquals("Event 1", event.getName());
    }

    @Test
    public void testEquals() {
        FacebookEvent event1 = new FacebookEvent("1", "Test");
        FacebookEvent event2 = new FacebookEvent("1", "Test");

        Assert.assertTrue(event1.equals(event2));
    }

    @Test
    public void testNotEquals() {
        FacebookEvent event1 = new FacebookEvent("1", "Test");
        FacebookEvent event2 = new FacebookEvent("2", "Test2");

        Assert.assertFalse(event1.equals(event2));
    }
}
