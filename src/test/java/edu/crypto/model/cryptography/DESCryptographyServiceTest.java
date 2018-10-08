package edu.crypto.model.cryptography;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DESCryptographyServiceTest {

    @Autowired
    @Qualifier("DES")
    DESCryptographyService desCryptographyService;

    @Test
    public void encryptTextTestForConsistency() throws Exception {
        assertEquals("MAX ZHIR", desCryptographyService.decryptText(
                desCryptographyService.encryptText("MAX ZHIR")));
    }

}