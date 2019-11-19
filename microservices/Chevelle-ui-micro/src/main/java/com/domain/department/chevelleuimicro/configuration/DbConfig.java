package com.domain.department.chevelleuimicro.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@RefreshScope
public class DbConfig {

    @Bean
    @RefreshScope
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource getDatasource() {
        return DataSourceBuilder.create()
                .build();
    }

}
