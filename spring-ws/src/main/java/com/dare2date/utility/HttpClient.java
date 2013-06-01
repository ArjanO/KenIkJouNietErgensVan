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
package com.dare2date.utility;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * HTTP Client.
 */
@Component
public class HttpClient implements IHttpClient {
    public String get(String url) {
        URL request = null;

        try {
            request = new URL(url);
        } catch (MalformedURLException e) {
            return null;
        }

        URLConnection yc = null;
        try {
            yc = request.openConnection();
        } catch (IOException e) {
            return null;
        }

        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(yc.getInputStream()));
        } catch (IOException e) {
            return null;
        }

        String inputLine;
        StringBuffer result = new StringBuffer();

        try {
            while ((inputLine = in.readLine()) != null)
                result.append(inputLine);
        } catch (IOException e) {
            return null;
        }

        try {
            in.close();
        } catch (IOException e) {
            return null;
        }

        return result.toString();
    }
}
