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

import fr.pizzeria.model.Utilisateur;

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class UtilisateursService {

	@PersistenceContext(unitName = "pizzeria-admin-web")
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Utilisateur> findAll() {

		TypedQuery<Utilisateur> query = em.createQuery("select u from Utilisateur u", Utilisateur.class);

		List<Utilisateur> l = query.getResultList();

		return l;

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveNew(Utilisateur utilisateur) {

		em.persist(utilisateur);

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(String code, Utilisateur utilisateur) {

		TypedQuery<Utilisateur> query = em.createQuery("select u from Utilisateur u where p.id='" + code + "'",
				Utilisateur.class);
		Utilisateur p = (Utilisateur) query.getResultList().get(0);

		if (p != null) {

			int id = p.getId();

			p = utilisateur;

			p.setId(id);

			em.merge(p);
		}

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(String id) {

		TypedQuery<Utilisateur> query = em.createQuery("select u from Utilisateur u where p.id='" + id + "'",
				Utilisateur.class);
		Utilisateur p = (Utilisateur) query.getResultList().get(0);

		if (p != null) {
			em.remove(p);
		}

	}

}