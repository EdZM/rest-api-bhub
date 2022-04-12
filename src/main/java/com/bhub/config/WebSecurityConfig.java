package com.bhub.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, "/user/create").permitAll()
                .antMatchers(HttpMethod.POST, "/user/createBankData/{userId}").permitAll()
                .antMatchers(HttpMethod.GET, "/user/all").permitAll()
                .antMatchers(HttpMethod.GET, "/user/{userId}").permitAll()
                .antMatchers(HttpMethod.PUT, "/user/update/{userId}").permitAll()
                .antMatchers(HttpMethod.PUT, "/user/updateBankData/{bankDataId}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/user/delete/{userId}").permitAll()
                .antMatchers(HttpMethod.DELETE, "/user/deleteBankData/{bankDataId}").permitAll()
                .anyRequest().authenticated();

    }

}
