package edu.crypto.model.cryptography;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("Dumb")
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
