package com.bravechen.spring.security.demo.config.security.sms;/*
 * Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * An {@link AuthenticationProvider} implementation that retrieves user details from a
 * {@link UserDetailsService}.
 *
 * @author Ben Alex
 * @author Rob Winch
 */
@Component
public class SmsAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;

    public SmsAuthenticationProvider(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Object code = authentication.getCredentials();
        String mobile = (code == null) ? "NONE_PROVIDED" : authentication.getName();

        UserDetails user = userDetailsService.loadUserByUsername(mobile);

        if (!code.equals(SmsUtil.getCode(mobile))) {
            throw new BadCredentialsException("手机验证码不正确");
        }

        return new SmsAuthenticationToken(user, authentication.getCredentials(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (SmsAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
