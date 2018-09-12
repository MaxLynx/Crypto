package edu.crypto.controller;

import edu.crypto.model.CryptographyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class CryptographyController {

    @Autowired
    CryptographyService cryptographyAlgorithm;

    public Path encrypt(Path P){
        return cryptographyAlgorithm.encrypt(P);
    }

    public Path decrypt(Path C){
        return cryptographyAlgorithm.decrypt(C);
    }

}
