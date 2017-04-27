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

	public void update(Integer id, Pizza pizza) {

		Pizza pizzaMod = em.createQuery("select piz from Pizza piz where piz.id=:idP", Pizza.class)
				.setParameter("idP", id).getSingleResult();
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

		return em.createQuery("select piz from Pizza piz where piz.id=:codP", Pizza.class).setParameter("codP", id)
				.getSingleResult();

	}

	// Trouve les versions de pizza actifs, renvoit cette liste
	public List<Pizza> findNewestPizzaByName() {
		return em.createQuery("select piz from Pizza piz where piz.actif=:val", Pizza.class).setParameter("val", true)
				.getResultList();

	}
}
