package edu.crypto.model;

import org.springframework.stereotype.Component;

@Component
public class DumbCryptographyService extends FileSystemConfiguredCryptographyService{

    @Override
    protected String encryptText(String source) {
        return cryptographyConfiguration.getDumbEncryption() + source;
    }

    @Override
    protected String decryptText(String source) {
        return source.substring(cryptographyConfiguration.getDumbEncryption().length());
    }
}
