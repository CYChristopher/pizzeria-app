package fr.pizzeria.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Embeddable
public class CommandePizzaId implements Serializable {

	@Column(name = "pizza_id")
	private Pizza pizza;

	@Column(name = "commande_id")
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
