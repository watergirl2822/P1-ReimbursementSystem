package com.revature.P1.services;

import com.revature.P1.utils.JwtConfig;

public class TokenService {

    private JwtConfig jwtConfig;

    public TokenService(){
        super();
    }
    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }
}
