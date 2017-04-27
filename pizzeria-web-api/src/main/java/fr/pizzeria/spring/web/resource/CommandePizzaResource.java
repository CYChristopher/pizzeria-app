package fr.pizzeria.spring.web.resource;

import fr.pizzeria.model.CommandePizza;
import fr.pizzeria.spring.web.repository.ICommandePizzaRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Resource CommandePizza.
 */
@RestController
@RequestMapping("/commandePizzas")
public class CommandePizzaResource {

	@Autowired
	private ICommandePizzaRepository commandePizzaDao;

	@RequestMapping(method = RequestMethod.GET)
	public List<CommandePizza> listAllCommandes() {
		List<CommandePizza> list = this.commandePizzaDao.findAll();
		return list;
	}

	@RequestMapping(method = RequestMethod.POST)
	public void ajouterCommande(@RequestBody CommandePizza commandePizza) {
		this.commandePizzaDao.save(commandePizza);
	}
}
