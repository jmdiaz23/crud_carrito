/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrito.config;

import com.carrito.Model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


/**
 *
 * @author FABIO
 */
@Component
public class JwtUtil {

    private String secret = "appcarritoisaac"; // Reemplaza con una clave secreta segura

    // Genera el token JWT
    public String generateToken(Usuario usuario) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, usuario.getEmail());
    }

    // Crea el token con una firma y fecha de expiración
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 horas de expiración
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Extrae el email del token
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Extrae los claims del token
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Valida el token
//    public Boolean validateToken(String token, Usuario usuario) {
//        final String email = extractEmail(token);
//        return (email.equals(usuario.getEmail()) && !isTokenExpired(token));
//    }
    
    public Boolean validateToken(String token, UserDetails userDetails) {
    final String email = extractEmail(token);
    return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
}
    

    // Verifica si el token ha expirado
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Extrae la fecha de expiración del token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Extrae todos los claims (información) del token
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
