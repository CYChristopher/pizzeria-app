package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pizzeria.model.Ingredient;

@Stateless
public class IngredientService {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Ingredient> findAll(){
		return em.createQuery("select i from Ingredient i", Ingredient.class).getResultList();
	}

	public void save(Ingredient ingredient) {
		em.persist(ingredient);
	}

}
