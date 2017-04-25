package fr.pizzeria.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String prenom;
	private String email;
	private String motDePasse;
	private String adresse; 
	private LocalDateTime dateCreation;
	
	
	public Client(Integer id, String nom, String prenom, String email, String motDePasse, String adresse) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.adresse = adresse;
	}
	public Client(String nom, String prenom, String email, String motDePasse, String adresse) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.motDePasse = motDePasse;
		this.adresse = adresse;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
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
		this.motDePasse = motDePasse;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public LocalDateTime getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}
	
	public static String encode(String motDePasse, LocalDateTime dateCreation){
		String motDePasseHashe = motDePasse;
		try {
	        byte[] bytesOfMessage = (motDePasse + dateCreation.toString()).getBytes("UTF-8");
	        MessageDigest md;
	        md = MessageDigest.getInstance("SHA");
	        byte[] thedigest = md.digest(bytesOfMessage);
	        motDePasseHashe = new String(thedigest);
	    } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }
		return motDePasseHashe;
	}

}
