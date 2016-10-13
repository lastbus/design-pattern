package com.disanyuzhou.io;

import java.io.File;
import java.io.IOException;

import static java.lang.System.out;
/**
 * Created by make on 7/7/16.
 */
public class FileDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("/root/home");

        out.println(file.getName());
        out.println(file.getPath());
        out.println(file.getAbsolutePath());
//        out.println(file.getParent());
        out.println(file.getTotalSpace());
        out.println(file.listFiles());
        out.println(file.getCanonicalPath());



    }
}
