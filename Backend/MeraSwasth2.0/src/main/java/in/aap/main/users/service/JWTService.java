package in.aap.main.users.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JWTService {

	String encodedkey;
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	@PostConstruct
	public void init() {
		try {
			KeyGenerator generator = KeyGenerator.getInstance("HmacSha256");
			SecretKey sk = generator.generateKey();
			encodedkey = Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String encodePasword(String password) {
		return encoder.encode(password);
	}
	
	public boolean matchPassword(String pass, String raw) {
		return encoder.matches(pass, raw);
	}
	
	public SecretKey retrieveKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(encodedkey));
	}
	
	
	public String tokengen(String username) {
		Map<String, Object> claims = new HashMap<String, Object>();
		return Jwts.builder()
				.claims()
				.add(claims)
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.and()
				.signWith(retrieveKey())
				.compact();
	}
	
	public Claims extractAllClaims(String token) {
		return Jwts.parser()
				.verifyWith(retrieveKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	private <T> T extractClaims(String token, Function<Claims, T> resolver) {
		Claims claim = extractAllClaims(token);
		return resolver.apply(claim);
	}
	
	public String extractUsername(String token) {
		return extractClaims(token, Claims::getSubject);
	}
	
	public boolean validate(String token,UserDetails details) {
		String email = extractUsername(token);
		return details.getUsername().equals(email);
	}
}
