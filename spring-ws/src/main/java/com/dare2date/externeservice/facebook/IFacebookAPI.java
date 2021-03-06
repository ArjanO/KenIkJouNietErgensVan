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

import com.dare2date.domein.facebook.FacebookEducationHistory;
import com.dare2date.domein.facebook.FacebookEvent;
import com.dare2date.domein.facebook.FacebookWorkHistory;

import java.util.List;

/**
 * Facebook API
 */
public interface IFacebookAPI {
    /**
     * Get the events from the user.
     *
     * @param accessToken Users access token.
     * @return List Events form the user.
     */
    List<FacebookEvent> getUsersEvents(String accessToken);

    /**
     * Get the work history from the user.
     *
     * @param accessToken Access token for the user.
     * @return List with users work history.
     */
    List<FacebookWorkHistory> getUsersWorkHistory(String accessToken);

    /**
     * Get the education history of the user.
     *
     * @param accessToken Users access token.
     * @return List with the users education history.
     */
    List<FacebookEducationHistory> getUsersEducationHistory(String accessToken);
}
