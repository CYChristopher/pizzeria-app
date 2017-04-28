package fr.pizzeria.admin.web.listener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import fr.pizzeria.admin.metier.BoissonService;
import fr.pizzeria.admin.metier.ClientService;
import fr.pizzeria.admin.metier.DessertService;
import fr.pizzeria.admin.metier.IngredientService;
import fr.pizzeria.admin.metier.LivreurService;
import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.admin.metier.UtilisateursService;
import fr.pizzeria.model.Boisson;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Dessert;
import fr.pizzeria.model.Ingredient;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;
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
	private IngredientService is;
	@Inject
	private PizzaService ps;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		/* Utilisateur */
		Utilisateur utilisateur1 = new Utilisateur("A", "Nicolas", "a@orange.fr", "admin", "Ingenierie",
				LocalDateTime.of(2017, 4, 16, 16, 26, 42));
		utilisateurService.saveNew(utilisateur1);
		utilisateurService.saveNew(new Utilisateur("Admin", "Jean-Pierre", "a@a", "a", "Nantes", LocalDateTime.now()));

		/* Ingrédient */

		is.save(new Ingredient("Totomate", 100, 1.5));
		is.save(new Ingredient("Maurizzella", 50, 3.5));
		is.save(new Ingredient("Eclipserie", 250, 1.0));
		is.save(new Ingredient("Chauve-sourizo", 50, 1.5));
		is.save(new Ingredient("Anti-ananas", 150, 2.0));

		/* Pizza */
		ps.save(new Pizza("PEP", "Peperoni", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE, TypePizza.PIZZA,
				LocalDateTime.now(), true, is.findAll()));
		ps.save(new Pizza("REI", "Reine", BigDecimal.valueOf(11.00), CategoriePizza.VIANDE, TypePizza.PIZZA,
				LocalDateTime.now(), true, is.findAll()));
		ps.save(new Pizza("4FR", "4 Fromages", BigDecimal.valueOf(12.00), CategoriePizza.SANS_VIANDE, TypePizza.OUICHE,
				LocalDateTime.now(), true, is.findAll()));

		/* Client */
		Client client1 = new Client("A", "Nicolas", "a@orange.fr", "aaaaaa", "DTA Ingenierie");
		Client client2 = new Client("G", "Valentin", "totot@gmail.com", "123soleil", "Quelque part");
		clientService.save(client1);
		clientService.save(client2);

		/* Livreur */
		Livreur livreur1 = new Livreur();
		livreur1.setNom("Martin");
		livreur1.setPrenom("Julien");
		livreurService.save(livreur1);

		/* Boisson */
		Boisson boisson1 = new Boisson("PEP", "Pepsi", 1.5, null);
		boissonService.saveNew(boisson1);

		/* Dessert */
		Dessert dessert1 = new Dessert("COO", "Cookie", new BigDecimal(2));
		dessertService.save(dessert1);
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}