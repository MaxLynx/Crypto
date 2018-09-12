package edu.crypto.model;

import java.nio.file.Path;

public interface CryptographyAlgorithm {

    /**
     * Encrypts the given file
     */
    Path encrypt(Path P);

    /**
     * Decrypts the given file
     */
    Path decrypt(Path C);
}
