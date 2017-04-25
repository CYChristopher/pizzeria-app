package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.pizzeria.model.Livreur;

@Stateless
public class LivreurService {

	@PersistenceContext
	private EntityManager em;

	public List<Livreur> findAll() {
		return em.createQuery("select l from Livreur l", Livreur.class).getResultList();
	}

	public Livreur find(Integer id) {
		Query findQuery = em.createQuery("select l from Livreur l where id=:id", Livreur.class).setParameter("id", id);
		Livreur livreur = (Livreur) findQuery.getSingleResult();
		return livreur;
	}

	public Livreur save(Livreur livreur) {
		em.persist(livreur);
		return livreur;
	}

	public boolean delete(Integer id) {
		Livreur livreur = (Livreur) em.createQuery("select l from Livreur l where id=:id", Livreur.class)
				.setParameter("id", id).getSingleResult();
		if (livreur != null)
			em.remove(livreur);
		return true;
	}

	public boolean update(Integer id, Livreur livreur) {
		Livreur livreurToModify = (Livreur) em.createQuery("select l from Livreur l where id=:id", Livreur.class)
				.setParameter("id", id).getSingleResult();
		if (livreurToModify != null) {
			livreurToModify.setNom(livreur.getNom());
			livreurToModify.setPrenom(livreur.getPrenom());
			return true;
		}
		return false;
	}

}
