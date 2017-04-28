package fr.pizzeria.admin.web.utilisateurs;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.UtilisateursService;
import fr.pizzeria.model.Utilisateur;

@WebServlet("/utilisateurs/editer")
public class EditerUtilisateurController extends HttpServlet {

	private static final String VUE_EDITER = "/WEB-INF/views/utilisateurs/editerUtilisateur.jsp";
	private static final String URL_LISTE = "/utilisateurs/liste";

	@EJB
	private UtilisateursService utilisateursService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");

		Utilisateur utilisateur = utilisateursService.find(Integer.parseInt(id));

		if (utilisateur != null) {

			request.setAttribute("utilisateur", utilisateur);

			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_EDITER);
			dispatcher.forward(request, response);

		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String motDePasse = request.getParameter("motDePasse");
		String adresse = request.getParameter("adresse");

		Integer oldId = Integer.parseInt(request.getParameter("oldId"));
		String stringDate = request.getParameter("dateCreation");

		LocalDateTime dateCreation = LocalDateTime.parse(stringDate, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

		Utilisateur utilisateur = utilisateursService.find(oldId);
		Utilisateur utili = new Utilisateur(nom, prenom, email, motDePasse, adresse, dateCreation);

		if (utilisateur.getEmail().equals(email)) // check if the email is the
													// same
		{

			utilisateursService.update(oldId, utili);
			response.sendRedirect(request.getContextPath() + URL_LISTE);

		} else // check if the new one is already exist
		{

			if (utilisateursService.findByEmail(email) == null) {
				utilisateursService.update(oldId, utili);
				response.sendRedirect(request.getContextPath() + URL_LISTE);
			} else {
				request.setAttribute("msg", "Email modifié: nouveau déjà existant");
				doGet(request, response);
			}
		}

	}

}
