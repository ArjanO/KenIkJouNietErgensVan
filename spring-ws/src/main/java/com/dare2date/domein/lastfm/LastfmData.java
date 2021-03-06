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

import java.util.ArrayList;
import java.util.TreeSet;

public class LastfmData {
    private TreeSet<LastfmEvent> events;

    public LastfmData() {
        events = new TreeSet<LastfmEvent>();
    }

    public void addEvent(LastfmEvent event) {
        events.add(event);
    }

    public TreeSet<LastfmEvent> getEvents() {
        return events;
    }

    public void setEvents(TreeSet<LastfmEvent> events)  {
        this.events = events;
    }

    /**
     * Compares two lastfm data objects and returns matching lastfm events.
     * @param eventsToCompare Events to Compare
     * @return Matching events
     */
    public LastfmData getMatchingEvents(LastfmData eventsToCompare) {
        LastfmData matchingEvents = new LastfmData();
        for(LastfmEvent event : events) {
            if(eventsToCompare.getEvents().contains(event)) {
                matchingEvents.getEvents().add(event);
            }
        }
        return matchingEvents;
    }

    public ArrayList<String> toStringList() {
        ArrayList<String> data = new ArrayList<String>();
        for(LastfmEvent event : events) {
            data.add(event.getTitle());
        }
        return data;
    }
}
