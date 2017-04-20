package fr.pizzeria.admin.web.utilisateurs;

import java.io.IOException;

<<<<<<< HEAD
import javax.ejb.EJB;
=======
import javax.inject.Inject;
>>>>>>> #3 USA003 - Utilisateurs - premier jet
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.UtilisateursService;
<<<<<<< HEAD
=======
import fr.pizzeria.model.Utilisateur;
>>>>>>> #3 USA003 - Utilisateurs - premier jet

@WebServlet("/utilisateurs/list")
public class ListerUtilisateurController extends HttpServlet {

	private static final String VUE_LISTER_UTILISATEURS = "/WEB-INF/views/utilisateurs/listerUtilisateurs.jsp";

<<<<<<< HEAD
	@EJB
=======
	@Inject
>>>>>>> #3 USA003 - Utilisateurs - premier jet
	private UtilisateursService utilisateursService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

<<<<<<< HEAD
=======
		this.utilisateursService.saveNew(new Utilisateur("toto", "tata", "titi", "tutu", "tonton"));

>>>>>>> #3 USA003 - Utilisateurs - premier jet
		req.setAttribute("listeUtilisateurs", this.utilisateursService.findAll());
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_UTILISATEURS);
		dispatcher.forward(req, resp);
	}

}
