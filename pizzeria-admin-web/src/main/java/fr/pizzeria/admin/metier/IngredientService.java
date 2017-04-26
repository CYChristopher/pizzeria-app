package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Ingredient;

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class IngredientService {
	
	@PersistenceContext(unitName = "pizzeria-admin-web")
	private EntityManager em;
	
	private TypedQuery<Ingredient> query;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Ingredient> findAll(){
		this.query = em.createQuery("select i from Ingredient i", Ingredient.class);
		List<Ingredient> ingredients = query.getResultList();
		//ingredients != null ? return ingredients : //todo exception
		return ingredients;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Ingredient findById(Integer id){
		
		this.query = em.createQuery("select i from Ingredient i where i.id=:id", Ingredient.class);
		this.query.setParameter("id", id);
		
		return query.getSingleResult();
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void save(Ingredient ingredient) {
		em.persist(ingredient);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Integer id, Ingredient ingredient){
		
		Ingredient old = findById(id);
		
		if(old != null){
			ingredient.setId(old.getId());
			em.merge(ingredient);			
		}
		//todo else exception
		
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Integer id){
		Ingredient ingredient = findById(id);
		if(ingredient != null){
			em.remove(ingredient);			
		}
		//todo else exception
	}
	
}
