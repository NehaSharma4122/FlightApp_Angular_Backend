package com.apigateway.filter;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpStatus;

import org.springframework.cloud.gateway.filter.*;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.apigateway.jwt.JWTUtils;
import com.apigateway.repository.BlacklistedTokenRepository;

@Component
@RequiredArgsConstructor
public class JWTAuthFilter extends AbstractGatewayFilterFactory<Object> {

    private final JWTUtils jwtUtils;
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    @Override
    public GatewayFilter apply(Object config) {

        return (exchange, chain) -> {

            String authHeader = exchange.getRequest()
                    .getHeaders()
                    .getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return unauthorized(exchange);
            }

            String token = authHeader.substring(7);

            if (!jwtUtils.validate(token)) {
                return unauthorized(exchange);
            }
            System.out.println("JWT FILTER HIT");
            System.out.println("Authorization header = " +
                    exchange.getRequest().getHeaders().getFirst("Authorization"));


            return blacklistedTokenRepository.existsByToken(token)
                    .flatMap(isBlacklisted -> {
                        if (isBlacklisted) {
                            return unauthorized(exchange);
                        }
                        return chain.filter(exchange);
                    });
        };
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();
    }
}
