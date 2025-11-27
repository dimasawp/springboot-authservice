package com.example.authservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token;
    
    @Builder.Default
    private String tokenType = "Bearer";
}
