package com.voyageone.retail.gateway;

import com.voyageone.retail.gateway.utils.JwtUtils;
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

    private final JwtUtils jwtUtils;


    public AuthenticateGlobalFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
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
        jwtUtils.parserToken(token);
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
}
