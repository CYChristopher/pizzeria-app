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
		
		if (!req.getParameter("nom").isEmpty() 
				&& !req.getParameter("quantite").isEmpty() 
				&& !req.getParameter("prix").isEmpty()) {
			
			LOG.log(Level.INFO, "------------- !!! -------------  nom :" + req.getParameter("nom"));
			LOG.log(Level.INFO, "------------- !!! -------------  quantite :" + req.getParameter("quantite"));
			LOG.log(Level.INFO, "------------- !!! -------------  prix :" + req.getParameter("prix"));
			
			Ingredient i = new Ingredient(
					req.getParameter("nom").toString(), 
					Integer.valueOf(req.getParameter("quantite").toString()), 
					Double.valueOf(req.getParameter("prix").toString())
					);
			
			LOG.log(Level.INFO, "-------!!----- Ajout ingrédient :" + i.toString());
			
			ingredientService.save(i);
			
			resp.sendRedirect(req.getContextPath() + "/ingredients/list");
		} else {
			String erreur[] = { "", "", "" };
			if (req.getParameter("nom").isEmpty()) {
				erreur[0] = "red";
			} else {
				req.setAttribute("nom", req.getParameter("nom"));
			}
			
			if(req.getParameter("quantite").isEmpty()){
				erreur[1] = "red";
			} else {
				req.setAttribute("quantite", req.getParameter("quantite"));
			}
			
			if(req.getParameter("prix").isEmpty()){
				erreur[2] = "red";
			} else {
				req.setAttribute("prix", req.getParameter("prix"));
			}
			
			req.setAttribute("erreur", erreur);
			req.setAttribute("msg", "Veuillez saisir les champs en rouge : ");
			doGet(req, resp);
		}
		
		// redirection vers la liste des ingredients
		resp.sendRedirect(req.getContextPath() + "/ingredients/list");
	}
}
