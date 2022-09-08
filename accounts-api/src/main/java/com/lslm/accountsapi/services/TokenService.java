package com.lslm.accountsapi.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.lslm.accountsapi.models.Account;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    public String generateToken(Authentication authentication) {
        Account account = (Account) authentication.getPrincipal();

        return JWT.create()
                .withSubject(account.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 900_000))
                .sign(Algorithm.HMAC512("MY_SECRET".getBytes()));
    }
}
