package fr.pizzeria.admin.web.boisson;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.BoissonService;
import fr.pizzeria.model.Boisson;

@WebServlet("/boissons/edit")
public class EditerBoissonController extends HttpServlet {

	private static final String VUE_EDIT_BOISSON = "/WEB-INF/views/boissons/editerBoisson.jsp";
	private Integer id;

	@Inject
	private BoissonService boissonService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		this.id = Integer.parseInt(request.getParameter("id"));

		request.setAttribute("boisson", this.boissonService.find(this.id));

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_EDIT_BOISSON);

		dispatcher.forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		Boisson oldBoisson = this.boissonService.find(this.id);

		String nom = request.getParameter("nom").isEmpty() ? oldBoisson.getNom() : request.getParameter("nom");
		String code = request.getParameter("code").isEmpty() ? (oldBoisson.getCode()) : request.getParameter("code");
		Double prix = request.getParameter("prix").isEmpty() ? oldBoisson.getPrix()
				: Double.valueOf(request.getParameter("prix"));
		String urlImage = request.getParameter("urlImage").isEmpty() ? oldBoisson.getUrlImage() : request.getParameter("urlImage");

		Boisson boisson = new Boisson(code, nom, prix, urlImage);

		this.boissonService.update(this.id, boisson);

		response.sendRedirect(request.getContextPath() + "/boissons/liste");

	}

}