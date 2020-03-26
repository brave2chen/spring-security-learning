package com.bravechen.spring.security.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.bravechen.spring.security.demo.mapper")
public class MybatisPlusConfig {
}
