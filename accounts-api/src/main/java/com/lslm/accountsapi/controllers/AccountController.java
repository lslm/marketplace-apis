package com.lslm.accountsapi.controllers;

import com.lslm.accountsapi.models.Account;
import com.lslm.accountsapi.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account newAccount) {
        Account account = accountService.create(newAccount);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
}
