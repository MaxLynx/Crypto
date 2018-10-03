package edu.crypto.model.cryptography;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Component
@Qualifier("DES")
public class DESCryptographyService extends FileSystemConfiguredCryptographyService {

    private static final char[] ALPHABET = {' ', '_', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '?', '!', '#', '(', ')'};

    SecretKey key;

    private static final int N = 33;

    public DESCryptographyService() {
        try {
            key = KeyGenerator.getInstance("DES").generateKey();
        } catch (NoSuchAlgorithmException e) {
        }
    }

    @Override
    public String encryptText(String source) {
        try {
            byte[] utf8 = source.getBytes("UTF8");

            Cipher encrypter = Cipher.getInstance("DES");

            encrypter.init(Cipher.ENCRYPT_MODE, key);

            byte[] encrypted = encrypter.doFinal(utf8);

            return new sun.misc.BASE64Encoder().encode(encrypted);

        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }

    @Override
    public String decryptText(String source) {
        try {
            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(source);

            Cipher decrypter = Cipher.getInstance("DES");

            decrypter.init(Cipher.DECRYPT_MODE, key);

            byte[] utf8 = decrypter.doFinal(dec);
            return new String(utf8, "UTF8");

        } catch (Exception e) {
            System.out.println(e);
            return "";
        }
    }
}