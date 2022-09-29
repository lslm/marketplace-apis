package com.lslm.accountsapi.services;

import com.lslm.accountsapi.adapters.AccountRegistrationAdapter;
import com.lslm.accountsapi.adapters.requests.AccountRegistrationRequest;
import com.lslm.accountsapi.adapters.responses.AccountRegistrationResponse;
import com.lslm.accountsapi.models.Account;
import com.lslm.accountsapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRegistrationAdapter accountRegistrationAdapter;

    public AccountRegistrationResponse create(AccountRegistrationRequest accountRegistrationRequest) {
        Account account = accountRegistrationAdapter.toAccount(accountRegistrationRequest);
        String passwordDigest = new BCryptPasswordEncoder().encode(accountRegistrationRequest.password());
        account.setPasswordDigest(passwordDigest);

        Account persistedAccount = accountRepository.save(account);
        return accountRegistrationAdapter.toResponse(persistedAccount);
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }
}
