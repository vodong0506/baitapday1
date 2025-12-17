package Buoi4;

import java.math.BigInteger;
import java.security.SecureRandom;

public class Rsa {

    private BigInteger p, q, n, phi, e, d;
    private int bitLength = 512;

    public Rsa() {
        SecureRandom r = new SecureRandom();

        p = BigInteger.probablePrime(bitLength, r);
        q = BigInteger.probablePrime(bitLength, r);

        n = p.multiply(q);
        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.valueOf(65537);
        while (phi.gcd(e).intValue() > 1) {
            e = e.add(BigInteger.TWO);
        }

        d = e.modInverse(phi);
    }

    // ====== MÃ HÓA RSA → HEX ======
    public String encrypt(String plainText) {
        BigInteger m = new BigInteger(plainText.getBytes());
        BigInteger c = m.modPow(e, n);
        return bytesToHex(c.toByteArray());
    }

    // ====== GIẢI MÃ HEX → RSA ======
    public String decrypt(String cipherHex) {
        byte[] cipherBytes = hexToBytes(cipherHex);
        BigInteger c = new BigInteger(cipherBytes);
        BigInteger m = c.modPow(d, n);
        return new String(m.toByteArray());
    }

    // ====== HÀM CHUYỂN BYTE → HEX ======
    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }

    // ====== HÀM CHUYỂN HEX → BYTE ======
    private byte[] hexToBytes(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) Integer.parseInt(
                    hex.substring(i, i + 2), 16);
        }
        return data;
    }
}
