package fr.pizzeria.admin.web.dessert;

import java.io.IOException;
import java.math.BigDecimal;
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
@WebServlet("/desserts/ajouter")
public class SaveDessertController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(SaveDessertController.class.getName());

	private static final String VUE_SAVE_DESSERT = "/WEB-INF/views/desserts/saveDesserts.jsp";
	private static final String URL_LISTE = "/desserts/liste";

	@Inject
	private DessertService dessertService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_SAVE_DESSERT);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!request.getParameter("newcode").isEmpty() && !request.getParameter("ref").isEmpty()
				&& !request.getParameter("prix").isEmpty()) {
			String newcode = request.getParameter("newcode");
			String ref = request.getParameter("ref");
			String prix = request.getParameter("prix");
			String urlImage = request.getParameter("urlImage") ;

			Dessert dessert = new Dessert(newcode, ref, BigDecimal.valueOf(Double.valueOf(prix)), urlImage);

			dessertService.save(dessert);

			response.sendRedirect(request.getContextPath() + URL_LISTE);

		} else {
			String erreur[] = { "", "", "" };
			if (request.getParameter("newcode").isEmpty()) {
				erreur[0] = "red";
			} else {
				request.setAttribute("newcode", request.getParameter("newcode"));
			}

			if (request.getParameter("ref").isEmpty()) {
				erreur[1] = "red";
			} else {
				request.setAttribute("ref", request.getParameter("ref"));
			}

			if (request.getParameter("prix").isEmpty()) {
				erreur[2] = "red";
			} else {
				request.setAttribute("prix", request.getParameter("prix"));
			}

			request.setAttribute("erreur", erreur);
			request.setAttribute("msg", "Veuillez saisir les champs en rouge:");
			doGet(request, response);
		}

	}

}