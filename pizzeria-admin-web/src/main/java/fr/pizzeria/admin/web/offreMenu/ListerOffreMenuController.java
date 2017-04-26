package fr.pizzeria.admin.web.offreMenu;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.OffreMenuService;

@WebServlet("/offre_menu/liste")
public class ListerOffreMenuController extends HttpServlet {

	private static final String VUE_LISTER_OFFRE_MENU = "/WEB-INF/views/offreMenu/listerOffreMenu.jsp";

	@Inject
	private OffreMenuService oms;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("listeOffreMenu", this.oms.findAll());
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_OFFRE_MENU);
		dispatcher.forward(req, resp);
	}

}
