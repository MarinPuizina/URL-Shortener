package com.urlshortener.controller;

import com.urlshortener.domain.response.AccountResponse;
import com.urlshortener.domain.request.AccountRequest;
import com.urlshortener.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * AccountResponse Controller
 * Send JSON in RequestBody of format: { "accountId" : "String" }
 * Rensponse returns JSON
 *
 * @author  Marin Puizina
 */
@RestController
public class AccountController {

    AccountResponse accountResponse;
    AccountRequest accountRequest;
    AccountService accountService;

    // Dependency Injection
    @Autowired
    public AccountController(AccountResponse accountResponse, AccountRequest accountRequest, AccountService accountService) {
        this.accountResponse = accountResponse;
        this.accountRequest = accountRequest;
        this.accountService = accountService;
    }




    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public AccountResponse account(@Valid @RequestBody AccountRequest accountRequest) {

        if(accountService.isAccountValid(accountRequest)) {

            accountService.generateResponse(accountResponse, true);
            accountService.storeAccountIdAndPassword(accountRequest.getAccountId(), accountResponse.getPassword());

        } else {
            accountService.generateResponse(accountResponse, false);
        }

        return accountResponse;

    }

}
