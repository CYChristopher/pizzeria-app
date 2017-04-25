package fr.pizzeria.admin.web.commande;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.CommandeService;

@WebServlet("/commandes/edit")
public class EditerCommandeController extends HttpServlet {

	private static final String VUE_EDITER_COMMANDES = "/WEB-INF/views/commandes/editerCommandes.jsp";
	private Integer id;

	@Inject
	private CommandeService commandeService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.id = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("editCommande", this.commandeService.find(id));

		this.getServletContext().getRequestDispatcher(VUE_EDITER_COMMANDES).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
