package com.urlshortener.domain.request;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * Used for mapping JSON from AccountController POST request's RequestBody
 *
 * @author  Marin Puizina
 */
@Component
public class AccountRequest {

    @NotNull
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
