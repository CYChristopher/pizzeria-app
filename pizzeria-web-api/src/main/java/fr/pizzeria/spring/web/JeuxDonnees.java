package fr.pizzeria.spring.web;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;

import fr.pizzeria.model.Boisson;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.Dessert;
import fr.pizzeria.model.Ingredient;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.StatutCommande;
import fr.pizzeria.model.TypePizza;
import fr.pizzeria.spring.web.repository.IBoissonRepository;
import fr.pizzeria.spring.web.repository.IClientRepository;
import fr.pizzeria.spring.web.repository.ICommandeRepository;
import fr.pizzeria.spring.web.repository.IDessertReporitory;
import fr.pizzeria.spring.web.repository.IIngredientRepository;
import fr.pizzeria.spring.web.repository.ILivreurReposiroty;
import fr.pizzeria.spring.web.repository.IPizzaRepository;

public class JeuxDonnees {
	
	@Autowired
	private IClientRepository clientDao;
	
	@Autowired
	private IPizzaRepository pizzaDao;
	
	@Autowired
	private IBoissonRepository boissonDao;
	
	@Autowired
	private IIngredientRepository ingredientDao;
	
	@Autowired
	private IDessertReporitory dessertDao;
	
	@Autowired
	private ILivreurReposiroty livreurDao;
	
	@Autowired
	private ICommandeRepository commandeDao;

	
	private List<Client> clients = new ArrayList<>();
	private List<Boisson> boissons = new ArrayList<>();
	private List<Ingredient> ingredients = new ArrayList<>();
	private List<Dessert> desserts = new ArrayList<>();
	private List<Pizza> pizzas = new ArrayList<>();
	private List<Livreur> livreurs = new ArrayList<>();
	private List<Commande> commandes = new ArrayList<>();
	
	@PostConstruct
	public void init(){
		generateClients();
		generateBoissons();
		generateIngredients();
		generateDesserts();
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
		clientDao.save(clients);
	}
	
	private void generateBoissons(){
		boissons.add(new Boisson("FAN", "Fanta Orange", Double.valueOf(9.0), "http://www.mazalv.com/wp-content/uploads/2016/11/fanta-1.png", false));
		boissons.add(new Boisson("COCA", "Coca-Pepsi", Double.valueOf(2.5), "http://www.boulangerie-laboulange.fr/1-thickbox_default/boisson-coca-cola.jpg", false));
		boissons.add(new Boisson("PIM", "Pimento - gingembre", Double.valueOf(8.30), "https://img3.bibamagazine.fr/var/bibamagazine/storage/images/style-de-vie/pimento-la-nouvelle-boisson-au-gingembre-et-au-piment-qui-debarque-chez-monopr-56518/426552-1-fre-FR/Pimento-la-nouvelle-boisson-au-gingembre-et-au-piment-qui-debarque-chez-Monoprix_width1024.jpg", false));
		boissons.add(new Boisson("ACT", "Actimel ++", Double.valueOf(0.50), "http://www.avisdemamans.com/images/produit/actimelmodif.jpg", false));
		boissonDao.save(boissons);
	}

	private void generateIngredients(){
		ingredients.add(new Ingredient("Totomate", 100, 1.5));
		ingredients.add(new Ingredient("Maurizzella", 50, 3.5));
		ingredients.add(new Ingredient("Eclipserie", 250, 1.0));
		ingredients.add(new Ingredient("Chauve-sourizo", 50, 1.5));
		ingredients.add(new Ingredient("Anti-ananas", 150, 2.0));
		ingredientDao.save(ingredients);
	}
	
