package com.urlshortener.domain.request;

import org.springframework.stereotype.Component;

@Component
public class AccountRequest {

    private String accountId;


    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "AccountRequest{" +
                "accountId='" + accountId + '\'' +
                '}';
    }

}
