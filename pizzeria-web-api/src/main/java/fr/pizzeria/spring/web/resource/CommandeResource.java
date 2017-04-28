package fr.pizzeria.spring.web.resource;

import fr.pizzeria.model.Commande;
import fr.pizzeria.model.CommandeComplete;
import fr.pizzeria.model.CommandePizza;
import fr.pizzeria.spring.web.repository.ICommandePizzaRepository;
import fr.pizzeria.spring.web.repository.ICommandeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Resource Commande.
 */
@RestController
@RequestMapping("/commandes")
public class CommandeResource {

	@Autowired
	private ICommandeRepository commandeDao;
	@Autowired
	private ICommandePizzaRepository commandePizzaDao;

	@RequestMapping(method = RequestMethod.GET)
	public List<Commande> listAllCommandes() {
		List<Commande> list = this.commandeDao.findAll();
		return list;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void ajouterCommande(@RequestBody CommandeComplete commandeComplete) {
		this.commandeDao.save(commandeComplete.getCommande());
		for (CommandePizza cp : commandeComplete.getCommandesPizza()) {
			cp.getId().setCommande(commandeComplete.getCommande());
			System.out.println(cp);
			this.commandePizzaDao.save(cp);
		}
	}
}
