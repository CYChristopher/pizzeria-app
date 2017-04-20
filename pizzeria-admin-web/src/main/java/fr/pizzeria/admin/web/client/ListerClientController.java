package fr.pizzeria.admin.web.client;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.ClientService;

@WebServlet("/clients/list")
public class ListerClientController extends HttpServlet {
	
	private static final String VUE_LISTER_CLIENTS = "/WEB-INF/views/clients/listerClients.jsp";
	
	@Inject private ClientService clientService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("listeClients", clientService.findAll());
		this.getServletContext().getRequestDispatcher(VUE_LISTER_CLIENTS).forward(request, response);
	}

}
