package com.culqi.util;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

public class Encrypt {

    public static String encrypt(String plainText, RSAPublicKey publicKey) throws Exception {

        // Generar una llave AES aleatoria
        Key aesKey = generateRandomAESKey();

        // Cifrar el texto plano con la llave AES
        byte[] cipherText = encryptAES(plainText.getBytes("UTF-8"), aesKey);

        // Cifrar la llave AES con la llave pública RSA
        byte[] encryptedAESKey = encryptRSA(aesKey.getEncoded(), publicKey);

        // Combinar el texto cifrado y la llave cifrada en un único mensaje
        byte[] combinedMessage = new byte[encryptedAESKey.length + cipherText.length];
        System.arraycopy(encryptedAESKey, 0, combinedMessage, 0, encryptedAESKey.length);
        System.arraycopy(cipherText, 0, combinedMessage, encryptedAESKey.length, cipherText.length);

        // Codificar el mensaje combinado en base64 y devolverlo como String
        return Base64.getEncoder().encodeToString(combinedMessage);
    }

    private static Key generateRandomAESKey() throws Exception {
        Key aesKey = new SecretKeySpec(new byte[16], "AES");
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] iv = cipher.getIV();
        return new SecretKeySpec(iv, "AES");
    }

    private static byte[] encryptAES(byte[] plainText, Key aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        return cipher.doFinal(plainText);
    }

    private static byte[] encryptRSA(byte[] data, RSAPublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }
}
