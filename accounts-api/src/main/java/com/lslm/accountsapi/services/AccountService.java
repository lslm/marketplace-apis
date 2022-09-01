package com.lslm.accountsapi.services;

import com.lslm.accountsapi.adapters.AccountRegistrationAdapter;
import com.lslm.accountsapi.adapters.requests.AccountRegistrationRequest;
import com.lslm.accountsapi.adapters.responses.AccountRegistrationResponse;
import com.lslm.accountsapi.models.Account;
import com.lslm.accountsapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRegistrationAdapter accountRegistrationAdapter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AccountRegistrationResponse create(AccountRegistrationRequest accountRegistrationRequest) {
        Account account = accountRegistrationAdapter.toAccount(accountRegistrationRequest);
        String passwordDigest = passwordEncoder.encode(accountRegistrationRequest.password());
        account.setPasswordDigest(passwordDigest);

        Account persistedAccount = accountRepository.save(account);
        return accountRegistrationAdapter.toResponse(persistedAccount);
    }
}
