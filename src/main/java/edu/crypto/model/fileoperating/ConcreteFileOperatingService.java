package edu.crypto.model.fileoperating;


import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.CREATE;

@Component
public class ConcreteFileOperatingService implements FileOperatingService{

    public String getFileAsString(String filename) {

        String result = "";

        Path path = Paths.get(filename);

        try (InputStream in = new BufferedInputStream(
                Files.newInputStream(path))) {
            result = new BufferedReader(new InputStreamReader(in))
                    .lines().collect(Collectors.joining("\n"));
        } catch (IOException x) {
            System.err.println(x);
        }

        return result;
    }

    public Path getStringAsFile(String string, String filename) {

        Path path = Paths.get(filename);

        byte data[] = string.getBytes();

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(path, CREATE))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }

        return path;
    }

}
