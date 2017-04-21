package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pizzeria.model.Commande;

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class CommandeService {

	@PersistenceContext(unitName = "pizzeria-admin-web")
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Commande> findAll() {
		return em.createQuery("select c from Commande c", Commande.class).getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void create(Commande cmd) {

		em.persist(cmd);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Integer id, Commande cmd) {

		Commande majCommande = em.createQuery("select c from Commande c where c.id=:id", Commande.class)
				.setParameter("id", id).getSingleResult();
		cmd.setId(majCommande.getId());

		em.merge(cmd);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Integer id) {

		Commande cmd = em.createQuery("select c from Commande c where c.id=:id", Commande.class).setParameter("id", id)
				.getSingleResult();

		em.remove(cmd);
	}

}
