package com.example.cryptousersjwt.Responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LogOutResponse {
    private Boolean isLoggedOut;
    private String message;

    public LogOutResponse(Boolean isLoggedOut, String message) {
        this.isLoggedOut = isLoggedOut;
        this.message = message;
    }
}
