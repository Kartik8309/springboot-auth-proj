package com.example.cryptousersjwt.Responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class signUpResponse {
    private Boolean isSignedUp;
    private String message;
}
