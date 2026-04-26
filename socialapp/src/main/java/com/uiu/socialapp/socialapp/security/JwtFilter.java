package com.uiu.socialapp.socialapp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getServletPath();

        if(path.contains("/api/users/login") || path.contains("/api/users/register")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");
        System.out.println("AUTH HEADER: " + authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("NO TOKEN OR WRONG FORMAT");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String token = authHeader.substring(7).trim();
        System.out.println("TOKEN: " + token);

        boolean isvalid = jwtService.isTokenValid(token);
        System.out.println("isvalid: " + isvalid);

        if(!isvalid) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String email = jwtService.extractEmail(token);
        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var authorities = java.util.List.of(new org.springframework.security.core.authority.SimpleGrantedAuthority("ROLE_USER"));
            org.springframework.security.authentication.UsernamePasswordAuthenticationToken authToken =
                    new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                            email,
                            null,
                            authorities
                    );
            SecurityContextHolder.getContext().setAuthentication(authToken);
            request.setAttribute("userEmail", email);
        }

        filterChain.doFilter(request, response);
    }
}
