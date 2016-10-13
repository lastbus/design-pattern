package com.disanyuzhou.designpattern.singleton;

/**
 * Created by make on 5/11/16.
 */
public class Singleton2 {
    private static Singleton2 singleton2 = new Singleton2();

    private Singleton2(){}

    public static Singleton2 getInstace() {
        return singleton2;
    }
}
