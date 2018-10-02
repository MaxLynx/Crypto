package edu.crypto;

import edu.crypto.model.cryptography.AffineCryptographyService;
import edu.crypto.model.cryptography.CryptographyService;
import edu.crypto.model.cryptography.ElGamalCryptographyService;
import edu.crypto.model.cryptography.RSACryptographyService;
import edu.crypto.model.fileoperating.FileOperatingService;
import edu.crypto.model.fileoperating.FilenameConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/app")
public class CryptographyController {

    @Autowired
    @Qualifier("Affine")
    private CryptographyService affineCryptographyService;
    @Autowired
    @Qualifier("RSA")
    private CryptographyService rsaCryptographyService;
    @Autowired
    @Qualifier("ElGamal")
    private CryptographyService elGamalCryptographyService;
    @Autowired
    private FileOperatingService fileOperatingService;
    @Autowired
    private FilenameConfiguration filenameConfiguration;


    @RequestMapping("")
    public String getIndex(Map<String, Object> model){
        model.put("originalMessage", "");
        model.put("encryptedMessage", "");
        model.put("algorithm", "Affine");
        return "index";
    }

    @RequestMapping(method=RequestMethod.POST, value="/encryption")
    public String encrypt(@RequestParam String message, @RequestParam String algorithm,
                                Map<String, Object> model){
        fileOperatingService.getStringAsFile(message, filenameConfiguration.getBase()
                + filenameConfiguration.getSource());
        if(algorithm.equals("Affine"))
            affineCryptographyService.encrypt();
        else
        if(algorithm.equals("RSA"))
            rsaCryptographyService.encrypt();
        else
        if(algorithm.equals("El Gamal"))
            elGamalCryptographyService.encrypt();
        model.put("originalMessage", message);
        model.put("encryptedMessage", fileOperatingService.getFileAsString(filenameConfiguration.getBase()
                + filenameConfiguration.getEncrypted()));
        model.put("algorithm", algorithm);
        return "index";
    }

    @RequestMapping(method=RequestMethod.POST, value="/decryption")
    public String decrypt(@RequestParam String message, @RequestParam String algorithm,
                                Map<String, Object> model){
        fileOperatingService.getStringAsFile(message, filenameConfiguration.getBase()
                + filenameConfiguration.getEncrypted());
        if(algorithm.equals("Affine"))
            affineCryptographyService.decrypt();
        else
        if(algorithm.equals("RSA"))
            rsaCryptographyService.decrypt();
        else
        if(algorithm.equals("El Gamal"))
            elGamalCryptographyService.decrypt();
        model.put("encryptedMessage", message);
        model.put("originalMessage", fileOperatingService.getFileAsString(filenameConfiguration.getBase()
                + filenameConfiguration.getDecrypted()));
        model.put("algorithm", algorithm);
        return "index";
    }

}
