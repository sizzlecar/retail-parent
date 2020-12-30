package com.voyageone.retail.gateway.utils;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "jwt-config")
@Component
@Data
public class JwtConfiguration {

    /**
     * 密钥
     */
    String key;
}
