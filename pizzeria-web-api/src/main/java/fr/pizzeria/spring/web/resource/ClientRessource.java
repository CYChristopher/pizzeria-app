package fr.pizzeria.spring.web.resource;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.pizzeria.model.Client;
import fr.pizzeria.spring.security.JwtService;
import fr.pizzeria.spring.web.repository.IClientRepository;

/**
 * Ressource Client
 *
 * @author ETY 12
 *
 */
@RestController
@RequestMapping("/clients")
public class ClientRessource {

	@Autowired
	private IClientRepository clientDao;

	@Autowired
	private JwtService jwtService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Client getClient(@PathVariable("id") Integer id) {
		return this.clientDao.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	public void ajouterClient(@RequestBody Client client) {
		// Hash du mot de passe
		client.setMotDePasse(DigestUtils.sha256Hex(client.getMotDePasse()));
		this.clientDao.save(client);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public void modifierClient(@PathVariable("id") Integer id, @RequestBody Client newClient) {

		Client oldClient = this.clientDao.findById(id);
		newClient.setId(oldClient.getId());
		if (newClient.getMotDePasse() == "") {
			newClient.setMotDePasse(oldClient.getMotDePasse());
		}

		// Hash du mot de passe
		newClient.setMotDePasse(DigestUtils.sha256Hex(newClient.getMotDePasse()));
		this.clientDao.save(newClient);
	}

	// cette methode renvoie un token JWT
	@RequestMapping(method = RequestMethod.GET)
	public Client recupererClient(@RequestParam("email") String email, @RequestParam("motDePasse") String motDePasse,
			HttpServletResponse response) {

		Client reponse = this.clientDao.findByEmailAndMotDePasse(email, motDePasse);

		if (reponse != null) {

			try {
				response.setHeader("Token", jwtService.tokenFor(reponse));
			} catch (IOException | URISyntaxException e) {
				throw new RuntimeException("Erreur avec le token jwt", e);
			}
			return reponse;

		} else {
			throw new RuntimeException("Le login/mdp est mauvais");
		}

	}
}
