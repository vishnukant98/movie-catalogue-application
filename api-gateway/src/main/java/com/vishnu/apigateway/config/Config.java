package com.vishnu.apigateway.config;

import com.vishnu.apigateway.filters.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Autowired
    AuthenticationFilter authenticationFilter;

    @Bean
    public RouteLocator route(RouteLocatorBuilder routeLocatorBuilder){
        //System.out.println("inside configuration");
        return routeLocatorBuilder.routes()
                .route("user-authentication-service",r->r.path("/v1/users/**")
                .filters(f-> f.filter(authenticationFilter))
                        .uri("lb://user-authentication-service"))
                .route("movie-info-service",r->r.path("/v1/movieInfo/**")
                        .filters(f->f.filter(authenticationFilter))
                        .uri("lb://movie-info-service"))
                        .build();
    }
}
