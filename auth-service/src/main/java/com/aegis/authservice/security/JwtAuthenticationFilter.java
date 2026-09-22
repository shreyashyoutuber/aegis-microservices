package com.aegis.authservice.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JwtAuthenticationFilter
 *
 * This filter runs on EVERY incoming request (once per request).
 * It checks if the request has a valid JWT token in the Authorization header.
 *
 * Flow:
 * 1. Read "Authorization" header from request
 * 2. Extract JWT token (remove "Bearer " prefix)
 * 3. Extract username from token
 * 4. Validate the token
 * 5. If valid, set authentication in Spring Security context
 * 6. Allow request to proceed
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final int BEARER_PREFIX_LENGTH = 7;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(final JwtUtil jwtUtil, final UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            final HttpServletRequest request,
            final HttpServletResponse response,
            final FilterChain filterChain
    ) throws ServletException, IOException {

        // Step 1: Get Authorization header
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String username;

        // Step 2: Check if header exists and starts with "Bearer "
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // No token found — pass request to next filter
            filterChain.doFilter(request, response);
            return;
        }

        // Step 3: Extract the token (remove "Bearer " prefix)
        jwt = authHeader.substring(BEARER_PREFIX_LENGTH);

        // Step 4: Extract username from token
        username = jwtUtil.extractUsername(jwt);

        // Step 5: If username found and no existing authentication
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Load user details from database
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

            // Step 6: Validate token
            if (jwtUtil.isTokenValid(jwt, userDetails.getUsername())) {

                // Step 7: Create authentication token and set in security context
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Step 8: Continue processing the request
        filterChain.doFilter(request, response);
    }
}
