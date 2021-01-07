package com.voyageone.retail.gateway;

import com.voyageone.retail.common.auth.jwt.JwtUtils;
import com.voyageone.retail.common.auth.jwt.RSA512Utils;
import com.voyageone.retail.gateway.configuration.JwtConfiguration;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author carl.che
 * 认证全局过滤器
 */
@Slf4j
@Component
public class AuthenticateGlobalFilter implements GlobalFilter, Ordered {

    private static final String SPACER = " ";
    private static final String BEARER_SPACER = "Bearer ";
    private static final String AUTHORIZATION = "Authorization";

    private final JwtConfiguration jwtConfiguration;

    public AuthenticateGlobalFilter(JwtConfiguration jwtConfiguration) {
        this.jwtConfiguration = jwtConfiguration;
    }

    /**
     * 1. 获取请求头 Authorization 中的token
     * 2. 校验token
     * 3. 错误则抛出异常，通过则放行
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //获取gateway路由信息配置
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        ServerHttpRequest request = exchange.getRequest();
        RequestPath path = request.getPath();
        HttpHeaders requestHeaders = request.getHeaders();
        //获取token
        String token = parseToken(requestHeaders);
        log.info("开始过滤，routeId:{}, path:{},token:{}", route.getId(), path, token);
        //验证token是否正确
        String publicKey = jwtConfiguration.getPublicKey();
        PublicKey restorePublicKey = RSA512Utils.restorePublicKey(publicKey);
        JwtUtils.parserToken(token, restorePublicKey);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }


    /**
     * 解析 token
     * 格式：Authorization: Bearer <token>
     * return <token>
     */
    private String parseToken(HttpHeaders requestHeaders) throws RuntimeException{

        if(requestHeaders == null){
            throw new RuntimeException("httpHeaders 不能为空");
        }

        String tokenStr = requestHeaders.getFirst(AUTHORIZATION);
        if(StringUtils.isEmpty(tokenStr)){
            throw new RuntimeException("not found token");
        }

        if(!tokenStr.startsWith(BEARER_SPACER)){
            throw new RuntimeException("invalid token");
        }

        String[] splitArray = tokenStr.split(SPACER);
        if(splitArray.length < 2){
            throw new RuntimeException("invalid token");
        }
        return splitArray[1];
    }

    public static void main(String[] args) {

        String pubStr = "305c300d06092a864886f70d0101010500034b0030480241008667b4c9df4d132f5fb0108e3890d3d4f3dc637c98edc92ab1d41f11a7ac467385ad5c247e1fc447288db84b4cbb4682edfe3c448fb004ce2a7e1cf8e224941d0203010001";
        PublicKey publicKey = RSA512Utils.restorePublicKey(pubStr);

        String priStr = "30820154020100300d06092a864886f70d01010105000482013e3082013a0201000241008667b4c9df4d132f5fb0108e3890d3d4f3dc637c98edc92ab1d41f11a7ac467385ad5c247e1fc447288db84b4cbb4682edfe3c448fb004ce2a7e1cf8e224941d020301000102406cfdc22c8609418a84250977db450e92bce23646fed8acee1360b9851e9c644ff2bbecef719883204730ebe58ec94c5f772f4631cec2c58fea31be861d5b1f7d022100c11b45e9d34fa143b8305656913e683d45add54fc9a2f172c5fb2daa68b08b5b022100b22e0ef2ea5219ddefdd814b0b8ad77508ef566c7c76b11b20103a8b4a2d8fe7022059bf8e37c5711df73654a8dbe4f747452aec46d6a55528b87a116a40e8151d3f022059ddbd106b7a692d74b34568b2339ea39a940c04bf69a5bdc69828cee3e4c2f5022100ab660e1c3821f15ce14b20318c7b271ae5b809e52cb700f5cd3e3cd41aa5bff6";
        PrivateKey privateKey = RSA512Utils.restorePrivateKey(priStr);

        Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("head", "ping");
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("1","2");
        claimsMap.put("2","3");
        claimsMap.put("3","4");
        String token = JwtUtils.genToken(headerMap, claimsMap, new Date(System.currentTimeMillis() + 3600 * 1000), privateKey);
        System.out.println("token:" + token);
        Jws<Claims> claimsJws = JwtUtils.parserToken(token, publicKey);
        System.out.println(claimsJws.toString());


    }
}
