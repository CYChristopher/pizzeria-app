package fr.pizzeria.admin.web.livreur;

import java.io.IOException;
import java.util.logging.Logger;

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
@WebServlet("/livreurs/list")
public class ListerLivreurController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(ListerLivreurController.class.getName());

	private static final String VUE_LISTER_LIVREURS = "/WEB-INF/views/livreurs/listerLivreurs.jsp";

	@Inject
	private LivreurService livreurService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listeLivreurs", this.livreurService.findAll());
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_LIVREURS);
		dispatcher.forward(req, resp);
	}

}
