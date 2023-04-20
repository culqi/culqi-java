package com.culqi.util;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class RSAKeysLoader {
	
	static {
		Security.addProvider(new BouncyCastleProvider());
	}
	
	public RSAPublicKey getPublicKeyPKCS1Format(String publicKeyContent) throws NoSuchAlgorithmException, InvalidKeySpecException {

		RSAPublicKey pubKey = null;

		publicKeyContent = publicKeyContent.replace("-----BEGIN PUBLIC KEY-----\n", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s+", "");
		
		KeyFactory kf = KeyFactory.getInstance("RSA");
		X509EncodedKeySpec keySpecX509 = new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyContent));
		pubKey = (RSAPublicKey) kf.generatePublic(keySpecX509);

		return pubKey;
	}
	
	public RSAPrivateKey getPrivateKeyPKCS1Format(String privateKeyContent) throws NoSuchAlgorithmException, InvalidKeySpecException {

		//privateKeyContent = privateKeyContent.replaceAll("\\n", "").replace("-----BEGIN RSA PRIVATE KEY-----", "").replace("-----END RSA PRIVATE KEY-----", "");
		KeyFactory kf = KeyFactory.getInstance("RSA");

		PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
		RSAPrivateKey privKey = (RSAPrivateKey) kf.generatePrivate(keySpecPKCS8);
		return privKey;
	}

}
