package fr.pizzeria.spring.web;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Ingredient;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.TypePizza;
import fr.pizzeria.spring.security.JwtAuthenticationProvider;
import fr.pizzeria.spring.security.JwtService;
import fr.pizzeria.spring.security.SecretKeyProvider;
import fr.pizzeria.spring.security.SecurityConfig;
import fr.pizzeria.spring.web.repository.IIngredientRepository;
import fr.pizzeria.spring.web.repository.IPizzaRepository;
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

	@Autowired
	private IPizzaRepository ipizza;
	@Autowired
	private IIngredientRepository iIngredient;

	@PostConstruct
	public void initApp() {
		List<Ingredient> listIngredient = new ArrayList<>();
		listIngredient.add(new Ingredient("Tomate", 20, 0.5));
		listIngredient.add(new Ingredient("Jambon", 21, 0.6));
		listIngredient.add(new Ingredient("Fromage", 22, 0.7));
		iIngredient.save(listIngredient);
		ipizza.save(new Pizza("PEP", "Peperroni", BigDecimal.valueOf(12), CategoriePizza.VIANDE,
				"https://www.tutti-pizza.com/images/350x270/produit/_/marguerita-jambon_marguerita-jambon.jpg",
				LocalDateTime.now(), true, TypePizza.PIZZA, listIngredient));
		ipizza.save(new Pizza("REI", "Reine", BigDecimal.valueOf(12), CategoriePizza.VIANDE,
				"https://www.tutti-pizza.com/images/350x270/produit/_/marguerita-jambon_marguerita-jambon.jpg",
				LocalDateTime.now(), true, TypePizza.PIZZA, listIngredient));
		ipizza.save(new Pizza("ROY", "Royal", BigDecimal.valueOf(12), CategoriePizza.SANS_VIANDE,
				"https://www.tutti-pizza.com/images/350x270/produit/_/marguerita-jambon_marguerita-jambon.jpg",
				LocalDateTime.now(), true, TypePizza.PIZZA, listIngredient));
		ipizza.save(new Pizza("4FRO", "4 Fromages", BigDecimal.valueOf(12), CategoriePizza.SANS_VIANDE,
				"https://www.tutti-pizza.com/images/350x270/produit/_/marguerita-jambon_marguerita-jambon.jpg",
				LocalDateTime.now(), false, TypePizza.PIZZA, listIngredient));
		ipizza.save(new Pizza("Lorr", "Lorraine", BigDecimal.valueOf(12), CategoriePizza.SANS_VIANDE,
				"https://www.tutti-pizza.com/images/350x270/produit/_/marguerita-jambon_marguerita-jambon.jpg",
				LocalDateTime.now(), true, TypePizza.OUICHE, listIngredient));
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
