package fr.pizzeria.admin.web.pizza;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.model.Pizza;

import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Contrôleur de la page Liste des pizzas.
 */
@WebServlet("/allPizzas/list")
public class ListerActivePizzaController extends HttpServlet {

  private static final Logger LOG = Logger.getLogger(ListerActivePizzaController.class.getName());

  private static final String VUE_LISTER_PIZZAS = "/WEB-INF/views/pizzas/listerAllPizzas.jsp";

  @Inject private PizzaService pizzaService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    req.setAttribute("listePizzas", this.pizzaService.findAll());
    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_PIZZAS);
    dispatcher.forward(req, resp);
  }
  
  
  @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = Integer.valueOf(request.getParameter("id"));

		
		
		Pizza editerPizza = pizzaService.findById(id);
		
		editerPizza.setActif(!editerPizza.getActif());
		
		pizzaService.update(id,editerPizza);

		response.sendRedirect(request.getContextPath() + "/pizzas/list");

	}
  

}
