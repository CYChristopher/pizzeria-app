package fr.pizzeria.admin.web.utilisateurs;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.UtilisateursService;
import fr.pizzeria.model.Utilisateur;

@WebServlet("/utilisateur/nouveau")
public class NouvelUtilisateurController extends HttpServlet {

	@EJB
	private UtilisateursService utilisateursService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/utilisateurs/nouvelUtilisateur.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String motDePasse = request.getParameter("motDePasse");
		String adresse = request.getParameter("adresse");

		LocalDateTime dateCreation = LocalDateTime.now();

		Utilisateur piz = new Utilisateur(nom, prenom, email, motDePasse, adresse, dateCreation);

		utilisateursService.saveNew(piz);
		response.sendRedirect(request.getContextPath() + "/utilisateurs/list");

	}

}