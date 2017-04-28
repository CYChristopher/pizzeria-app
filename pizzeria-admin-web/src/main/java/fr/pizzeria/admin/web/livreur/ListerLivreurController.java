package fr.pizzeria.admin.web.livreur;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.LivreurService;

/**
 * Contrôleur de la page Liste des livreurs.
 */
@WebServlet("/livreurs/liste")
public class ListerLivreurController extends HttpServlet {

	private static final String VUE_LISTER = "/WEB-INF/views/livreurs/listerLivreurs.jsp";

	@Inject
	private LivreurService livreurService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listeLivreurs", this.livreurService.findAll());
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER);
		dispatcher.forward(req, resp);
	}

}
