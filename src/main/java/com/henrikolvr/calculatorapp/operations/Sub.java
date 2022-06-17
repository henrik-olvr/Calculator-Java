package com.henrikolvr.calculatorapp.operations;

public class Sub implements Operation {
    private double val1, val2;

    public Sub(double val1, double val2) {
        this.val1 = val1;
        this.val2 = val2;
    }
    @Override
    public double calculate() {
        return val1 - val2;
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
        return "\nSub: " + val1 + " - " + val2 + " = " + calculate();
    }
}
