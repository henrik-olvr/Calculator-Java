package com.henrikolvr.calculatorapp.servlet;

import com.henrikolvr.calculatorapp.annotation.CalculatorConfig;
import com.henrikolvr.calculatorapp.operations.OperationType;
import com.henrikolvr.calculatorapp.service.Calculator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/calculator")
public class CalculatorServlet extends HttpServlet {

    private final ApplicationContext ac;
    private final Calculator calculator;

    public CalculatorServlet() {
        this.ac = new AnnotationConfigApplicationContext(CalculatorConfig.class);
        this.calculator = (Calculator) ac.getBean("calculate");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter pw = response.getWriter();
        pw.println("Welcome to calculator app!\n");

        if(request.getParameterMap().containsKey("val1") && request.getParameterMap().containsKey("val2")
        && request.getParameterMap().containsKey("operation")) {

            try {
                double val1 = Double.parseDouble(request.getParameter("val1"));
                double val2 = Double.parseDouble(request.getParameter("val2"));
                OperationType operation = OperationType.valueOf(request.getParameter("operation"));
                double result = calculator.calculate(val1, val2, operation);

                pw.println("Operation: " + val1 + " " + operation + " " + val2 + " = " + result);

            } catch(Exception e) {
                pw.println(e.fillInStackTrace());
            }
        }

        if(request.getParameterMap().containsKey("showhistory")) {
            pw.println(calculator.getHistory());
        }
        pw.close();
    }
}
