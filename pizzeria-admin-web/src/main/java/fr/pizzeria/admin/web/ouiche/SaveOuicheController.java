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
@WebServlet("/ouiches/new")
public class SaveOuicheController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(SaveOuicheController.class.getName());

	private static final String VUE_SAVE_OUICHES = "/WEB-INF/views/ouiches/saveOuiche.jsp";

	@Inject
	private OuicheService ouicheService;

	@Inject
	private IngredientService ingredientService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Set<Object> setCategorie = new TreeSet<>();

		for (CategoriePizza current : CategoriePizza.values()) {
			setCategorie.add(current);

		}
		req.setAttribute("listeIngredients", this.ingredientService.findAll());
		req.setAttribute("categoriePizza", setCategorie);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_SAVE_OUICHES);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		try {

			if (!request.getParameter("newcode").isEmpty() && !request.getParameter("ref").isEmpty()
							&& !request.getParameter("prix").isEmpty()) {
				String newcode = request.getParameter("newcode");
				String ref = request.getParameter("ref");
				String prix = request.getParameter("prix");
				String categorie = request.getParameter("categorie");
				String urlImage = request.getParameter("urlImage");
				String[] ingredients = request.getParameterValues("ingredientSelectione");

				List<Ingredient> listIngredient = new ArrayList<>();

				for (String ing : ingredients) {
					listIngredient.add(this.ingredientService.findByName(ing));
				}

				Pizza pizza = new Pizza(newcode, ref, BigDecimal.valueOf(Double.valueOf(prix)),
								CategoriePizza.valueOf(categorie),  urlImage,
								LocalDateTime.now(), true, TypePizza.OUICHE, listIngredient);

				this.ouicheService.save(pizza);

				response.sendRedirect(request.getContextPath() + "/ouiches/liste");

			} else {
				String erreur[] = { "", "", "", "" };
				if (request.getParameter("newcode").isEmpty()) {
					erreur[0] = "red";
				} else {
					request.setAttribute("newcode", request.getParameter("newcode"));
				}

				if (request.getParameter("ref").isEmpty()) {
					erreur[1] = "red";
				} else {
					request.setAttribute("ref", request.getParameter("ref"));
				}

				if (request.getParameter("prix").isEmpty()) {
					erreur[2] = "red";
				} else {
					request.setAttribute("prix", request.getParameter("prix"));
				}

				request.setAttribute("erreur", erreur);
				request.setAttribute("msg", "Veuillez saisir les champs en rouge:");
				this.doGet(request, response);
			}

		} catch (NullPointerException e) {
			request.setAttribute("msg", "Liste des ingredients vide");
			request.setAttribute("newcode", request.getParameter("newcode"));
			request.setAttribute("ref", request.getParameter("ref"));
			request.setAttribute("prix", request.getParameter("prix"));
			this.doGet(request, response);
		}

	}

}
