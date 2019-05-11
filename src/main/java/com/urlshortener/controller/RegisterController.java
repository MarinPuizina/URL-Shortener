package com.urlshortener.controller;

import com.urlshortener.domain.request.RegisterRequest;
import com.urlshortener.domain.response.RegisterResponse;
import com.urlshortener.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class RegisterController {

    RegisterResponse registerResponse;
    RegisterRequest registerRequest;
    RegisterService registerService;

    // Dependency Injection
    @Autowired
    public RegisterController(RegisterResponse registerResponse, RegisterRequest registerRequest, RegisterService registerService) {
        this.registerResponse = registerResponse;
        this.registerRequest = registerRequest;
        this.registerService = registerService;
    }

    @Secured("ROLE_USER")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RegisterResponse register(@Valid @RequestBody RegisterRequest registerRequest) {

        // registerService.fillMaps(registerRequest, registerResponse);

        registerService.generateShortUrl(registerResponse);

        return registerResponse;
    }

}
