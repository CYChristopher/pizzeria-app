package fr.pizzeria.admin.metier;

import java.time.LocalDateTime;

public class Evenement {

	public enum Action {
		SAVE, UPDATE
	}

	public enum Type {
		PIZZA, COMMANDE
	}

	private LocalDateTime date;

	private Action action;

	private Type type;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

}
