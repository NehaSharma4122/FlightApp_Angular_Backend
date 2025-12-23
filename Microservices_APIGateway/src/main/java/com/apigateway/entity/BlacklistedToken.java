package com.apigateway.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "blacklisted_tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlacklistedToken {

    @Id
    private String id;

    private String token;
    private Date expiryDate;
}
