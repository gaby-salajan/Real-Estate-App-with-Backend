package com.gabys.backend.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig{

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
        This is where we configure the security required for our endpoints and setup our app to serve as
        an OAuth2 Resource Server, using JWT validation.
        */

        http
                .csrf().disable();
            /*.authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/public").permitAll()
                        .requestMatchers("/api/private").authenticated()
                        .requestMatchers("/api/private-scoped").hasAuthority("SCOPE_read:messages"))
                .cors().and()
                .oauth2ResourceServer((oauth2ResourceServer) ->
                        // works, but not as clear
                        // oauth2ResourceServer.jwt());
                        oauth2ResourceServer.jwt(jwt -> jwt.decoder(jwtDecoder())));*/


        return http.build();
    }
}


