package com.lslm.accountsapi.services;

import com.lslm.accountsapi.models.Account;
import com.lslm.accountsapi.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> maybeAccount = accountRepository.findByEmail(username);

        if(maybeAccount.isPresent())
            return maybeAccount.get();

        throw new UsernameNotFoundException("Account not found");
    }
}
