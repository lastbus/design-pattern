package com.disanyuzhou.designpattern.decoratorPattern;

/**
 * Created by make on 12/1/15.
 */
public class RedShapeDecorator extends ShapeDecorator{

    public RedShapeDecorator(Shape decoratorShape) {
        super(decoratorShape);
    }

    @Override
    public void draw(){
        decoratorShape.draw();
        setRedBorder(decoratorShape);
    }

    private void setRedBorder(Shape decoratorShape){
        System.out.println("Border Color: Red");
    }
}
