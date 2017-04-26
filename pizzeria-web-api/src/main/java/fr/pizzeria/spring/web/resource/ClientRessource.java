package fr.pizzeria.spring.web.resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.pizzeria.model.Client;
import fr.pizzeria.spring.web.repository.IClientRepository;

/**
 * Ressource Client
 * 
 * @author ETY 12
 *
 */
@RestController
public class ClientRessource {

	@Autowired
	private IClientRepository clientDao;

	@RequestMapping(value = "/client", method = RequestMethod.POST)
	public void ajouterClient(@RequestBody Client client) {
		//Hash du mot de passe
		client.setMotDePasse(DigestUtils.sha256Hex(client.getMotDePasse()));
		clientDao.save(client);
	}

}
