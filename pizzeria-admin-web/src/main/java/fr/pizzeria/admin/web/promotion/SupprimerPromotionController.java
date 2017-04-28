package fr.pizzeria.admin.web.promotion;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.PromotionService;

@WebServlet("/promotions/delete")
public class SupprimerPromotionController extends HttpServlet {
	
	private static final String VUE_LISTER_PROMOTIONS = "/promotions/list";

	@Inject
	private PromotionService promotionService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		promotionService.delete(Integer.valueOf(request.getParameter("id")));
		response.sendRedirect(request.getContextPath()+VUE_LISTER_PROMOTIONS);
	}

}
