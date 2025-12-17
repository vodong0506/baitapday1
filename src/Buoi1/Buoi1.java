/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Buoi1;

/**
 *
 * @author Administrator
 */
public class Buoi1 {

    public static String encrypt(String text, int key) {
        return caesarCipher(text, key, true);
    }
    public static String decrypt(String text, int key) {
        return caesarCipher(text, key, false);
    }
    
    private static String caesarCipher(String text, int key, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        int newkey = encrypt ? key : - key;
        
        for(char character : text.toCharArray()) {
            if(Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a' ;
                int offset = (character - base + newkey) % 26 ; //C = (P + K) mod 26
                if (offset < 0) { //TH C < 0
                    offset += 26;
                }
                result.append((char) (base + offset));
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
