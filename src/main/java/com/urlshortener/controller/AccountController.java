package com.urlshortener.controller;

import com.urlshortener.domain.Account;
import com.urlshortener.domain.response.AccountResponse;
import com.urlshortener.domain.request.AccountRequest;
import com.urlshortener.domain.response.AccountRest;
import com.urlshortener.repository.AccountRepository;
import com.urlshortener.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    Logger logger = LoggerFactory.getLogger(AccountController.class);

    AccountResponse accountResponse;
    AccountRequest accountRequest;
    AccountService accountService;

    AccountRepository accountRepository;

    // Dependency Injection
    @Autowired
    public AccountController(AccountResponse accountResponse, AccountRequest accountRequest, AccountService accountService, AccountRepository accountRepository) {
        this.accountResponse = accountResponse;
        this.accountRequest = accountRequest;
        this.accountService = accountService;
        this.accountRepository = accountRepository;
    }


    @RequestMapping(value = "/account", method = RequestMethod.POST)
    public ResponseEntity<AccountRest> account(@Valid @RequestBody AccountRequest accountRequest) {

        logger.info("AccountId = " + accountRequest.getAccountId());

        if(accountRequest.getAccountId() == null)
            return new ResponseEntity<AccountRest>(new AccountRest(), HttpStatus.BAD_REQUEST);


        if(accountService.isAccountValid(accountRepository, accountRequest)) {

            String password = accountService.generatePassword();

            logger.info("Generated password = " + password);

            accountService.storeAccountInDatabase(accountRepository, accountRequest, accountService.passwordEncryption(password));

            return new ResponseEntity<AccountRest>(accountService.createAccountEntity(password, true), HttpStatus.OK);

        }

        return new ResponseEntity<AccountRest>(accountService.createAccountEntity(null, false), HttpStatus.OK);

    }

}
