package fr.pizzeria.admin.web.boisson;

import java.io.IOException;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.BoissonService;
import fr.pizzeria.model.Boisson;

/**
 * Contrôleur de la page Liste des boissons.
 */
@WebServlet("/historiqueBoissons/liste")
public class ListerHistoriqueBoissonController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(ListerHistoriqueBoissonController.class.getName());

	private static final String VUE_LISTER_BOISSONS = "/WEB-INF/views/boissons/listerAllBoissons.jsp";

	@Inject
	private BoissonService boissonService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
		req.setAttribute("listeBoissons", this.boissonService.findAll());
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_BOISSONS);
		dispatcher.forward(req, resp);
	}


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		Integer id = Integer.valueOf(request.getParameter("id"));



		Boisson editerBoisson = this.boissonService.find(id);

		editerBoisson.setArchive(!editerBoisson.getArchive());

		this.boissonService.update(id, editerBoisson);

		response.sendRedirect(request.getContextPath() + "/historiqueBoissons/liste");

	}


}
