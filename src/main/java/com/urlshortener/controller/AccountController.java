package com.urlshortener.controller;

import com.urlshortener.domain.Account;
import com.urlshortener.domain.request.AccountRequest;
import com.urlshortener.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccountController {

    Account account;
    AccountService accountService;
    AccountRequest accountRequest; //Check if this is needed


    @Autowired
    public AccountController(Account account, AccountService accountService, AccountRequest accountRequest) {
        this.account = account;
        this.accountService = accountService;
        this.accountRequest = accountRequest;
    }


    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public Account account(@RequestBody AccountRequest accountRequest) {

        if(accountService.isAccountValid(accountRequest)) {

            account.setSuccess(false);
            account.setDescription("Account with that ID already exists.");

        } else {

            account.setSuccess(true);
            account.setDescription("Account ID is valid.");
            account.setPassword(accountService.generatePassword());

        }

        return account;

    }

}
