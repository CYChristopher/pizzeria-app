package fr.pizzeria.model;

public enum TypePizza {
	OUICHE("Ouiche"), PIZZA("Pizza");
	
	private String libelle;

	private TypePizza(String libelle) {
		this.libelle = libelle;
	}

	public String getLibelle() {
		return libelle;
	}
	
	@Override
	public String toString() {
		return libelle;
	}
}
