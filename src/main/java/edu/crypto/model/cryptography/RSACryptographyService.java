package edu.crypto.model.cryptography;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("RSA")
public class RSACryptographyService extends FileSystemConfiguredCryptographyService{

    private static final char[] ALPHABET = {' ', '_', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '?', '!', '#', '(', ')'};

    /**
     * N - product of two primes (alphabet size)
     * E - 3, 5, 17, 257 OR 65537
     * (N, E) - public key
     * D - secret exponent
     * (N, D) - private key
     */
    private static final int N = 11 * 3;

    private int alphabetNumerationStart;

    private int textLength;

    @Override
    public String encryptText(String source) {
        final int E = 3;

        alphabetNumerationStart = cryptographyConfiguration.getAlphabetNumerationStart();
        textLength = source.length();

        return convertIntToText(encryptConvertedText(convertTextToInt(source), E));
    }

    @Override
    public String decryptText(String source) {
        final int D = 7;

        alphabetNumerationStart = cryptographyConfiguration.getAlphabetNumerationStart();

        return convertIntToText(decryptConvertedText(convertTextToInt(source), D));
    }

    private int[] convertTextToInt(String text){
        int[] ints = new int[textLength];
        for(int i = 0; i < textLength; i++) {
            ints[i] = getCode(text.charAt(i));
        }
        return ints;
    }

    private String convertIntToText(int[] code){
        StringBuilder text = new StringBuilder();
        for(int i = 0; i < textLength; i++) {
            text.append(getLetter(code[i]));
        }
        return text.toString();
    }

    private int[] encryptConvertedText(int[] text, int E){
        int[] encrypted = new int[textLength];
        for(int i = 0; i < textLength; i++) {
            encrypted[i] = alphabetNumerationStart + (int) (Math.pow(text[i], E) % N);
        }
        return encrypted;
    }

    private int[] decryptConvertedText(int[] text, int D){
        int[] decrypted = new int[textLength];
        for(int i = 0; i < textLength; i++) {
            decrypted[i] = (int) (Math.pow(text[i] - alphabetNumerationStart, D) % N);
        }
        return decrypted;
    }

    private int getCode(char letter){
        for(int i = 0; i < ALPHABET.length; i++)
            if(ALPHABET[i] == letter)
                return alphabetNumerationStart + i;
        return 0;
    }

    private char getLetter(int code){
        return ALPHABET[code - alphabetNumerationStart];
    }

}
