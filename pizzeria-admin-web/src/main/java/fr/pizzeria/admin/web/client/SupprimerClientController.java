package fr.pizzeria.admin.web.client;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.ClientService;

@WebServlet("/clients/delete")
public class SupprimerClientController extends HttpServlet {

	private static final String VUE_LISTER_CLIENTS = "/clients/liste";

	@Inject
	private ClientService clientService;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {
		this.clientService.delete(Integer.valueOf(request.getParameter("id")));
		response.sendRedirect(request.getContextPath()+VUE_LISTER_CLIENTS);
	}

}
