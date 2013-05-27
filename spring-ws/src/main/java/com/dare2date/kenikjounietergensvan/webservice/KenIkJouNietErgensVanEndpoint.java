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
package com.dare2date.kenikjounietergensvan.webservice;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;

import java.util.ArrayList;

@Endpoint
public class KenIkJouNietErgensVanEndpoint {
    private Marshaller marshaller;
    private Unmarshaller unmarshaller;

    public KenIkJouNietErgensVanEndpoint(Marshaller marshaller, Unmarshaller unmarshaller) {
        this.marshaller = marshaller;
        this.unmarshaller = unmarshaller;
    }

    @PayloadRoot(localPart = "OvereenkomstenUsersRequest", namespace = "http://www.dare2date.com/schemas/kenikjounietergensvan/messages")
    public OvereenkomstenUsersResponse agreements(OvereenkomstenUsersRequest req) {
        Data users = new Data();
        users.items = new ArrayList<String>();

        users.items.add(req.getInput().users.user.get(0));
        users.items.add(req.getInput().users.user.get(1));

        OvereenkomstenUsersResult result = new OvereenkomstenUsersResult();
        result.setData(users);

        OvereenkomstenUsersResponse response = new OvereenkomstenUsersResponse();
        response.setResult(result);
        return response;
    }
}
