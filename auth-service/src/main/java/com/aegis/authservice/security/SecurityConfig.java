package com.aegis.authservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * SecurityConfig - Spring Security Configuration
 *
 * Defines:
 * - Which APIs are public (no token needed)
 * - Which APIs are protected (token required)
 * - Password encryption method (BCrypt)
 * - JWT filter integration
 * - Stateless session (no server-side sessions, only JWT)
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, UserDetailsService userDetailsService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.userDetailsService = userDetailsService;
    }

    /**
     * Main Security Filter Chain
     * Defines security rules for all HTTP requests
     */
    @SuppressWarnings("null")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, PasswordEncoder passwordEncoder)
            throws Exception {
        http
                // Disable CSRF (not needed for REST APIs with JWT)
                .csrf(AbstractHttpConfigurer::disable)

                // Define which routes are public and which need authentication
                .authorizeHttpRequests(auth -> auth
                        // PUBLIC routes - no token needed
                        .requestMatchers("/auth/login", "/auth/register").permitAll()
                        // Health check endpoint - public
                        .requestMatchers("/actuator/**").permitAll()
                        // H2 console - public (for development)
                        .requestMatchers("/h2-console/**").permitAll()
                        // All other routes require authentication
                        .anyRequest().authenticated())

                // Use stateless sessions (JWT handles state, not server)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Set authentication provider
                .authenticationProvider(authenticationProvider(passwordEncoder))

                // Add JWT filter BEFORE Spring's default username/password filter
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)

                // Allow H2 console frames (development only)
                .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    /**
     * Authentication Provider
     * Connects UserDetailsService and PasswordEncoder to Spring Security
     */
    @Bean
    public AuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Authentication Manager
     * Used to authenticate login requests
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
