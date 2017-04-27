package fr.pizzeria.admin.web.promotion;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.PromotionService;
import fr.pizzeria.model.Client;

@WebServlet("/promotions/new")
public class AjouterPromotionController extends HttpServlet {
	
	private static final String VUE_AJOUTER_PROMOTIONS = "/WEB-INF/views/promotions/ajouterPromotions.jsp";
	private static final String VUE_LISTER_PROMOTIONS = "/promotions/list";
	
	@Inject private PromotionService promotionService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_AJOUTER_PROMOTIONS).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String code = request.getParameter("code").trim();
		// A FAIRE
		response.sendRedirect(request.getContextPath()+VUE_LISTER_PROMOTIONS);
	}

}
