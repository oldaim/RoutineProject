package com.oldaim.routineproject.config.jwt;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthenticProvider jwtAuthenticProvider;

    public JwtAuthenticationFilter(JwtAuthenticProvider provider) {
        jwtAuthenticProvider = provider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtAuthenticProvider.resolveToken(request);

        if(token != null && jwtAuthenticProvider.validateToken(token)){
            Authentication authentication = jwtAuthenticProvider.getAuthentication(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
