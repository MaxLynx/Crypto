package edu.crypto.model.fileoperating;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("app.filename")
public class FilenameConfiguration {

    private String base;
    private String source;
    private String encrypted;
    private String decrypted;

    public FilenameConfiguration(){
    }

    public FilenameConfiguration(String base, String source, String encrypted, String decrypted) {
        this.base = base;
        this.source = source;
        this.encrypted = encrypted;
        this.decrypted = decrypted;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getEncrypted() {
        return encrypted;
    }

    public void setEncrypted(String encrypted) {
        this.encrypted = encrypted;
    }

    public String getDecrypted() {
        return decrypted;
    }

    public void setDecrypted(String decrypted) {
        this.decrypted = decrypted;
    }
}
