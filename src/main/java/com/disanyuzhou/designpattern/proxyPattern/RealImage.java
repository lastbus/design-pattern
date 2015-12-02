package com.disanyuzhou.designpattern.proxyPattern;

/**
 * Created by make on 12/2/15.
 */
public class RealImage implements Image {
    String fileName;

    public RealImage(String fileName){
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName){
        System.out.println("Loading " + fileName);
    }
}
