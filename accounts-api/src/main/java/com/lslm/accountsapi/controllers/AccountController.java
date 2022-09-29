package com.lslm.accountsapi.controllers;

import com.lslm.accountsapi.adapters.AccountRegistrationAdapter;
import com.lslm.accountsapi.adapters.requests.AccountRegistrationRequest;
import com.lslm.accountsapi.adapters.responses.AccountRegistrationResponse;
import com.lslm.accountsapi.models.Account;
import com.lslm.accountsapi.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRegistrationAdapter accountRegistrationAdapter;

    @PostMapping
    public ResponseEntity<AccountRegistrationResponse> create(@RequestBody AccountRegistrationRequest accountRegistrationRequest) {
        AccountRegistrationResponse response = accountService.create(accountRegistrationRequest);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AccountRegistrationResponse>> listAccounts() {
        List<Account> accounts = accountService.findAll();
        List<AccountRegistrationResponse> response = accountRegistrationAdapter.toListResponse(accounts);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
