package edu.crypto.controller;

import edu.crypto.model.CryptographyAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

@Component
public class CryptographyController {

    @Autowired
    CryptographyAlgorithm cryptographyAlgorithm;

    public Path encrypt(Path P){
        return cryptographyAlgorithm.encrypt(P);
    }

    public Path decrypt(Path C){
        return cryptographyAlgorithm.decrypt(C);
    }

}
