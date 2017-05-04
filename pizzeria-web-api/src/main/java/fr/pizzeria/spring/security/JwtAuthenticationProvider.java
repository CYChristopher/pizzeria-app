package fr.pizzeria.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import fr.pizzeria.model.Client;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {
	private final JwtService jwtService;

	@SuppressWarnings("unused")
	public JwtAuthenticationProvider() {
		this(null);
	}

	@Autowired
	public JwtAuthenticationProvider(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) {
		try {
			Client possibleProfile = jwtService.verify((String) authentication.getCredentials());
			return new JwtAuthenticatedClient(possibleProfile);
		} catch (Exception e) {
			throw new RuntimeException("Failed to verify token", e);
		}
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JwtAuthToken.class.equals(authentication);
	}
}