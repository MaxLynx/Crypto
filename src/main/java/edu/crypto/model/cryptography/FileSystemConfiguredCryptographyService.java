package edu.crypto.model.cryptography;

import edu.crypto.model.fileoperating.FilenameConfiguration;
import edu.crypto.model.fileoperating.FileOperatingService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class FileSystemConfiguredCryptographyService implements CryptographyService {

    @Autowired
    FileOperatingService fileOperatingService;

    @Autowired
    FilenameConfiguration filenameConfiguration;

    @Autowired
    CryptographyConfiguration cryptographyConfiguration;

    public void encrypt(){
        fileOperatingService.getStringAsFile(
                encryptText(fileOperatingService.getFileAsString(filenameConfiguration.getBase() +
                        filenameConfiguration.getSource())),
                filenameConfiguration.getBase() + filenameConfiguration.getEncrypted());
    }

    public void decrypt(){
        fileOperatingService.getStringAsFile(
                decryptText(fileOperatingService.getFileAsString(filenameConfiguration.getBase() +
                        filenameConfiguration.getEncrypted())),
                filenameConfiguration.getBase() + filenameConfiguration.getDecrypted());
    }

    protected abstract String encryptText(String source);

    protected abstract String decryptText(String source);

}
