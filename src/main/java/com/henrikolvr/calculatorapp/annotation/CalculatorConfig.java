package com.henrikolvr.calculatorapp.annotation;

import com.henrikolvr.calculatorapp.operations.*;
import com.henrikolvr.calculatorapp.service.Calculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class CalculatorConfig {

    @Bean(name = "DIV")
    @Scope("prototype")
    public Division division(double val1, double val2) {
        return new Division(val1, val2);
    }

    @Bean(name = "MULT")
    @Scope("prototype")
    public Multiply multiply(double val1, double val2) {
        return new Multiply(val1, val2);
    }

    @Bean(name = "POW")
    @Scope("prototype")
    public Pow pow(double val1, double val2) {
        return new Pow(val1, val2);
    }

    @Bean(name = "SUB")
    @Scope("prototype")
    public Sub sub(double val1, double val2) {
        return new Sub(val1, val2);
    }

    @Bean(name = "SUM")
    @Scope("prototype")
    public Sum sum(double val1, double val2) {
        return new Sum(val1, val2);
    }

    @Bean
    public Calculator calculate() {
        return new Calculator();
    }
}
