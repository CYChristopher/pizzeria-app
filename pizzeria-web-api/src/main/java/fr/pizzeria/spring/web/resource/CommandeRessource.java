package fr.pizzeria.spring.web.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fr.pizzeria.model.Commande;
import fr.pizzeria.spring.web.repository.ICommandeRepository;

@RestController
public class CommandeRessource {

	@Autowired
	private ICommandeRepository commandeDao;

	@RequestMapping(value = "/commande/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Commande recupererCommande(@PathVariable Integer id) {
		return commandeDao.findOne(id);
	}

}
