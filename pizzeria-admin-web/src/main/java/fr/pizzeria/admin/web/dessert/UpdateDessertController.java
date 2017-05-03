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
@WebServlet("/desserts/editer")
public class UpdateDessertController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(UpdateDessertController.class.getName());

	private static final String VUE_EDIT_DESSERT = "/WEB-INF/views/desserts/editDesserts.jsp";
	private static final String URL_LISTE = "/desserts/liste";

	private Integer id;

	@Inject
	private DessertService dessertService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.id = Integer.parseInt(request.getParameter("id"));

		request.setAttribute("editDessert", this.dessertService.find(this.id));

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_EDIT_DESSERT);

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Dessert oldDessert = this.dessertService.find(this.id);

		String newcode;
		String ref;
		BigDecimal prix;

		newcode = request.getParameter("newcode").isEmpty() ? (oldDessert.getCode()) : request.getParameter("newcode");
		ref = request.getParameter("ref").isEmpty() ? oldDessert.getNom() : request.getParameter("ref");
		prix = request.getParameter("prix").isEmpty() ? oldDessert.getPrix()
				: BigDecimal.valueOf(Double.valueOf(request.getParameter("prix")));
		String urlImage = request.getParameter("urlImage").isEmpty() ? oldDessert.getUrlImage() : request.getParameter("urlImage");

		oldDessert.setArchive(true);
		dessertService.update(this.id, oldDessert);
		
<<<<<<< HEAD
		Dessert dessert = new Dessert(newcode, ref, prix, urlImage);
		dessertService.save(dessert);
=======
		this.dessertService.update(this.id, dessert);
>>>>>>> refs/remotes/origin/BUG007-ArchivageDonneesSupprimees

		response.sendRedirect(request.getContextPath() + URL_LISTE);

	}

}