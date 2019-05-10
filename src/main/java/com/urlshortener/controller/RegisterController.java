package com.urlshortener.controller;

import com.urlshortener.domain.request.RegisterRequest;
import com.urlshortener.domain.response.RegisterResponse;
import com.urlshortener.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {

    RegisterResponse registerResponse;
    RegisterRequest registerRequest;

    // Dependency Injection
    @Autowired
    public RegisterController(RegisterResponse registerResponse, RegisterRequest registerRequest) {
        this.registerResponse = registerResponse;
        this.registerRequest = registerRequest;
    }



    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RegisterResponse register(@RequestBody RegisterRequest registerRequest) {

        return registerResponse;
    }

}
