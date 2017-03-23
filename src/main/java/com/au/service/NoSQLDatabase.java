package com.au.service;

public interface NoSQLDatabase {
    /*
    Saves an arbitrary object in a NoSQL database
    */
    void save(Object object);
    /*
    Find all objects whose attibute @attr matches the regular * expression @what
    */
    Object find(String attr, String regExp);
}
