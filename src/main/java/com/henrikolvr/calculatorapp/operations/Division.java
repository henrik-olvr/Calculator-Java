package com.henrikolvr.calculatorapp.operations;

import com.henrikolvr.calculatorapp.exception.DivisionByZeroException;

public class Division implements Operation {
    private double val1, val2;

    public Division(double val1, double val2) {
        this.val1 = val1;
        this.val2 = val2;
    }

    @Override
    public double calculate() {
        if(val2 == 0) {
            throw new DivisionByZeroException("Division by zero is undefined");
        }
        return val1/val2;
    }

    @Override
    public double getVal1() {
        return this.val1;
    }

    @Override
    public double getVal2() {
        return this.val2;
    }

    @Override
    public String toString() {
        return "\nDivision: " + val1 + " / " + val2 + " = " + calculate();
    }
}
