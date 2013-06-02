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
import com.dare2date.utility.HttpClient;
import com.dare2date.utility.IHttpClient;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class LastfmAPI  implements ILastfmAPI{

    private IHttpClient httpClient;
    private String apiKey;

    /**
     * Set the HTTP client.
     *
     * @param httpClient HTTP client.
     */
    public void setHttpClient(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Makes a call to an external Lastfm service and processes this set of LastfmEvent objects.
     * @param username The user from whom the event will be requested.
     * @return A LastfmData object from a specific user, which hold data from all visited last fm events.
     */
    public TreeSet<LastfmEvent> getUserEventHistory(String username) {
        TreeSet<LastfmEvent> result = new TreeSet<LastfmEvent>();
        String response = httpClient.get("http://ws.audioscrobbler.com/2.0/?method=user.getpastevents&user="+username+"&api_key="+apiKey+"&format=JSON");
        if(response != null)  {
            try {
               List<JSONObject> eventsData = JsonPath.read(response,"$..event");
               for(JSONObject eventData : eventsData) {
                   result.add(createEventFromJSONData(eventData));
               }
            } catch(ParseException e) {
               e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Creates an LastfmEvent from JSON data.
     * @param eventdata Raw data from Lastfm in JSON
     * @return A LastfmEvent object.
     */
    private LastfmEvent createEventFromJSONData(JSONObject eventdata) {
        int id = Integer.valueOf(eventdata.get("id").toString());
        String title = eventdata.get("title").toString();
        LastfmEvent event = new LastfmEvent(id, title);
        addDateToEventFromJSONData(eventdata, event);
        addVenueDataToEvent(eventdata, event);
        return event;
    }

    /**
     * Adds venue data to an event.
     * @param eventdata Raw event data from lastfm.
     * @param event An LastfmData object.
     */
    private void addVenueDataToEvent(JSONObject eventdata, LastfmEvent event) {
        JSONObject venue = (JSONObject)eventdata.get("venue");
        event.setVenueName(venue.get("name").toString());
        JSONObject location = (JSONObject)venue.get("location");
        if(location.containsKey("city")) {
            event.setCity(location.get("city").toString());
        }
        if(location.containsKey("country")) {
            event.setCountry(location.get("country").toString());
        }
        if(location.containsKey("street")) {
            event.setStreet(location.get("street").toString());
        }
    }

    /**
     * Adds date data to an event.
     * @param eventdata
     * @param event
     */
    private void addDateToEventFromJSONData(JSONObject eventdata, LastfmEvent event) {
        try{
            Calendar date = Calendar.getInstance();
            Date _date = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss", Locale.ENGLISH).parse(eventdata.get("startDate").toString());
            date.set(_date.getYear(),_date.getMonth(),_date.getDay(),_date.getHours(),_date.getMinutes(),_date.getSeconds());
            event.setStartDate(date);
        }  catch (ParseException e){
            e.printStackTrace();
        }
    }
}
