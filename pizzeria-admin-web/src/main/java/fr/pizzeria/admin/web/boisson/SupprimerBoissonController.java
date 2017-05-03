package fr.pizzeria.admin.web.boisson;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.BoissonService;

@WebServlet("/boissons/supprimer")
public class SupprimerBoissonController extends HttpServlet {

	@EJB
	private BoissonService boissonService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Integer id = Integer.parseInt(request.getParameter("id"));

		boissonService.delete(id);

		response.sendRedirect(request.getContextPath() + "/boissons/list");
	}
}