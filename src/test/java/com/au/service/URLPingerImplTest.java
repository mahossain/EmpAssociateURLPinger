package com.au.service;

import com.au.model.ResponseData;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mohammad on 24/3/17.
 */
public class URLPingerImplTest {

    private NoSQLDatabase db = Mockito.mock(NoSQLDatabaseImpl.class);

    private URLPinger urlPinger = null;
    private String[] urls = new String[]{
            "http://newscorp.com.au/index.html",
            "http://google.com.au",
            "http://test.com/index"};

    @Before
    public void init() {
        urlPinger = new URLPingerImpl(db);
    }

    @Test
    public void testPingUrl() throws Exception {
        //test only when internet connection is available
        //urlPinger.pingUrl(urls);
        //Mockito.verify(db).save(ResponseData.class);
    }

    @Test
    public void testFindAllUrlWithCookies() throws Exception {

        final String attribute = "cookie";
        List<ResponseData> responseDataList = new ArrayList<ResponseData>(Arrays.asList(
                new ResponseData("http://google.com", "response data", "YES"),
                new ResponseData("http://yahoo.com", "response data", "NO"),
                new ResponseData("http://test.com/index", "some data", "YES")));

        Mockito.when(db.find(attribute, "YES")).thenReturn(responseDataList);

        List<String> urls = urlPinger.findAllUrlWithCookies(attribute);
        Assert.assertThat(urls.isEmpty(), Is.is(false));
        Assert.assertThat(urls.size(), Is.is(2));
    }
}