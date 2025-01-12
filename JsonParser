package com.example.prog_j;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonParser {
    public String ParseFileByParser(String file_name) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(file_name));

        JSONArray mathematicalEquations = (JSONArray) jsonObject.get("MathematicalEquations");

        StringBuilder s = new StringBuilder();

        for (Object equationObj : mathematicalEquations) {
            JSONObject equationJson = (JSONObject) equationObj;
            String equation = (String) equationJson.get("equation");
            s.append(equation).append("\n");
        }

        return String.valueOf(s).replace("\r", "");
    }

    public String ParseStringByParser(String json_string) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(json_string);

        JSONArray mathematicalEquations = (JSONArray) jsonObject.get("MathematicalEquations");

        StringBuilder s = new StringBuilder();

        for (Object equationObj : mathematicalEquations) {
            JSONObject equationJson = (JSONObject) equationObj;
            String equation = (String) equationJson.get("equation");
            s.append(equation).append("\n");
        }

        return String.valueOf(s).replace("\r", "");
    }

    public String makeJson(String result) {
        String[] lines = result.split("\n");
        List<String> vec = new Vector<>(Arrays.asList(lines));

        JSONObject jsonObject = new JSONObject();
        JSONArray equationsArray = new JSONArray();

        for (Object v : vec) {
            JSONObject equation1 = new JSONObject();
            equation1.put("answer", v);
            equationsArray.add(equation1);
        }

        jsonObject.put("Equation answer", equationsArray);

        return jsonObject.toJSONString().replace("\r", "");
    }

    public String ParseFileByReadingLineByLine(String file_name) throws Exception {
        String jsonString = fileOP.readStringFromFile(file_name);

        Pattern pattern = Pattern.compile("\"equation\": \"(.*?)\"");
        Matcher matcher = pattern.matcher(jsonString);

        StringBuilder equations = new StringBuilder();
        while (matcher.find()) {
            equations.append(matcher.group(1)).append("\n");
        }

        return String.valueOf(equations);
    }

    public String ParseStringByReadingLineByLine(String jsonString) throws Exception {
        Pattern pattern = Pattern.compile("\"equation\": \"(.*?)\"");
        Matcher matcher = pattern.matcher(jsonString);

        StringBuilder equations = new StringBuilder();
        while (matcher.find()) {
            equations.append(matcher.group(1)).append("\n");
        }

        return String.valueOf(equations);
    }
}
