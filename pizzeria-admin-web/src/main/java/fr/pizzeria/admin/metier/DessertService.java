package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pizzeria.model.Dessert;

@Stateless
public class DessertService {

	@PersistenceContext
	private EntityManager em;

	public List<Dessert> findAll() {
		return em.createQuery("select d from Dessert d", Dessert.class).getResultList();
	}

	public void update(String code, Dessert dessert) {

		Dessert dessertMod = em.createQuery("select des from Dessert des where des.code=:codP", Dessert.class)
				.setParameter("codP", code).getSingleResult();
		dessert.setId(dessertMod.getId());
		em.merge(dessert);

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

		return  em.createQuery("select des from Dessert des where des.code=:codP", Dessert.class)
				.setParameter("codP", code).getSingleResult();

	}
	

}