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

	private static final String FIND_BY_ID = "select c from Commande c where c.id=:id";

	@PersistenceContext(unitName = "pizzeria-admin-web")
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Commande> findAll() {

		return em.createQuery("select c from Commande c", Commande.class).getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Commande find(Integer id) {

		return em.createQuery(FIND_BY_ID, Commande.class).setParameter("id", id).getSingleResult();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void create(Commande cmd) {

		em.persist(cmd);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Integer id, Commande cmd) {

		cmd.setId(find(id).getId());

		em.merge(cmd);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Integer id) {

		em.remove(find(id));
	}

}
