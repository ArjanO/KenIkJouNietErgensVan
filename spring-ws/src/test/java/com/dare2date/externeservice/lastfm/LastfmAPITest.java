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
        lastfm.setApiKey("2381cb66d98ee29ab508b4a37cb837c5");
        lastfm.setHttpClient(new HttpClient());
        TreeSet<LastfmEvent> events = lastfm.getUserEventHistory("Olodanderfluf");
        Assert.assertEquals(4,events.size());
        Assert.assertNotNull(events.first().getId());
        Assert.assertNotNull(events.first().getTitle());
        Assert.assertNotNull(events.first().getCity());
        Assert.assertNotNull(events.first().getCountry());
        Assert.assertNotNull(events.first().getStreet());
        Assert.assertNotNull(events.first().getVenueName());
        Assert.assertNotNull(events.first().getStartDate());
    }
}
