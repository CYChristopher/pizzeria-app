package fr.pizzeria.admin.web.client;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.admin.metier.ClientService;
import fr.pizzeria.model.Client;

@WebServlet("/clients/password")
public class ForgotPasswordController extends HttpServlet {

	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

	private static final String VUE_LISTER_CLIENTS = "/clients/liste";

	@Inject
	private ClientService clientService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
					throws ServletException, IOException {

		Client client = this.clientService.getById(Integer.valueOf(request.getParameter("id")));


		// Parameters
		String host = "smtp.gmail.com";
		String user = "dta201702pizzayolo@gmail.com";
		String password = "dtapizza";
		String to = client.getEmail();

		// Get system properties
		Properties props = new Properties();

		// Setup mail server
		props.put("mail.smtp.host", host);

		// Disable the authentication
		props.setProperty("mail.smtp.auth", "true");

		// Enable TSL
		props.put("mail.smtp.starttls.enable", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(props);

		StringBuilder mdp = new StringBuilder();
		Random random = new Random();
		for(int i=0; i<10; i++){
			int number = (random.nextInt(CHAR_LIST.length()));
			mdp.append(CHAR_LIST.charAt(number));
		}

		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Réinitialisation du mot de passe");
			message.setText("Votre mot de passe temporaire est le suivant: " + mdp.toString() + "\nVeuillez le changer au plus vite.");
			Transport transport = session.getTransport("smtp");
			transport.connect(host, user, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			client.setMotDePasse(mdp.toString());
			this.clientService.update(client.getId(), client);
		} catch(MessagingException e) {
			e.printStackTrace();
		}

		response.sendRedirect(request.getContextPath() + VUE_LISTER_CLIENTS);
	}

}
