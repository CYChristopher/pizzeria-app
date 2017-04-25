package fr.pizzeria.admin.web.utilisateurs;

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

import fr.pizzeria.admin.metier.UtilisateursService;
import fr.pizzeria.model.Utilisateur;

@WebServlet("/utilisateurs/password")
public class ForgotPasswordUtilisateurController extends HttpServlet {
	
	private static final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final String VUE_LOGIN = "/utilisateurs/login";
	
	@Inject
	private UtilisateursService utilisateurService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur utilisateur = utilisateurService.find(Integer.valueOf(request.getParameter("id")));
		
		
		// Parameters
		String host = "smtp.gmail.com";
		String user = "dta201702pizzayolo@gmail.com";
		String password = "dtapizza";
		String to = utilisateur.getEmail();

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
            int number = (int)(random.nextInt(CHAR_LIST.length()));
            mdp.append(CHAR_LIST.charAt(number));
        }

		try {
			MimeMessage message = new MimeMessage(session);
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Réinitialisation du mot de passe");
			message.setText("Votre mot de passe temporaire est le suivant: " + mdp.toString() + "\n"
					+ "Veuillez vous connecter et le changer au plus vite en cliquant sur le lien ci-dessous.\n"
					+ request.getContextPath() + VUE_LOGIN);
			Transport transport = session.getTransport("smtp");
	        transport.connect(host, user, password);
	        transport.sendMessage(message, message.getAllRecipients());
	        transport.close();
	        utilisateur.setMotDePasse(mdp.toString());
	        utilisateurService.update(utilisateur.getId(), utilisateur);
		} catch(MessagingException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + VUE_LOGIN);
		
	}

}
