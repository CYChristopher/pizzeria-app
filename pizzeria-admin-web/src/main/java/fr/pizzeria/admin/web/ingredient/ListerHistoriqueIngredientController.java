package fr.pizzeria.admin.web.ingredient;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.IngredientService;
import fr.pizzeria.model.Ingredient;

/**
 * Contrôleur de la page Liste des ingredients.
 */
@WebServlet("/historiqueIngredients/liste")
public class ListerHistoriqueIngredientController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(ListerHistoriqueIngredientController.class.getName());

	private static final String VUE_LISTER_INGREDIENTS = "/WEB-INF/views/ingredients/listerAllIngredients.jsp";

	@Inject
	private IngredientService ingredientService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
		req.setAttribute("listeIngredients", this.ingredientService.findAll());
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_INGREDIENTS);
		dispatcher.forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		Integer id = Integer.valueOf(request.getParameter("id"));

		Ingredient editerIngredient = this.ingredientService.findById(id);

		editerIngredient.setArchive(!editerIngredient.getArchive());

		this.ingredientService.update(id, editerIngredient);

		response.sendRedirect(request.getContextPath() + "/historiqueIngredients/liste");

	}


}
