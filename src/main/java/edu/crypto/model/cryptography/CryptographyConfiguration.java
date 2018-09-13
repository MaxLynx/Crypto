package edu.crypto.model.cryptography;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.cryptography")
public class CryptographyConfiguration {
    private String dumbEncryption;

    public CryptographyConfiguration(){
    }

    public CryptographyConfiguration(String dumbEncryption) {
        this.dumbEncryption = dumbEncryption;
    }

    public String getDumbEncryption() {
        return dumbEncryption;
    }

    public void setDumbEncryption(String dumbEncryption) {
        this.dumbEncryption = dumbEncryption;
    }
}
