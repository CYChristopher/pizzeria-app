package fr.pizzeria.admin.web.commande;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

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

		req.setAttribute("statusPossible", EnumSet.allOf(StatutCommande.class));

		req.setAttribute("listeLivreur", livreurService.findAll());
		req.setAttribute("listePizza", pizzaService.findAll());
		req.setAttribute("listeClient", clientService.findAll());

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_SAVE_BOISSON);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String[] pizzaCommandeId = request.getParameterValues("pizzaCommandeId");
		String statut = request.getParameter("statut");
		String adresse = request.getParameter("adresse");
		String numCommande = request.getParameter("numCommande");

		String idClient = request.getParameter("client");
		String idLivreur = request.getParameter("livreur");

		Livreur livreur = livreurService.find(Integer.parseInt(idLivreur));
		Client client = clientService.getById(Integer.parseInt(idClient));

		List<Pizza> listePizza = new ArrayList<>();

		if (pizzaCommandeId != null) {
			for (String idPizza : pizzaCommandeId) {
				listePizza.add(pizzaService.findById(Integer.parseInt(idPizza)));
			}
		}

		Commande commande = new Commande(numCommande, StatutCommande.valueOf(statut), adresse, livreur, client,
				listePizza);

		commandeService.create(commande);

		response.sendRedirect(request.getContextPath() + "/commandes/list");

	}

}