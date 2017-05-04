package fr.pizzeria.admin.web.commande;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.ClientService;
import fr.pizzeria.admin.metier.CommandeService;
import fr.pizzeria.admin.metier.LivreurService;
import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.CommandeComplete;
import fr.pizzeria.model.CommandePizza;
import fr.pizzeria.model.Livreur;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.StatutCommande;

@WebServlet("/commandes/nouvelle")
public class NouvelleCommandeController extends HttpServlet {

	private static final String VUE_SAVE_BOISSON = "/WEB-INF/views/commandes/nouvelleCommande.jsp";

	@Inject
	private CommandeService commandeService;
	@Inject
	private LivreurService livreurService;
	@Inject
	private PizzaService pizzaService;
	@Inject
	private ClientService clientService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.montrerPageNouvelleCommande(req, resp, true, null);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String numCommande = UUID.randomUUID().toString();
		String[] pizzaCommandeId = request.getParameterValues("pizzaCommandeId");
		String statut = request.getParameter("statut");
		String idClient = request.getParameter("client");
		String idLivreur = request.getParameter("livreur");

		String livraison = request.getParameter("typeCommande");
		if (livraison.equals("LIV") && idLivreur.equals("noLiv")) {
			request.setAttribute("numero", numCommande);
			request.setAttribute("statut", statut);
			request.setAttribute("idClient", idClient);
			request.setAttribute("idLivreur", idLivreur);
			request.setAttribute("msg", "Livreur obligatoire pour livraison !");
			montrerPageNouvelleCommande(request, response, true, null);
		} else {
			if (pizzaCommandeId == null) {
				request.setAttribute("numero", numCommande);
				request.setAttribute("statut", statut);
				request.setAttribute("idClient", idClient);
				request.setAttribute("idLivreur", idLivreur);
				request.setAttribute("msg", "Aucun produit commandé");
				montrerPageNouvelleCommande(request, response, true, null);
			} else if (commandeService.findByNum(numCommande).isEmpty()) {
				Livreur livreur = null;
				if (!idLivreur.equals("noLiv")) {
					livreur = this.livreurService.find(Integer.parseInt(idLivreur));
				}

				Client client = this.clientService.getById(Integer.parseInt(idClient));

				if (client.getAdresse().isEmpty() && livraison.equals("LIV")) {
					request.setAttribute("numero", numCommande);
					request.setAttribute("statut", statut);
					request.setAttribute("idClient", idClient);
					request.setAttribute("idLivreur", idLivreur);
					request.setAttribute("msg",
							"Ce client n'a pas d'adresse, livraison impossible, veuillez choisir un autre type de commande");
					montrerPageNouvelleCommande(request, response, true, null);
				} else {

					String adresse = "";
					if (livraison.equals("LIV")) {
						adresse = client.getAdresse();
					} else {
						livreur = null;
					}

					List<Pizza> listePizza = new ArrayList<>();
					for (String idPizza : pizzaCommandeId) {
						listePizza.add(this.pizzaService.findById(Integer.parseInt(idPizza)));
					}

					Commande commande = new Commande(numCommande, StatutCommande.valueOf(statut), adresse, livreur,
							client);

					List<CommandePizza> commandesPizza = new ArrayList<>();
					for (Pizza pizza : listePizza) {
						commandesPizza.add(new CommandePizza(pizza, commande, 1));
					}

					CommandeComplete commandeComplete = new CommandeComplete(commande, commandesPizza);

					this.commandeService.create(commandeComplete);

					response.sendRedirect(request.getContextPath() + "/commandes/liste");
				}
			} else {
				request.setAttribute("numero", numCommande);
				request.setAttribute("statut", statut);
				request.setAttribute("idClient", idClient);
				request.setAttribute("idLivreur", idLivreur);
				montrerPageNouvelleCommande(request, response, false, Arrays.asList(pizzaCommandeId));
			}
		}
	}

	private void montrerPageNouvelleCommande(HttpServletRequest req, HttpServletResponse resp, boolean numOk,
			List<String> pizzaCommandeId) throws ServletException, IOException {
		req.setAttribute("statusPossible", EnumSet.allOf(StatutCommande.class));
		req.setAttribute("listeLivreur", this.livreurService.findAll());
		req.setAttribute("listeClient", this.clientService.findAll());
		req.setAttribute("numOk", numOk);

		Map<Pizza, Boolean> mapPizzas = new HashMap<>();
		this.pizzaService.findNewestPizzaByName().forEach(pizza -> {
			if (pizzaCommandeId != null && pizzaCommandeId.contains(pizza.getId().toString())) {
				mapPizzas.put(pizza, true);
			} else {
				mapPizzas.put(pizza, false);
			}
		});
		req.setAttribute("listePizza", mapPizzas);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_SAVE_BOISSON);
		dispatcher.forward(req, resp);
	}
}