package com.boot.app;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class SecurityTests {

    @Test
    public void bcryptEncoderCheck() {
        System.out.println(new BCryptPasswordEncoder().encode("aadavan")); // medium strong password authentication
        System.out.println(new Pbkdf2PasswordEncoder().encode("aadavan")); // more stronger password authentication
        //System.out.println(new NoOpPasswordEncoder[].encode("aadavan")); // plain text password, don't use in prod, use for testing purposes only.

        System.out.println(new SCryptPasswordEncoder().encode("aadavan")); // much more stronger password authentication
        // java.lang.NoClassDefFoundError: org/bouncycastle/crypto/generators/SCrypt
    }
}
