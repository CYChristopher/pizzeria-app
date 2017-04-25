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
 * @author collidorlionel
 * contrôleur de la page ajout d'un ingredient.
 */
@WebServlet("/ingredient/add")
public class AjouterIngredientController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(AjouterIngredientController.class.getName());
	
	private static final String VUE_LISTER_INGREDIENTS = "/WEB-INF/views/ingredients/ajouterIngredient.jsp";
	
	@Inject
	private IngredientService ingredientService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_INGREDIENTS);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Ingredient i = new Ingredient(req.getAttribute("nom").toString(), 
				Integer.parseInt(req.getAttribute("qte").toString()), 
				Double.parseDouble(req.getAttribute("prix").toString()));
		LOG.log(Level.INFO, "-------!!----- Ajout ingrédient :" + i.toString());
		// si un id est présent c'est un update si non c'est un create
		if(req.getAttribute("id").toString().isEmpty() || req.getAttribute("id").toString().equals(null)){
			ingredientService.save(i);
		}else {
			i.setId(Integer.parseInt(req.getAttribute("id").toString()));
			//ingredientService.update(i);
		}
		
		// redirection vers la liste des ingredients
		resp.sendRedirect(req.getContextPath() + "/ingredients/list");
	}
}
