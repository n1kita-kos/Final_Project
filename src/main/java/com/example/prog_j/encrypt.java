package com.example.prog_j;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class encrypt {
    private static final String ALGORITHM = "AES";

    public static byte[] EncryptBytes(String key, byte[] input_Bytes) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        return cipher.doFinal(input_Bytes);
    }

    public static String DecryptFileToString(String key, String input_File) throws Exception {
        byte[] inputBytes = fileOP.readBytesFromFile(input_File);

        byte[] outputBytes = DecryptBytes(key, inputBytes);

        return new String(outputBytes, StandardCharsets.UTF_8).replace("\r", "");
    }

    public static byte[] DecryptFileToBytes(String key, String input_File) throws Exception {
        byte[] inputBytes = fileOP.readBytesFromFile(input_File);

        return DecryptBytes(key, inputBytes);

    }

    public static byte[] DecryptBytes(String key, byte[] input_Bytes) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        return cipher.doFinal(input_Bytes);
    }
}
