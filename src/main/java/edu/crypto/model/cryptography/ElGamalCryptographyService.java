package edu.crypto.model.cryptography;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("ElGamal")
public class ElGamalCryptographyService extends FileSystemConfiguredCryptographyService{

    private static final char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '?', '!', '#', '_', ' '};

    /**
     * P - prime number
     * G - number between 1 and P-1
     * X - random private key
     * K - random value for encryption
     */
    private static final int P = 31;
    private static final int G = 9;
    private static final int X = 3;
    private static final int Y = (int) (Math.pow(G, X) % P);

    private static int A;

    private int alphabetNumerationStart;

    private int textLength;

    @Override
    protected String encryptText(String source) {
        final int K = 3;


        alphabetNumerationStart = cryptographyConfiguration.getAlphabetNumerationStart();
        textLength = source.length();

        return convertIntToText(encryptConvertedText(convertTextToInt(source), K));
    }
    @Override
    protected String decryptText(String source) {
        alphabetNumerationStart = cryptographyConfiguration.getAlphabetNumerationStart();

        return convertIntToText(decryptConvertedText(convertTextToInt(source)));
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

    private int[] encryptConvertedText(int[] text, int K){
        A = (int) (Math.pow(G, K) % P);
        int[] B = new int[textLength];
        for(int i = 0; i < textLength; i++) {
            B[i] = alphabetNumerationStart + (int) (Math.pow(Y, K) * text[i] % P);
        }
        return B;
    }

    private int[] decryptConvertedText(int[] text){
        int[] decrypted = new int[textLength];
        for(int i = 0; i < textLength; i++) {
            decrypted[i] = (int) ((text[i] - alphabetNumerationStart) * Math.pow(A, P - 1 - X ) % P);
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
