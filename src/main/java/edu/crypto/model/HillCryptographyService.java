package edu.crypto.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class HillCryptographyService implements CryptographyService {

    @Autowired
    FileOperatingService fileOperatingService;

    @Autowired
    FilenameConfiguration filenameConfiguration;

    public Path encrypt(Path P){
        return P;
    }

    public Path decrypt(Path C){
        return C;
    }

}
