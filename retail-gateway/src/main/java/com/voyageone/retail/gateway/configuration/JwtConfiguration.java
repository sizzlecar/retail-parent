package com.voyageone.retail.gateway.configuration;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * jwt 相关配置
 */
@ConfigurationProperties(prefix = "jwt-config")
@Component
@Data
public class JwtConfiguration {

    /**
     * 验证jwt是否合法公钥
     */
    private String publicKey;
}
