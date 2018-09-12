package edu.crypto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryptographyApp implements CommandLineRunner {

    public static void main(String[] args) throws Exception {

        SpringApplication.run(CryptographyApp.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {
    }
}
