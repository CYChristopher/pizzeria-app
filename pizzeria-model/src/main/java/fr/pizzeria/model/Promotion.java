package fr.pizzeria.model;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

public class Promotion {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String code;
    private LocalDateTime dateFinPromotion;
    private int reductionEnPourcentage;
    @OneToOne
    private Pizza pizza;
    
	public Promotion(Integer id, String code, LocalDateTime dateFinPromotion, int reductionEnPourcentage, Pizza pizza) {
		super();
		this.id = id;
		this.code = code;
		this.dateFinPromotion = dateFinPromotion;
		this.reductionEnPourcentage = reductionEnPourcentage;
		this.pizza = pizza;
	}

	public Promotion(String code, LocalDateTime dateFinPromotion, int reductionEnPourcentage, Pizza pizza) {
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
	public LocalDateTime getDateFinPromotion() {
		return dateFinPromotion;
	}
	public void setDateFinPromotion(LocalDateTime dateFinPromotion) {
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
