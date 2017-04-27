package fr.pizzeria.admin.web.utilisateurs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class DeconnexionUtilisateurController extends HttpServlet {
	
	private static final String VUE_LOGIN = "/login";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath()+VUE_LOGIN);
	}

}
