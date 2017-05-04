package fr.pizzeria.spring.security;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.pizzeria.model.Client;
import fr.pizzeria.spring.web.repository.IClientRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtService {
	private static final String ISSUER = "bernard";
	private SecretKeyProvider secretKeyProvider;

	@Autowired
	private IClientRepository clientDao;

	@SuppressWarnings("unused")
	public JwtService() {
		this(null);
	}

	@Autowired
	public JwtService(SecretKeyProvider secretKeyProvider) {
		this.secretKeyProvider = secretKeyProvider;
	}

	public String tokenFor(Client client) throws IOException, URISyntaxException {
		byte[] secretKey = secretKeyProvider.getKey();
		Date expiration = Date.from(LocalDateTime.now().toInstant(ZoneOffset.UTC));
		return Jwts.builder().setSubject(client.getId().toString()).setExpiration(expiration).setIssuer(ISSUER)
				.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}

	public Client verify(String token) throws IOException, URISyntaxException {
		byte[] secretKey = secretKeyProvider.getKey();
		Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

		String toto = claims.getBody().getSubject().toString();

		return clientDao.findById(Integer.parseInt(claims.getBody().getSubject().toString()));
	}

}