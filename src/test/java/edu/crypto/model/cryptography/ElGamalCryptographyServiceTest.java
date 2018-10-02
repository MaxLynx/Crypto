package edu.crypto.model.cryptography;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElGamalCryptographyServiceTest {
    @Autowired
    ElGamalCryptographyService elGamalCryptographyService;

    @Test
    public void encryptTextTestOnCorrectAlphabet() throws Exception {
        assertEquals("ZIH", elGamalCryptographyService.encryptText("MAX"));
    }

    @Test
    public void decryptTextTestOnCorrectAlphabet() throws Exception {
        assertEquals("MAX", elGamalCryptographyService.decryptText("ZIH"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void decryptTextTestOnWrongAlphabet() throws Exception {
        elGamalCryptographyService.decryptText(elGamalCryptographyService.encryptText("123"));
    }

}