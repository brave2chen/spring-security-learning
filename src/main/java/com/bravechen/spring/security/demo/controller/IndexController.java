package com.bravechen.spring.security.demo.controller;

import com.bravechen.spring.security.demo.config.security.sms.SmsUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("")
    public Object index(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/sendCode/{mobile}")
    public String sendCode(@PathVariable String mobile) {
        return SmsUtil.sendCode(mobile);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public Object user(Authentication authentication) {
        return authentication;
    }
}
