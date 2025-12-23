package com.apigateway;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.apigateway.repository.BlacklistedTokenRepository;
import com.apigateway.repository.UserRepository;

@SpringBootTest
class MicroservicesApiGatewayApplicationTests {
	 @MockitoBean
	 private UserRepository userRepository;

    @MockitoBean
    private BlacklistedTokenRepository blacklistedTokenRepository;
	@Test
	void contextLoads() {
	}

}
