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
package com.dare2date.externeservice.facebook;

import com.dare2date.domein.facebook.FacebookEvent;
import com.dare2date.domein.facebook.FacebookWorkHistory;
import com.dare2date.utility.IHttpClient;
import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FacebookAPI implements IFacebookAPI {
    private String appId;
    private String appSecret;

    private IHttpClient httpClient;

    /**
     * Set the Facebook app id.
     *
     * @param appId Facebook app id.
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * Set the Facebook app secret.
     *
     * @param appSecret Facebook app secret.
     */
    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    /**
     * Set the HTTP client.
     *
     * @param httpClient HTTP client.
     */
    public void setHttpClient(IHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Get the events from the user.
     *
     * @param accessToken Users access token.
     * @return List Events form the user.
     */
    public List<FacebookEvent> getUsersEvents(String accessToken) {
        if (accessToken == null) {
            throw new IllegalArgumentException();
        }

        String response = httpClient.get("https://graph.facebook.com/me/events?access_token=" + accessToken);
        List<FacebookEvent> result = new ArrayList<FacebookEvent>();

        if (response != null) {
            try {
                List<JSONObject> items = JsonPath.read(response, "$.data");

                if (items == null) {
                    return result;
                }

                for (JSONObject item : items) {
                    String id = null;
                    String name = null;

                    if (item.containsKey("id")) {
                        id = item.get("id").toString();
                    }
                    if (item.containsKey("name")) {
                        name = item.get("name").toString();
                    }

                    if (id != null && name != null) {
                        result.add(new FacebookEvent(id, name));
                    }
                }
            } catch (ParseException e) {
                return null;
            }
        }

        return result;
    }

    /**
     * Get the work history from the user.
     *
     * @param accessToken Access token for the user.
     * @return List with users work history.
     */
    public List<FacebookWorkHistory> getUsersWorkHistory(String accessToken) {
        return null;
    }
}
