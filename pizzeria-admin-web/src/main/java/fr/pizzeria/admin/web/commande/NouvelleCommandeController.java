package fr.pizzeria.admin.web.commande;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		montrerPageNouvelleCommande(req, resp, true, null);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String numCommande = request.getParameter("numCommande");
		String[] pizzaCommandeId = request.getParameterValues("pizzaCommandeId");
		String statut = request.getParameter("statut");
		String adresse = request.getParameter("adresse");

		String idClient = request.getParameter("client");
		String idLivreur = request.getParameter("livreur");
		
		if (commandeService.findByNum(numCommande).isEmpty()) {

			Livreur livreur = this.livreurService.find(Integer.parseInt(idLivreur));
			Client client = this.clientService.getById(Integer.parseInt(idClient));

			List<Pizza> listePizza = new ArrayList<>();

			if (pizzaCommandeId != null) {
				for (String idPizza : pizzaCommandeId) {
					listePizza.add(this.pizzaService.findById(Integer.parseInt(idPizza)));
				}

				Commande commande = new Commande(numCommande, StatutCommande.valueOf(statut), adresse, livreur, client);

				List<CommandePizza> commandesPizza = new ArrayList<>();
				for (Pizza pizza : listePizza) {
					commandesPizza.add(new CommandePizza(pizza, commande, 1));
				}

				CommandeComplete commandeComplete = new CommandeComplete(commande, commandesPizza);

				this.commandeService.create(commandeComplete);

				response.sendRedirect(request.getContextPath() + "/commandes/list");
			} else {

				request.setAttribute("statut", statut);
				request.setAttribute("adresse", adresse);
				request.setAttribute("idClient", idClient);
				request.setAttribute("idLivreur", idLivreur);
				request.setAttribute("msg", "Aucun produit commandé");
				montrerPageNouvelleCommande(request, response, false, Arrays.asList(pizzaCommandeId));
			}
		}
	}

	private void montrerPageNouvelleCommande(HttpServletRequest req, HttpServletResponse resp, boolean numOk,
			List<String> pizzaCommandeId) throws ServletException, IOException {
		req.setAttribute("statusPossible", EnumSet.allOf(StatutCommande.class));
		req.setAttribute("listeLivreur", livreurService.findAll());
		req.setAttribute("listeClient", clientService.findAll());
		req.setAttribute("numOk", numOk);

		Map<Pizza, Boolean> mapPizzas = new HashMap<>();
		pizzaService.findAll().forEach(pizza -> {
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