package fr.pizzeria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "commande_pizza")
public class CommandePizza  implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "pizza_id", referencedColumnName = "id")
	private Pizza pizza;

	@Id
	@ManyToOne
	@JoinColumn(name = "commande_id", referencedColumnName = "id")
	@JsonIgnore
	private Commande commande;

	@Column(name = "quantite", nullable = false)
	private Integer quantite;

	/**
	 * @return the pizza
	 */
	public Pizza getPizza() {
		return this.pizza;
	}

	/**
	 * @param pizza
	 *            the pizza to set
	 */
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	/**
	 * @return the commande
	 */
	public Commande getCommande() {
		return this.commande;
	}

	/**
	 * @param commande
	 *            the commande to set
	 */
	public void setCommande(Commande commande) {
		this.commande = commande;
	}


	/**
	 * @return the quantite
	 */
	public Integer getQuantite() {
		return this.quantite;
	}


	/**
	 * @param quantite
	 *            the quantite to set
	 */
	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}
}