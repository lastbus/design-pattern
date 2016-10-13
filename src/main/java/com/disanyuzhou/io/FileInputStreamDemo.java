package com.disanyuzhou.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import static java.lang.System.out;
/**
 * Created by make on 7/7/16.
 */
public class FileInputStreamDemo {

    public static void main(String[] args) throws IOException {
        FileInputStream in = null;
        try {
             in = new FileInputStream("tt");
        } catch (Exception e) {
            out.println(e.toString());
        }

//        out.println(in.available());
//
        int length = in.available();
        byte[] b = new byte[length];
        in.read(b);
//
        FileOutputStream outputStream = new FileOutputStream("out");
        outputStream.write(b);
//
////        out.println(new String(b));
//        for (byte bb : b) {
//            out.println(bb);
//        }
//        out.println(new String(b));

    }

}
