package Buoi5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA {

    /**
     * Băm chuỗi với SHA-256 hoặc SHA-512
     * @param input Chuỗi cần băm
     * @param type "SHA-256" hoặc "SHA-512"
     * @return Chuỗi băm hex
     */
    public static String hash(String input, String type) {
        try {
            // Kiểm tra type
            if (!type.equals("SHA-256") && !type.equals("SHA-512")) {
                throw new IllegalArgumentException("Chỉ hỗ trợ SHA-256 hoặc SHA-512");
            }

            MessageDigest md = MessageDigest.getInstance(type);
            byte[] messageDigest = md.digest(input.getBytes());

            // Chuyển byte sang hex
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // Hàm main test
    public static void main(String[] args) {
        String input = "HelloWorld";

        String sha256 = SHA.hash(input, "SHA-256");
        String sha512 = SHA.hash(input, "SHA-512");

        System.out.println("SHA-256 của '" + input + "' là: " + sha256);
        System.out.println("SHA-512 của '" + input + "' là: " + sha512);
    }
}
