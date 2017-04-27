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

import fr.pizzeria.model.Boisson;

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class BoissonService {

	@PersistenceContext(unitName = "pizzeria-admin-web")
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Boisson> findAll() {

		TypedQuery<Boisson> query = em.createQuery("select b from Boisson b", Boisson.class);

		List<Boisson> l = query.getResultList();

		return l;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Boisson find(Integer id) {

		TypedQuery<Boisson> query = em.createQuery("select b from Boisson b where b.id='" + id + "'", Boisson.class);

		return query.getResultList().get(0);

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveNew(Boisson boisson) {

		em.persist(boisson);

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Integer id, Boisson boisson) {

		TypedQuery<Boisson> query = em.createQuery("select b from Boisson b where b.id='" + id + "'", Boisson.class);
		Boisson b = (Boisson) query.getResultList().get(0);

		if (b != null) {

			Integer idUpdate = b.getId();

			b = boisson;

			b.setId(idUpdate);

			em.merge(b);

		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Integer id) {

		TypedQuery<Boisson> query = em.createQuery("select b from Boisson b where b.id='" + id + "'", Boisson.class);
		Boisson p = (Boisson) query.getResultList().get(0);

		if (p != null) {
			em.remove(p);
		}

	}

}
