package edu.crypto.model.cryptography;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Qualifier("Hill")
public class HillCryptographyService extends FileSystemConfiguredCryptographyService{

    private static final char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                                            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                                            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '?', '!'};

    private double [][] key = {{1, 10},
                                {2, 21}};

    private double [][] invertedKey = {{21, 18},
                                        {26, 1}};

    private int alphabetNumerationStart;

    @Override
    public String encryptText(String source) {
        alphabetNumerationStart = cryptographyConfiguration.getAlphabetNumerationStart();

        int dimension = source.length();
        if(dimension % 2 == 1)
            source += "X";

        StringBuilder encrypted = new StringBuilder();

        for(int i = 0; i < dimension; i+=key[0].length) {
            double [] data = new double[key[0].length];
            for(int j = 0; j < key[0].length; j++){
                data[j] = getCode(source.charAt(i + j));
            }
            double[] encryptedData = multiply(key, data);

            for (int j = 0; j < key[0].length; j++) {
                encrypted.append(getLetter(encryptedData[j] + alphabetNumerationStart));
            }
        }
        return encrypted.toString();
    }


    @Override
    public String decryptText(String source) {

        alphabetNumerationStart = cryptographyConfiguration.getAlphabetNumerationStart();

        int dimension = source.length();

        StringBuilder decrypted = new StringBuilder();

        for(int i = 0; i < dimension; i+=key[0].length) {
            double [] data = new double[key[0].length];
            for(int j = 0; j < key[0].length; j++){
                data[j] = getCode(source.charAt(i + j)) - alphabetNumerationStart;
            }
            double[] decryptedData = multiply(invertedKey, data);

            for (int j = 0; j < key[0].length; j++) {
                decrypted.append(getLetter(decryptedData[j]));
            }
        }

        return decrypted.toString();
    }

    private double getCode(char letter){
        for(int i = 0; i < ALPHABET.length; i++)
            if(ALPHABET[i] == letter)
                return i + alphabetNumerationStart;
        return 0;
    }

    private char getLetter(double code){
        return ALPHABET[(int)Math.round(code) - alphabetNumerationStart];
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
                result[i] = ALPHABET.length
                        - result[i] % ALPHABET.length;
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
