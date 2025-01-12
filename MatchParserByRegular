package com.example.prog_j;

import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchParserByRegular extends MatchParserBuilder {

    protected static boolean hasPrecedence(char operator1, char operator2) {
        return (operator1 == '*' || operator1 == '/') && (operator2 == '+' || operator2 == '-');
    }

    protected static double performOperation(char operator, double operand1, double operand2) {
        switch (operator) {
            case '+' -> {
                return operand1 + operand2;
            }
            case '-' -> {
                return operand1 - operand2;
            }
            case '*' -> {
                return operand1 * operand2;
            }
            case '/' -> {
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero!");
                }
                return operand1 / operand2;
            }
        }
        throw new IllegalArgumentException("Unknown operator: " + operator);
    }

    double calculate(String expression) {
        Stack<Double> numbers = new Stack<>();
        Stack<String> operators = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            String ch = Character.toString(expression.charAt(i));
            if (ch.equals(" ")) {
                i++;
                continue;
            } else if (ch.matches("\\d+(\\.\\d+)?") || ch.equals(".")) {
                StringBuilder numBuilder = new StringBuilder();
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numBuilder.append(expression.charAt(i));
                    i++;
                }
                double number = Double.parseDouble(numBuilder.toString());
                numbers.push(number);
            } else if (ch.matches("[+\\-*/]")) {
                while (!operators.isEmpty() && !hasPrecedence(ch.charAt(0), operators.peek().charAt(0)) && !operators.peek().equals("(")) {
                    double operand2 = numbers.pop();
                    double operand1 = numbers.pop();
                    String op = operators.pop();
                    double result = performOperation(op.charAt(0), operand1, operand2);
                    numbers.push(result);
                }
                operators.push(ch);
                i++;
            } else if (ch.equals("(")) {
                operators.push(ch);
                i++;
            } else if (ch.equals(")")) {
                while (!operators.isEmpty() && operators.peek().charAt(0) != '(') {
                    double operand2 = numbers.pop();
                    double operand1 = numbers.pop();
                    String op = operators.pop();
                    double result = performOperation(op.charAt(0), operand1, operand2);
                    numbers.push(result);
                }
                operators.pop();
                i++;
            } else {
                throw new IllegalArgumentException("Invalid character: " + ch);
            }
        }

        while (!operators.isEmpty()) {
            double operand2 = numbers.pop();
            double operand1 = numbers.pop();
            String op = operators.pop();
            double result = performOperation(op.charAt(0), operand1, operand2);
            numbers.push(result);
        }

        return numbers.pop();
    }

    private String processLine(String line) throws Exception {
        Pattern pattern = Pattern.compile("[-+*/().0-9]+");
        Matcher matcher = pattern.matcher(line);
        StringBuilder result = new StringBuilder();
        int lastEnd = 0;

        while (matcher.find()) {
            result.append(line, lastEnd, matcher.start()); // Append non-matching text
            String expression = matcher.group();
            try {
                double evaluatedResult = calculate(expression);
                result.append(evaluatedResult);
            } catch (Exception e) {
                result.append(expression); // Keep original expression if an error occurs
            }
            lastEnd = matcher.end();
        }
        result.append(line.substring(lastEnd)); // Append remaining text
        return result.toString();
    }

    @Override
    void ParseTxt(FileSettings InputFileSettings, FileSettings OutputFileSettings) throws Exception {
        String[] lines = InputFileSettings.txt_info.split("\n");
        StringBuilder resultBuilder = new StringBuilder();

        for (String line : lines) {
            resultBuilder.append(processLine(line)).append("\n");
        }

        OutputFileSettings.txt_info = resultBuilder.toString();
        OutputFileSettings.byte_info = fileOP.StringToBytes(OutputFileSettings.txt_info);
    }
}
