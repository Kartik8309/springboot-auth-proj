package com.example.cryptousersjwt.Requests;

import com.example.cryptousersjwt.Entity.Cryptos;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Set;

@Data
@Getter
@Setter
public class SignUpRequest {
    private String username;
    private String email;
    private Set<String> role;
    private String password;
    private Collection<Cryptos> cryptosCollection;

}
