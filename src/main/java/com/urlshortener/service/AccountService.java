package com.urlshortener.service;

import com.urlshortener.domain.Account;
import com.urlshortener.domain.request.AccountRequest;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * Account Service
 * Business Logic used in our AccountController
 *
 * @author  Marin Puizina
 */
@Service
public class AccountService {

    private static final String ACCOUNT_ID_IS_VALID = "Account ID is valid.";
    private static final String ACCOUNT_ID_ALREADY_EXISTS = "Account with that ID already exists.";

    public static Map<String, String> validationValues;

    static {
        validationValues = new HashMap<>();
        validationValues.put("marin", "m123");
        validationValues.put("admin", "admin");
    }

    public boolean isAccountValid(AccountRequest accountRequest) {

        if(validationValues.get(accountRequest.getAccountId()) != null) {
            return false;
        }

        return true;

    }

    public String generatePassword() {

        String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzžABCDĐEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom RANDOM = new SecureRandom();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();

    }

    public void generateResponse(Account account, boolean isValid) {

        if(isValid) {

            account.setSuccess(isValid);
            account.setDescription(ACCOUNT_ID_IS_VALID);
            account.setPassword(generatePassword());


        } else {

            account.setSuccess(isValid);
            account.setDescription(ACCOUNT_ID_ALREADY_EXISTS);
        }

    }

    /**
     *
     * @param accountId Send from RequestBody
     * @param password pasword
     */
    public void storeAccountIdAndPassword(String accountId, String password) {

        validationValues.put(accountId, password);

    }

}
