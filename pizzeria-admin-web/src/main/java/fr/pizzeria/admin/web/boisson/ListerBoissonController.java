package fr.pizzeria.admin.web.boisson;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.BoissonService;

@WebServlet("/boissons/list")
public class ListerBoissonController extends HttpServlet {

	private static final String VUE_LISTER_BOISSON = "/WEB-INF/views/boisson/listerBoissons.jsp";

	@EJB
	private BoissonService boissonService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setAttribute("listeBoissons", this.boissonService.findAll());
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_BOISSON);
		dispatcher.forward(req, resp);
	}

}
