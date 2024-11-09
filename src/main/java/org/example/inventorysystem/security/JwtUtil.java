package org.example.inventorysystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.inventorysystem.models.Person;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

	private final SecretKey jwtSecret = Keys.secretKeyFor(SignatureAlgorithm.HS512);
	private final int jwtExpirationMs = 86400000;

	public String generateToken(Authentication authentication) {
		Person user = (Person) authentication.getPrincipal();
		String role =
				authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).findFirst().orElse("ROLE_USER");
		return Jwts.builder()
				.setSubject(authentication.getName())
				.claim("role", role)
				.claim("id", user.getId())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(jwtSecret)
				.compact();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser()
					.setSigningKey(jwtSecret)
					.build()
					.parseSignedClaims(token)
					.getPayload();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getUsernameFromToken(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(jwtSecret)
				.build()
				.parseSignedClaims(token)
				.getPayload();
		return claims.getSubject();
	}
}
