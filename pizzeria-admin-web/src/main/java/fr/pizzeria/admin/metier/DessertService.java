package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Dessert;

@Stateless
public class DessertService {

	@PersistenceContext
	private EntityManager em;

	public List<Dessert> findAll() {
		return em.createQuery("select d from Dessert d", Dessert.class).getResultList();
	}

	public void update(Integer id, Dessert dessert) {

		TypedQuery<Dessert> query = em.createQuery("select des from Dessert des where des.id='" + id + "'",
				Dessert.class);

		Dessert dessertMod = (Dessert) query.getResultList().get(0);

		if (dessertMod != null) {

			dessert.setId(dessertMod.getId());
			em.merge(dessert);

		}

	}

	public Dessert find(Integer id) {

		TypedQuery<Dessert> query = em.createQuery("select des from Dessert des where des.id='" + id + "'",
				Dessert.class);

		return query.getResultList().get(0);

	}

	public void delete(String code) {

		Dessert dessertDel = em.createQuery("select des from Dessert des where des.code=:codP", Dessert.class)
				.setParameter("codP", code).getSingleResult();

		em.remove(dessertDel);

	}

	public void save(Dessert dessert) {

		em.persist(dessert);

	}

	public Dessert findbycode(String code) {

		return em.createQuery("select des from Dessert des where des.code=:codP", Dessert.class)
				.setParameter("codP", code).getSingleResult();

	}

}