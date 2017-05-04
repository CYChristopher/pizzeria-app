package fr.pizzeria.spring.web.resource;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

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
		client.setDateCreation(LocalDateTime.now());
		this.clientDao.save(client);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	public void modifierClient(@PathVariable("id") Integer id, @RequestBody Client newClient) {

		Client oldClient = this.clientDao.findById(id);
		newClient.setId(oldClient.getId());

		if ("".equals(newClient.getMotDePasse().trim())) {
			newClient.setMotDePasse(oldClient.getMotDePasse());
		} else {

			// Hash du mot de passe
			newClient.setMotDePasse(DigestUtils.sha256Hex(newClient.getMotDePasse()));
		}

		clientDao.save(newClient);

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

	@RequestMapping(value = "/email", method = RequestMethod.GET)
	public boolean loginClientExiste(@RequestParam("value") String email) {

		Client reponse = clientDao.findByEmail(email);
		return reponse != null;
	}

	@RequestMapping(value = "/verifPwd", method = RequestMethod.GET)
	public boolean testMdpClient(@RequestParam("id") Integer id, @RequestParam("motDePasse") String motDePasse) {

		Client client = clientDao.findById(id);

		return (client.getMotDePasse().equals(motDePasse));
	}

}
