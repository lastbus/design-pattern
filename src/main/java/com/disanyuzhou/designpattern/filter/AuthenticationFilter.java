package com.disanyuzhou.designpattern.filter;

/**
 * Created by make on 6/14/16.
 */

public class AuthenticationFilter implements Filter {
    public void execute(String request){
        System.out.println("Authenticating request: " + request);
    }
}

