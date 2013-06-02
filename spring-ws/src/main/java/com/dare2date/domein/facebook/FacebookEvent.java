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

/**
 * Stores information about a Facebook event.
 */
public class FacebookEvent {
    private String id;
    private String name;

    /**
     * Facebook event.
     *
     * @param id Facebook ID of the event.
     * @param name Name of the event.
     * @throws IllegalAccessException if id or name is null.
     */
    public FacebookEvent(String id, String name) {
        if (id == null || name == null) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.name = name;
    }

    /**
     * Facebook events ID.
     *
     * @return The Facebook ID of the event.
     */
    public String getId() {
        return id;
    }

    /**
     * Get the Facebook events name.
     *
     * @return Name of the Facebook event.
     */
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FacebookEvent) {
            FacebookEvent event = (FacebookEvent)obj;

            // Look only to the id.
            return event.id.equals(id);
        }
        return super.equals(obj);
    }
}
