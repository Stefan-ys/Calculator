package com.example.calculator.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public String calculate(String input) {

        Stack<Double> result = new Stack<>();

        List<String> list = new ArrayList<>();

        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (Character.isDigit(c)) {
                StringBuilder stringBuilder = new StringBuilder();
                while (Character.isDigit(c) || c == '.') {
                    stringBuilder.append(c);
                    i++;
                    if (i >= input.length()) {
                        break;
                    }
                    c = input.charAt(i);
                }
                i--;
                list.add(stringBuilder.toString());
                result.push(Double.parseDouble(stringBuilder.toString()));
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (operators.peek() != '(') {
                    char operator = operators.pop();
                    list.add(operator + "");
                    pushToStack(result, operator);
                }
                operators.pop();
            } else {
                while (!operators.isEmpty() && (operators.peek() != '(' && (c == '-' || c == '+'))) {
                    char operator = operators.pop();
                    list.add(operator + "");
                    pushToStack(result, operator);
                }
                operators.push(c);
            }
        }
        while (!operators.isEmpty()) {
            char operator = operators.pop();
            list.add(operator + "");
            pushToStack(result, operator);

        }
        System.out.println(list);
        
        return trimOutput(result.pop() + "");

    }

    private String trimOutput(String output) {
        if (output.endsWith(".0")) {
            output = output.replace(".0", "");
        }
        return output;
    }

    private void pushToStack(Stack<Double> result, char operator) {
        double num1 = result.pop();
        double num2 = result.pop();
        double res = 0;
        switch (operator) {
            case '+' -> res = num2 + num1;
            case '-' -> res = num2 - num1;
            case '*' -> res = num2 * num1;
            case '/' -> res = num2 / num1;
        }
        result.push(res);


    }

}
