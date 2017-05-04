package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.admin.exception.StockageException;
import fr.pizzeria.model.Dessert;

@Stateless
public class DessertService {

	@PersistenceContext
	private EntityManager em;

	private TypedQuery<Dessert> query;

	public List<Dessert> findAll() {
		return this.em.createQuery("select d from Dessert d", Dessert.class).getResultList();
	}

	public void update(Integer id, Dessert dessert) {

		TypedQuery<Dessert> query = this.em.createQuery("select des from Dessert des where des.id='" + id + "'",
						Dessert.class);

		Dessert dessertMod = query.getResultList().get(0);

		if (dessertMod != null) {

			dessert.setId(dessertMod.getId());
			this.em.merge(dessert);

		}

	}

	public Dessert find(Integer id) {

		TypedQuery<Dessert> query = this.em.createQuery("select des from Dessert des where des.id='" + id + "'",
						Dessert.class);

		return query.getResultList().get(0);

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(String code) throws StockageException {
		try {
			Dessert dessert = this.findbycode(code);
			if (dessert != null) {
				dessert.setArchive(true);
				this.em.merge(dessert);
			}
		} catch (Exception e) {
			throw new StockageException("Erreur à la suppression d'un dessert", e);
		}
	}

	public void save(Dessert dessert) {

		this.em.persist(dessert);

	}

	public Dessert findbycode(String code) {

		return this.em.createQuery("select des from Dessert des where des.code=:codP", Dessert.class)
						.setParameter("codP", code).getSingleResult();

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Dessert> findAllAvailable() throws StockageException {
		try {
			this.query = this.em.createQuery("select d from Dessert d where d.archive=:archive", Dessert.class);
			this.query.setParameter("archive", Boolean.FALSE);

			List<Dessert> dessert = this.query.getResultList();
			return dessert;
		} catch (Exception e) {
			throw new StockageException("Erreur de récupération des desserts", e);
		}
	}

}