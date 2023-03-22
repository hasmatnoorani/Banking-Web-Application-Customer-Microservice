package com.stl.hasmatnoorani.bankingwebapplication.security;

import com.stl.hasmatnoorani.bankingwebapplication.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class  SecurityConfig {

    @Autowired
    CustomerLoginDetailsService customerLoginDetailsService;

    @Autowired
    JwtFilter filter;

    @Bean
    public AuthenticationProvider auth() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customerLoginDetailsService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Bean
    SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception {

        http.csrf().disable().cors().disable().authorizeHttpRequests()
                .requestMatchers("/api/v1/customer/auth/add","/api/v1/customer/auth/authenticate","api/v1/customer-bank-account/all","api/v1/customer-bank-account/error")
                .permitAll()
                .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();

    }

    @Bean
    AuthenticationManager authmanager(AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }


}

