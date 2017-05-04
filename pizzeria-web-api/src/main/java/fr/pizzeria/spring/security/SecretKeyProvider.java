package fr.pizzeria.spring.security;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.stereotype.Component;

@Component
public class SecretKeyProvider {
	public byte[] getKey() throws URISyntaxException, IOException {
		return System.getProperties().getProperty("JWT_KEY").getBytes();
	}
}