package dev.rdziuba.authapi.security;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import dev.rdziuba.authapi.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

public class TokenUtil {

	public static final String EMISSOR = "Rdziuba";
	public static final long EXPIRATION =  30 * 1000;
	public static final String SECRET_KEY = "0123456789012345678901234567890123456789";

	public static MyToken encode(User user) {
		try {
			Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
			String jwtToken = Jwts.builder().subject(user.getUsername())
					.expiration(new Date(System.currentTimeMillis() + EXPIRATION)).issuer(EMISSOR).signWith(key)
					.compact();
			return new MyToken(jwtToken);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static Authentication decode(HttpServletRequest request) {
		try {
			String header = request.getHeader("Authorization");
			System.out.println("Request header: " + header);
			if (header == null) return null;

			String token = header.replace("Bearer ", "");
			SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
			JwtParser parser = Jwts.parser().verifyWith(key).build();
			Claims claims = (Claims)parser.parse(token).getPayload();

			String subject = claims.getSubject();
			String issuer = claims.getIssuer();
			Date   exp    = claims.getExpiration();

			if (issuer.equals(EMISSOR) && subject.length() > 0 && exp.after(new Date(System.currentTimeMillis())))
			   return new UsernamePasswordAuthenticationToken("valido", null, Collections.emptyList());
		} catch (Exception ex) {
			ex.printStackTrace();
			if (ex instanceof ExpiredJwtException) throw ex;
		}
		return null;
	}
}
