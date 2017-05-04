package fr.pizzeria.admin.web;

import java.io.IOException;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.ClientService;
import fr.pizzeria.admin.metier.PizzaService;

@WebServlet("/technique")
public class TechniqueController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(TechniqueController.class.getName());
	private static final String VUE_TECHNIQUE = "/WEB-INF/views/technique/technique.jsp";

	@EJB
	private PizzaService pizzaService;

	@EJB
	private ClientService clientService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("topFivePizzas", this.pizzaService.findTopFivePizzas());		
		req.setAttribute("topFiveClients", this.clientService.findTopFiveClients());		
		this.getServletContext().getRequestDispatcher(VUE_TECHNIQUE).forward(req, resp);
	}

}
