package com.revature.P1.services;

import com.revature.P1.dtos.response.Principal;
import com.revature.P1.utils.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.util.Date;

public class TokenService {

    private JwtConfig jwtConfig;

    public TokenService(){
        super();
    }
    public TokenService(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
    }

    public String generateToken(Principal subject) {
        long now = System.currentTimeMillis();
        JwtBuilder tokenBuilder = Jwts.builder()
                .setId(subject.getUser_id())
                .setIssuer("P1")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtConfig.getExpiration()))
                .setSubject(subject.getUsername())
                .claim("Admin", subject.getRole_id())
                .signWith(jwtConfig.getSigAlg(), jwtConfig.getSigningKey());
        //compact builds the JWT and serializes it to a compact, URL safe string.
        return tokenBuilder.compact();
    }

    public Principal extractRequesterDetails(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSigningKey())
                    .parseClaimsJws(token)
                    .getBody();
            //Claims class holds the value in a generic way so that it can be recovered in many representations

            return new Principal(claims.getId(), claims.getSubject(), claims.get("Admin", String.class));
        } catch (Exception e) {
            return null;
        }
    }
}
