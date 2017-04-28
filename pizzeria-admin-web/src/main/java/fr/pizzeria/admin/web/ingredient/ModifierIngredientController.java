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
import fr.pizzeria.model.Ingredient;

/**
 * Servlet de modification des ingredients.
 */
@WebServlet("/ingredients/editer")
public class ModifierIngredientController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(ModifierIngredientController.class.getName());

	private static final String VUE_EDIT_INGREDIENT = "/WEB-INF/views/ingredients/modifierIngredient.jsp";
	private static final String URL_LISTE = "/ingredients/liste";

	private Integer id;

	@EJB
	private IngredientService ingredientService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			this.id = Integer.valueOf(req.getParameter("id").toString());
			req.setAttribute("editIngredient", ingredientService.findById(this.id));

		} catch (StockageException e) {
			LOG.log(Level.WARNING, "-------!!!------- exception levée : " + e.getMessage() + " => " + e.getCause());
			req.setAttribute("msg", "Erreur du serveur, merci de contacter le support de l'application ");
		} finally {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_EDIT_INGREDIENT);
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		LOG.log(Level.INFO, "-------!!!------- modification de l'ingredient n : " + this.id);
		Ingredient old;

		try {
			old = ingredientService.findById(this.id);

			String nom = req.getParameter("nom").isEmpty() ? old.getNom() : req.getParameter("nom").toString();
			Integer quantite = req.getParameter("quantite").isEmpty() ? old.getQuantite()
					: Integer.valueOf(req.getParameter("quantite").toString());
			Double prix = req.getParameter("prix").isEmpty() ? old.getPrix()
					: Double.valueOf(req.getParameter("prix").toString());

			Ingredient ingredient = new Ingredient(nom, quantite, prix);

			ingredientService.update(this.id, ingredient);

		} catch (StockageException e) {
			LOG.log(Level.WARNING, "-------!!!------- exception levée : " + e.getMessage() + " => " + e.getCause());
			req.setAttribute("msg", "Erreur du serveur, merci de contacter le support de l'application ");
		} finally {
			resp.sendRedirect(req.getContextPath() + URL_LISTE);
		}
	}

}
