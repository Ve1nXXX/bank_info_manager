package com.bank_manager.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis全局扫包配置,没有这个配置mybatis就无法实现增删改查
 */
@Configuration
@MapperScan("com.bank_manager.mapper")
public class MpConfig {
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
