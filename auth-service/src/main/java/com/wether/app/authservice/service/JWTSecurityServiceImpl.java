package com.wether.app.authservice.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.wether.app.authservice.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTSecurityServiceImpl implements SecurityService{
   
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
    }
    @Override
    public Map<String, String> getAuthToken(User user) {
        String token = null;
        token = Jwts.builder()
            .setSubject(user.getUserName())
            .claim("userId", user.getId())
            .claim("watchList", user.getWatchList())
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS256, "secretkey")
            .compact();
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("message", "token generated");
        tokenMap.put("token", token);
        tokenMap.put("status", HttpStatus.OK.toString());
        return tokenMap;
    }

    @Override
    public boolean validateToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey("secretkey")
            .parseClaimsJws(token)
            .getBody();
        if(claims != null){
            return true;
        }
        return false;
    }
    
}
