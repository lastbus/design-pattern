package com.disanyuzhou.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by make on 7/7/16.
 */
public class InputStreamDemo {
    public static void main(String[] args) throws IOException {

        InputStreamReader in = new InputStreamReader(System.in);

        print((char)in.read());

        print(in.getEncoding());

        print(in.markSupported());





    }


    public static void print(Object o ) {
        System.out.println(o);
    }
}
