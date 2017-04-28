package fr.pizzeria.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class CommandePizzaId implements Serializable {

	@ManyToOne
	@JoinColumn(name = "pizza_id", insertable = false, updatable = false)
	private Pizza pizza;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "commande_id", insertable = false, updatable = false)
	private Commande commande;

	public CommandePizzaId() {
	}

	public CommandePizzaId(Pizza pizza, Commande commande) {
		this.pizza = pizza;
		this.commande = commande;
	}

	public Pizza getPizza() {
		return this.pizza;
	}

	public Commande getCommande() {
		return this.commande;
	}

	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof CommandePizzaId))
			return false;
		CommandePizzaId that = (CommandePizzaId) o;
		return Objects.equals(this.getPizza(), that.getPizza()) && Objects.equals(this.getCommande(), that.getCommande());
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.getPizza(), this.getCommande());
	}

}
