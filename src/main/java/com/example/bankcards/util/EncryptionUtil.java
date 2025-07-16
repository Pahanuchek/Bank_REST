package com.example.bankcards.util;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EncryptionUtil {
    private final TextEncryptor textEncryptor;

    public String encrypt(String originalData) {
        return textEncryptor.encrypt(originalData);
    }

    public String decrypt(String encryptionData) {
        return textEncryptor.decrypt(encryptionData);
    }
}
