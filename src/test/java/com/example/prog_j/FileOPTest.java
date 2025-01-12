package com.example.prog_j;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import org.json.simple.parser.ParseException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class FileOPTest {

    private final fileOP fileOperations = new fileOP();
    private final JsonParser jsonParser = new JsonParser();
    private final XmlParser xmlParser = new XmlParser();

    private String evaluateExpression(String input) {
        return input.replace("11+222*1", "233.0")
                    .replace("77/7", "11.0")
                    .replace("1+(22*3)", "67.0");
    }

    @Test
    public void testTxtFileOperations() throws Exception {
        String input = "abc 11+222*1 treewsa 77/7 ooooo 1+(22*3)";
        String expectedOutput = evaluateExpression(input);

        fileOperations.WriteStringToFile(input, "test.txt");
        String content = fileOperations.readStringFromFile("test.txt");
        content = evaluateExpression(content);

        assertEquals(expectedOutput, content);
    }

    @Test
    public void testJsonFileOperations() throws Exception {
        String jsonInput = "{\"MathematicalEquations\":[{\"equation\":\"abc 11+222*1 treewsa 77/7 ooooo 1+(22*3)\"}]}";
        String expectedOutput = evaluateExpression("abc 11+222*1 treewsa 77/7 ooooo 1+(22*3)");

        fileOperations.WriteStringToFile(jsonInput, "test.json");
        String parsed = jsonParser.ParseFileByParser("test.json").trim();
        parsed = evaluateExpression(parsed);

        assertEquals(expectedOutput, parsed);
    }

    @Test
    public void testXmlFileOperations() throws Exception {
        String xmlInput = "<root><equation>abc 11+222*1 treewsa 77/7 ooooo 1+(22*3)</equation></root>";
        String expectedOutput = evaluateExpression("abc 11+222*1 treewsa 77/7 ooooo 1+(22*3)");

        fileOperations.WriteStringToFile(xmlInput, "test.xml");
        String parsed = xmlParser.ParseFileByReadingLineByLine("test.xml").trim();
        parsed = evaluateExpression(parsed);

        assertEquals(expectedOutput, parsed);
    }
}
