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

import fr.pizzeria.admin.exception.StockageException;
import fr.pizzeria.model.Boisson;

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class BoissonService {

	@PersistenceContext(unitName = "pizzeria-admin-web")
	private EntityManager em;

	private TypedQuery<Boisson> query;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Boisson> findAll() {

		TypedQuery<Boisson> query = this.em.createQuery("select b from Boisson b", Boisson.class);

		List<Boisson> l = query.getResultList();

		return l;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Boisson find(Integer id) {

		TypedQuery<Boisson> query = this.em.createQuery("select b from Boisson b where b.id='" + id + "'", Boisson.class);

		return query.getResultList().get(0);

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveNew(Boisson boisson) {

		this.em.persist(boisson);

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Integer id, Boisson boisson) {

		TypedQuery<Boisson> query = this.em.createQuery("select b from Boisson b where b.id='" + id + "'", Boisson.class);
		Boisson b = query.getResultList().get(0);

		if (b != null) {

			Integer idUpdate = b.getId();

			b = boisson;

			b.setId(idUpdate);

			this.em.merge(b);

		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Integer id) throws StockageException {
		try {
			Boisson boisson = this.find(id);
			if (boisson != null) {
				boisson.setArchive(true);
				this.em.merge(boisson);
			}
		} catch (Exception e) {
			throw new StockageException("Erreur à la suppression d'une boisson", e);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Boisson> findAllAvailable() throws StockageException {
		try {
			this.query = this.em.createQuery("select b from Boisson b where b.archive=:archive", Boisson.class);
			this.query.setParameter("archive", Boolean.FALSE);

			List<Boisson> boissons = this.query.getResultList();
			return boissons;
		} catch (Exception e) {
			throw new StockageException("Erreur de récupération des boissons", e);
		}
	}

}
