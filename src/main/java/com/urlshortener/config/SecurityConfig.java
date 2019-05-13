package com.urlshortener.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser(SeurityConstants.FIRST_USER).password(SeurityConstants.FIRST_USER_PASSWORD).roles(SeurityConstants.USER_ROLE)
                .and()
                .withUser(SeurityConstants.SECOND_USER).password(SeurityConstants.SECOND_USER_PASSWORD).roles(SeurityConstants.ADMIN_ROLE);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable().authorizeRequests()
                .antMatchers(SeurityConstants.ACCOUNT_URL).permitAll()
                .antMatchers(SeurityConstants.CONSOLE_URL).permitAll()
                .antMatchers(SeurityConstants.CONSOLE_DASH_URL).permitAll()
                .antMatchers(SeurityConstants.STATISTIC_ACCOUNTID_URL).permitAll()
                .antMatchers(SeurityConstants.REGISTER_URL).hasRole(SeurityConstants.USER_ROLE)
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        // TODO EDIT /STATISTIC AUTHORIZATION

        // NEEDED TO BE ABLE TO ACCESS H2 CONSOLE
        http.headers().frameOptions().sameOrigin();
    }

}