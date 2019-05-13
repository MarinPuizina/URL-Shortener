package com.urlshortener.service;

import com.urlshortener.domain.entity.Account;
import com.urlshortener.domain.entity.Register;
import com.urlshortener.domain.request.RegisterRequest;
import com.urlshortener.domain.response.RegisterResponse;
import com.urlshortener.repository.AccountRepository;
import com.urlshortener.repository.RegisterRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * StatisticService Service
 * Business Logic used in RegisterController
 *
 * @author  Marin Puizina
 */
@Service
public class RegisterService {

    Logger logger = LoggerFactory.getLogger(RegisterService.class);

    public static Map<String, String> urlAndShortUrl = new HashMap<>();
    public static Map<String, Integer> urlAndCallNumber = new HashMap<>();


    @Autowired
    private PasswordEncoder passwordEncoder;


    public void fillMaps(RegisterRequest registerRequest, RegisterResponse registerResponse) {

        String shortUrl = generateShortUrl();
        urlAndShortUrl.put(registerRequest.getUrl(), shortUrl);

        if(urlAndCallNumber.get(registerRequest.getUrl()) >= 1)
        {

            Integer i = urlAndCallNumber.get(registerRequest.getUrl());
            urlAndCallNumber.put(registerRequest.getUrl(), ++i);

        } else {
            urlAndCallNumber.put(registerRequest.getUrl(), 1);
        }

        registerResponse.setShortUrl(shortUrl);

    }

    public String generateShortUrl() {

        String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzžABCDĐEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom RANDOM = new SecureRandom();

        StringBuilder sb = new StringBuilder();
        sb.append("http://short.com/");

        for (int i = 0; i < 6; ++i) {
            sb.append("http://short.com/")
                    .append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return sb.toString();

    }
/*
    public RegisterResponse generateShortUrl(RegisterResponse registerResponse) {

        String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom RANDOM = new SecureRandom();

        StringBuilder sb = new StringBuilder();
        sb.append("http://short.com/");

        for (int i = 0; i < 6; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        registerResponse.setShortUrl(sb.toString());

        return  registerResponse;
    }*/

    public String generateShortUrl(RegisterResponse registerResponse) {

        String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        SecureRandom RANDOM = new SecureRandom();

        StringBuilder sb = new StringBuilder();
        sb.append("http://short.com/");

        for (int i = 0; i < 6; ++i) {
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return  sb.toString();
    }

    /**
     *
     * @param request http request
     * @return map with headers
     */
    public Map<String, Object> getHeaders(HttpServletRequest request) {

        Map<String, Object> returnValue = new HashMap<>();

        Enumeration<String> hearderNames = request.getHeaderNames();
        while(hearderNames.hasMoreElements()) {
            String headerName = hearderNames.nextElement();
            returnValue.put(headerName, request.getHeader(headerName));
        }

        return returnValue;
    }

    /**
     *
     * @param returnValue map with request headers
     * @return decoded password
     */
    public String decodingAuthorizationHeader(Map<String, Object> returnValue) {

        if(returnValue.containsKey(ServiceConstants.AUTHORIZATION)) {

            String password = returnValue.get(ServiceConstants.AUTHORIZATION).toString();

            if ("".equals(password)
                    || password == null
                        || !password.startsWith("Basic ")) {
                return null;
            }

            logger.info("" + password);

            String decodedString = new String(Base64.decodeBase64(password.substring(6)));

            logger.info(decodedString);

            return decodedString;

        }

        return null;

    }

    /**
     *
     * @param registerRepository used for querying the database
     * @param url
     * @param shortUrl
     */
    public void storeRegisterInDatabase(RegisterRepository registerRepository, String url, String shortUrl) {

        registerRepository.save(new Register(url, shortUrl));

    }

    public boolean isAccountValid(AccountRepository accountRepository, String decodeddAuthHeader) {

        String extractedAccountId = decodeddAuthHeader.substring(0,decodeddAuthHeader.indexOf(":"));
        String extractedPassword = decodeddAuthHeader.substring(decodeddAuthHeader.indexOf(":") + 1);
        logger.info("extractedAccountId = " + extractedAccountId);
        logger.info("extractedPassword = " + extractedPassword);

        Account dbValue = accountRepository.findAccountByAccountId(extractedAccountId);
        if(dbValue == null){
            logger.warn("Failed getting account from db.");
            return false;
        }

        String accountPassword = dbValue.getPassword();
        String encryptedPassword = passwordEncoder.encode(extractedPassword);
        String encryptedPassword2 = passwordEncoder.encode("591W2ooM");
        logger.info("accountPassword = " + accountPassword);
        logger.info("encryptedPassword = " + encryptedPassword);
        logger.info("encryptedPassword2 = " + encryptedPassword2);

        if(accountPassword.equals(encryptedPassword)) {
            logger.info("It's fine dood.");
            return true;
        }

        return false;

    }

}
