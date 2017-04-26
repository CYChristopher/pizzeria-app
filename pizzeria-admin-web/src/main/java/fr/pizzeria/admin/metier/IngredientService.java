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

import fr.pizzeria.admin.exception.StockageException;
import fr.pizzeria.model.Ingredient;

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class IngredientService {
	
	@PersistenceContext(unitName = "pizzeria-admin-web")
	private EntityManager em;
	
	private TypedQuery<Ingredient> query;
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Ingredient> findAll() throws StockageException {
		try {
			this.query = em.createQuery("select i from Ingredient i", Ingredient.class);
			List<Ingredient> ingredients = query.getResultList();
			return ingredients;
		} catch (Exception e) {
			throw new StockageException("Erreur de récupération des ingredients", e);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Ingredient findById(Integer id) throws StockageException {
		try {
			this.query = em.createQuery("select i from Ingredient i where i.id=:id", Ingredient.class);
			this.query.setParameter("id", id);
			
			return query.getSingleResult();
			
		} catch (Exception e) {
			throw new StockageException("Erreur de récupération d'un ingredient", e);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void save(Ingredient ingredient) throws StockageException {
		try {
			em.persist(ingredient);
		} catch (Exception e) {
			throw new StockageException("Erreur à l'ajout d'un ingredient", e);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Integer id, Ingredient ingredient) throws StockageException {
		try {
			Ingredient old = findById(id);
			
			if(old != null){
				ingredient.setId(old.getId());
				em.merge(ingredient);			
			}
		} catch (Exception e) {
			throw new StockageException("Erreur à l'update d'un ingredient", e);
		}
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Integer id) throws StockageException {
		try {
			Ingredient ingredient = findById(id);
			if(ingredient != null){
				em.remove(ingredient);			
			}
		} catch (Exception e) {
			throw new StockageException("Erreur à la suppression d'un ingredient", e);
		}
	}
	
}
