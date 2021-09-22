package com.boot.app.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    /**
     * We write our own custom authentication logic.
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        final String password = authentication.getCredentials().toString();
        if ("tom".equals(username) && "cruise".equals(password)) {
            System.out.println("Success");
            return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList());
        } else {
            System.out.println("Invalid username");
            throw new BadCredentialsException("Invalid Username or Password");
        }
    }

    /**
     * Tells the provider if the username password authentication is supported.
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
