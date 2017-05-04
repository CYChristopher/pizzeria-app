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

@WebServlet("/utilisateurs/ajouter")
public class NouvelUtilisateurController extends HttpServlet {

	private static final String VUE_AJOUTER = "/WEB-INF/views/utilisateurs/nouvelUtilisateur.jsp";
	private static final String URL_LISTE = "/utilisateurs/liste";

	@EJB
	private UtilisateursService utilisateursService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_AJOUTER);
		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String motDePasse = request.getParameter("motDePasse");
		String validationMotDePasse = request.getParameter("validationMotDePasse");
		String adresse = request.getParameter("adresse");

		LocalDateTime dateCreation = LocalDateTime.now();
		Utilisateur utilisateur = new Utilisateur(nom, prenom, email, motDePasse, adresse, dateCreation);

		if (!motDePasse.equals(validationMotDePasse)) {
			request.setAttribute("donnees", utilisateur);
			request.setAttribute("msg", "Confirmation du mot de passe incorrecte");
			doGet(request, response);

		} else if (utilisateursService.findByEmail(email) == null) {

			utilisateursService.saveNew(utilisateur);
			response.sendRedirect(request.getContextPath() + URL_LISTE);

		} else {

			request.setAttribute("donnees", utilisateur);
			request.setAttribute("msg", "Email déjà existant");
			doGet(request, response);

		}

	}

}