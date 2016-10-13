package com.disanyuzhou.designpattern.singleton;

/**
 * Created by make on 5/11/16.
 */
public class Singleton {
    private static Singleton singleton = null;

    private Singleton(){}

    // not thread-safe
    public static Singleton getInstance() {
        if (singleton == null) singleton = new Singleton();
        return singleton;
    }

    // thread-safe, but not efficient.
    public static synchronized Singleton getInstance2(){
        if (singleton == null) singleton = new Singleton();
        return singleton;
    }

    // thread-safe, effcient.
    public static Singleton getInstance3() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) singleton = new Singleton();
            }
        }

        return singleton;
    }


    /**
     *  static inner class, best!
     */
    private static class LazyHolder {
        private static final Singleton singleton = new Singleton();
    }

    public static final Singleton getInstace4() {
        return LazyHolder.singleton;
    }

}
