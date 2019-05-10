package com.urlshortener.service;

import com.urlshortener.domain.request.AccountRequest;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class AccountService {

    public static Map<String, String> validationValues;

    static {
        validationValues = new HashMap<>();
        validationValues.put("marin", "m123");
        validationValues.put("admin", "admin");
    }

    public boolean isAccountValid(AccountRequest accountRequest) {

        System.out.println("ACCOUNT ID: " + accountRequest.getAccountId());
        if(validationValues.get(accountRequest.getAccountId()) != null) {
            return true;
        }

        return false;

    }

    public String generatePassword() {

        String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom RANDOM = new SecureRandom();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();

    }

}
