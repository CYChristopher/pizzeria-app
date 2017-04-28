package fr.pizzeria.admin.metier;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pizzeria.admin.metier.Evenement.Action;
import fr.pizzeria.admin.metier.Evenement.Type;
import fr.pizzeria.model.Pizza;
import fr.pizzeria.model.TypePizza;

@Stateless
public class PizzaService {

	@PersistenceContext
	private EntityManager em;

	@Inject
	private Event<Evenement> event;

	public List<Pizza> findAll() {
		return em.createQuery("select p from Pizza p where p.typePizza=:typeP", Pizza.class)
				.setParameter("typeP", TypePizza.PIZZA)
				.getResultList();
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
		Evenement ev = new Evenement();
		ev.setDate(LocalDateTime.now());
		ev.setAction(Action.SAVE);
		ev.setType(Type.PIZZA);
		event.fire(ev);
		em.persist(pizza);

	}

	public Pizza findById(Integer id) {

		return em.createQuery("select piz from Pizza piz where piz.id=:codP", Pizza.class).setParameter("codP", id)
				.getSingleResult();

	}

	// Trouve les versions de pizza actifs, renvoit cette liste
	public List<Pizza> findNewestPizzaByName() {
		return em.createQuery("select piz from Pizza piz where piz.actif=:val and piz.typePizza=:typeP", Pizza.class)
				.setParameter("val", true)
				.setParameter("typeP", TypePizza.PIZZA)
				.getResultList();

	}

}
