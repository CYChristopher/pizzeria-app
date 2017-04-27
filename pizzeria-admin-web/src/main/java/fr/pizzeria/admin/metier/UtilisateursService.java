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

	private static final String SELECT_BY_EMAIL = "select u from Utilisateur u where u.email=:email";
	private static final String SELECT_BY_ID = "select u from Utilisateur u where u.id=:id";

	@PersistenceContext(unitName = "pizzeria-admin-web")
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Utilisateur> findAll() {

		return em.createQuery("select u from Utilisateur u", Utilisateur.class).getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Utilisateur find(Integer id) {

		return em.createQuery(SELECT_BY_ID, Utilisateur.class).setParameter("id", id).getSingleResult();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveNew(Utilisateur utilisateur) {

		em.persist(utilisateur);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Integer id, Utilisateur utilisateur) {

		TypedQuery<Utilisateur> query = em.createQuery(SELECT_BY_ID, Utilisateur.class).setParameter("id", id);
		Utilisateur u = query.getResultList().get(0);

		if (u != null) {

			Integer idUpdate = u.getId();

			u = utilisateur;

			u.setId(idUpdate);

			em.merge(u);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Integer id) {

		TypedQuery<Utilisateur> query = em.createQuery(SELECT_BY_ID, Utilisateur.class).setParameter("id", id);
		Utilisateur u = query.getResultList().get(0);

		if (u != null) {
			em.remove(u);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Utilisateur findByEmail(String email) {

		TypedQuery<Utilisateur> query = em.createQuery(SELECT_BY_EMAIL, Utilisateur.class).setParameter("email", email);

		if (query.getResultList().isEmpty()) {
			return null;
		}
		return query.getSingleResult();
	}

}