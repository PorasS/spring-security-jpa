package com.pratech.springsecurityjpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    UserDetailsService userDetailsService;
//    for authentication
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        logger.info("calling from configure for auth");
        auth.userDetailsService(userDetailsService);
    }

//    for authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        logger.info("calling from configure for authorization");
//      http.authorizeRequests()
//              .antMatchers("/admin").hasRole("ADMIN")
//              .antMatchers("/user").hasAnyRole("ADMIN","USER")
//              .antMatchers("/").permitAll()
//              .and().formLogin();

        http.authorizeRequests()
                .antMatchers("/").hasRole("ADMIN")
                .and().formLogin();
    }

//    finally create a password encoder
//    because spring Security expects you to have a password encoder
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
