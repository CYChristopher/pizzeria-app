package fr.pizzeria.admin.web.utilisateurs;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.UtilisateursService;

@WebServlet("/utilisateur/supprimer")
public class SupprimerUtilisateurController extends HttpServlet {

	@EJB
	private UtilisateursService utilisateursService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = Integer.parseInt(request.getParameter("id"));

		utilisateursService.delete(id);

		response.sendRedirect(request.getContextPath() + "/utilisateurs/list");
	}
}
