package com.disanyuzhou.designpattern.proxyPattern;

/**
 * Created by make on 12/2/15.
 */
public class ProxyPatternDemo {
    public static void main(String[] args){
        Image image = new ProxyImage("make");

        image.display();


    }

}
