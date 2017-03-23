package com.au.model;


import java.io.Serializable;

public class ResponseData implements Serializable {
    private String url;
    private String data;
    private String cookie;

    public ResponseData(){}

    public ResponseData(final String url, final String data, final String cookie){
        this.url = url;
        this.data = data;
        this.cookie = cookie;
    }

    public String getUrl() {
        return url;
    }

    public String getData() {
        return data;
    }

    public String getCookie() {
        return cookie;
    }
}
