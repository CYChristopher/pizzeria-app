package fr.pizzeria.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Commande {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String numeroCommande;
	@Enumerated(EnumType.STRING)
	private StatutCommande statut;
	private String adresse;
	@Enumerated(EnumType.STRING)
	private TypeCommande type;
	private LocalDateTime dateCommande;
	@ManyToOne
	private Livreur livreur;
	@ManyToOne
	private Client client;

	@OneToMany(mappedBy = "id.commande", cascade = CascadeType.ALL)
	private List<CommandePizza> commandesPizzas = new ArrayList<>();

	public Commande() {
	}

	public Commande(String numCommande, StatutCommande statut, String adresse, Livreur livreur, Client client) {
		this.numeroCommande = numCommande;
		this.statut = statut;
		this.adresse = adresse;
		this.livreur = livreur;
		this.client = client;
	}

	public Commande(String numCommande, StatutCommande statut, Client client, List<CommandePizza> commandePizzas) {
		this.numeroCommande = numCommande;
		this.statut = statut;
		this.adresse = this.adresse;
		this.livreur = this.livreur;
		this.client = client;
		this.commandesPizzas = commandePizzas;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumeroCommande() {
		return this.numeroCommande;
	}

	public void setNumeroCommande(String numeroCommande) {
		this.numeroCommande = numeroCommande;
	}

	public StatutCommande getStatut() {
		return this.statut;
	}

	public void setStatut(StatutCommande statut) {
		this.statut = statut;
	}

	public LocalDateTime getDateCommande() {
		return this.dateCommande;
	}

	public void setDateCommande(LocalDateTime dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Livreur getLivreur() {
		return this.livreur;
	}

	public void setLivreur(Livreur livreur) {
		this.livreur = livreur;
	}

	public Client getClient() {
		return this.client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<CommandePizza> getCommandesPizzas() {
		return this.commandesPizzas;
	}

	public void setCommandesPizzas(List<CommandePizza> commandesPizzas) {
		this.commandesPizzas = commandesPizzas;
	}

	@Override
	public String toString() {
		return "Commande [id=" + this.id + ", numeroCommande=" + this.numeroCommande + "]";
	}

	public TypeCommande getType() {
		return this.type;
	}

	public void setType(TypeCommande type) {
		this.type = type;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

}
