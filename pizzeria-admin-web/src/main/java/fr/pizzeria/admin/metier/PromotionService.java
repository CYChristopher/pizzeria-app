package fr.pizzeria.admin.metier;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Promotion;

public class PromotionService {
	
	@PersistenceContext
	private EntityManager em;

	public List<Promotion> findAll() {
		return em.createQuery("select p from Promotion p", Promotion.class).getResultList();
	}

	public void save(Promotion promotion) {
		em.persist(promotion);
	}

	public void update(Integer id, Promotion promotion) {
		promotion.setId(id);
		em.merge(promotion);
	}

	public void delete(Integer id) {
		Promotion promotion = getById(id);
		em.remove(promotion);
	}

	public Promotion getById(Integer id) {
		TypedQuery<Promotion> query = em.createQuery("select p from Promotion p where p.id = :id", Promotion.class);
		query.setParameter("id", id);
		List<Promotion> promotions = query.getResultList();
		if (!promotions.isEmpty()) {
			return promotions.get(0);
		}
		return null;
	}
	
	public Promotion getByEmail(String code) {
		TypedQuery<Promotion> query = em.createQuery("select p from Promotion p where p.code = :code", Promotion.class);
		query.setParameter("code", code);
		List<Promotion> promotions = query.getResultList();
		if (!promotions.isEmpty()) {
			return promotions.get(0);
		}
		return null;
	}

}
