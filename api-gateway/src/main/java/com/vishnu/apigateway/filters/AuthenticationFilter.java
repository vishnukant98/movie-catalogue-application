package com.vishnu.apigateway.filters;

import com.vishnu.apigateway.route.RouteValidator;
import com.vishnu.apigateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.netty.http.server.HttpServerRequest;
@Slf4j
@RefreshScope
@Component
public class AuthenticationFilter implements GatewayFilter {
    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("inside filter");
        ServerHttpRequest request = exchange.getRequest();
        log.info("step1");
        if(routeValidator.isSecured.test(request)){
            if(this.isMissingAuth(request))
                return this.onError(exchange,"Authorization header is missing in request",HttpStatus.UNAUTHORIZED);
            String token = this.getAuthHeader(request);
            if(jwtUtil.isTokenExpired(token))
                return this.onError(exchange,"INVALID TOKEN",HttpStatus.UNAUTHORIZED);

            this.populateRequestWithHeader(exchange,token);


        }
        return chain.filter(exchange);
    }

    private void populateRequestWithHeader(ServerWebExchange exchange,String token){
        exchange.getRequest().mutate().header("user_id",jwtUtil.extractClaim(token, Claims::getId)).build();

    }

    private String getAuthHeader(ServerHttpRequest request){
        return request.getHeaders().getOrEmpty("AUTHENTICATION").get(0);
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus status){
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.setComplete();

    }
    private boolean isMissingAuth(ServerHttpRequest request){
        return request.getHeaders().containsKey("AUTHENTICATION");
    }
}
