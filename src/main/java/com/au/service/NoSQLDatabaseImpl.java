package com.au.service;

import com.au.model.ResponseData;

import java.util.Arrays;
import java.util.List;

public class NoSQLDatabaseImpl implements NoSQLDatabase {
    @Override
    public void save(Object object) {
        //dummy
    }

    @Override
    public List<ResponseData> find(String attr, String what) {
        return Arrays.asList(new ResponseData("test.com", "some response data", "YES"),
                new ResponseData("test.com", "data found", "NO"));
    }
}
