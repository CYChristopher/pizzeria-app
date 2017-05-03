package fr.pizzeria.admin.web.dessert;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.DessertService;
import fr.pizzeria.model.Dessert;

/**
 * Contrôleur de la page Liste des desserts.
 */
@WebServlet("/historiqueDesserts/liste")
public class ListerHistoriqueDessertController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(ListerHistoriqueDessertController.class.getName());

	private static final String VUE_LISTER_DESSERTS = "/WEB-INF/views/desserts/listerAllDesserts.jsp";

	@Inject
	private DessertService dessertService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
		req.setAttribute("listeDesserts", this.dessertService.findAll());
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_DESSERTS);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		Integer id = Integer.valueOf(request.getParameter("id"));

		Dessert editerDessert = this.dessertService.find(id);

		editerDessert.setArchive(!editerDessert.getArchive());

		this.dessertService.update(id, editerDessert);

		response.sendRedirect(request.getContextPath() + "/historiqueDesserts/liste");

	}

}
