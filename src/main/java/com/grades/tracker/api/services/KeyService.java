package com.grades.tracker.api.services;


import com.grades.tracker.api.configs.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;

@Service
public class KeyService {
    private AppConfig keyConfig;

    @Autowired
    public void setKeyConfig(AppConfig keyConfig) {
        this.keyConfig = keyConfig;
    }

    /**
     * Convert a SecretKey object to a String
     *
     * @param secretKey to be converted SecretKey
     * @return a SecretKey converted to String
     */
    public String ConvertSecretKeyToString(Key secretKey) {
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    /**
     * Convert a String to a SecretKey Object
     *
     * @param secretKey to be converted String
     * @return a String converted to a SecretKey object
     */
    public Key ConvertStringToSecretKey(String secretKey) {
        return new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
    }

    /**
     * Get the SecretKey from the config
     *
     * @return a key
     */
    public Key getSecretKey() {
        return ConvertStringToSecretKey(keyConfig.getSecret());
    }

}
