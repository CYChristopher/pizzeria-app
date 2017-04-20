package fr.pizzeria.admin.web.utilisateurs;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.UtilisateursService;
import fr.pizzeria.model.Utilisateur;

@WebServlet("/utilisateurs/list")
public class ListerUtilisateurController extends HttpServlet {

	private static final String VUE_LISTER_UTILISATEURS = "/WEB-INF/views/utilisateurs/listerUtilisateurs.jsp";

	@Inject
	private UtilisateursService utilisateursService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.utilisateursService.saveNew(new Utilisateur("toto", "tata", "titi", "tutu", "tonton"));

		req.setAttribute("listeUtilisateurs", this.utilisateursService.findAll());
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_UTILISATEURS);
		dispatcher.forward(req, resp);
	}

}
