package Buoi4;

import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Elliptic {

    private KeyPair keyPair;
    private SecretKey aesKey;

    // Sinh khóa ECC
    public void generateKeys() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(256);
        keyPair = kpg.generateKeyPair();

        // Demo: dùng private key làm AES key
        byte[] keyBytes = MessageDigest.getInstance("SHA-256")
                .digest(keyPair.getPrivate().getEncoded());
        aesKey = new SecretKeySpec(keyBytes, 0, 16, "AES");
    }

    public String getPublicKey() {
        return Base64.getEncoder()
                .encodeToString(keyPair.getPublic().getEncoded());
    }

    public String getPrivateKey() {
        return Base64.getEncoder()
                .encodeToString(keyPair.getPrivate().getEncoded());
    }

    // Mã hóa
    public String encrypt(String plainText) throws Exception {
        if (aesKey == null)
            throw new Exception("Chưa có khóa");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] enc = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(enc);
    }

    // Giải mã
    public String decrypt(String cipherText) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);
        byte[] dec = cipher.doFinal(Base64.getDecoder().decode(cipherText));
        return new String(dec);
    }
}
