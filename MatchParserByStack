package com.example.prog_j;

import java.util.HashMap;

public class MatchParserByStack extends MatchParserBuilder {
    private final HashMap<String, Double> variables;

    public MatchParserByStack() {
        variables = new HashMap<>();
    }

    public void setVariable(String variableName, Double variableValue) {
        variables.put(variableName, variableValue);
    }

    public Double getVariable(String variableName) throws Exception {
        if (!variables.containsKey(variableName)) {
            throw new Exception("Error: Try get unexists variable '" + variableName + "'");
        }
        return variables.get(variableName);
    }

    private res PlusMinus(String s) throws Exception {
        res current = MulDiv(s);
        double acc = current.acc;

        while (!current.rest.isEmpty()) {
            if (!(current.rest.charAt(0) == '+' || current.rest.charAt(0) == '-')) break;

            char sign = current.rest.charAt(0);
            String next = current.rest.substring(1);

            current = MulDiv(next);
            if (sign == '+') {
                acc += current.acc;
            } else {
                acc -= current.acc;
            }
        }
        return new res(acc, current.rest);
    }

    private res MulDiv(String s) throws Exception {
        res current = Num(s);

        double acc = current.acc;
        while (true) {
            if (current.rest.isEmpty()) {
                return current;
            }
            char sign = current.rest.charAt(0);
            if ((sign != '*' && sign != '/')) return current;

            String next = current.rest.substring(1);
            res right = Num(next);

            if (sign == '*') {
                acc *= right.acc;
            } else {
                acc /= right.acc;
            }

            current = new res(acc, right.rest);
        }
    }

    private res Num(String s) throws Exception {
        int i = 0;
        int dot_cnt = 0;
        boolean negative = false;
        if (s.charAt(0) == '-') {
            negative = true;
            s = s.substring(1);
        }
        while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
            if (s.charAt(i) == '.' && ++dot_cnt > 1) {
                throw new Exception("not valid number '" + s.substring(0, i + 1) + "'");
            }
            i++;
        }
        if (i == 0) {
            throw new Exception("can't get valid number in '" + s + "'");
        }

        double dPart = Double.parseDouble(s.substring(0, i));
        if (negative) dPart = -dPart;
        String restPart = s.substring(i);

        return new res(dPart, restPart);
    }

    public double Parse(String s) throws Exception {
        res result = PlusMinus(s);
        if (!result.rest.isEmpty()) {
            throw new Exception("Error: can't fully parse. Rest: " + result.rest);
        }
        return result.acc;
    }

    @Override
    public void ParseTxt(FileSettings InputFileSettings, FileSettings OutputFileSettings) throws Exception {
        StringBuilder resultBuilder = new StringBuilder();

        for (String line : InputFileSettings.txt_info.split("\\n")) {
            String[] parts = line.split(" ");
            for (String part : parts) {
                try {
                    double value = this.Parse(part);
                    resultBuilder.append(value).append(" ");
                } catch (Exception e) {
                    resultBuilder.append(part).append(" ");
                }
            }
            resultBuilder.append("\n");
        }

        OutputFileSettings.txt_info = resultBuilder.toString().trim();
        OutputFileSettings.byte_info = fileOP.StringToBytes(OutputFileSettings.txt_info);
    }
} 
