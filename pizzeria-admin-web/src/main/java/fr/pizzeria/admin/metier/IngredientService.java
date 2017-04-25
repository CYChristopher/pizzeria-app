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
	
	public void update(Integer id, Ingredient ingredient){
		Ingredient old = findById(id);
		
		ingredient.setId(old.getId());
		em.merge(ingredient);
	}
	
	public Ingredient findById(Integer id){
		return em.createQuery("select i from Ingredient i where i.id=:id", Ingredient.class)
					.setParameter("id", id).getSingleResult();
	}

}
