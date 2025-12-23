package com.apigateway.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignoutResponse {
    private String email;
    private String message;
}