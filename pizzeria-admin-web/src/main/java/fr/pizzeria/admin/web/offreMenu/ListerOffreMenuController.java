package fr.pizzeria.admin.web.offreMenu;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.OffreMenuService;

@WebServlet("/offre_menu/liste")
public class ListerOffreMenuController extends HttpServlet {
	
	private static final Logger LOG = Logger.getLogger(ListerOffreMenuController.class.getName());

	private static final String VUE_LISTER_OFFRE_MENU = "/WEB-INF/views/offreMenu/listerOffreMenu.jsp";

	@Inject
	private OffreMenuService oms;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			req.setAttribute("listeOffreMenu", this.oms.findAll());
		} catch (NoResultException e) {
			LOG.log(Level.WARNING, "-------!!!------- exception levée : " + e.getMessage() + " => " + e.getCause());
			req.setAttribute("msg", "Erreur du serveur, merci de contacter le support de l'application ");
		} finally {
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher(VUE_LISTER_OFFRE_MENU);
			dispatcher.forward(req, resp);
		}
	}
	
	/**
	 * Méthode de d'archivage de l'offre menu
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.valueOf(req.getParameter("id").toString());
		LOG.log(Level.INFO, "-------!!!------- suppression/archivage de l'ingredient n : " + id);
		
		try {
			oms.archive(id);
		} catch (NoResultException e) {
			LOG.log(Level.WARNING, "-------!!!------- exception levée : " + e.getMessage() + " => " + e.getCause());
			req.setAttribute("msg", "Erreur du serveur, merci de contacter le support de l'application ");
		} finally {
			resp.sendRedirect(req.getContextPath() + "/offre_menus/liste");
		}
	}
}
