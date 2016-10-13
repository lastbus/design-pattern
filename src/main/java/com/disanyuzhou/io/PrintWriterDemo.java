package com.disanyuzhou.io;

import java.io.PrintWriter;

/**
 * Created by make on 7/7/16.
 */
public class PrintWriterDemo {
    public static void main(String[] args){
        PrintWriter pw = new PrintWriter(System.out, true);

        pw.println("1232");

        pw.println(true);

        pw.println(4.5e10);

//        pw.flush();


    }
}
