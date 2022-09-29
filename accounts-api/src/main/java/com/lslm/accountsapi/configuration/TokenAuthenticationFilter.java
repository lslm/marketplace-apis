package com.lslm.accountsapi.configuration;

import com.lslm.accountsapi.models.Account;
import com.lslm.accountsapi.services.AccountService;
import com.lslm.accountsapi.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private AccountService accountService;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Pegar o token do header da requisição
        // Verificar se aquele token é válido
        // Se o token for válido, devemos verificar se o usuário está cadastrado na API
        String token = getTokenFromHeader(request);

        String requestPath = request.getServletPath();

        if (!requestPath.equals("/api/auth")) {
            if (tokenService.isTokenValid(token)) {
                authenticate(token);
            }
        }

        filterChain.doFilter(request, response);
    }

    private void authenticate(String token) {
        // obter o email do token
        // e verificar se existe algum usuário para aquele email
        // se existir um usuário, dizemos que a requisição é de um usuário altenticado
        String email = tokenService.getEmailFromToken(token);

        Optional<Account> maybeAccount = accountService.findByEmail(email);
        if (maybeAccount.isPresent()) {
            Account account = maybeAccount.get();
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(account, null, account.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
    }

    private String getTokenFromHeader(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if(token == null || token.isEmpty())
            token = null;

        return token;
    }
}
