package fr.pizzeria.admin.web.pizza;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.TreeSet;
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
@WebServlet("/pizza/edit")
public class UpdatePizzaController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(UpdatePizzaController.class.getName());

	private static final String VUE_EDIT_PIZZA = "/WEB-INF/views/pizzas/editPizza.jsp";
	private Integer id;

	@Inject
	private PizzaService pizzaService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.id = Integer.valueOf(request.getParameter("id"));

		Set<Object> setCategorie = new TreeSet<>();

		for (CategoriePizza current : CategoriePizza.values()) {
			setCategorie.add(current);
		}
		
		request.setAttribute("editPizza", pizzaService.findById(this.id));
		request.setAttribute("categoriePizza", setCategorie);

		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher(VUE_EDIT_PIZZA);

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Pizza oldPizza = pizzaService.findById(this.id);
		
		String newcode ;
		String ref  ;
		BigDecimal prix  ;
		String categorie ;
		
		
		newcode = request.getParameter("newcode").isEmpty()?(oldPizza.getCode()):request.getParameter("newcode");
		ref = request.getParameter("ref").isEmpty()?oldPizza.getNom():request.getParameter("ref");
		prix = request.getParameter("prix").isEmpty()?oldPizza.getPrix():BigDecimal.valueOf(Double.valueOf(request.getParameter("prix")));
		categorie = request.getParameter("categorie").isEmpty()?oldPizza.getNom():request.getParameter("categorie");
		

		Pizza pizza = new Pizza(newcode, ref, prix, CategoriePizza.valueOf(categorie),LocalDateTime.now());
			
		pizzaService.save(pizza);

		response.sendRedirect(request.getContextPath() + "/pizzas/list");

	}

}
