package security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class Main {
    static void main(String[] args) throws Exception {
        String text = "Hello World";
        System.out.println("Encrypting...");
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128);
        SecretKey secret = keyGenerator.generateKey();

        String encryptText = encrypt(text, secret);
        System.out.println(encryptText);

        String decryptText = decrypt(encryptText, secret);
        System.out.println(decryptText);
    }

    public static String encrypt(String text, SecretKey secret) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        byte[] textByte = cipher.doFinal(text.getBytes());
        return Base64.getEncoder().encodeToString(textByte);
    }

    public static String decrypt(String text, SecretKey secret) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secret);
        byte[] cryptoByte = Base64.getDecoder().decode(text.getBytes());
        byte[] textByte = cipher.doFinal(cryptoByte);
        return new String(textByte);
    }
}
