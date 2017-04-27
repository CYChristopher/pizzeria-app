package fr.pizzeria.admin.metier;

import fr.pizzeria.model.Pizza;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	
	public Pizza findbycode(String code) {

		return  em.createQuery("select piz from Pizza piz where piz.code=:codP", Pizza.class)
				.setParameter("codP", code).getSingleResult();

	}
	

}
