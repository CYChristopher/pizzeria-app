package fr.pizzeria.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
	@OneToMany(fetch = FetchType.EAGER) // permet de demander à JPA d'aller
										// charger les pizzas en profondeurs.
	@JoinTable(name = "commande_pizza", joinColumns = @JoinColumn(name = "commande_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "pizza_id", referencedColumnName = "id"))
	private List<Pizza> pizzas = new ArrayList<>();

	public Commande() {
	}

	public Commande(String numeroCommande, StatutCommande statut, String adresse, Livreur livreur, Client client,
			List<Pizza> pizzas) {
		this.numeroCommande = numeroCommande;
		this.statut = statut;
		this.dateCommande = LocalDateTime.now();
		this.adresse = adresse;
		this.livreur = livreur;
		this.client = client;
		this.pizzas = pizzas;
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

	public List<Pizza> getPizzas() {
		return new ArrayList<>(pizzas);
	}

	public void addPizza(Pizza pizza) {
		pizzas.add(pizza);
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	@Override
	public String toString() {
		return "Commande [id=" + id + ", numeroCommande=" + numeroCommande + "]";
	}

}
