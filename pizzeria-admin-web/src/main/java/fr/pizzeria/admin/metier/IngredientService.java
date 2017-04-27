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

	public List<Ingredient> findAll() {
		return em.createQuery("select ingr from Ingredient ingr", Ingredient.class).getResultList();
	}

	public void update(Integer id, Ingredient ingredient) {

		Ingredient ingredientMod = em.createQuery("select ingr from Ingredient ingr where ingr.id=:idP", Ingredient.class)
				.setParameter("idP", id).getSingleResult();
		ingredient.setId(ingredientMod.getId());
		em.merge(ingredient);

	}

	public void delete(String code) {

		Ingredient ingredientDel = em.createQuery("select ingr from Ingredient piz where ingr.code=:codP", Ingredient.class)
				.setParameter("codP", code).getSingleResult();

		em.remove(ingredientDel);

	}

	public void save(Ingredient ingredient) {

		em.persist(ingredient);

	}
	
	
	public Ingredient findById(Integer id) {

		return  em.createQuery("select ingr from Ingredient ingr where ingr.id=:codP", Ingredient.class)
				.setParameter("codP", id).getSingleResult();

	}
	
	public Ingredient findByName(String name) {
		
		return  em.createQuery("select ingr from Ingredient ingr where ingr.nom=:codP", Ingredient.class)
				.setParameter("codP", name).getSingleResult();

	}
	

}
