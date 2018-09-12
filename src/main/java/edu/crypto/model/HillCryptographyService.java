package edu.crypto.model;

import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

@Component
public class HillCryptographyService implements CryptographyService, FileOperatingService {

    public Path encrypt(Path P){
        return P;
    }

    public Path decrypt(Path C){
        return C;
    }

    public String getPathAsString(Path path) {

        byte data[] = new byte[100];

        path = Paths.get(TEMPORARY_FILE_NAME);

        try (InputStream in = new BufferedInputStream(
                Files.newInputStream(path))) {
            in.read(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }

        destroyFile(path);

        return new String(data);
    }

    public Path getStringAsPath(String string) {

        Path path = Paths.get(TEMPORARY_FILE_NAME);

        byte data[] = string.getBytes();

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(path, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }

        return path;
    }

    private void destroyFile(Path path){
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
