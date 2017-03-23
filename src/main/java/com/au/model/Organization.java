package com.au.model;

/**
 *
 * @author Mohammad
 */
public class Organization {
    private String name;
    private String details;
    
    public Organization(){}
    
    public Organization(final String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }   
}
