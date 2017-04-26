package fr.pizzeria.spring.web.resource;

import fr.pizzeria.model.Commande;
import fr.pizzeria.spring.web.repository.ICommandeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Resource Commande.
 */
@RestController
@RequestMapping("/commandes")
public class CommandeResource {

	@Autowired private ICommandeRepository commandeDao;

	@RequestMapping(method = RequestMethod.GET)
	public List<Commande> listAllCommandes() {
		List<Commande> list = this.commandeDao.findAll();
		return list;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void addCommande(Commande commande) {
		this.commandeDao.save(commande);
	}
}
