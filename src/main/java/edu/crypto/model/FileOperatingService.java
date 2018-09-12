package edu.crypto.model;


import java.nio.file.Path;

public interface FileOperatingService {

    String getFileAsString(String filename);

    Path getStringAsFile(String string, String filename);
}
