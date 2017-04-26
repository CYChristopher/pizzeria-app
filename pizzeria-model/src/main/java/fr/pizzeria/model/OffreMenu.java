package fr.pizzeria.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OffreMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String nom;
	private Double prix;
	private Pizza[] pizzas;
	private Boisson[] boissons;
	private Dessert[] desserts;

	public OffreMenu() {
	}

	public OffreMenu(String code, String nom, Double prix, Pizza[] pizzas, Boisson[] boissons, Dessert[] desserts) {
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.pizzas = pizzas;
		this.boissons = boissons;
		this.desserts = desserts;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	public Pizza[] getPizzas() {
		return pizzas;
	}

	public void setPizzas(Pizza[] pizzas) {
		this.pizzas = pizzas;
	}

	public Boisson[] getBoissons() {
		return boissons;
	}

	public void setBoissons(Boisson[] boissons) {
		this.boissons = boissons;
	}

	public Dessert[] getDesserts() {
		return desserts;
	}

	public void setDesserts(Dessert[] desserts) {
		this.desserts = desserts;
	}

}
