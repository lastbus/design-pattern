package com.disanyuzhou.designpattern.decoratorPattern;

/**
 * Created by make on 12/1/15.
 */
public class BlackShapeDecorator extends ShapeDecorator {
    public BlackShapeDecorator(Shape decoratorShape) {
        super(decoratorShape);
    }

    @Override
    public void draw(){
        decoratorShape.draw();
        setBlackBorder(decoratorShape);
    }

    public void setBlackBorder(Shape decoratorShape){
        System.out.println("the border is black");
    }
}
