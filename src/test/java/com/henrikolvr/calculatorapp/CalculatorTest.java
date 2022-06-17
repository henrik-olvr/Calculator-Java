package com.henrikolvr.calculatorapp;

import com.henrikolvr.calculatorapp.annotation.CalculatorConfig;
import com.henrikolvr.calculatorapp.exception.DivisionByZeroException;
import com.henrikolvr.calculatorapp.operations.OperationType;
import com.henrikolvr.calculatorapp.service.Calculator;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculatorTest {

    private ApplicationContext ac = new AnnotationConfigApplicationContext(CalculatorConfig.class);
    private Calculator calculator = (Calculator) ac.getBean("calculate");

    @Test
    public void shouldReturnAnExceptionWhenOperationTypeIsNull() {
        assertThrows(IllegalArgumentException.class, ()->calculator.calculate(1, 1, null));
    }

    @Test
    public void shouldReturnAnExceptionInDivisionByZero() {
        assertThrows(DivisionByZeroException.class, ()->calculator.calculate(5, 0, OperationType.DIV));
    }

    @Test
    public void shouldDiv() {
        assertEquals(2.1, calculator.calculate(10.5, 5, OperationType.DIV));
    }

    @Test
    public void shouldMult() {
        assertEquals(15, calculator.calculate(2, 7.5, OperationType.MULT));
    }

    @Test
    public void shouldPow() {
        assertEquals(49, calculator.calculate(7, 2, OperationType.POW));
    }

    @Test
    public void shouldSub() {
        assertEquals(8.30, calculator.calculate(9.50, 1.20, OperationType.SUB));
    }

    @Test
    public void shouldSum() {
        assertEquals(18.9, calculator.calculate(15, 3.9, OperationType.SUM));
    }

    @Test
    public void shouldGetOperationsHistory() {
        calculator.calculate(4, 3, OperationType.SUM);
        calculator.calculate(4, 3, OperationType.MULT);

        assertEquals(2, calculator.getHistory().size());
    }
}
