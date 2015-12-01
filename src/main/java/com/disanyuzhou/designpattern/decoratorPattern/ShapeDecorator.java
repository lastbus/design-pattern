package com.disanyuzhou.designpattern.decoratorPattern;

/**
 * Created by make on 12/1/15.
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratorShape;

    public ShapeDecorator(Shape decoratorShape){
        this.decoratorShape = decoratorShape;
    }

    @Override
    public void draw(){
        decoratorShape.draw();
    }
}
