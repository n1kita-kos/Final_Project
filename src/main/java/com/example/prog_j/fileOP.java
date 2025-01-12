package com.example.prog_j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class fileOP {

    public static byte[] readBytesFromFile(String input_File) throws IOException, Exception {
        File inputFile = new File(input_File);
        FileInputStream inputStream = new FileInputStream(inputFile);

        byte[] inputBytes = new byte[(int) inputFile.length()];
        inputStream.read(inputBytes);

        inputStream.close();
        return inputBytes;
    }

    public static String readStringFromFile(String input_File) throws IOException, Exception {
        return BytesToString(readBytesFromFile(input_File));
    }

    public static String BytesToString(byte[] b) throws IOException, Exception {
        StringBuilder stringBuilder = new StringBuilder();
        for (int byt : b) {
            char c = (char) byt;
            stringBuilder.append(c);
        }
        return String.valueOf(stringBuilder).replace("\r", "");
    }

    public static byte[] StringToBytes(String s) throws IOException, Exception {
        WriteStringToFile(s, "file_translator.txt");

        return readBytesFromFile("file_translator.txt");
    }

    public static void WriteBytesToFile(byte[] input_Bytes, String output_File) throws Exception {
        File outputFile = new File(output_File);
        FileOutputStream outputStream = new FileOutputStream(outputFile);

        outputStream.write(input_Bytes);
        outputStream.close();
    }

    public static void WriteStringToFile(String text, String output_File) throws Exception {
        FileWriter writer = new FileWriter(output_File);
        writer.write(text);
        writer.close();
    }
}
