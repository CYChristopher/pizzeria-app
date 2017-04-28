package fr.pizzeria.spring.web;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.StatutCommande;
import fr.pizzeria.spring.web.repository.IClientRepository;
import fr.pizzeria.spring.web.repository.IPizzaRepository;

public class DonneesClient {
	
	@Autowired
	private IClientRepository clientDao;
	
	@Autowired private IPizzaRepository pizzaDao;
	
	private List<Client> clients = new ArrayList<>();
	private List<Pizza> pizzas = new ArrayList<>();
	private List<Livreur> livreurs = new ArrayList<>();
	private List<Commande> commandes = new ArrayList<>();
	
	public DonneesClient(){
		generateClients();
		generatePizzas();
		generateLivreurs();
		generateCommandes();
	}
	
	private void generateClients(){
		clients.add(new Client("Nantes", "Gerard", "f.g@gmail.com", DigestUtils.sha256Hex("password147"), "Rue de la paix"));
		clients.add(new Client("Paris", "Marie", "m.p@gmail.com", DigestUtils.sha256Hex("password874"), "Champs Elysées"));
		clients.add(new Client("Rennes", "Jean", "j.r@gmail.com", DigestUtils.sha256Hex("password965"), "Au bord de la mer"));
		clients.add(new Client("Strasbourg", "Charlotte", "s.c@gmail.com", DigestUtils.sha256Hex("password365"), "A la frontière"));
		clients.add(new Client("Lille", "Jon", "l.j@gmail.com", DigestUtils.sha256Hex("password954"), "Au nord"));
	}
	
	private void generatePizzas(){
		pizzas.add(new Pizza(null, "PEP", "Pépéroni", BigDecimal.valueOf(12.40), CategoriePizza.VIANDE, "http://www.cicis.com/media/1139/pizza_trad_pepperoni_sm.png"));
		pizzas.add(new Pizza(null, "4FRO", "4 Fromages", BigDecimal.valueOf(14.70), CategoriePizza.SANS_VIANDE, "http://www.cicis.com/media/1177/pizza_trad_cheese_sm.png"));
		pizzas.add(new Pizza(null, "VEG", "Végétarienne", BigDecimal.valueOf(11.30), CategoriePizza.SANS_VIANDE, "http://www.cicis.com/media/1173/pizza_trad_veggie_sm.png"));
		pizzas.add(new Pizza(null, "SAUC", "Saucisses", BigDecimal.valueOf(10.50), CategoriePizza.VIANDE, "http://www.cicis.com/media/1169/pizza_trad_sausage_sm.png"));
		pizzas.add(new Pizza(null, "SAUM", "Saumon", BigDecimal.valueOf(10.50), CategoriePizza.POISSON, "https://www.dominos.fr/ManagedAssets/FR/product/PSSE/FR_PSSE_fr_hero_1146.png?v1112248177"));
		pizzas.add(new Pizza(null, "BEF", "Boeuf", BigDecimal.valueOf(13.20), CategoriePizza.VIANDE, "http://www.cicis.com/media/1170/pizza_trad_beef_sm.png"));
		pizzas.add(new Pizza(null, "POUL", "Poulet", BigDecimal.valueOf(13.20), CategoriePizza.VIANDE, "http://www.cicis.com/media/1336/buffalo-chicken_new72dpi.png"));
		pizzas.add(new Pizza(null, "KEB", "Kebab", BigDecimal.valueOf(16.80), CategoriePizza.VIANDE, "https://www.dominos.fr/ManagedAssets/FR/product/PSKE/FR_PSKE_fr_hero_997.png?v-1581416390"));
		pizzas.add(new Pizza(null, "REI", "Reine", BigDecimal.valueOf(16.80), CategoriePizza.VIANDE, "https://www.dominos.fr/ManagedAssets/FR/product/PREI/FR_PREI_fr_hero_541.png?v-1935795394"));
		pizzas.add(new Pizza(null, "ORI", "Orientale", BigDecimal.valueOf(16.80), CategoriePizza.VIANDE, "https://www.dominos.fr/ManagedAssets/FR/product/PORI/FR_PORI_fr_hero_541.png?v334099666"));
	}
	
	private void generateLivreurs(){
		livreurs.add(new Livreur("Scout","Boris"));
		livreurs.add(new Livreur("Roue","Michel"));
		livreurs.add(new Livreur("Terreau","Jean"));
	}
	
	private void generateCommandes(){
		Commande c1 = new Commande();
		c1.setNumeroCommande("HGS471");
		c1.setStatut(StatutCommande.EXPEDIE);
		c1.setDateCommande(LocalDateTime.of(2016, Month.AUGUST, 24, 18, 38));
		c1.setLivreur(livreurs.get(0));
		
	}
	
}
