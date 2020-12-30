package com.voyageone.retail.gateway.utils;


import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * jwt utils
 *
 * @author calr.che
 */
@Service
@Slf4j
public class JwtUtils {

    private final JwtConfiguration jwtConfiguration;

    public JwtUtils(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    /**
     * 生成jwt token
     *
     * @param headerMap Header Map
     * @param claims    Claims
     * @return token
     */
    public String genToken(Map<String, Object> headerMap,
                           Map<String, Object> claims,
                           Date expireDate,
                           SignatureAlgorithm signatureAlgorithm) throws RuntimeException {
        if (headerMap == null || headerMap.isEmpty()
                || claims == null || claims.isEmpty()
                || signatureAlgorithm == null) {
            throw new RuntimeException("缺少必填参数");
        }

        JwtBuilder jwtBuilder = Jwts.builder();
        if (expireDate != null) {
            //设置过期时间
            jwtBuilder.setExpiration(expireDate);
        }
        return jwtBuilder.setHeader(headerMap)
                .addClaims(claims)
                .signWith(signatureAlgorithm, jwtConfiguration.getKey().getBytes())
                .compact();
    }

    /**
     * 解析token
     * @param token  需要解析的token
     * @return 解析后的body
     * @throws RuntimeException
     */
    public Jws<Claims> parserToken(String token) throws RuntimeException {
        return Jwts.parser().setSigningKey(jwtConfiguration.getKey().getBytes()).parseClaimsJws(token);
    }



}
