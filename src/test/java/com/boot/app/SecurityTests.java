package com.boot.app;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityTests {

    @Test
    public void bcryptEncoderCheck() {
        System.out.println(new BCryptPasswordEncoder().encode("aadavan"));
    }
}
