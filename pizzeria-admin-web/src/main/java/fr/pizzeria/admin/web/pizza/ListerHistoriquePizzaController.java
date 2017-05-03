package fr.pizzeria.admin.web.pizza;

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
import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.model.Pizza;

/**
 * Contrôleur de la page Liste des pizzas.
 */
@WebServlet("/historiquePizzas/liste")
public class ListerHistoriquePizzaController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(ListerHistoriquePizzaController.class.getName());

	private static final String VUE_LISTER_PIZZAS = "/WEB-INF/views/pizzas/listerAllPizzas.jsp";

	@Inject private PizzaService pizzaService;

	@Inject
	private IngredientService ingredientService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listePizzas", this.pizzaService.findAll());
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_PIZZAS);
		dispatcher.forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		Integer id = Integer.valueOf(request.getParameter("id"));



		Pizza editerPizza = this.pizzaService.findById(id);

		editerPizza.setArchive(!editerPizza.getArchive());

		this.pizzaService.update(id,editerPizza);

		response.sendRedirect(request.getContextPath() + "/historiquePizzas/liste");

	}


}
