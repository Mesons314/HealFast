package com.HealQueue.Auth.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

@Service
public class jwtService {

    private final String secretKey;
    public jwtService() {
        KeyGenerator keyGen = null;
        try {
           keyGen =  KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    public String generateToken(String userName){
        Map<String,Object> claims = new HashMap<>();
        return Jwts.builder()
                .claims()
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ 1000*60*60))
                .and()
                .signWith(getKey())
                .compact();
    }
    private SecretKey getKey(){
        byte [] KeyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(KeyBytes);
    }
    public String extractUserName(String token){
        return extractClaims(token, Claims::getSubject);
    }

    public String extractRoles(String token){
        return extractClaims(token,claims -> claims.get("roles", String.class));
    }

    private <T> T extractClaims(String token , Function<Claims,T> claimResolver){
       final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Date extractExpiration(String token){
        return extractClaims(token,Claims::getExpiration);
    }
}
