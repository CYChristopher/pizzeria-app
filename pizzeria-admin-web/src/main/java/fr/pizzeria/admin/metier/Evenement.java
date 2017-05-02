package fr.pizzeria.admin.metier;

import java.time.LocalDateTime;

public class Evenement {

	public enum Action {
		SAVE, UPDATE
	}

	public enum Type {
		PIZZA, COMMANDE, OUICHE
	}

	private LocalDateTime date;

	private Action action;

	private Type type;

	private Integer id;

	private String nom;

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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