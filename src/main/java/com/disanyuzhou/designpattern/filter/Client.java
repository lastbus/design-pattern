package com.disanyuzhou.designpattern.filter;

/**
 * Created by make on 6/14/16.
 */

public class Client {
    FilterManager filterManager;

    public void setFilterManager(FilterManager filterManager){
        this.filterManager = filterManager;
    }

    public void sendRequest(String request){
        filterManager.filterRequest(request);
    }
}

