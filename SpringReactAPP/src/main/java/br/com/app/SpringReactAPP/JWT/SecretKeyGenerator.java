package br.com.app.SpringReactAPP.JWT;

import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class SecretKeyGenerator {

    public static SecretKey generateSecretKey() {
        SecureRandom secureRandom = new SecureRandom("appReactDoZorje".getBytes());
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);
        SecretKey key = new SecretKeySpec(keyBytes, "HmacSHA256");
        return key;
    }
}
