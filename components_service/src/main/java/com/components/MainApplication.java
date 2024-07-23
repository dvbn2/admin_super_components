package com.components;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author dvbn
 * @title: MainApplication
 * @createDate 2024/5/7 12:06
 */
@SpringBootApplication
@EnableTransactionManagement //开启注解方式的事务管理
@EnableAspectJAutoProxy // 使用AOP
@MapperScan("com.components.mapper")
@EnableMethodSecurity
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
