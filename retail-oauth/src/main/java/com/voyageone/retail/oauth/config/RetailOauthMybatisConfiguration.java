package com.voyageone.retail.oauth.config;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Configuration
public class RetailOauthMybatisConfiguration {


    @Bean("sqlSessionFactoryOauth")
    @ConditionalOnBean(name = "datasourceOauth")
    public SqlSessionFactory sqlSessionFactoryBean(@Qualifier("datasourceOauth") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("com/voyageone/retail/oauth/dao/mybatis/mappers/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    @Bean("mapperScannerConfigOauth")
    public MapperScannerConfigurer mapperScannerConfigOauth() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryOauth");
        mapperScannerConfigurer.setBasePackage("com.voyageone.retail.oauth.dao");
        mapperScannerConfigurer.setAnnotationClass(Repository.class);
        return mapperScannerConfigurer;
    }

    @Bean("configurationCustomizerOauth")
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> {
            configuration.setLazyLoadingEnabled(true);
            configuration.setDefaultStatementTimeout(20000);
            configuration.setMapUnderscoreToCamelCase(true);
        };
    }


}
