package com.example.prog_j;

import net.objecthunter.exp4j.ExpressionBuilder;

public class MatchParserByLib extends MatchParserBuilder {

    public double evaluateExpression(String expression) throws Exception {
        return new ExpressionBuilder(expression).build().evaluate();
    }

    private boolean isNumericOrOperator(char c) {
        return Character.isDigit(c) || ".+-*/()".indexOf(c) >= 0;
    }

    private String processLine(String line) throws Exception {
        StringBuilder result = new StringBuilder();
        StringBuilder currentExpression = new StringBuilder();

        for (char c : line.toCharArray()) {
            if (isNumericOrOperator(c)) {
                currentExpression.append(c);
            } else {
                if (currentExpression.length() > 0) {
                    try {
                        double evaluatedResult = evaluateExpression(currentExpression.toString());
                        result.append(evaluatedResult);
                    } catch (Exception e) {
                        result.append(currentExpression);
                    }
                    currentExpression.setLength(0);
                }
                result.append(c);
            }
        }

        if (currentExpression.length() > 0) {
            try {
                double evaluatedResult = evaluateExpression(currentExpression.toString());
                result.append(evaluatedResult);
            } catch (Exception e) {
                result.append(currentExpression);
            }
        }

        return result.toString();
    }

    @Override
    public void ParseTxt(FileSettings InputFileSettings, FileSettings OutputFileSettings) throws Exception {
        String[] lines = InputFileSettings.txt_info.split("\n");
        StringBuilder resultBuilder = new StringBuilder();

        for (String line : lines) {
            resultBuilder.append(processLine(line)).append("\n");
        }

        OutputFileSettings.txt_info = resultBuilder.toString();
        OutputFileSettings.byte_info = fileOP.StringToBytes(OutputFileSettings.txt_info);
    }
}
