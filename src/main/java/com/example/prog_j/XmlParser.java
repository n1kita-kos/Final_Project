package com.example.prog_j;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlParser {

    public String ParseFileByDOM(String file_name) throws IOException, ParseException, ParserConfigurationException, SAXException {
        File file = new File(file_name);
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();

        NodeList equationList = doc.getElementsByTagName("equation");
        StringBuilder equations = new StringBuilder();

        for (int i = 0; i < equationList.getLength(); i++) {
            Node equationNode = equationList.item(i);
            if (equationNode.getNodeType() == Node.ELEMENT_NODE) {
                Element equationElement = (Element) equationNode;
                String equation = equationElement.getTextContent();
                equations.append(equation).append("\n");
            }
        }

        return String.valueOf(equations).replace("\r", "");
    }

    public String ParseStringByDOM(String xml_string) throws IOException, ParseException, ParserConfigurationException, SAXException {
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputStream is = new ByteArrayInputStream(xml_string.getBytes());
        Document doc = dBuilder.parse(is);
        doc.getDocumentElement().normalize();

        NodeList equationList = doc.getElementsByTagName("equation");
        StringBuilder equations = new StringBuilder();

        for (int i = 0; i < equationList.getLength(); i++) {
            Node equationNode = equationList.item(i);
            if (equationNode.getNodeType() == Node.ELEMENT_NODE) {
                Element equationElement = (Element) equationNode;
                String equation = equationElement.getTextContent();
                equations.append(equation).append("\n");
            }
        }

        return String.valueOf(equations).replace("\r", "");
    }

    public String makeXml(String result) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        Document document = factory.newDocumentBuilder().newDocument();

        Element rootElement = document.createElement("EquationAnswer");
        String[] answers = result.split("\n");

        for (String answer : answers) {
            Element answerElement = document.createElement("answer");
            answerElement.setTextContent(answer);
            rootElement.appendChild(answerElement);
        }

        document.appendChild(rootElement);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        DOMSource source = new DOMSource(document);
        StringWriter writer = new StringWriter();

        transformer.transform(source, new StreamResult(writer));

        return writer.getBuffer().toString().replace("\r", "");
    }

    public String ParseFileByReadingLineByLine(String file_name) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file_name));
        String line;
        StringBuilder result = new StringBuilder();

        while ((line = reader.readLine()) != null) {
            if (line.contains("<equation>")) {
                result.append(line, line.indexOf("<equation>") + 10, line.indexOf("</equation>")).append("\n");
            }
        }

        reader.close();
        return String.valueOf(result);
    }

    public String ParseStringByReadingLineByLine(String xmlString) throws Exception {
        String[] equations = xmlString.split("<equation>");
        StringBuilder result = new StringBuilder();

        for (int i = 1; i < equations.length; i++) {
            result.append(equations[i], 0, equations[i].indexOf("</equation>")).append("\n");
        }

        return String.valueOf(result);
    }
    
}
