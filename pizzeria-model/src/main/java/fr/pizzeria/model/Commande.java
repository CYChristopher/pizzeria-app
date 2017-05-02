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
	@Enumerated(EnumType.STRING)
	private TypeCommande type;
	private LocalDateTime dateCommande;
	@ManyToOne
	private Livreur livreur;
	@ManyToOne
	private Client client;

	@OneToMany(mappedBy = "id.commande", cascade = CascadeType.ALL)
	private List<CommandePizza> commandesPizzas = new ArrayList<>();

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
		return dateCommande;
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

}
