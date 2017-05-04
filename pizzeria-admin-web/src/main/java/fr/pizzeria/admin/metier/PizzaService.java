package fr.pizzeria.admin.metier;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pizzeria.admin.exception.StockageException;
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
		return this.em.createQuery("select p from Pizza p where p.typePizza=:typeP", Pizza.class)
						.setParameter("typeP", TypePizza.PIZZA)
						.getResultList();
	}

	public void update(Integer id, Pizza pizza) {

		Pizza pizzaMod = this.em.createQuery("select piz from Pizza piz where piz.id=:idP", Pizza.class)
						.setParameter("idP", id).getSingleResult();
		pizza.setId(pizzaMod.getId());
		this.em.merge(pizza);

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(String code) throws StockageException {
		try {
			Pizza Pizza = this.findByCode(code);
			if (Pizza != null) {
				Pizza.setArchive(true);
				this.em.merge(Pizza);
			}
		} catch (Exception e) {
			throw new StockageException("Erreur à la suppression d'un ingredient", e);
		}
	}

	public void save(Pizza pizza) {
		Evenement ev = new Evenement();
		ev.setDate(LocalDateTime.now());
		ev.setAction(Action.SAVE);
		ev.setType(Type.PIZZA);
		ev.setNom(pizza.getNom());
		this.em.persist(pizza);
		ev.setId(pizza.getId());
		this.event.fire(ev);

	}

	public Pizza findById(Integer id) {

		return this.em.createQuery("select piz from Pizza piz where piz.id=:idP", Pizza.class).setParameter("idP", id).getSingleResult();

	}

	public Pizza findByCode(String code) {

		return this.em.createQuery("select piz from Pizza piz where piz.code=:codP", Pizza.class).setParameter("codP", code)
						.getSingleResult();

	}

	// Trouve les versions de pizza actifs, renvoit cette liste
	public List<Pizza> findNewestPizzaByName() {
		return this.em.createQuery("select piz from Pizza piz where piz.archive=:val and piz.typePizza=:typeP", Pizza.class).setParameter("val", false).setParameter("typeP", TypePizza.PIZZA).getResultList();

	}

	public List<Pizza> findTopFivePizzas() {
		return this.em.createQuery("select piz from Pizza piz where id in (select cmd_p.id.pizza.id from fr.pizzeria.model.CommandePizza cmd_p group by cmd_p.id.pizza.id order by sum(cmd_p.quantite) desc)", Pizza.class).setMaxResults(5).getResultList();

	}
	
}
