package fr.pizzeria.admin.web.utilisateurs;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.pizzeria.admin.metier.UtilisateursService;
import fr.pizzeria.model.Utilisateur;

@WebServlet("/login")
public class ConnexionUtilisateurController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(ConnexionUtilisateurController.class.getName());

	private static final String VUE_LOGIN = "/WEB-INF/views/utilisateurs/connexionUtilisateur.jsp";
	private static final String URL_HOME = "/";

	@Inject
	private UtilisateursService utilisateurService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String email = request.getParameter("email").trim();
		String motDePasse = request.getParameter("motDePasse");
		Utilisateur utilisateur = utilisateurService.findByEmail(email);

		// LOG.log(Level.INFO, "utilisateur != null :" + (utilisateur != null));
		// LOG.log(Level.INFO, "utilisateur.motDePasseOk(motDePasse) :" +
		// utilisateur.motDePasseOk(motDePasse));
		// LOG.log(Level.INFO, "motDePasse :" + motDePasse);
		// LOG.log(Level.INFO, "motDePasse hashé:" +
		// utilisateur.hashSha1(motDePasse, utilisateur.getDateCreation()));
		// LOG.log(Level.INFO, "hash en base:" + utilisateur.getMotDePasse());
		//
		// LOG.log(Level.INFO, "date récupéré en base avec un toString()" +
		// utilisateur.getDateCreation());

		if (utilisateur != null && utilisateur.motDePasseOk(motDePasse)) {
			HttpSession session = request.getSession();
			session.setAttribute("utilisateur", utilisateur);
			response.sendRedirect(request.getContextPath() + URL_HOME);
		} else {
			request.setAttribute("msg",
					"Utilisateur non identifié. Veuillez vous connecter avec les bons identifiants");
			this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
		}
	}
}