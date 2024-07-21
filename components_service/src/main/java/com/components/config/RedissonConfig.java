package com.components.config;

import lombok.Data;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dvbn
 * @title: RedissonConfig
 * @createDate 2023/12/19 10:26
 */

@Configuration
@ConfigurationProperties(prefix = "spring.data.redis")
@Data
public class RedissonConfig {
    private Integer database;
    private String host;
    private String port;
    private String password;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useSingleServer().setDatabase(database).setPassword(password).setAddress("redis://" + host + ":" + port);
        return Redisson.create(config);
    }
}
