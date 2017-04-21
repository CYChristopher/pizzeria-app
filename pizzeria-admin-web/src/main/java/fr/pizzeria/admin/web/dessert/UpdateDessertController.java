package fr.pizzeria.admin.web.dessert;

import java.io.IOException;
import java.math.BigDecimal;
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

import fr.pizzeria.admin.metier.DessertService;
import fr.pizzeria.model.Dessert;

/**
 * Contrôleur de la page Liste des pizzas.
 */
@WebServlet("/desserts/edit")
public class UpdateDessertController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(UpdateDessertController.class.getName());

	private static final String VUE_EDIT_DESSERT = "/WEB-INF/views/desserts/editDesserts.jsp";
	private String code;

	@Inject
	private DessertService dessertService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.code = request.getParameter("code");

		Set<Object> setCategorie = new TreeSet<>();

		
		
		request.setAttribute("editDessert", dessertService.findbycode(this.code));
		
		RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher(VUE_EDIT_DESSERT);

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Dessert oldPizza = dessertService.findbycode(this.code);
		
		String newcode ;
		String ref  ;
		BigDecimal prix  ;
		
		
		newcode = request.getParameter("newcode").isEmpty()?(oldPizza.getCode()):request.getParameter("newcode");
		ref = request.getParameter("ref").isEmpty()?oldPizza.getNom():request.getParameter("ref");
		prix = request.getParameter("prix").isEmpty()?oldPizza.getPrix():BigDecimal.valueOf(Double.valueOf(request.getParameter("prix")));
			
		Dessert dessert = new Dessert(newcode, ref, prix);

		dessertService.update(this.code, dessert);

		response.sendRedirect(request.getContextPath() + "/desserts/list");

	}

}