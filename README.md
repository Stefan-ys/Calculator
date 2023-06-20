# Calculator App
This is a simple calculator application built with Java 17, Spring Boot, and Swing. It utilizes the Reverse Polish Notation (RPN) algorithm for evaluating mathematical expressions.

# Features
Accepts mathematical expressions in infix notation (e.g., "3 + 4 * 2").
Supports basic arithmetic operations: addition (+), subtraction (-), multiplication (*), and division (/).
Handles parentheses for grouping expressions.
Provides accurate and reliable results.

# Prerequisites
Java 17 JDK
Maven

# How it Works
The calculator app utilizes the Reverse Polish Notation (RPN) algorithm to evaluate mathematical expressions. RPN eliminates the need for parentheses and provides a simplified approach for calculating expressions.

Here's how the RPN algorithm works:

The input expression is converted from infix notation to postfix notation (RPN) using the Shunting Yard algorithm.
The postfix expression is then evaluated using a stack-based approach.
The algorithm scans each token (number or operator) in the postfix expression.
If the token is a number, it is pushed onto the stack.
If the token is an operator, the top two numbers on the stack are popped, the operation is performed, and the result is pushed back onto the stack.
After processing all tokens, the final result is the top element on the stack.
Technologies Used
Java 17
Spring Boot
Swing (Java GUI library)
