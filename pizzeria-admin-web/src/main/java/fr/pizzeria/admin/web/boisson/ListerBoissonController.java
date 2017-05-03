package fr.pizzeria.admin.web.boisson;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.exception.StockageException;
import fr.pizzeria.admin.metier.BoissonService;

@WebServlet("/boissons/liste")
public class ListerBoissonController extends HttpServlet {

	private static final Logger LOG = Logger.getLogger(ListerBoissonController.class.getName());
	private static final String VUE_LISTER_BOISSON = "/WEB-INF/views/boissons/listerBoissons.jsp";
	private static final String URL_LISTE = "/boissons/liste";

	@EJB
	private BoissonService boissonService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			req.setAttribute("listeBoissons", this.boissonService.findAllAvailable());
		} catch (StockageException e) {
			LOG.log(Level.WARNING, "-------!!!------- exception levée : " + e.getMessage() + " => " + e.getCause());
			req.setAttribute("msg", "Erreur du serveur, merci de contacter le support de l'application ");
		} finally {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_BOISSON);
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer id = Integer.valueOf(req.getParameter("id").toString());
		LOG.log(Level.INFO, "-------!!!------- suppression/archivage de l'ingredient n : " + id);
		try {
			this.boissonService.delete(id);
		} catch (StockageException e) {
			LOG.log(Level.WARNING, "-------!!!------- exception levée : " + e.getMessage() + " => " + e.getCause());
			req.setAttribute("msg", "Erreur du serveur, merci de contacter le support de l'application ");
		} finally {
			resp.sendRedirect(req.getContextPath() + URL_LISTE);
		}
	}

}
