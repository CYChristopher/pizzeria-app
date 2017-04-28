package fr.pizzeria.admin.web.utilisateurs;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.UtilisateursService;

@WebServlet("/utilisateurs/liste")
public class ListerUtilisateurController extends HttpServlet {

	private static final String VUE_LISTER = "/WEB-INF/views/utilisateurs/listerUtilisateurs.jsp";

	@EJB
	private UtilisateursService utilisateursService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("listeUtilisateurs", this.utilisateursService.findAll());
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER);
		dispatcher.forward(req, resp);
	}

}
