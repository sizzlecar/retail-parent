package com.voyageone.retail.oauth.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RetailOauthDataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("retail.datasource.oauth")
    public DataSourceProperties oauthDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("datasourceOauth")
    @Primary
    @ConfigurationProperties("app.datasource.oauth.configuration")
    public HikariDataSource oauthDataSource() {
        return oauthDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }




}
