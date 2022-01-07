package com.example.cryptousersjwt.Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class JWTResponse {

    private String type="Bearer";
    private Long Id;
    private String username;
    private String email;
    private String message;
    private Boolean isSuccess;

    public JWTResponse(Long id, String username, String email, String message, Boolean isSuccess) {
        Id = id;
        this.username = username;
        this.email = email;
        this.message = message;
        this.isSuccess = isSuccess;
    }

    public JWTResponse(Long id, String username, String email) {
        this.Id = id;
        this.username=username;
        this.email=email;
    }
}
