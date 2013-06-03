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

import java.util.ArrayList;
import java.util.List;

public class FacebookData {
    private List<FacebookEvent> events;
    private List<FacebookWorkHistory> work;
    private List<FacebookEducationHistory> educations;

    /**
     * Set Facebook events.
     *
     * @param events Facebook events.
     */
    public void setEvents(List<FacebookEvent> events) {
        this.events = events;
    }

    /**
     * Set work history.
     *
     * @param work Facebook work history.
     */
    public void setWorkHistory(List<FacebookWorkHistory> work) {
        this.work = work;
    }

    /**
     * Set education history.
     *
     * @param educations Facebook education history.
     */
    public void setEducationHistory(List<FacebookEducationHistory> educations) {
        this.educations = educations;
    }

    /**
     * Get Facebook events.
     *
     * @return Facebook events.
     */
    public List<FacebookEvent> getEvents() {
        return events;
    }

    /**
     * Get Facebook work.
     *
     * @return Facebook work history.
     */
    public List<FacebookWorkHistory> getWorkHistory() {
        return work;
    }

    /**
     * Get Facebook education history.
     *
     * @return Facebook education history.
     */
    public List<FacebookEducationHistory> getEducationHistory() {
        return educations;
    }

    /**
     * Get all the matching Facebook events, education and work.
     *
     * @param other Facebook data to match.
     * @return FacebookData object with matching events, work and education.
     */
    public FacebookData match(FacebookData other) {
        FacebookData result = new FacebookData();
        result.setEvents(matchingEvents(other.getEvents()));
        result.setWorkHistory(matchingWorkHistory(other.getWorkHistory()));
        result.setEducationHistory(matchingEducationHistory(other.getEducationHistory()));

        return result;
    }

    private List<FacebookEvent> matchingEvents(List<FacebookEvent> other) {
        List<FacebookEvent> matching = new ArrayList<FacebookEvent>();

        if (events != null && other != null) {
            for (FacebookEvent event : events) {
                for (FacebookEvent otherEvent : other) {
                    if (event.equals(otherEvent)) {
                        matching.add(event);
                    }
                }
            }
        }

        return matching;
    }

    private List<FacebookWorkHistory> matchingWorkHistory(List<FacebookWorkHistory> other) {
        List<FacebookWorkHistory> matching = new ArrayList<FacebookWorkHistory>();

        if (work != null && other != null) {
            for (FacebookWorkHistory item : work) {
                for (FacebookWorkHistory otherWork : other) {
                    if (item.equals(otherWork)) {
                        matching.add(item);
                    }
                }
            }
        }

        return matching;
    }

    private List<FacebookEducationHistory> matchingEducationHistory(List<FacebookEducationHistory> other) {
        List<FacebookEducationHistory> matching = new ArrayList<FacebookEducationHistory>();

        if (educations != null && other != null) {
            for (FacebookEducationHistory item : educations) {
                for (FacebookEducationHistory otherEducation : other) {
                    if (item.equals(otherEducation)) {
                        matching.add(item);
                    }
                }
            }
        }

        return matching;
    }

    public ArrayList<String> toStringList() {
        ArrayList<String> data = new ArrayList<String>();
        for(FacebookEvent event : events) {
            data.add(event.getName());
        }
        for(FacebookEducationHistory edu : educations) {
            data.add(edu.getName());
        }
        for(FacebookWorkHistory _work : work) {
            data.add(_work.getName());
        }
        return data;
    }
}
