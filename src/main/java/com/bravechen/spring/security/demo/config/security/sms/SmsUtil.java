package com.bravechen.spring.security.demo.config.security.sms;

import org.springframework.util.Assert;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SmsUtil {
    public static final Map<String, String> mobileCode = new ConcurrentHashMap<>();

    public static String sendCode(String mobile) {
        Assert.notNull(mobile);
        String code = String.valueOf(UUID.randomUUID().hashCode()).substring(0, 4);
        mobileCode.put(mobile, code);
        return code;
    }

    public static String getCode(String mobile) {
        return mobileCode.get(mobile);
    }
}
