package com.urlshortener.service;

import com.urlshortener.domain.entity.Account;
import com.urlshortener.domain.entity.Register;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Method appends randomly generated characters to default url
     * @return
     */
    public String generateShortUrl() {

        SecureRandom RANDOM = new SecureRandom();

        StringBuilder sb = new StringBuilder();
        sb.append(ServiceConstants.DEFAULT_URL);

        for (int i = 0; i < 6; ++i) {
            sb.append(ServiceConstants.ALPHABET.charAt(RANDOM.nextInt(ServiceConstants.ALPHABET.length())));
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
                        || !password.startsWith(ServiceConstants.BASIC)) {
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

        registerRepository.save(new Register(shortUrl, url));

    }

    /**
     * //TODO - node used method, for custom validation
     * @param accountRepository
     * @param decodeddAuthHeader
     * @return
     */
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
