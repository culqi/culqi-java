package com.culqi.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.StringReader;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.spec.RSAKeyGenParameterSpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;
import com.google.gson.Gson;
import java.security.spec.PKCS8EncodedKeySpec;
import java.io.IOException;
import org.bouncycastle.asn1.*;
import org.bouncycastle.asn1.pkcs.*;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.json.JSONObject;


public class Encrypt {

    public String getJsonEncryptAESRSA(String json, String publicKeyString, Boolean isJson) throws Exception {
        String publicKey2 = "MIGJAoGBAM7CA3cWPn6OmLjCnYGyo8otEhi6pXrXL7WaPPT1yeEbnlhEAUv0XosXChTYawytFQAM+ZY9OPWqZy2PUrkuSs8HYd4eILUPbBGk8nLAcp1XDGmnncWg3E245dZR1D/H5s4XDr6ZyxqVMqUq7M9/tTMlsk6JPiriy7X43Xo+2GP9AgMBAAE=";
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey2);
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(new BigInteger(1, publicKeyBytes), BigInteger.valueOf(65537));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        byte[] keyBytes = new byte[32];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(keyBytes);
        //SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        SecretKey secretKeySpec = keyGenerator.generateKey();

        byte[] ivBytes = new byte[16];
        secureRandom.nextBytes(ivBytes);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivBytes);

        Cipher cipherAES = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipherAES.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encryptedBytes;
        encryptedBytes = cipherAES.doFinal(json.getBytes(StandardCharsets.UTF_8));

        String encryptedData =  Base64.getEncoder().encodeToString(encryptedBytes);

        Cipher cipherRSA = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipherRSA.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedKeyBytes = cipherRSA.doFinal(keyBytes);
        String encryptedKey = Base64.getEncoder().encodeToString(encryptedKeyBytes);
        byte[] encryptedIvBytes = cipherRSA.doFinal(ivBytes);
        String encryptedIv =  Base64.getEncoder().encodeToString(encryptedIvBytes);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("encrypted_data", encryptedData);
        jsonObject.put("encrypted_key", encryptedKey);
        jsonObject.put("encrypted_iv", encryptedIv);

        return jsonObject.toString();
    }

}
