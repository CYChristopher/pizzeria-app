
package fr.pizzeria.spring.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import fr.pizzeria.spring.security.JwtAuthenticationProvider;
import fr.pizzeria.spring.security.JwtService;
import fr.pizzeria.spring.security.SecretKeyProvider;
import fr.pizzeria.spring.security.SecurityConfig;
import fr.pizzeria.spring.web.resource.ClientRessource;

/**
 * Application PizzeriaApp démarré via Spring Boot.
 */
@SpringBootApplication
@EnableJpaRepositories("fr.pizzeria.spring.web.repository")
@EntityScan({ "fr.pizzeria.model" })
@Import({ SecurityConfig.class, JwtService.class, ClientRessource.class, SecretKeyProvider.class,
		JwtAuthenticationProvider.class })
public class PizzeriaApp {

	/**
	 * Activation de CORS pour tous les domaines.
	 * 
	 * @return Configurateur Spring MVC.
	 */
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").exposedHeaders("Token").allowedMethods(
						HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.POST.name(), HttpMethod.GET.name());
			}
		};
	}

	/**
	 * Démarrage de l'application Web.
	 *
	 * @param args
	 *            argument du programme
	 */
	public static void main(String[] args) {

		SpringApplication.run(PizzeriaApp.class);
	}
}