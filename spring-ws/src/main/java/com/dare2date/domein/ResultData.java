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
import com.dare2date.domein.lastfm.LastfmData;
import com.dare2date.domein.lastfm.LastfmEvent;
import com.dare2date.kenikjounietergensvan.webservice.Data;

import java.util.ArrayList;

public class ResultData {
    private LastfmData lastfmData1;
    private LastfmData lastfmData2;
    private FacebookData facebookData1;
    private FacebookData facebookData2;

    public void setLastfmData1(LastfmData lastfmData1) {
        this.lastfmData1 = lastfmData1;
    }

    public void setLastfmData2(LastfmData lastfmData2) {
        this.lastfmData2 = lastfmData2;
    }

    public void setFacebookData1(FacebookData facebookData1) {
        this.facebookData1 = facebookData1;
    }

    public void setFacebookData2(FacebookData facebookData2) {
        this.facebookData2 = facebookData2;
    }

    public Data getMatchingData() {
        Data data = new Data();
        ArrayList<String> fbdata = facebookData1.match(facebookData2).toStringList();
        ArrayList<String> lfmdata = lastfmData1.getMatchingEvents(lastfmData2).toStringList();
        for(String match : fbdata) {
            data.getItems().add(match);
        }
        for(String match : lfmdata) {
            data.getItems().add(match);
        }
        return data;
    }
}
