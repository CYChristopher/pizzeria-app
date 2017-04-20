package fr.pizzeria.admin.web.pizza;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Contrôleur de la page Liste des pizzas.
 */
@WebServlet("/pizzas/new")
public class SavePizzaController extends HttpServlet {

  private static final Logger LOG = Logger.getLogger(SavePizzaController.class.getName());

  private static final String VUE_SAVE_PIZZA = "/WEB-INF/views/pizzas/savePizza.jsp";

  @Inject private PizzaService pizzaService;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
 
    RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_SAVE_PIZZA);
    dispatcher.forward(req, resp);
  }
  
  
  @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String newcode = request.getParameter("newcode");
		String ref = request.getParameter("ref");
		String prix = request.getParameter("prix");
		String categorie = request.getParameter("categorie");

		System.out.println("coucou " +newcode+" "+ ref+" "+ prix+" " + categorie + " "+ CategoriePizza.valueOf("VIANDE"));
		
		Pizza pizza = new Pizza(newcode,ref, BigDecimal.valueOf(Double.valueOf(prix)), CategoriePizza.valueOf(categorie));
		
		pizzaService.save(pizza);
		response.sendRedirect(request.getContextPath() + "/pizzas/list");
		
		

	}
  
  

}
