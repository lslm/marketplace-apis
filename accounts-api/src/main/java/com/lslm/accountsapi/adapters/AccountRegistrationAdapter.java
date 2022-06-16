package com.lslm.accountsapi.adapters;

import com.lslm.accountsapi.adapters.requests.AccountRegistrationRequest;
import com.lslm.accountsapi.adapters.responses.AccountRegistrationResponse;
import com.lslm.accountsapi.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountRegistrationAdapter {
    public Account toAccount(AccountRegistrationRequest accountRegistrationRequest) {
        return Account.builder()
                .firstName(accountRegistrationRequest.firstName())
                .lastName(accountRegistrationRequest.lastName())
                .email(accountRegistrationRequest.email())
                .build();
    }

    public AccountRegistrationResponse toResponse(Account account) {
        return AccountRegistrationResponse.builder()
                .id(account.getId())
                .firstName(account.getFirstName())
                .lastName(account.getLastName())
                .email(account.getEmail())
                .createdAt(account.getCreatedAt())
                .build();
    }
}
