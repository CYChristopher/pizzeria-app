package fr.pizzeria.admin.web.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.ClientService;
import fr.pizzeria.model.Client;

@WebServlet("/clients/edit")
public class EditerClientController extends HttpServlet {

	private static final String VUE_EDITER_CLIENTS = "/WEB-INF/views/clients/editerClients.jsp";
	private static final String VUE_LISTER_CLIENTS = "/clients/list";
	
	@Inject
	private ClientService clientService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client client = clientService.getById(Integer.valueOf(request.getParameter("id")));
		request.setAttribute("client", client);
		this.getServletContext().getRequestDispatcher(VUE_EDITER_CLIENTS).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		String nom = request.getParameter("nom").trim();
		String prenom = request.getParameter("prenom").trim();
		String email = request.getParameter("email").trim();
		String motDePasse = request.getParameter("motDePasse");
		String adresse = request.getParameter("adresse").trim();
		Client client = new Client(nom, prenom, email, motDePasse, adresse);
		clientService.update(id, client);
		response.sendRedirect(request.getContextPath()+VUE_LISTER_CLIENTS);
	}

}
