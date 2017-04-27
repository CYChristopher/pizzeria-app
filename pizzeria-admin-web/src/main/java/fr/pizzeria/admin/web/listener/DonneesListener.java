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
import fr.pizzeria.admin.metier.LivreurService;
import fr.pizzeria.admin.metier.UtilisateursService;
import fr.pizzeria.model.Boisson;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Dessert;
import fr.pizzeria.model.Livreur;
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

    public void contextInitialized(ServletContextEvent arg0)  {
    	/* Utilisateur */
    	Utilisateur utilisateur1 = new Utilisateur("Amiot", "Nicolas", "amiot-nicolas@orange.fr", "admin", "DTA Ingenierie", LocalDateTime.of(2017, 04, 16, 16, 26, 42));
    	utilisateurService.saveNew(utilisateur1);
    	
    	/* Client */
    	Client client1 = new Client("Amiot", "Nicolas", "amiot-nicolas@orange.fr", "aaaaaa", "DTA Ingenierie");
    	Client client2 = new Client("Giboulot", "Valentin", "totot@gmail.com", "123soleil", "Quelque part");
    	clientService.save(client1);
    	clientService.save(client2);
    	
    	/* Livreur */
    	Livreur livreur1 = new Livreur();
    	livreur1.setNom("Marzin");
    	livreur1.setPrenom("Julien");
    	livreurService.save(livreur1);
    	
    	/* Boisson */
    	Boisson boisson1 = new Boisson("PEP", "Pepsi", 1.5, null);
    	boissonService.saveNew(boisson1);
    	
    	/* Dessert */
    	Dessert dessert1 = new Dessert("COO", "Cookie", new BigDecimal(2));
    	dessertService.save(dessert1);
    }
    
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	
    }
	
}
