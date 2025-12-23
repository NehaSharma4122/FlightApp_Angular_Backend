package com.apigateway.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.apigateway.entity.BlacklistedToken;

import reactor.core.publisher.Mono;

public interface BlacklistedTokenRepository
        extends ReactiveMongoRepository<BlacklistedToken, String> {

    Mono<Boolean> existsByToken(String token);
}
