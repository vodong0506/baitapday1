package Buoi1;

public class VigenereCipher {

    public static String encrypt(String text, String key) {
        return process(text, key, true);
    }

    public static String decrypt(String text, String key) {
        return process(text, key, false);
    }

    private static String process(String text, String key, boolean isEncrypt) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();  // chuẩn hóa key
        int keyLen = key.length();
        int keyIndex = 0;

        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                int shift = key.charAt(keyIndex % keyLen) - 'A';

                if (!isEncrypt) {
                    shift = 26 - shift; // giải mã
                }

                char newChar = (char) ((c - base + shift) % 26 + base);
                result.append(newChar);

                keyIndex++;
            } else {
                result.append(c); // giữ nguyên ký tự không phải chữ
            }
        }

        return result.toString();
    }
}
