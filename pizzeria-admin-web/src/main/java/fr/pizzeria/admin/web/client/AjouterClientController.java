package fr.pizzeria.admin.web.client;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.ClientService;

@WebServlet("/clients/new")
public class AjouterClientController extends HttpServlet {
	
private static final String VUE_AJOUTER_CLIENTS = "/WEB-INF/views/clients/ajouterClients.jsp";
	
	@Inject private ClientService clientService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_AJOUTER_CLIENTS).forward(request, response);
	}

}
