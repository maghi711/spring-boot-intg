package com.boot.app;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

public class SecurityTests {

    @Test
    public void bcryptEncoderCheck() {
        System.out.println(new BCryptPasswordEncoder().encode("aadavan")); // medium strong password authentication
        System.out.println(new Pbkdf2PasswordEncoder().encode("aadavan")); // more stronger password authentication
        System.out.println(new SCryptPasswordEncoder().encode("aadavan")); // much more stronger password authentication

        // Define a map of password encoders
        Map<String, PasswordEncoder> passwordEncoders = new HashMap<>();
        passwordEncoders.put("bcrypt", new BCryptPasswordEncoder());
        passwordEncoders.put("scrypt", new SCryptPasswordEncoder());

        // choose them at run time based on the value available in the header or the body.
        final DelegatingPasswordEncoder delegatingPasswordEncoder = new DelegatingPasswordEncoder("scrypt", passwordEncoders);
        System.out.println(delegatingPasswordEncoder.encode("aadavan"));
    }
}
