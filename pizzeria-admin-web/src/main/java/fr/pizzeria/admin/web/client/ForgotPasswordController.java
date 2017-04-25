package fr.pizzeria.admin.web.client;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ForgotPasswordController")
public class ForgotPasswordController extends HttpServlet {
	
	private static final String VUE_LISTER_CLIENTS = "/clients/list";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath()+VUE_LISTER_CLIENTS);
	}

}
