package fr.pizzeria.admin.web.ingredient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.exception.StockageException;
import fr.pizzeria.admin.metier.IngredientService;

/**
 * Servlet de la page Liste des ingrédients.
 */
@WebServlet("/ingredients/liste")
public class ListerIngredientController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(ListerIngredientController.class.getName());

	private static final String VUE_LISTER_INGREDIENTS = "/WEB-INF/views/ingredients/listerIngredients.jsp";
	private static final String URL_LISTE = "/ingredients/liste";

	@EJB
	private IngredientService ingredientService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			req.setAttribute("listeIngredients", this.ingredientService.findAllAviable());
		} catch (StockageException e) {
			LOG.log(Level.WARNING, "-------!!!------- exception levée : " + e.getMessage() + " => " + e.getCause());
			req.setAttribute("msg", "Erreur du serveur, merci de contacter le support de l'application ");
		} finally {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_INGREDIENTS);
			dispatcher.forward(req, resp);
		}
	}

	/**
	 * méthode de suppression de l'ingredient
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.valueOf(req.getParameter("id").toString());
		LOG.log(Level.INFO, "-------!!!------- suppression/archivage de l'ingredient n : " + id);
		try {
			ingredientService.delete(id);
		} catch (StockageException e) {
			LOG.log(Level.WARNING, "-------!!!------- exception levée : " + e.getMessage() + " => " + e.getCause());
			req.setAttribute("msg", "Erreur du serveur, merci de contacter le support de l'application ");
		} finally {
			resp.sendRedirect(req.getContextPath() + URL_LISTE);
		}
	}
}
