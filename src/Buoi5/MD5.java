package Buoi5;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    // Phương thức chuyển chuỗi sang MD5 hash
    public static String hash(String input) {
        try {
            // Tạo instance MessageDigest với thuật toán MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // Chuyển input thành mảng byte và cập nhật digest
            byte[] messageDigest = md.digest(input.getBytes());
            
            // Chuyển mảng byte sang chuỗi hex
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

    // Hàm main để test
    public static void main(String[] args) {
        String input = "HelloWorld";
        String md5Hash = MD5.hash(input);
        System.out.println("MD5 của '" + input + "' là: " + md5Hash);
    }
}
