package com.boot.app.security;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * My custom filter.
 */
@Component
public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Before");
        chain.doFilter(request, response);
        System.out.println("After");
    }

}
