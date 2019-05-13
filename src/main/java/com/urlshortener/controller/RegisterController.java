package com.urlshortener.controller;

import com.urlshortener.domain.request.RegisterRequest;
import com.urlshortener.domain.response.RegisterResponse;
import com.urlshortener.domain.response.RegisterRest;
import com.urlshortener.repository.AccountRepository;
import com.urlshortener.repository.RegisterRepository;
import com.urlshortener.service.RegisterService;
import com.urlshortener.service.ServiceConstants;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RegisterController {

    Logger logger = LoggerFactory.getLogger(RegisterController.class);

    RegisterResponse registerResponse;
    RegisterRequest registerRequest;
    RegisterService registerService;
    RegisterRepository registerRepository;
    AccountRepository accountRepository;

    // Dependency Injection
    @Autowired
    public RegisterController(RegisterResponse registerResponse, RegisterRequest registerRequest,
                              RegisterService registerService, RegisterRepository registerRepository,
                              AccountRepository accountRepository) {
        this.registerResponse = registerResponse;
        this.registerRequest = registerRequest;
        this.registerService = registerService;
        this.registerRepository = registerRepository;
        this.accountRepository = accountRepository;
    }
    /*
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RegisterResponse register(HttpServletRequest request, @Valid @RequestBody RegisterRequest registerRequest) {

        // registerService.fillMaps(registerRequest, registerResponse);
        Map<String, Object> returnValue = new HashMap<>();

        Enumeration<String> hearderNames = request.getHeaderNames();
        while(hearderNames.hasMoreElements()) {
            String headerName = hearderNames.nextElement();
            returnValue.put(headerName, request.getHeader(headerName));
        }

        if(returnValue.containsKey("authorization")) {
            System.out.println(returnValue.get("authorization"));
            String decodedString = new String(Base64.decodeBase64("bWFyaW46bTEyMw=="));
            System.out.println(decodedString);
        }

        // TODO - REFACTOR THE CODE, MAKE LOGIC FOR STORING URLs
        // TODO - MAKE PASSWORD VALIDATOR, MAKE CUSTOM RESPONSE STATUSES

        System.out.println(returnValue);


        registerService.generateShortUrl(registerResponse);

        return registerResponse;
    }*/

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<RegisterRest> register(HttpServletRequest request, @Valid @RequestBody RegisterRequest registerRequest) {

        // TODO CUSTOM VALIDATION - atm it doesn't work since it's using default basic auth with in memory users
        /*
        Map<String, Object> returnValue = registerService.getHeaders(request);

        String decodeddAuthHeader = registerService.decodingAuthorizationHeader(returnValue);

        if("".equals(decodeddAuthHeader) || decodeddAuthHeader == null) {
            return new ResponseEntity<RegisterRest>(new RegisterRest(), HttpStatus.UNAUTHORIZED);
        }

        String extractedPassword = decodeddAuthHeader.substring(decodeddAuthHeader.indexOf(":") + 1);
        logger.info(extractedPassword);

        registerService.isAccountValid(accountRepository, decodeddAuthHeader);
        */

        String url = registerRequest.getUrl();
        String shortUrl = registerService.generateShortUrl(registerResponse);

        registerService.storeRegisterInDatabase(registerRepository, url, shortUrl);

        return new ResponseEntity<RegisterRest>(new RegisterRest(shortUrl), HttpStatus.OK);
    }


}
