package fr.pizzeria.admin.web.listener;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import fr.pizzeria.admin.metier.BoissonService;
import fr.pizzeria.admin.metier.ClientService;
import fr.pizzeria.admin.metier.CommandeService;
import fr.pizzeria.admin.metier.DessertService;
import fr.pizzeria.admin.metier.IngredientService;
import fr.pizzeria.admin.metier.LivreurService;
import fr.pizzeria.admin.metier.OuicheService;
import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.admin.metier.UtilisateursService;
import fr.pizzeria.model.Boisson;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.CommandeComplete;
import fr.pizzeria.model.CommandePizza;
import fr.pizzeria.model.Dessert;
import fr.pizzeria.model.Ingredient;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.StatutCommande;
import fr.pizzeria.model.TypePizza;
import fr.pizzeria.model.Utilisateur;

@WebListener
public class DonneesListener implements ServletContextListener {

	@Inject
	private UtilisateursService utilisateurService;
	@Inject
	private ClientService clientService;
	@Inject
	private LivreurService livreurService;
	@Inject
	private BoissonService boissonService;
	@Inject
	private DessertService dessertService;
	@Inject
	private CommandeService commandeService ;
	@Inject
	private IngredientService is;
	@Inject
	private PizzaService ps;
	@Inject
	private OuicheService os;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		/* Utilisateur */
		Utilisateur utilisateur1 = new Utilisateur("A", "Nicolas", "a@orange.fr", "admin", "Ingenierie",
						LocalDateTime.of(2017, 4, 16, 16, 26, 42));
		this.utilisateurService.saveNew(utilisateur1);
		this.utilisateurService.saveNew(new Utilisateur("Admin", "Jean-Pierre", "a@a", "a", "Nantes", LocalDateTime.now()));

		/* Ingrédient */

		this.is.save(new Ingredient("Totomate", 100, 1.5, false));
		this.is.save(new Ingredient("Maurizzella", 50, 3.5, false));
		this.is.save(new Ingredient("Eclipserie", 250, 1.0, false));
		this.is.save(new Ingredient("Chauve-sourizo", 50, 1.5, false));
		this.is.save(new Ingredient("Anti-ananas", 150, 2.0, false));

		/* Pizza */
		this.ps.save(new Pizza("PEP", "Peperoni", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE, "http://www.cicis.com/media/1243/pizza_adven_zestypepperoni.png", 
						LocalDateTime.now(), false, TypePizza.PIZZA, this.is.findAll()));
		this.ps.save(new Pizza("REI", "Reine", BigDecimal.valueOf(11.00), CategoriePizza.VIANDE, "http://www.macommune.info/sites/v2.macommune.info/files/imagecache/grid_36_ovl/pizza_reine.jpeg", 
						LocalDateTime.now(), false, TypePizza.PIZZA, this.is.findAll()));
		this.os.save(new Pizza("4FR", "4 Fromages", BigDecimal.valueOf(12.00), CategoriePizza.SANS_VIANDE, "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTecVgX76oYqXu4n6humUi2Wo6RtI3APmC3mVhrsaYeekU5rpMHMA",
						LocalDateTime.now(), false, TypePizza.OUICHE, this.is.findAll()));

		/* Client */
		Client client1 = new Client("A", "Nicolas", "a@orange.fr", "aaaaaa", "DTA Ingenierie");
		Client client2 = new Client("G", "Valentin", "totot@gmail.com", "123soleil", "Quelque part");
		this.clientService.save(client1);
		this.clientService.save(client2);

		/* Livreur */
		Livreur livreur1 = new Livreur();
		livreur1.setNom("Martin");
		livreur1.setPrenom("Julien");
		this.livreurService.save(livreur1);

		/* Boisson */
		Boisson boisson1 = new Boisson("PEP", "Pepsi", 1.5, "http://icons.iconarchive.com/icons/michael/coke-pepsi/128/Pepsi-Can-icon.png", false);
		this.boissonService.saveNew(boisson1);

		/* Dessert */
		Dessert dessert1 = new Dessert("COO", "Cookie", new BigDecimal(2), "http://1.bp.blogspot.com/-1Yrw5R9auMI/Vjui-JcFpOI/AAAAAAAAkms/ovJCNgnt4iI/s1600/Capture%2Bd%25E2%2580%2599e%25CC%2581cran%2B2015-11-05%2Ba%25CC%2580%2B19.41.21.png", false);
		this.dessertService.save(dessert1);
		
		/* Commande */
		
		Commande commande1 = new Commande("1", StatutCommande.LIVRE, "35 avenue du petit bateau 64852 Une Certaine Ville", livreur1, client1) ;
		List<CommandePizza> commandePizzas = new ArrayList<>() ;
		commandePizzas.add(new CommandePizza(this.ps.findById(1), commande1, 3)) ;
		commandePizzas.add(new CommandePizza(this.ps.findById(2), commande1, 2)) ;
		CommandeComplete commandeComplete1= new CommandeComplete(commande1, commandePizzas) ;

		this.commandeService.create(commandeComplete1);
		
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}