package com.belle_bliss_fashions_user_service_1.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private JwtAuthConvertor jwtAuthConvertor;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception{
        security.csrf(AbstractHttpConfigurer::disable);
        security.authorizeHttpRequests(authorize -> {
            authorize
                    .requestMatchers(HttpMethod.GET,"/belle-bliss-user-service/api/v1/test").permitAll()
                    .anyRequest().authenticated();
        });
        security.oauth2ResourceServer(t->{
            t.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthConvertor));
        });
        security.sessionManagement(t->t.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return security.build();
    }

}
