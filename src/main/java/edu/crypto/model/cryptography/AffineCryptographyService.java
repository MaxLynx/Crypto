package edu.crypto.model.cryptography;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Affine")
public class AffineCryptographyService extends FileSystemConfiguredCryptographyService{

    private static final char[] ALPHABET = {' ', '_', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '?', '!', '#', '(', ')'};


    private static final int N = 33;

    private int alphabetNumerationStart;

    private int textLength;

    private int A = 5;
    private int B = 3;

    private int A_INVERTED = 20;

    @Override
    public String encryptText(String source) {

        alphabetNumerationStart = cryptographyConfiguration.getAlphabetNumerationStart();
        textLength = source.length();

        return convertIntToText(encryptConvertedText(convertTextToInt(source)));
    }

    @Override
    public String decryptText(String source) {

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

    private int[] encryptConvertedText(int[] text){
        int[] encrypted = new int[textLength];
        for(int i = 0; i < textLength; i++){
            encrypted[i] = alphabetNumerationStart + ((A * text[i] + B) % N);
        }
        return encrypted;
    }

    private int[] decryptConvertedText(int[] text){
        int[] decrypted = new int[textLength];
        for(int i = 0; i < textLength; i++) {
            decrypted[i] = (A_INVERTED * (text[i] - alphabetNumerationStart - B) % N);
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
