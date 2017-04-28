package fr.pizzeria.admin.web.commande;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.CommandeService;

@WebServlet("/commandes/supprimer")
public class SupprimerCommandeController extends HttpServlet {

	@Inject
	private CommandeService commandeService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		commandeService.delete(Integer.valueOf(request.getParameter("id")));
		response.sendRedirect(request.getContextPath() + "/commandes/list");
	}

}
