package fr.pizzeria.model;

import javax.persistence.*;

@Entity(name = "commande_pizza")
public class CommandePizza {

	@EmbeddedId
	private CommandePizzaId id;

	@ManyToOne
	@JoinColumn(name = "commande_id", insertable = false, updatable = false)
	private Commande commande;

	@ManyToOne
	@JoinColumn(name = "pizza_id", insertable = false, updatable = false)
	private Commande pizza;

	@Column(name = "quantite", nullable = false)
	private Integer quantite;

	public CommandePizza() {
	}

	public CommandePizza(Integer quantite) {
		super();
		this.quantite = quantite;
	}

	public Integer getQuantite() {
		return this.quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
	}

}
