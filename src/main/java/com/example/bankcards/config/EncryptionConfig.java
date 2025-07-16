package com.example.bankcards.config;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

import java.nio.charset.StandardCharsets;

@Configuration
public class EncryptionConfig {
    @Value("${encoding.password}")
    private String password;
    @Value("${encoding.salt}")
    private String salt;


    @Bean
    public TextEncryptor textEncryptor() {
        String hexPassword = Hex.encodeHexString(password.getBytes(StandardCharsets.UTF_8));
        String hexSalt = Hex.encodeHexString(salt.getBytes(StandardCharsets.UTF_8));
        return Encryptors.delux(hexPassword, hexSalt);
    }
}
