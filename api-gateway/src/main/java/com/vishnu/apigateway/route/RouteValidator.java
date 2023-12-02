package com.vishnu.apigateway.route;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    private static final List<String> routes = List.of("/v1/users/login");
    public static Predicate<ServerHttpRequest> isSecured =
            request -> routes.stream().noneMatch(url -> request.getURI().getPath().contains(url));
}
