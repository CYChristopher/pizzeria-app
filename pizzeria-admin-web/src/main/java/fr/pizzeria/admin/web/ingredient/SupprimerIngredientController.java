package fr.pizzeria.admin.web.ingredient;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.IngredientService;


@WebServlet("/ingredient/delete")
public class SupprimerIngredientController extends HttpServlet {
	
	private static final Logger LOG = Logger.getLogger(SupprimerIngredientController.class.getName());
	
	private Integer id;
	@Inject private IngredientService ingredientService;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		LOG.log(Level.INFO, "-------!!!------- suppression de l'ingredient n : " + this.id);
		
		this.id = Integer.valueOf(req.getParameter("id").toString());
		ingredientService.delete(this.id);
		
		resp.sendRedirect(req.getContextPath() + "/ingredients/list");
	}
}
