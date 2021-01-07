package com.voyageone.retail.common.auth.jwt;


import io.jsonwebtoken.*;

import java.security.Key;
import java.security.KeyPair;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类
 *
 * @author carl.che
 */
public class JwtUtils {

    /**
     * 生成jwt token
     *
     * @param headerMap Header Map
     * @param claims    Claims
     * @return token
     */
    public static String genToken(Map<String, Object> headerMap,
                                  Map<String, Object> claims,
                                  Date expireDate,
                                  SignatureAlgorithm signatureAlgorithm,
                                  Key privateKey) throws RuntimeException {
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
                .signWith(signatureAlgorithm, privateKey)
                .compact();
    }

    public static String genToken(Map<String, Object> headerMap,
                                  Map<String, Object> claims,
                                  Date expireDate,
                                  Key privateKey) throws RuntimeException {
        return genToken(headerMap, claims, expireDate, SignatureAlgorithm.RS256, privateKey);
    }

    /**
     * 解析token
     *
     * @param token 需要解析的token
     * @return 解析后的body
     * @throws RuntimeException
     */
    public static Jws<Claims> parserToken(String token, Key publicKey) throws RuntimeException {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }


    public static void main(String[] args) {
        KeyPair keyPair = RSA512Utils.generateKeyBytes();

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("head", "ping");
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("1", "2");
        claimsMap.put("2", "3");
        claimsMap.put("3", "4");

        String token = JwtUtils.genToken(headerMap, claimsMap, new Date(System.currentTimeMillis() + 3600), keyPair.getPrivate());


        Jws<Claims> claimsJws = JwtUtils.parserToken(token, keyPair.getPublic());
        System.out.println(claimsJws.toString());


    }
}
