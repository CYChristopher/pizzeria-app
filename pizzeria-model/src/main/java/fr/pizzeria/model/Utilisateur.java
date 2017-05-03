package fr.pizzeria.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import fr.pizzeria.exception.UtilisateurRuntimeException;

@Entity
public class Utilisateur {

	private static final Logger LOG = Logger.getLogger(Utilisateur.class.getName());

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String prenom;
	private String email;
	private String motDePasse;
	private String adresse;
	private LocalDateTime dateCreation;

	public Utilisateur() {

	}

	public Utilisateur(Integer id, String nom, String prenom, String email, String motDePasse, String adresse,
			LocalDateTime dateCreation) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;

		this.motDePasse = hashSha1(motDePasse, dateCreation.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

		this.adresse = adresse;
		this.dateCreation = dateCreation;
	}

	public Utilisateur(String nom, String prenom, String email, String motDePasse, String adresse,
			LocalDateTime dateCreation) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;

		LOG.log(Level.INFO, "date avant d'aller en base avec un toString()"
				+ dateCreation.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

		this.motDePasse = hashSha1(motDePasse, dateCreation.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

		this.adresse = adresse;
		this.dateCreation = dateCreation;
	}

	/**
	 * Cette méthode renvoie un hash du mot de passe afin d'éviter de stocker
	 * les mots de passe en clair. On ne hash pas directement le mot de passe
	 * mais on concatène un sel avant.
	 * 
	 * @param pass
	 *            Le mot de passe à hasher
	 * @param sel
	 *            Le sel utilisé pour le hash, on utilisera la date de creation
	 *            avec un toString()
	 * @return
	 */
	public String hashSha1(String pass, String sel) {

		String motDePasseHashe = "";
		try {

			byte[] bytesOfMessage = (pass + sel).getBytes("UTF-8");
			MessageDigest md;
			md = MessageDigest.getInstance("SHA");
			byte[] thedigest = md.digest(bytesOfMessage);

			motDePasseHashe = new String(thedigest);

		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

			throw new UtilisateurRuntimeException("Erreur lors du hashage du mot de passe", e);

		}
		return motDePasseHashe;
	}

	/**
	 * Cette fonction hash un mot de passe et la compare avec le mot de passe
	 * hashé en stocké en base. Elle est utile pour l'authentification.
	 * 
	 * @param passAComparer
	 * @return
	 */
	public boolean motDePasseOk(String passAComparer) {

		String motDePasseHashe = hashSha1(passAComparer,
				dateCreation.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

		if (motDePasseHashe.equals(motDePasse))
			return true;
		return false;

	}

	public String getDateCreation() {
		return dateCreation.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = hashSha1(motDePasse, dateCreation.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
	}

	// Permet de directement inserer le hash (pour qu'edition ne force pas à
	// réecrire le mdp)
	public void setHash(String hash) {
		this.motDePasse = hash;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

}
