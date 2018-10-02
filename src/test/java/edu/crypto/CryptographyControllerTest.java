package edu.crypto;

import edu.crypto.model.cryptography.AffineCryptographyService;
import edu.crypto.model.cryptography.ElGamalCryptographyService;
import edu.crypto.model.cryptography.RSACryptographyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CryptographyControllerTest {

    @Autowired
    CryptographyController cryptographyController;
    @Autowired
    AffineCryptographyService affineCryptographyService;
    @Autowired
    RSACryptographyService rsaCryptographyService;
    @Autowired
    ElGamalCryptographyService elGamalCryptographyService;

    @Test
    public void getIndexTest() throws Exception {
        Map<String, Object> model = new HashMap<>();
        assertEquals("index", cryptographyController.getIndex(model));
        assertEquals("", model.get("originalMessage"));
        assertEquals("", model.get("encryptedMessage"));
        assertEquals("Affine", model.get("algorithm"));
    }

    @Test
    public void encryptTestOnAffine() throws Exception {
        Map<String, Object> model = new HashMap<>();
        String message = "MAX";
        String algorithm = "Affine";
        assertEquals("index", cryptographyController.encrypt(message, algorithm, model));
        assertEquals(message, model.get("originalMessage"));
        assertEquals(affineCryptographyService.encryptText(message), model.get("encryptedMessage"));
        assertEquals(algorithm, model.get("algorithm"));
    }

    @Test
    public void encryptTestOnRSA() throws Exception {
        Map<String, Object> model = new HashMap<>();
        String message = "MAX";
        String algorithm = "RSA";
        assertEquals("index", cryptographyController.encrypt(message, algorithm, model));
        assertEquals(message, model.get("originalMessage"));
        assertEquals(rsaCryptographyService.encryptText(message), model.get("encryptedMessage"));
        assertEquals(algorithm, model.get("algorithm"));
    }

    @Test
    public void encryptTestOnElGamal() throws Exception {
        Map<String, Object> model = new HashMap<>();
        String message = "MAX";
        String algorithm = "El Gamal";
        assertEquals("index", cryptographyController.encrypt(message, algorithm, model));
        assertEquals(message, model.get("originalMessage"));
        assertEquals(elGamalCryptographyService.encryptText(message), model.get("encryptedMessage"));
        assertEquals(algorithm, model.get("algorithm"));
    }

    @Test
    public void decryptTestOnAffine() throws Exception {
        Map<String, Object> model = new HashMap<>();
        String message = "PVE";
        String algorithm = "Affine";
        assertEquals("index", cryptographyController.decrypt(message, algorithm, model));
        assertEquals(message, model.get("encryptedMessage"));
        assertEquals(affineCryptographyService.decryptText(message), model.get("originalMessage"));
        assertEquals(algorithm, model.get("algorithm"));
    }

    @Test
    public void decryptTestOnRSA() throws Exception {
        Map<String, Object> model = new HashMap<>();
        String message = "C(N";
        String algorithm = "RSA";
        assertEquals("index", cryptographyController.decrypt(message, algorithm, model));
        assertEquals(message, model.get("encryptedMessage"));
        assertEquals(rsaCryptographyService.decryptText(message), model.get("originalMessage"));
        assertEquals(algorithm, model.get("algorithm"));
    }

    @Test
    public void decryptTestOnElGamal() throws Exception {
        Map<String, Object> model = new HashMap<>();
        String message = "ZIH";
        String algorithm = "El Gamal";
        assertEquals("index", cryptographyController.decrypt(message, algorithm, model));
        assertEquals(message, model.get("encryptedMessage"));
        assertEquals(elGamalCryptographyService.decryptText(message), model.get("originalMessage"));
        assertEquals(algorithm, model.get("algorithm"));
    }

}