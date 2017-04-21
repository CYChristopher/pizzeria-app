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

@WebServlet("/livreurs/edit")
public class EditerLivreurController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Inject
	private LivreurService livreurService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("livreur", this.livreurService.find((String) request.getParameter("id")));
		if (request.getParameter("del") != null) {
			deleteLivreur(request, response);
		} else {
			RequestDispatcher dispatcher = this.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/livreurs/editerLivreur.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void deleteLivreur(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.livreurService.delete(request.getParameter("id"));
		response.sendRedirect(request.getContextPath() + "/livreurs/list");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Livreur livreur = new Livreur();
		livreur.setNom(request.getParameter("nom"));
		livreur.setPrenom(request.getParameter("prenom"));
		this.livreurService.update(request.getParameter("id"), livreur);
		response.sendRedirect(request.getContextPath() + "/livreurs/list");
	}

}
