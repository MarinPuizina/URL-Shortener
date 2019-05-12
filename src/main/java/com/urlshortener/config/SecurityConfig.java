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
                .withUser("marin")
                .password("{noop}m123")
                .roles("USER")
            .and()
                .withUser("admin")
                .password("{noop}admin")
                .roles("ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable().authorizeRequests()
                .antMatchers("/account").permitAll()
                .antMatchers("/console").permitAll()
                .antMatchers("/console/**").permitAll()
                .antMatchers("/register").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        // NEEDED TO BE ABLE TO ACCESS H2 CONSOLE
        http.headers().frameOptions().sameOrigin();
    }

}