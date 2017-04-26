package fr.pizzeria.admin.web.ingredient;

import java.io.IOException;
import java.util.logging.Level;
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
 * Servlet de modification des ingredients.
 */
@WebServlet("/ingredient/edit")
public class ModifierIngredientController extends HttpServlet {
	
	private static final Logger LOG = Logger.getLogger(ModifierIngredientController.class.getName());
	
	private static final String VUE_EDIT_INGREDIENT = "/WEB-INF/views/ingredients/modifierIngredient.jsp";
	private Integer id;
	
	@Inject private IngredientService ingredientService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		this.id = Integer.valueOf(req.getParameter("id").toString());
		
		req.setAttribute("editIngredient", ingredientService.findById(this.id));
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_EDIT_INGREDIENT);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		LOG.log(Level.INFO, "-------!!!------- modification de l'ingredient n : " + this.id);
		
		Ingredient old = ingredientService.findById(this.id);
		
		String nom = req.getParameter("nom").isEmpty() ? old.getNom() : req.getParameter("nom").toString();
		Integer quantite = req.getParameter("quantite").isEmpty() ? old.getQuantite() : Integer.valueOf(req.getParameter("quantite").toString());
		Double prix = req.getParameter("prix").isEmpty() ? old.getPrix() : Double.valueOf(req.getParameter("prix").toString());
		
		Ingredient ingredient = new Ingredient(nom, quantite, prix);
		
		ingredientService.update(this.id, ingredient);
		
		resp.sendRedirect(req.getContextPath() + "/ingredients/list");
	}

}
