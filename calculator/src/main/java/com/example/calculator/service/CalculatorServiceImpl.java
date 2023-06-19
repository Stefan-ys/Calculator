package com.example.calculator.service;

import java.util.Stack;

public class CalculatorServiceImpl implements CalculatorService {

    public Double calculate(String input) {
        double result = 0;
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c >= '0' && c <= '9') {
                StringBuilder stringBuilder = new StringBuilder();
                while ((c >= '0' && c <= '9') || c == '.') {
                    stringBuilder.append(c);
                    i++;
                    if (i > input.length()) {
                        break;
                    }
                    c = input.charAt(i);
                }
                stack.push(stringBuilder.toString());
            }

            double num = 0;


        }


        return result;
    }

}
