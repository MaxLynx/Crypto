package edu.crypto.model.cryptography;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AffineCryptographyServiceTest {

    @Autowired
    AffineCryptographyService affineCryptographyService;

    @Test
    public void encryptTextTestOnCorrectAlphabet() throws Exception {
        assertEquals("PVE", affineCryptographyService.encryptText("MAX"));
    }

    @Test
    public void decryptTextTestOnCorrectAlphabet() throws Exception {
        assertEquals("MAX", affineCryptographyService.decryptText("PVE"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void decryptTextTestOnWrongAlphabet() throws Exception {
        affineCryptographyService.decryptText(affineCryptographyService.encryptText("123"));
    }

}