package com.bravechen.spring.security.demo.config.security.sms;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author brave
 */
@Component
public class SmsAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private final SmsAuthenticationProvider smsAuthenticationProvider;

    public SmsAuthenticationConfig(SmsAuthenticationProvider smsAuthenticationProvider) {
        this.smsAuthenticationProvider = smsAuthenticationProvider;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //将自己写的Provider和Filter添加Security中
        http.authenticationProvider(smsAuthenticationProvider);

        //set进去AuthenticationManager
        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        http.addFilterBefore(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}