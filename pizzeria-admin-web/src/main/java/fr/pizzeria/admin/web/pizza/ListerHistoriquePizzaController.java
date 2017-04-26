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
import fr.pizzeria.model.Ingredient;
import fr.pizzeria.model.Pizza;

/**
 * Contrôleur de la page Liste des pizzas.
 */
@WebServlet("/allPizzas/list")
public class ListerHistoriquePizzaController extends HttpServlet {

  private static final Logger LOG = Logger.getLogger(ListerHistoriquePizzaController.class.getName());

  private static final String VUE_LISTER_PIZZAS = "/WEB-INF/views/pizzas/listerAllPizzas.jsp";

  @Inject private PizzaService pizzaService;
  
  @Inject 
	private IngredientService ingredientService;	
  
 //private int test =0;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
	  
	 // pour creer vite fait des ingredients 
	 /* if(test==0) 
	  {
		  test =1;
		  System.out.println("SAVE INGREDIENT");
		  ingredientService.save(new Ingredient("Tomate", 10, 0.5));
		  ingredientService.save(new Ingredient("Champignon", 10, 0.5));
		  ingredientService.save(new Ingredient("Fromage", 10, 0.5));
		  
	  }*/
	    
	  
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

		response.sendRedirect(request.getContextPath() + "/allPizzas/list");

	}
  

}
