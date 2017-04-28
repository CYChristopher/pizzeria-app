package fr.pizzeria.model;

import javax.persistence.*;

@Entity(name = "commande_pizza")
public class CommandePizza {

	@EmbeddedId
	private CommandePizzaId id;

	// private Commande commande;
	// private Commande pizza;

	@Column(name = "quantite", nullable = false)
	private Integer quantite;

	public CommandePizza() {
	}

	public CommandePizza(Pizza pizza, Commande commande, Integer quantite) {
		this.id = new CommandePizzaId();
		this.id.setCommande(commande);
		this.id.setPizza(pizza);
		this.quantite = quantite;
	}

	public Integer getQuantite() {
		return this.quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

	public CommandePizzaId getId() {
		return this.id;
	}

	public void setId(CommandePizzaId id) {
		this.id = id;
	}

}
