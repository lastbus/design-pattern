package com.disanyuzhou.designpattern.strategyPattern;

/**
 * Created by make on 11/30/15.
 */
public class OperationSubstract implements Strategy {
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}
