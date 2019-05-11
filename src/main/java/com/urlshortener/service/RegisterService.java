package com.urlshortener.service;

import com.urlshortener.domain.request.RegisterRequest;
import com.urlshortener.domain.response.RegisterResponse;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class RegisterService {

    public static Map<String, String> urlAndShortUrl = new HashMap<>();
    public static Map<String, Integer> urlAndCallNumber = new HashMap<>();


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
    }

}
