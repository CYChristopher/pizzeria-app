package fr.pizzeria.admin.metier;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pizzeria.model.Pizza;

@Stateless
public class PizzaService {

	@PersistenceContext
	private EntityManager em;

	public List<Pizza> findAll() {
		return em.createQuery("select p from Pizza p", Pizza.class).getResultList();
	}

	public void update(String code, Pizza pizza) {

		Pizza pizzaMod = em.createQuery("select piz from Pizza piz where piz.code=:codP", Pizza.class)
				.setParameter("codP", code).getSingleResult();
		pizza.setId(pizzaMod.getId());
		em.merge(pizza);

	}

	public void delete(String code) {

		Pizza pizzaDel = em.createQuery("select piz from Pizza piz where piz.code=:codP", Pizza.class)
				.setParameter("codP", code).getSingleResult();

		em.remove(pizzaDel);

	}

	public void save(Pizza pizza) {

		em.persist(pizza);

	}
	
	
	public Pizza findById(Integer id) {

		return  em.createQuery("select piz from Pizza piz where piz.id=:codP", Pizza.class)
				.setParameter("codP", id).getSingleResult();

	}
	
	public Pizza findByName(String name) {

		return  em.createQuery("select piz from Pizza piz where piz.name=:nameP and piz.able=:val", Pizza.class)
				.setParameter("nameP", name).setParameter("val", true).getSingleResult();

	}
	
	// Trouve les versions de pizza les plus recentes, renvoit cette liste
	public List<Pizza> findNewestPizzaByName()
	{
		List<Pizza> listAllPizza = findAll();
		TreeSet<String> listOfName = null;
		List<Pizza> listnewestPizza = null;
		
		for(Pizza pizz:listAllPizza)
		{
			listOfName.add(pizz.getNom());
		}
		
		for(String name:listOfName)
		{
			listnewestPizza.add(null);
			for(Pizza pizz:listAllPizza)
			{
				if(pizz.getNom().equals(name))
				{
					if(listnewestPizza.get(listnewestPizza.size()-1) ==null)
					{
						listnewestPizza.set(listnewestPizza.size()-1, pizz);
					}else
					{
						if(listnewestPizza.get(listnewestPizza.size()-1).getVersionPizza().compareTo(pizz.getVersionPizza()) > 1)
						{
							listnewestPizza.set(listnewestPizza.size()-1, pizz);
						}
						
					}
				}
			}			
			
		}
		
		
		return listnewestPizza;
		
	}
	
	

}
