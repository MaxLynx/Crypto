package edu.crypto;

import edu.crypto.model.cryptography.CryptographyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CryptographyApp implements CommandLineRunner {

    @Autowired
    @Qualifier("ElGamal")
    CryptographyService cryptographyService;

    public static void main(String[] args) throws Exception {

        SpringApplication.run(CryptographyApp.class, args);

    }

    @Override
    public void run(String... strings) throws Exception {
        cryptographyService.encrypt();
        cryptographyService.decrypt();
    }
}
