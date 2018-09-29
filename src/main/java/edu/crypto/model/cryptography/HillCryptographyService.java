package edu.crypto.model.cryptography;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Decryption works properly only when inverted key is declared manually
 */
@Component
@Qualifier("Hill")
public class HillCryptographyService extends FileSystemConfiguredCryptographyService{

    private static final char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                                            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                                            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    private double [][] key = {{3, 10, 20},
                                {20, 9, 17},
                                {9, 4, 17}};

    private double [][] invertedKey = {{11, 22, 14},
                                        {7, 9, 21},
                                        {17, 0, 3}};

    @Override
    protected String encryptText(String source) {
        int dimension = source.length();
        double [] data = new double[dimension];
        for(int i = 0; i < dimension; i++){
            data[i] = getCode(source.charAt(i));
        }

        double [] encryptedData = multiply(key, data);

        StringBuilder encrypted = new StringBuilder();

        for(int i = 0; i < dimension; i++) {
            encrypted.append(getLetter(encryptedData[i]));
        }

        return encrypted.toString();
    }


    @Override
    protected String decryptText(String source) {

        int dimension = source.length();
        double [] data = new double[dimension];
        for(int i = 0; i < dimension; i++){
            data[i] = getCode(source.charAt(i));
        }

        double [] decryptedData = multiply(invertedKey, data);

        StringBuilder decrypted = new StringBuilder();

        for(int i = 0; i < decryptedData.length; i++)
            decrypted.append(getLetter(decryptedData[i]));

        return decrypted.toString();
    }

    private double getCode(char letter){
        for(int i = 0; i < ALPHABET.length; i++)
            if(ALPHABET[i] == letter)
                return i;
        return 0;
    }

    private char getLetter(double code){
        return ALPHABET[(int)Math.round(code)];
    }

    /**
     * Not-nullable det check is not available
     */
    private double[][] generateKey(double dimension){
        Random random = new Random();
        double[][] key = new double[(int)Math.ceil(dimension)][(int)Math.ceil(dimension)];

        for(int i = 0; i < dimension; i++){
            for(int j = 0; j < dimension; j++){
                key[i][j] = random.nextInt(ALPHABET.length);
            }
        }

        return key;
    }

    private double[] multiply(double[][] key, double[] source){

        int dimension = source.length;
        double[] result = new double[dimension];

        for(int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++){
                result[i] += source[j] * key[i][j];
            }
            if(result[i] >= 0) {
                result[i] %= ALPHABET.length;
            }
            else{
                result[i] = ALPHABET.length - result[i] % ALPHABET.length;
            }
        }

        return result;
    }

    private double[][] inverse(double[][] source){
        int dimension = source.length;


        RealMatrix matrix = MatrixUtils.createRealMatrix(source);
        RealMatrix invertedMatrix = MatrixUtils.inverse(matrix);

        double [][] result = new double[dimension][dimension];
        for(int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++){
                result[i][j] = Math.round(invertedMatrix.getEntry(i, j)) % ALPHABET.length;
            }
        }

        return result;
    }

}
