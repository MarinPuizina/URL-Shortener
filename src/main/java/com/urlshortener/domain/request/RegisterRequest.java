package com.urlshortener.domain.request;

import org.springframework.stereotype.Component;

@Component
public class RegisterRequest {

    private String url;
    private Integer redirectType;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(Integer redirectType) {
        this.redirectType = redirectType;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "url='" + url + '\'' +
                ", redirectType=" + redirectType +
                '}';
    }

}
