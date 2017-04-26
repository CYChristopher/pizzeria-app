package fr.pizzeria.admin.web.pizza;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.IngredientService;
import fr.pizzeria.admin.metier.PizzaService;

/**
 * Contrôleur de la page Liste des pizzas.
 */
@WebServlet("/pizzas/delete")
public class DeletePizzaController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(DeletePizzaController.class.getName());

	
	@Inject
	private PizzaService pizzaService;
	
	@Inject 
	private IngredientService ingredientService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String code = request.getParameter("code");

		pizzaService.delete(code);

		response.sendRedirect(request.getContextPath() + "/pizzas/list");

	}

}
