package com.urlshortener.domain.response;

import org.springframework.stereotype.Component;

@Component
public class RegisterResponse {

    private String shortUrl;


    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    @Override
    public String toString() {
        return "RegisterResponse{" +
                "shortUrl='" + shortUrl + '\'' +
                '}';
    }

}
