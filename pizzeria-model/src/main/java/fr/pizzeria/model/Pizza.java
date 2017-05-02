package fr.pizzeria.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class Pizza {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String nom;
	private BigDecimal prix;
	@Enumerated(EnumType.STRING)
	private CategoriePizza categorie;
	private String urlImage;
	private LocalDateTime versionPizza;
	private Boolean actif;
	
	@Enumerated(EnumType.STRING)	
	private TypePizza typePizza;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Ingredient> listeIngredients;

	

	public Pizza() {
	}
	
	

	public Pizza(String code, String nom, BigDecimal prix, CategoriePizza categorie,TypePizza typePizza, LocalDateTime versionPizza,
			Boolean actif, List<Ingredient> listeIngredients) {
		
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categorie = categorie;
		this.typePizza=typePizza;
		this.versionPizza = versionPizza;
		this.actif = actif;
		this.listeIngredients = listeIngredients;
	}

	
	
	public Pizza(String code, String nom, BigDecimal prix, CategoriePizza categorie, String urlImage,
			LocalDateTime versionPizza, Boolean actif, TypePizza typePizza, List<Ingredient> listeIngredients) {
		super();
		this.code = code;
		this.nom = nom;
		this.prix = prix;
		this.categorie = categorie;
		this.urlImage = urlImage;
		this.versionPizza = versionPizza;
		this.actif = actif;
		this.typePizza = typePizza;
		this.listeIngredients = listeIngredients;
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

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public CategoriePizza getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public LocalDateTime getVersionPizza() {
		return versionPizza;
	}

	public void setVersionPizza(LocalDateTime versionPizza) {
		this.versionPizza = versionPizza;
	}

	public Boolean getActif() {
		return actif;
	}

	public void setActif(Boolean actif) {
		this.actif = actif;
	}
	
	public List<Ingredient> getListeIngredients() {
		return listeIngredients;
	}

	public void setListeIngredients(List<Ingredient> listeIngredients) {
		this.listeIngredients = listeIngredients;
	}

	


	public TypePizza getTypePizza() {
		return typePizza;
	}



	public void setTypePizza(TypePizza typePizza) {
		this.typePizza = typePizza;
	}



	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 37).append(code).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		Pizza rhs = (Pizza) obj;
		return new EqualsBuilder().append(code, rhs.code).isEquals();
	}

	
}
