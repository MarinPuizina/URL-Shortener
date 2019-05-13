package com.urlshortener.domain.response;

public class RegisterRest {

    private String url;


    public RegisterRest() {
    }

    public RegisterRest(String url) {
        this.url = url;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "RegisterRest{" +
                "url='" + url + '\'' +
                '}';
    }

}