	private void generateDesserts(){
		desserts.add(new Dessert("CUP", "Cupcake", BigDecimal.valueOf(3.20), "http://static.750g.com/images/auto-427/669506a50d29be25f628fb3baa2eb043/cupcake-tout-chocolat.jpg", false));
		desserts.add(new Dessert("FLA", "Flan", BigDecimal.valueOf(1.20), "http://regimea.com/wp-content/uploads/2014/10/Le-flan-fait-il-grossir-ou-maigrir--702x336.jpg", false));
		desserts.add(new Dessert("MAC", "Macaron", BigDecimal.valueOf(8.40), "http://cloudfront.danasbakery.com/media/catalog/product/cache/1/small_image/600x420/9df78eab33525d08d6e5fb8d27136e95/0/1/01-redvel.jpg", false));
		desserts.add(new Dessert("POM", "Pom'potes", BigDecimal.valueOf(0.20), "http://sourdoreille.net/2016/wp-content/uploads/2013/06/pompote.jpg", false));
		dessertDao.save(desserts);
	}
	
	
	private void generatePizzas(){
		pizzas.add(new Pizza("PEP", "Peperoni", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE,
				"https://www.tutti-pizza.com/images/350x270/produit/_/marguerita-jambon_marguerita-jambon.jpg", LocalDateTime.now(), true, TypePizza.PIZZA,  ingredients));
		pizzas.add(new Pizza("4FRO", "4 Fromages", BigDecimal.valueOf(14.70), CategoriePizza.SANS_VIANDE,
				"http://www.cicis.com/media/1177/pizza_trad_cheese_sm.png", LocalDateTime.now(), true, TypePizza.OUICHE,  ingredients));
		pizzas.add(new Pizza("VEG", "Végétarienne", BigDecimal.valueOf(11.30), CategoriePizza.SANS_VIANDE,
				"http://www.cicis.com/media/1173/pizza_trad_veggie_sm.png", LocalDateTime.now(), true, TypePizza.PIZZA,  ingredients));
		pizzas.add(new Pizza("SAUC", "Saucisses", BigDecimal.valueOf(10.50), CategoriePizza.VIANDE,
				"http://www.cicis.com/media/1169/pizza_trad_sausage_sm.png", LocalDateTime.now(), true, TypePizza.OUICHE,  ingredients));
		pizzas.add(new Pizza("SAUM", "Saumon", BigDecimal.valueOf(10.50), CategoriePizza.POISSON,
				"https://www.dominos.fr/ManagedAssets/FR/product/PSSE/FR_PSSE_fr_hero_1146.png?v1112248177", LocalDateTime.now(), false, TypePizza.PIZZA,  ingredients));
		pizzas.add(new Pizza("BEF", "Boeuf", BigDecimal.valueOf(13.20), CategoriePizza.VIANDE,
				"http://www.cicis.com/media/1170/pizza_trad_beef_sm.png", LocalDateTime.now(), true, TypePizza.PIZZA,  ingredients));
		pizzas.add(new Pizza("POUL", "Poulet", BigDecimal.valueOf(13.20), CategoriePizza.VIANDE,
				"http://www.cicis.com/media/1336/buffalo-chicken_new72dpi.png", LocalDateTime.now(), false, TypePizza.PIZZA,  ingredients));
		pizzas.add(new Pizza("KEB", "Kebab", BigDecimal.valueOf(16.80), CategoriePizza.VIANDE,
				"https://www.dominos.fr/ManagedAssets/FR/product/PSKE/FR_PSKE_fr_hero_997.png?v-1581416390", LocalDateTime.now(), true, TypePizza.PIZZA,  ingredients));
		pizzas.add(new Pizza("REI", "Reine", BigDecimal.valueOf(16.80), CategoriePizza.VIANDE,
				"https://www.dominos.fr/ManagedAssets/FR/product/PREI/FR_PREI_fr_hero_541.png?v-1935795394", LocalDateTime.now(), false, TypePizza.PIZZA,  ingredients));
		pizzas.add(new Pizza("ORI", "Orientale", BigDecimal.valueOf(16.80), CategoriePizza.VIANDE,
				"https://www.dominos.fr/ManagedAssets/FR/product/PORI/FR_PORI_fr_hero_541.png?v334099666", LocalDateTime.now(), true, TypePizza.PIZZA,  ingredients));
		
		pizzaDao.save(pizzas);
		pizzaDao.flush();
	}
	
	private void generateLivreurs(){
		Livreur livreur1 = new Livreur();
		livreur1.setNom("Scout");
		livreur1.setPrenom("Boris");
		livreurs.add(livreur1);
		Livreur livreur2 = new Livreur();
		livreur2.setNom("Roue");
		livreur2.setPrenom("Michel");
		livreurs.add(livreur2);
		Livreur livreur3 = new Livreur();
		livreur3.setNom("Terreau");
		livreur3.setPrenom("Jean");
		livreurs.add(livreur3);
		livreurDao.save(livreurs);
	}
	
	private void generateCommandes(){
		Commande c1 = new Commande();
		c1.setNumeroCommande("HGS471");
		c1.setStatut(StatutCommande.EXPEDIE);
		c1.setDateCommande(LocalDateTime.of(2016, Month.AUGUST, 24, 18, 38));
		c1.setLivreur(livreurs.get(0));
		commandes.add(c1);
		
		Commande c2 = new Commande();
		c2.setNumeroCommande("KLP456");
		c2.setStatut(StatutCommande.EN_PREPARATION);
		c2.setDateCommande(LocalDateTime.of(2016, Month.AUGUST, 24, 18, 38));
		c2.setLivreur(livreurs.get(1));
		commandes.add(c2);
		
		Commande c3 = new Commande();
		c3.setNumeroCommande("JTS469");
		c3.setStatut(StatutCommande.NON_TRAITE);
		c3.setDateCommande(LocalDateTime.now());
		c3.setLivreur(livreurs.get(3));
		commandes.add(c3);
		
		commandeDao.save(commandes);
	}
}
