package com.disanyuzhou.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by make on 7/25/16.
 */
public class Test {
    public static void main(String[] args) {
        int count = 0;
        Path filePath = null;
        try {
            filePath = Paths.get("pom.xml");
        } catch (InvalidPathException e) {
            System.out.println("Path error" + e);
            return;
        }

        try (SeekableByteChannel fChan = Files.newByteChannel(filePath)) {
            ByteBuffer mBuf = ByteBuffer.allocate(128);

            do {
                count = fChan.read(mBuf);
                if (count != -1) {
                    mBuf.rewind();
                    for (int i = 0; i < count; i++ ){
                        System.out.println((char) mBuf.get());
                    }
                }
            } while (count != -1);
            System.out.println();
        } catch (IOException e) {
            System.out.println("I/O Error " + e);
        }

    }
}
