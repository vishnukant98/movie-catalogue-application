//package com.vishnu.apigateway.filters;
//
//import com.vishnu.apigateway.dto.UserDto;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.gateway.filter.GatewayFilter;
//import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
//import org.springframework.http.HttpHeaders;
//import org.springframework.stereotype.Component;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;
//
//import javax.naming.AuthenticationException;
//
//@Component
//@Slf4j
//public class CustomAuthenticationFilter extends AbstractGatewayFilterFactory<CustomAuthenticationFilter.Config> {
//
//    @Autowired
//    private WebClient.Builder webclient;
//    public CustomAuthenticationFilter(){
//        super(Config.class);
//    }
//    @Override
//    public GatewayFilter apply(CustomAuthenticationFilter.Config config) {
//        return (exchange, chain) -> {
//            if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)){
//                    throw new RuntimeException("Missing Authorization Details");
//            }
//            String authorizationHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
//            log.info("token  "+authorizationHeader);
//            String[] parts = authorizationHeader.split(" ");
//            if(parts.length!=2 && !parts[0].equals("Bearer")){
//                throw new RuntimeException("Incorrect Autherization Structure");
//            }
//
//            return webclient.build().post()
//                    .uri("http://user-authentication-service/users/authenticate?token="+parts[1])
//                    .retrieve().bodyToMono(UserDto.class).
//                    map(userDto -> {
//                        exchange.getRequest().mutate().header("X-auth-user-name",String.valueOf(userDto.getUsername()));
//                        return exchange;
//                    }).flatMap(chain::filter);
//
//
//        };
//    }
//
//
//    public static class Config{
//
//    }
//}
//
