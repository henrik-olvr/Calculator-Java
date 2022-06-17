package com.henrikolvr.calculatorapp.service;

import com.henrikolvr.calculatorapp.operations.Operation;
import com.henrikolvr.calculatorapp.operations.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculator {
    private List<Operation> operationHistory;

    @Autowired
    private ApplicationContext ac;

    public Calculator() {
        this.operationHistory = new ArrayList<>();
    }

    public double calculate(double val1, double val2, OperationType operation) {
        if(operation == null) {
            throw new IllegalArgumentException("Operation cannot be null");
        }

        Operation operationResult = (Operation) ac.getBean(operation.name(), val1, val2);
        double result = operationResult.calculate();
        operationHistory.add(operationResult);

        return result;
    }

    public List<Operation> getHistory() {
        return Collections.unmodifiableList(operationHistory);
    }

    public void clearHistory() {
        operationHistory.clear();
    }
}
