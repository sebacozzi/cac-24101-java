
package ar.com.codo24101.service;

import java.util.Base64;
import javax.crypto.Cipher;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * desde ChatGPT
 * 
 */
public class EncriptarDesencriptar {
    private static final String ALGORITHM = "AES";
   
private static final String SECRET_KEY_STRING = "1234567890123456"; // Clave secreta fija (16 bytes para AES-128)

    // Crear una clave secreta a partir de una cadena fija
    private static SecretKey getSecretKey() {
        byte[] keyBytes = SECRET_KEY_STRING.getBytes();
        return new SecretKeySpec(keyBytes, ALGORITHM);
    }

    // Encriptar texto usando una clave secreta
    public static String encrypt(String plainText) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Desencriptar texto usando una clave secreta
    public static String decrypt(String encryptedText) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }

}
