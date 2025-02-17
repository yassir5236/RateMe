package com.yassir.RateMe.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

@Component
public class JwtTokenProvider {

    //    private String secretKey ="";
    @Value("${jwt.secret}")
    private String secretKey;

    public JwtTokenProvider() throws NoSuchAlgorithmException {
        KeyGenerator generator = KeyGenerator.getInstance("HmacSHA256");
        SecretKey sk = generator.generateKey();
        secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());

    }

    public String generateToken(String username, String role) {
        Map<String, Objects> claims = new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .and()
                .signWith(getKey())
                .compact();
    }


//    private SecretKey getKey() {
//        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
//        return Keys.hmacShaKeyFor(keyBytes);
//    }

    private SecretKey getKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey.replace('_', '/').replace('-', '+'));
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        // extract the username from jwt token
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }




}









//
//package com.yassir.RateMe.Security;
//
//import com.yassir.RateMe.Model.Enum.Role;
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.function.Function;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.GrantedAuthority;
//
//@Component
//public class JwtTokenProvider {
//
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    public JwtTokenProvider() throws NoSuchAlgorithmException {
//        KeyGenerator generator = KeyGenerator.getInstance("HmacSHA256");
//        SecretKey sk = generator.generateKey();
//        secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
//    }
//
//    public String generateToken(String username, String role) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", role); // Add role to claims
//
//        return Jwts.builder()
//                .claims(claims) // Add claims
//                .subject(username) // Set subject
//                .issuedAt(new Date()) // Set issued at
//                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Set expiration
//                .signWith(getKey()) // Sign with the key
//                .compact(); // Compact the token
//    }
//
//    private SecretKey getKey() {
//        byte[] keyBytes = Base64.getDecoder().decode(secretKey.replace('_', '/').replace('-', '+'));
//        return Keys.hmacShaKeyFor(keyBytes);
//    }
//
//    public String extractUserName(String token) {
//        return extractClaim(token, Claims::getSubject);
//    }
//
//    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
//        final Claims claims = extractAllClaims(token);
//        return claimResolver.apply(claims);
//    }
//
//    private Claims extractAllClaims(String token) {
//        return Jwts.parser()
//                .verifyWith(getKey()) // Verify with the key
//                .build()
//                .parseSignedClaims(token) // Parse the signed claims
//                .getPayload(); // Get the payload
//    }
//
//    public boolean validateToken(String token, UserDetails userDetails) {
//        final String userName = extractUserName(token);
//        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
//    }
//
//    private boolean isTokenExpired(String token) {
//        return extractExpiration(token).before(new Date());
//    }
//
//    private Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
//    }
//
//    public String extractRole(String token) {
//        Claims claims = Jwts.parser()
//                .verifyWith(getKey()) // Verify with the key
//                .build()
//                .parseSignedClaims(token) // Parse the signed claims
//                .getPayload(); // Get the payload
//
//        return claims.get("role", String.class); // Extract the role
//    }
//
//}