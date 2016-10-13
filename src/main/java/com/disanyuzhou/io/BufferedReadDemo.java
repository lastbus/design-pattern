package com.disanyuzhou.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.System.out;

/**
 * Created by make on 7/7/16.
 */
public class BufferedReadDemo {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        out.println(bufferedReader.readLine());



    }

}
