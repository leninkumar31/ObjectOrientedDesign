package com.example.todo.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.todo.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5881269948357095153L;

	private static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

	@Value("${jwt.secret}")
	private String secret;

	public boolean validateToken(String token, User user) {
		Optional<String> userName = getUserNameFromToken(token);
		if (userName.isEmpty()) {
			return false;
		}
		String name = userName.get();
		return user.getUserName() != null && name.equals(user.getUserName()) && !isTokenExpired(token);
	}

	public String generateToken(User user) {
		Map<String, Object> claims = new HashMap<>();
		long currTime = System.currentTimeMillis();
		return Jwts.builder().setClaims(claims).setSubject(user.getUserName()).setIssuedAt(new Date(currTime))
				.setExpiration(new Date(currTime + JWT_TOKEN_VALIDITY * 1000)).signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	private boolean isTokenExpired(String token) {
		Optional<Date> date = getExpirationDatFromToken(token);
		if (date.isEmpty()) {
			return true;
		}
		Date expirationDate = date.get();
		return expirationDate.before(new Date());
	}

	public Optional<String> getUserNameFromToken(String token) {
		return Optional.ofNullable(getClaimsFromToken(token, Claims::getSubject));
	}

	private Optional<Date> getExpirationDatFromToken(String token) {
		return Optional.ofNullable(getClaimsFromToken(token, Claims::getExpiration));
	}

	private <T> T getClaimsFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

}
