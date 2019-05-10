package com.urlshortener.controller;

import com.urlshortener.domain.response.AccountResponse;
import com.urlshortener.domain.request.AccountRequest;
import com.urlshortener.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    AccountService accountService;

    // Dependency Injection
    @Autowired
    public AccountController(AccountResponse accountResponse, AccountService accountService) {
        this.accountResponse = accountResponse;
        this.accountService = accountService;
    }


    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public AccountResponse account(@RequestBody AccountRequest accountRequest) {

        if(accountService.isAccountValid(accountRequest)) {

            accountService.generateResponse(accountResponse, true);
            accountService.storeAccountIdAndPassword(accountRequest.getAccountId(), accountResponse.getPassword());

        } else {
            accountService.generateResponse(accountResponse, false);
        }

        return accountResponse;

    }

}
