package edu.crypto.model.cryptography;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.cryptography")
public class CryptographyConfiguration {

    private String dumbEncryption;
    private int alphabetNumerationStart;

    public CryptographyConfiguration(){
    }

    public CryptographyConfiguration(String dumbEncryption, int alphabetNumerationStart) {
        this.dumbEncryption = dumbEncryption;
        this.alphabetNumerationStart = alphabetNumerationStart;
    }

    public String getDumbEncryption() {
        return dumbEncryption;
    }

    public void setDumbEncryption(String dumbEncryption) {
        this.dumbEncryption = dumbEncryption;
    }

    public int getAlphabetNumerationStart() {
        return alphabetNumerationStart;
    }

    public void setAlphabetNumerationStart(int alphabetNumerationStart) {
        this.alphabetNumerationStart = alphabetNumerationStart;
    }
}
