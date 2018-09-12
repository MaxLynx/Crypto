package edu.crypto;

import edu.crypto.model.CryptographyAlgorithm;
import edu.crypto.model.HillCryptographyAlgorithm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for the app dependency injection.
 */
@Configuration
@ComponentScan(basePackages = "edu.crypto")
public class CryptoConfig {

    @Bean
    CryptographyAlgorithm cryptographyAlgorithm(){
        return new HillCryptographyAlgorithm();
    }

}
