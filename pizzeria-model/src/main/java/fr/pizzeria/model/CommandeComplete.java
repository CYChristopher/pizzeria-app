package fr.pizzeria.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommandeComplete {

	private Commande commande;
	private List<CommandePizza> commandesPizza = new ArrayList<>();

	public CommandeComplete() {
	}

	public CommandeComplete(Commande commande, List<CommandePizza> commandesPizza) {
		this.commande = commande;
		this.commandesPizza = commandesPizza;
	}

	public Commande getCommande() {
		return this.commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public List<CommandePizza> getCommandesPizza() {
		return this.commandesPizza;
	}

	public void setCommandesPizza(List<CommandePizza> commandesPizza) {
		this.commandesPizza = commandesPizza;
	}

	public void setDateCommande(LocalDateTime date) {
		commande.setDateCommande(date);
	}

	public void setStatutCommande(StatutCommande statut) {
		commande.setStatut(statut);
	}

}
