package org.ibs.cds.gode.util;

import org.ibs.cds.gode.exception.KnownException;
import org.jetbrains.annotations.NotNull;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

public class FileCypherUtil {

    private final static SecretKey secretKey;
    private final static Cipher cipher;

    static {
        try {
            secretKey = KeyGenerator.getInstance("AES").generateKey();
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (Exception e) {

            throw KnownException.FAILED_TO_START.provide("System security startup failed");
        }
    }

    public static void encrypt(byte[] content, File fileName) throws IOException {
        try {
            encrypt0(content, fileName);
        } catch (InvalidKeyException e) {
            throw KnownException.SYSTEM_FAILURE.provide("Security configuration is invalid");
        }
    }

    private static void encrypt0(byte[] content, File fileName) throws InvalidKeyException, IOException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] iv = cipher.getIV();

        try (
                FileOutputStream fileOut = new FileOutputStream(fileName);
                CipherOutputStream cipherOut = new CipherOutputStream(fileOut, cipher)
        ) {
            fileOut.write(iv);
            cipherOut.write(content);
        }
    }

    public static InputStream decrypt(File fileName) throws IOException {
        try{
            return decrypt0(fileName);
        }catch ( InvalidAlgorithmParameterException | InvalidKeyException e){
            throw KnownException.SYSTEM_FAILURE.provide("Security configuration is undefined");
        }
    }

    @NotNull
    private static InputStream decrypt0(File fileName) throws IOException, InvalidKeyException, InvalidAlgorithmParameterException {
        FileInputStream fileIn = new FileInputStream(fileName);
        byte[] fileIv = new byte[16];
        fileIn.read(fileIv);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(fileIv));
        CipherInputStream cipherIn = new CipherInputStream(fileIn, cipher);
        return cipherIn;
    }
}
