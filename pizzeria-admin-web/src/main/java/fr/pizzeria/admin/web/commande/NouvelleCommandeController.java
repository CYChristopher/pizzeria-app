package fr.pizzeria.admin.web.commande;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.EnumSet;

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
import fr.pizzeria.model.CategoriePizza;
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

		Pizza totoPizza = new Pizza("TOT", "totoo" + Math.random(), BigDecimal.valueOf(12.0),
				CategoriePizza.SANS_VIANDE);
		pizzaService.save(totoPizza);

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

		// if (!request.getParameter("code").isEmpty() &&
		// !request.getParameter("nom").isEmpty()
		// && !request.getParameter("prix").isEmpty()) {
		// String code = request.getParameter("code");
		// String nom = request.getParameter("nom");

		String[] prix = request.getParameterValues("pizzaCommande");

		LocalDateTime dateCreation = LocalDateTime.now();

		System.out.println("TOTOTOTOTOTOTOTOTOTOTOTOTOTTOTOO : " + prix);

		Livreur totoLivreur = new Livreur();
		Client totoClient = new Client("MAURICE", "ROGER", "toto@toto.fr", "14", "chez roger");
		System.out.println("SAUVEGARDE CLIENT");
		clientService.save(totoClient);
		System.out.println("SAUVEGARDE pizza");

		System.out.println("SAUVEGARDE livreur");
		livreurService.save(totoLivreur);

		System.out.println("SAUVEGARDE commande");

		Commande dessert = new Commande("12", StatutCommande.NON_TRAITE, "chez toto", livreurService.findAll().get(0),
				clientService.findAll().get(0), null);

		commandeService.create(dessert);

		response.sendRedirect(request.getContextPath() + "/commandes/list");

		// } else {
		// String erreur[] = { "", "", "" };
		// if (request.getParameter("code").isEmpty()) {
		// erreur[0] = "red";
		// } else {
		// request.setAttribute("code", request.getParameter("code"));
		// }
		//
		// if (request.getParameter("nom").isEmpty()) {
		// erreur[1] = "red";
		// } else {
		// request.setAttribute("nom", request.getParameter("nom"));
		// }
		//
		// if (request.getParameter("prix").isEmpty()) {
		// erreur[2] = "red";
		// } else {
		// request.setAttribute("prix", request.getParameter("prix"));
		// }
		//
		// request.setAttribute("erreur", erreur);
		// request.setAttribute("msg", "Veuillez remplir les champs en rouge,
		// sinon ...");
		// doGet(request, response);
		// }

	}

}