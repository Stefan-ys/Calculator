package com.example.calculator;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


@SpringBootApplication
public class CalculatorApplication {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Calculator");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JTextField textField = new JTextField();
            textField.setPreferredSize(new Dimension(250, 30));

            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(5, 4, 5, 5));

            String[] buttonLabels = {
                    "%", "CE", "C", "⯇",
                    "7", "8", "9", "/",
                    "4", "5", "6", "*",
                    "1", "2", "3", "-",
                    "0", ".", "=", "+"
            };

            for (String label : buttonLabels) {
                JButton button = new JButton(label);
                button.addActionListener(new ButtonClickListener(textField));
                panel.add(button);
            }

            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(textField, BorderLayout.NORTH);
            frame.getContentPane().add(panel, BorderLayout.CENTER);

            frame.setSize(300, 300);
            frame.pack();
            frame.setVisible(true);
        });

        SpringApplication.run(CalculatorApplication.class, args);
    }

    static class ButtonClickListener implements ActionListener {
        private JTextField textField;

        public ButtonClickListener(JTextField textField) {
            this.textField = textField;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String buttonText = button.getText();

            String text = textField.getText();
            switch (buttonText) {
                case "=":
                    String result = calculateResult(text);

                    textField.setText(result);
                    break;
                case "⯇":

                    text = text.length() > 0 ? text.substring(0, text.length() - 1) :  "";
                    textField.setText(text);
                    break;
                case "CE":
                    break;
                case "C":
                    break;
                default:
                    textField.setText(textField.getText() + buttonText);

            }
        }


        private String calculateResult(String input) {
            RestTemplate restTemplate = new RestTemplate();
            String url = "http://localhost:8080/calculate";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
            requestParams.add("input", input);

            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestParams, headers);

            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return responseEntity.getBody();
        }
    }


}
