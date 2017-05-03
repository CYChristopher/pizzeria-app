package fr.pizzeria.admin.web.ouiche;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

import fr.pizzeria.admin.metier.IngredientService;
import fr.pizzeria.admin.metier.OuicheService;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Ingredient;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.TypePizza;

/**
 * Contrôleur de la page Liste des pizzas.
 */
@WebServlet("/ouiches/edit")
public class UpdateOuicheController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(UpdateOuicheController.class.getName());

	private static final String VUE_EDIT_OUICHES = "/WEB-INF/views/ouiches/editOuiche.jsp";
	private Integer id;

	@Inject
	private OuicheService ouicheService;

	@Inject
	private IngredientService ingredientService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.id = Integer.valueOf(request.getParameter("id"));

		Set<Object> setCategorie = new TreeSet<>();

		for (CategoriePizza current : CategoriePizza.values()) {
			setCategorie.add(current);
		}

		request.setAttribute("listeIngredients", this.ingredientService.findAll());
		request.setAttribute("editPizza", ouicheService.findById(this.id));
		request.setAttribute("categoriePizza", setCategorie);

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_EDIT_OUICHES);

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Pizza oldPizza = ouicheService.findById(this.id);
		String newcode;
		String ref;
		BigDecimal prix;
		String categorie;
		String urlImage;


		// getParameterValues envoie NullPointerException si la liste des
		// ingredients selectionnées est vide
		try {

			newcode = request.getParameter("newcode").isEmpty() ? (oldPizza.getCode())
					: request.getParameter("newcode");
			ref = request.getParameter("ref").isEmpty() ? oldPizza.getNom() : request.getParameter("ref");
			prix = request.getParameter("prix").isEmpty() ? oldPizza.getPrix()
					: BigDecimal.valueOf(Double.valueOf(request.getParameter("prix")));
			categorie = request.getParameter("categorie").isEmpty() ? oldPizza.getNom()
					: request.getParameter("categorie");
			urlImage = request.getParameter("urlImage").isEmpty() ? oldPizza.getUrlImage() : request.getParameter("urlImage");

			String[] ingredients = request.getParameterValues("ingredientSelectione");
			List<Ingredient> listIngredient = new ArrayList<>();

			for (String ing : ingredients) {
				listIngredient.add(ingredientService.findByName(ing));
			}

			oldPizza.setArchive(true);
			ouicheService.update(this.id, oldPizza);
			
			Pizza pizza = new Pizza(newcode, ref, prix,	CategoriePizza.valueOf(categorie),  urlImage, 
					 LocalDateTime.now(), false, TypePizza.OUICHE, listIngredient);
			ouicheService.save(pizza);

			response.sendRedirect(request.getContextPath() + "/ouiches/list");

		} catch (NullPointerException e) {
			request.setAttribute("msg", "Liste des ingredients vide");
			doGet(request, response);
		}

	}

}
