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
import fr.pizzeria.model.Livreur;

/**
 * Contrôleur de la page Ajouter un livreur.
 */
@WebServlet("/livreurs/ajouter")
public class AjouterLivreurController extends HttpServlet {

	private static final String VUE_AJOUTER = "/WEB-INF/views/livreurs/ajouterLivreur.jsp";
	private static final String URL_LISTE = "/livreurs/liste";

	@Inject
	private LivreurService livreurService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_AJOUTER);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Livreur livreur = new Livreur();
		if (!req.getParameter("nom").isEmpty() && !req.getParameter("prenom").isEmpty()) {
			System.out.println("------------------" + req.getParameter("prenom"));
			System.out.println("------------------" + req.getParameter("nom"));
			livreur.setNom(req.getParameter("nom"));
			livreur.setPrenom(req.getParameter("prenom"));
			this.livreurService.save(livreur);
			resp.sendRedirect(req.getContextPath() + URL_LISTE);
		} else {
			req.setAttribute("msg", "Veuillez remplir les champs !");
			doGet(req, resp);
		}
	}

}
