package fr.pizzeria.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numeroCommande;
	@Enumerated(EnumType.STRING)
	private StatutCommande statut;
	private LocalDateTime dateCommande;
	private String adresse;
	@ManyToOne
	private Livreur livreur;
	@ManyToOne
	private Client client;
	
	@OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
	private List<CommandePizza> commandesPizzas = new ArrayList<>();
	
	private List<Pizza> listPizzas = new ArrayList<>();

	public Commande(String numCommande, StatutCommande valueOf, String adresse2, Livreur livreur2, Client client2,
			List<Pizza> listePizza) {
		this.numeroCommande = numCommande;
		this.statut = valueOf;
		this.adresse = adresse2;
		this.livreur = livreur2;
		this.client = client2;
		this.setListPizzas(listePizza);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroCommande() {
		return numeroCommande;
	}

	public void setNumeroCommande(String numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	public StatutCommande getStatut() {
		return statut;
	}

	public void setStatut(StatutCommande statut) {
		this.statut = statut;
	}

	public LocalDateTime getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(LocalDateTime dateCommande) {
		this.dateCommande = dateCommande;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public Livreur getLivreur() {
		return livreur;
	}

	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<CommandePizza> getCommandesPizzas() {
		return commandesPizzas;
	}

	public void setCommandesPizzas(List<CommandePizza> commandesPizzas) {
		this.commandesPizzas = commandesPizzas;
	}

	public List<Pizza> getListPizzas() {
		return listPizzas;
	}

	public void setListPizzas(List<Pizza> listPizzas) {
		this.listPizzas = listPizzas;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", numeroCommande=" + numeroCommande + "]";
	}
	
	
}