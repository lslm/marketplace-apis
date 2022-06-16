package com.lslm.accountsapi.services;

import com.lslm.accountsapi.adapters.AccountRegistrationAdapter;
import com.lslm.accountsapi.adapters.requests.AccountRegistrationRequest;
import com.lslm.accountsapi.adapters.responses.AccountRegistrationResponse;
import com.lslm.accountsapi.models.Account;
import com.lslm.accountsapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRegistrationAdapter accountRegistrationAdapter;

    public AccountRegistrationResponse create(AccountRegistrationRequest accountRegistrationRequest) {
        Account account = accountRegistrationAdapter.toAccount(accountRegistrationRequest);
        Account persistedAccount = accountRepository.save(account);
        return accountRegistrationAdapter.toResponse(persistedAccount);
    }
}
