package fr.pizzeria.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name="menu")
public class OffreMenu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String nom;
	private Double prix;
	private Integer maxPizza;
	private Integer maxBoisson;
	private Integer maxDessert;
	private Boolean archive;
	@ManyToMany
	@JoinTable(name="menu",
		joinColumns=@JoinColumn(name="menu_id", referencedColumnName="id"),
		inverseJoinColumns=@JoinColumn(name="pizza_id", referencedColumnName="id"))
	private List<Pizza> pizzas;
	@ManyToMany
	@JoinTable(name="menu",
		joinColumns=@JoinColumn(name="menu_id", referencedColumnName="id"),
		inverseJoinColumns=@JoinColumn(name="boisson_id", referencedColumnName="id"))
	private List<Boisson> boissons;
	@ManyToMany
	@JoinTable(name="menu",
		joinColumns=@JoinColumn(name="menu_id", referencedColumnName="id"),
		inverseJoinColumns=@JoinColumn(name="dessert_id", referencedColumnName="id"))
	private List<Dessert> desserts;

	public OffreMenu() {
		this.archive = false;
	}
	
	public OffreMenu(String code, String nom, Double prix,
			Integer maxP, Integer maxB, Integer maxD,
			List<Pizza> pizzas, List<Boisson> boissons, List<Dessert> desserts, Boolean archive){
		super();
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.maxPizza = maxP;
		this.maxBoisson = maxB;
		this.maxDessert = maxD;
		this.pizzas = pizzas;
		this.boissons = boissons;
		this.desserts = desserts;
		this.archive = archive;
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

	public Integer getMaxPizza() {
		return maxPizza;
	}

	public void setMaxPizza(Integer maxPizza) {
		this.maxPizza = maxPizza;
	}

	public Integer getMaxBoisson() {
		return maxBoisson;
	}

	public void setMaxBoisson(Integer maxBoisson) {
		this.maxBoisson = maxBoisson;
	}

	public Integer getMaxDessert() {
		return maxDessert;
	}

	public void setMaxDessert(Integer maxDessert) {
		this.maxDessert = maxDessert;
	}

	public List<Pizza> getPizzas() {
		return pizzas;
	}

	public void setPizzas(List<Pizza> pizzas) {
		this.pizzas = pizzas;
	}

	public List<Boisson> getBoissons() {
		return boissons;
	}

	public void setBoissons(List<Boisson> boissons) {
		this.boissons = boissons;
	}

	public List<Dessert> getDesserts() {
		return desserts;
	}

	public void setDesserts(List<Dessert> desserts) {
		this.desserts = desserts;
	}

	public Boolean getArchive() {
		return archive;
	}

	public void setArchive(Boolean archive) {
		this.archive = archive;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj){
			return true;
		} else if(obj == null){
			return false;
		} else if (getClass() != obj.getClass()){
			return false;
		}
		OffreMenu other = (OffreMenu) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}

}
