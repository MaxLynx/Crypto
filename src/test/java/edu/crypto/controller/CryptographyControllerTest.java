package edu.crypto.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CryptographyControllerTest {

    @Autowired
    CryptographyController cryptographyController;
    Path path;

    @Before
    public void createFile(){
        String s = "Hello World! ";
        byte data[] = s.getBytes();
        path = Paths.get("./logfile.txt");

        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(path, CREATE, APPEND))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    @Test
    public void encryptTest() {
        assertEquals(path, cryptographyController.encrypt(path));
    }

    @Test
    public void decryptTest() {
        assertEquals(path, cryptographyController.decrypt(path));
    }

    @After
    public void destroyFile(){
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}