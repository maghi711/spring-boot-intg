package com.boot.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * A custom web security configurer adapter.
 *
 */
@Configuration
public class Config extends WebSecurityConfigurerAdapter {

    /**
     * Get the bean from the container.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.csrf().disable().authorizeRequests().anyRequest().authenticated().and().httpBasic();
        //http.httpBasic(); // Basic Authentication
        http.formLogin(); // Form Login
        //http.authorizeRequests().anyRequest().authenticated(); // all the requests has to be authenticated.
        http.authorizeRequests().antMatchers("/books/").authenticated(); // only authenticate specific endpoints
    }

    /*
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        final InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
        final UserDetails user = User.withUsername("tom").password(passwordEncoder.encode("cruise")).authorities("read").build();
        inMemoryUserDetailsManager.createUser(user);
        auth.userDetailsService(inMemoryUserDetailsManager).passwordEncoder(passwordEncoder);
    }
    */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    /*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password("admin")
                .roles("USER");
    }
     */

    /**
     * Inject the bean into the container.
     *
     * */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        System.out.println("Creating a new inMemoryUserDetailsManager");
        return new BCryptPasswordEncoder();
    }
}
