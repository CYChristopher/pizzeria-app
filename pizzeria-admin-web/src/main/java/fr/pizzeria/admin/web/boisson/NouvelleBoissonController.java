package fr.pizzeria.admin.web.boisson;

import java.io.IOException;
import java.math.BigDecimal;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.BoissonService;
import fr.pizzeria.model.Boisson;
import fr.pizzeria.model.Dessert;

/**
 * Contrôleur de la page Liste des boissons.
 */
@WebServlet("/boissons/nouvelle")
public class NouvelleBoissonController extends HttpServlet {

	private static final String VUE_SAVE_BOISSON = "/WEB-INF/views/boisson/nouvelleBoisson.jsp";

	@Inject
	private BoissonService boissonService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_SAVE_BOISSON);
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!request.getParameter("code").isEmpty() && !request.getParameter("nom").isEmpty()
				&& !request.getParameter("prix").isEmpty()) {
			String code = request.getParameter("code");
			String nom = request.getParameter("nom");
			String prix = request.getParameter("prix");
			String urlImage = request.getParameter("urlImage") ;

			Boisson dessert = new Boisson(code, nom, Double.valueOf(prix), urlImage);

			boissonService.saveNew(dessert);

			response.sendRedirect(request.getContextPath() + "/boissons/list");

		} else {
			String erreur[] = { "", "", "" };
			if (request.getParameter("code").isEmpty()) {
				erreur[0] = "red";
			} else {
				request.setAttribute("code", request.getParameter("code"));
			}

			if (request.getParameter("nom").isEmpty()) {
				erreur[1] = "red";
			} else {
				request.setAttribute("nom", request.getParameter("nom"));
			}

			if (request.getParameter("prix").isEmpty()) {
				erreur[2] = "red";
			} else {
				request.setAttribute("prix", request.getParameter("prix"));
			}

			request.setAttribute("erreur", erreur);
			request.setAttribute("msg", "Veuillez remplir les champs en rouge, sinon ...");
			doGet(request, response);
		}

	}

}