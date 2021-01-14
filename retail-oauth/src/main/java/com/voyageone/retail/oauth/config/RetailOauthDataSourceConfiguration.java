package com.voyageone.retail.oauth.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class RetailOauthDataSourceConfiguration {

    @Primary
    @Bean("datasourceOauth")
    @ConfigurationProperties("spring.datasource.druid.oauth")
    public DataSource dataSourceOauth(){
        return DruidDataSourceBuilder.create().build();
    }


}
