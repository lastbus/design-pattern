package com.disanyuzhou.designpattern.filter;

/**
 * Created by make on 6/14/16.
 */

public class Target {
    public void execute(String request){
        System.out.println("Executing request: " + request);
    }
}
