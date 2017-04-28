package fr.pizzeria.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Promotion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private LocalDate dateFinPromotion;
    private int reductionEnPourcentage;
    @OneToOne
    private Pizza pizza;
    
	public Promotion(Integer id, String code, LocalDate dateFinPromotion, int reductionEnPourcentage, Pizza pizza) {
		super();
		this.id = id;
		this.code = code;
		this.dateFinPromotion = dateFinPromotion;
		this.reductionEnPourcentage = reductionEnPourcentage;
		this.pizza = pizza;
	}

	public Promotion(String code, LocalDate dateFinPromotion, int reductionEnPourcentage, Pizza pizza) {
		super();
		this.code = code;
		this.dateFinPromotion = dateFinPromotion;
		this.reductionEnPourcentage = reductionEnPourcentage;
		this.pizza = pizza;
	}
	
	public Promotion() {
		super();
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
	public LocalDate getDateFinPromotion() {
		return dateFinPromotion;
	}
	public void setDateFinPromotion(LocalDate dateFinPromotion) {
		this.dateFinPromotion = dateFinPromotion;
	}
	public int getReductionEnPourcentage() {
		return reductionEnPourcentage;
	}
	public void setReductionEnPourcentage(int reductionEnPourcentage) {
		this.reductionEnPourcentage = reductionEnPourcentage;
	}
	public Pizza getPizza() {
		return pizza;
	}
	public void setPizza(Pizza pizza) {
		this.pizza = pizza;
	}

}
