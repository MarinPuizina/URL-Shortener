package com.urlshortener.service;

import com.urlshortener.domain.entity.Account;
import com.urlshortener.domain.request.AccountRequest;
import com.urlshortener.domain.response.AccountRest;
import com.urlshortener.repository.AccountRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * AccountResponse Service
 * Business Logic used in AccountController
 *
 * @author  Marin Puizina
 */
@Service
public class AccountService {

    Logger logger = LoggerFactory.getLogger(AccountService.class);

    public static final String USER_ROLE = "USER";
    private static final String ACCOUNT_ID_IS_VALID = "AccountResponse ID is valid.";
    private static final String ACCOUNT_ID_ALREADY_EXISTS = "AccountResponse with that ID already exists.";


    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     *
     * @return String type - randomly generated 8 alphanumeric characters
     */
    public String generatePassword() {

        String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom RANDOM = new SecureRandom();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();

    }

    /**
     *
     * @param accountRepository used for querying the database
     * @param accountRequest used for finding properties from request body
     * @return true or false
     */
    public boolean isAccountValid(AccountRepository accountRepository, AccountRequest accountRequest) {

        // DB query using JPA to find out if we have accountId in database
        Account accountValues = accountRepository.findAccountByAccountId(accountRequest.getAccountId());

        logger.info("Account values = " + accountValues);

        if(accountValues == null) {
            return true;
        }

        return false;

    }

    /**
     * Using BCryptPasswordEncoder (see SecurityConfig)
     * @param password password to encode
     * @return encrypted password
     */
    public String passwordEncryption(String password) {

        return passwordEncoder.encode(password);

    }

    /**
     * Using Base64 encoding
     * @param password password to encode
     * @return Base64 encoded password
     */
    public String passwordEncoding(String password) {

        Base64 base64 = new Base64();
        return new String(base64.encode(password.getBytes()));

    }

    /**
     *
     * @param accountRepository used for querying the database
     * @param accountRequest used for getting accountId
     * @param encryptedPassword used for storing in database
     */
    public void storeAccountInDatabase(AccountRepository accountRepository, AccountRequest accountRequest,
                                       String encryptedPassword) {

        String accountId = accountRequest.getAccountId();

        logger.info("accountId = " + accountId);
        logger.info("encryptedPassword = " + encryptedPassword);
        logger.info("role = " + USER_ROLE);

        accountRepository.save(new Account(accountId, encryptedPassword, USER_ROLE));

    }

    /**
     *
     * @param password generated password
     * @param isValid used for choosing optimal return case
     * @return
     */
    public AccountRest createAccountEntity(String password, Boolean isValid) {

        if(isValid) {
            return new AccountRest(true, ACCOUNT_ID_IS_VALID, password);
        }

        return new AccountRest(false, ACCOUNT_ID_ALREADY_EXISTS);

    }

}
