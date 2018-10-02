package edu.crypto.model.cryptography;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RSACryptographyServiceTest {
    @Autowired
    RSACryptographyService rsaCryptographyService;

    @Test
    public void encryptTextTestOnCorrectAlphabet() throws Exception {
        assertEquals("C(N", rsaCryptographyService.encryptText("MAX"));
    }

    @Test
    public void decryptTextTestOnCorrectAlphabet() throws Exception {
        assertEquals("MAX", rsaCryptographyService.decryptText("C(N"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void decryptTextTestOnWrongAlphabet() throws Exception {
        rsaCryptographyService.decryptText(rsaCryptographyService.encryptText("123"));
    }

}