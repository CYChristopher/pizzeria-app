package fr.pizzeria.admin.web.commande;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.inject.Inject;
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

@WebServlet("/commandes/edit")
public class EditerCommandeController extends HttpServlet {

	private static final String VUE_EDITER_COMMANDES = "/WEB-INF/views/commandes/editerCommandes.jsp";
	private Integer id;

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

		this.id = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("commande", this.commandeService.find(this.id));

		req.setAttribute("statusPossible", EnumSet.allOf(StatutCommande.class));

		req.setAttribute("listeLivreur", this.livreurService.findAll());
		req.setAttribute("listePizza", this.pizzaService.findAll());
		req.setAttribute("listeClient", this.clientService.findAll());

		this.getServletContext().getRequestDispatcher(VUE_EDITER_COMMANDES).forward(req, resp);
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

		Livreur livreur = this.livreurService.find(Integer.parseInt(idLivreur));
		Client client = this.clientService.getById(Integer.parseInt(idClient));

		List<Pizza> listePizza = new ArrayList<>();

		if (pizzaCommandeId != null) {
			for (String idPizza : pizzaCommandeId) {
				listePizza.add(this.pizzaService.findById(Integer.parseInt(idPizza)));
			}
		}

		Commande commande = new Commande(numCommande, StatutCommande.valueOf(statut), adresse, livreur, client);

		List<CommandePizza> commandesPizza = new ArrayList<>();
		for(Pizza pizza : listePizza){
			commandesPizza.add(new CommandePizza(pizza, commande, 1));
		}

		CommandeComplete commandeComplete= new CommandeComplete(commande,commandesPizza);

		this.commandeService.update(this.id, commandeComplete);

		response.sendRedirect(request.getContextPath() + "/commandes/liste");

	}

}
