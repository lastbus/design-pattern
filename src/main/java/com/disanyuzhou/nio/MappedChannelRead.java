package com.disanyuzhou.nio;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;

/**
 * Created by make on 7/25/16.
 */
public class MappedChannelRead {
    public static void main(String[] args){

        try(FileChannel fileChannel = (FileChannel) Files.newByteChannel(Paths.get("pom.xml"))) {
            long size = fileChannel.size();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, size);

            for (int i = 0; i < size; i ++){
                System.out.println((char)mappedByteBuffer.get());
            }

            System.out.println();

        } catch (InvalidPathException e) {
            System.out.println("Path error " + e);
        } catch (IOException e) {
            System.out.println("I/O Error " + e);
        }

    }
}
