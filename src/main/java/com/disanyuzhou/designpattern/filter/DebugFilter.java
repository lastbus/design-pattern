package com.disanyuzhou.designpattern.filter;

/**
 * Created by make on 6/14/16.
 */

public class DebugFilter implements Filter {
    public void execute(String request){
        System.out.println("request log: " + request);
    }
}

