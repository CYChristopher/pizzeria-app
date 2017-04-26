package fr.pizzeria.admin.web.ingredient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.IngredientService;

/**
 * Servlet de la page Liste des ingrédients.
 */
@WebServlet("/ingredients/list")
public class ListerIngredientController extends HttpServlet {
	
	private static final Logger LOG = Logger.getLogger(ListerIngredientController.class.getName());
	
	private static final String VUE_LISTER_INGREDIENTS = "/WEB-INF/views/ingredients/listerIngredients.jsp";
	
	@Inject
	private IngredientService ingredientService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setAttribute("listeIngredients", this.ingredientService.findAll());
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_INGREDIENTS);
		dispatcher.forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer id = Integer.valueOf(req.getParameter("id").toString());
		LOG.log(Level.INFO, "-------!!!------- suppression de l'ingredient n : " + id);
		ingredientService.delete(id);
		
		resp.sendRedirect(req.getContextPath() + "/ingredients/list");
	}
	
	
}
