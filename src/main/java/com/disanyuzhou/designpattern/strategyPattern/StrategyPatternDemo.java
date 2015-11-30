package com.disanyuzhou.designpattern.strategyPattern;

/**
 * Created by make on 11/30/15.
 */
public class StrategyPatternDemo {

    public static void main(String[] args){
        Context context = new Context(new OperationAdd());
        System.out.println("1 + 1 equals: " + context.executeStrategy(1, 1));

        context = new Context(new OperationSubstract());
        System.out.println("5 - 3 equals: " + context.executeStrategy(5, 3));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 10 equals: " + context.executeStrategy(10, 10));

    }
}
