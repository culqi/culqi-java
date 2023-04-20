package com.culqi.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class RSAUtil {


	private static final Logger logger = LoggerFactory.getLogger(RSAUtil.class);

	
	public byte[] encriptar(String mensaje, String pubKey) {
		 try {
			 
			 Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
			 RSAKeysLoader rsaKeysLoader = new RSAKeysLoader();
			 RSAPublicKey publicKeyRSA = rsaKeysLoader.getPublicKeyPKCS1Format(pubKey);

			 rsaCipher.init(Cipher.ENCRYPT_MODE, publicKeyRSA);
			 
			 byte[] mensajeCifrado = rsaCipher.doFinal(mensaje.getBytes("UTF8"));
			 return mensajeCifrado;

		 } catch (Exception e) {
		 	e.printStackTrace();
		 }
		 return null;
	}
	
	public String encriptarByte(byte[] mensaje, String pubKey) {
		 try {
			 
			 Cipher rsaCipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
			 RSAKeysLoader rsaKeysLoader = new RSAKeysLoader();
			 RSAPublicKey publicKeyRSA = rsaKeysLoader.getPublicKeyPKCS1Format(pubKey);

			 rsaCipher.init(Cipher.ENCRYPT_MODE, publicKeyRSA);
			 
			 byte[] mensajeCifrado = rsaCipher.doFinal(mensaje);
			 String mensajeCifrado64 = Base64.getEncoder().encodeToString(mensajeCifrado);
			 return mensajeCifrado64;

		 } catch (Exception e) {
		 	e.printStackTrace();
		 }
		 return null;
	}
	
	/*
	public String desencriptar(byte[] mensajeCifrado, String privKey) {
		 String mensajeDescifrado2 = null;
		 try {
			 
			 //String privKey = privateKey;

			 Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

			 RSAKeysLoader rsaKeysLoader = new RSAKeysLoader();
			 RSAPrivateKey privateKeyRSA = rsaKeysLoader.getPrivateKeyPKCS1Format(privKey);
			 
			 rsaCipher.init(Cipher.DECRYPT_MODE, privateKeyRSA);
			 byte[] mensajeDescifrado = rsaCipher.doFinal(mensajeCifrado);
			 mensajeDescifrado2 = new String(mensajeDescifrado, "UTF8");
			 
			 System.out.println(mensajeDescifrado2);

		 } catch (Exception e) {
		 	e.printStackTrace();
		 }
		 return mensajeDescifrado2;
	}
	
	*/
}

