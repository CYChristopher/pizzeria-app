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

@WebServlet("/livreurs/editer")
public class EditerLivreurController extends HttpServlet {

	private static final String URL_LISTE = "/livreurs/liste";
	private static final String VUE_EDITER = "/WEB-INF/views/livreurs/editerLivreur.jsp";

	@Inject
	private LivreurService livreurService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("livreur", this.livreurService.find(Integer.parseInt(request.getParameter("id"))));
		if (request.getParameter("del") != null) {
			deleteLivreur(request, response);
		} else {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_EDITER);
			dispatcher.forward(request, response);
		}
	}

	protected void deleteLivreur(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.livreurService.delete(Integer.parseInt(request.getParameter("id")));
		response.sendRedirect(request.getContextPath() + URL_LISTE);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Livreur livreur = new Livreur();
		livreur.setNom(request.getParameter("nom"));
		livreur.setPrenom(request.getParameter("prenom"));
		this.livreurService.update(Integer.parseInt(request.getParameter("id")), livreur);
		response.sendRedirect(request.getContextPath() + URL_LISTE);
	}

}
