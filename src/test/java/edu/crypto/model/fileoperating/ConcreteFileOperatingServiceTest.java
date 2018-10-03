package edu.crypto.model.fileoperating;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConcreteFileOperatingServiceTest {

    @Autowired
    ConcreteFileOperatingService concreteFileOperatingService;

    @Test
    public void getFileAsStringTest() throws Exception {

        assertEquals("test",
                concreteFileOperatingService.getFileAsString("src/test/resources/testRead.txt"));
    }

    @Test
    public void getStringAsFileTest() throws Exception {
        concreteFileOperatingService.getStringAsFile("new_test", "src/test/resources/testWrite.txt");
        assertEquals("new_test",
                concreteFileOperatingService.getFileAsString("src/test/resources/testWrite.txt"));
    }

}