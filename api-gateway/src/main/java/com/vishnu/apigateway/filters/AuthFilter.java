//package com.vishnu.apigateway.filters;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.gateway.filter.GatewayFilterChain;
//import org.springframework.cloud.gateway.filter.GlobalFilter;
//import org.springframework.core.Ordered;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;
//import org.springframework.web.server.ServerWebExchange;
//import reactor.core.publisher.Mono;
//
//import static org.springframework.http.HttpMethod.GET;
//import static org.springframework.http.HttpMethod.POST;
//
//@Slf4j
//@Component
//@RequiredArgsConstructor
//public class AuthFilter implements GlobalFilter, Ordered {
//    private final RestTemplate restTemplate;
//    private final WebClient.Builder webClient;
//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        ServerHttpRequest request = exchange.getRequest();
//        log.info(request.getPath().toString()+"  log");
//        if(request.getPath().toString().equals("/users/login/")) {
//            log.info("logging in");
//            return chain.filter(exchange);
//        }
//        else{
////            ResponseEntity<Boolean> response = null;//restTemplate.getForEntity("http://user-authentication-service",Boolean.class);
////            if(response.getStatusCode().equals(HttpStatus.OK)){
////                return chain.filter(exchange);
////            }
////            else{
////                ServerHttpResponse response1 = exchange.getResponse();
////                response1.setStatusCode(HttpStatus.FORBIDDEN);
////                log.info("forbidden");
////                return chain.filter(exchange);
////            }
//            log.info("inside else");
//
//            return WebClient.create("http://user-authentication-service/users/authenticate")
//                    .method(HttpMethod.GET)
//                    .retrieve()
//                    .bodyToMono(Boolean.class)
//                    .map(s -> {
//                        System.out.println(s);
//                        return exchange;
//                    })
//                    .flatMap(chain::filter);
//        }
//
//    }
//
//    @Override
//    public int getOrder() {
//        return -1;
//    }
//}
