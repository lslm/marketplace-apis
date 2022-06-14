package com.lslm.accountsapi.services;

import com.lslm.accountsapi.models.Account;
import com.lslm.accountsapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account create(Account account) {
        return accountRepository.save(account);
    }
}
