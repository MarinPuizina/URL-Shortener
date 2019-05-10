package com.urlshortener.controller;

import com.urlshortener.domain.Account;
import com.urlshortener.domain.request.AccountRequest;
import com.urlshortener.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Account Controller
 * Send JSON in RequestBody of format: { "accountId" : "String" }
 * Rensponse returns JSON
 *
 * @author  Marin Puizina
 */
@RestController
public class AccountController {

    Account account;
    AccountService accountService;
    AccountRequest accountRequest; //Check if this is needed

    // Dependency injection
    @Autowired
    public AccountController(Account account, AccountService accountService, AccountRequest accountRequest) {
        this.account = account;
        this.accountService = accountService;
        this.accountRequest = accountRequest;
    }


    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public Account account(@RequestBody AccountRequest accountRequest) {

        if(accountService.isAccountValid(accountRequest)) {

            accountService.generateResponse(account, true);
            accountService.storeAccountIdAndPassword(accountRequest.getAccountId(), account.getPassword());

        } else {
            accountService.generateResponse(account, false);
        }

        return account;

    }

}
