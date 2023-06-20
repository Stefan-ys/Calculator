package com.example.calculator.controllers;

import com.example.calculator.service.CalculatorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/")
    public String calculatorForm(Model model) {
        return "calculator";
    }

    @PostMapping("/calculate")
    @ResponseBody
    public String calculate(@RequestParam("input") String input) {
        
        String result;
        try {
            result = calculatorService.calculate(input);
        } catch (Exception e) {
            result = "ERROR";
        }
        return result;

    }
}
