package edu.crypto.model;

import org.springframework.stereotype.Component;

@Component
public class DumbCryptographyService extends FileSystemConfiguredCryptographyService{

    @Override
    protected String encryptText(String source) {
        return "?" + source;
    }

    @Override
    protected String decryptText(String source) {
        return source.substring(1);
    }
}
