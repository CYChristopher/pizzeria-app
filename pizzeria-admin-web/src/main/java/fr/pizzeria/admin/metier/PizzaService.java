package fr.pizzeria.admin.metier;

import java.util.List;

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

	public void save(Pizza pizza) {

		em.persist(pizza);

	}

}
