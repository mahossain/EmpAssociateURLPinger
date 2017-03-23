package com.au.service;

import com.au.model.ResponseData;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

/*
Q2.2Write a script/class that given 4 urls,
it will send a request to each one of them and store the result in the database.
 */

public class URLPingerImpl implements URLPinger {

    private final String TEST_DOMAIN = "test.com";

    private NoSQLDatabase db;

    public URLPingerImpl(NoSQLDatabase db) {
        this.db = db;
        if (db == null) {
            db = new NoSQLDatabaseImpl();
        }
    }

    @Override
    public void pingUrl(final String[] urls) throws Exception {

        Client client = Client.create();

        for (String url : urls) {

            WebResource webResource = client.resource(url);

            ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_TYPE).get(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed: Http Error code:" + response.getStatus());
            }

            final String data = response.getEntity(String.class);
            final String cookieRequested = response.getCookies().size() > 0 ? "YES" : "NO";

            final ResponseData responseData = new ResponseData(url, data, cookieRequested);

            db.save(responseData);

            final URL URL = new URL(url);
            if (TEST_DOMAIN.equals(URL.getAuthority())) {
                System.out.println(data);
            }
        }
    }

    @Override
    public List<String> findAllUrlWithCookies(final String attr) {
        final Object object = db.find(attr, "YES");
        final List<ResponseData> responseDataList = (List<ResponseData>) object;
        return responseDataList.stream()
                .filter(r -> "YES".equals(r.getCookie()))
                .map(r -> r.getUrl()).collect(Collectors.toList());
    }
}
