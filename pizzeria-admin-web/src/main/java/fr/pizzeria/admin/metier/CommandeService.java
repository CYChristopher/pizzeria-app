package fr.pizzeria.admin.metier;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pizzeria.admin.metier.Evenement.Action;
import fr.pizzeria.admin.metier.Evenement.Type;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.CommandeComplete;
import fr.pizzeria.model.CommandePizza;

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class CommandeService {

	private static final String FIND_BY_ID = "select c from Commande c where c.id=:id";
	private static final String FIND_BY_NUM = "select c from Commande c where c.numeroCommande=:numeroCommande";

	@PersistenceContext(unitName = "pizzeria-admin-web")
	private EntityManager em;

	@Inject
	private Event<Evenement> event;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Commande> findAll() {

		return this.em.createQuery("select c from Commande c", Commande.class).getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Commande> findAllNonLivrees() {

		return this.em.createQuery("select c from Commande c where c.statut <> 'LIVRE'", Commande.class).getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Commande find(Integer id) {

		return this.em.createQuery(FIND_BY_ID, Commande.class).setParameter("id", id).getSingleResult();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public List<Commande> findByNum(String num) {

		return this.em.createQuery(FIND_BY_NUM, Commande.class).setParameter("numeroCommande", num).getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void create(CommandeComplete commandeComplete) {
		Evenement ev = new Evenement();
		ev.setDate(LocalDateTime.now());
		ev.setAction(Action.SAVE);
		ev.setType(Type.COMMANDE);
		ev.setNom(commandeComplete.getCommande().getNumeroCommande());
		Commande com = commandeComplete.getCommande();
		this.em.persist(com);
		for (CommandePizza cmdP : commandeComplete.getCommandesPizza()) {
			this.em.persist(cmdP);
		}
		ev.setId(commandeComplete.getCommande().getId());
		this.event.fire(ev);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void update(Integer id, CommandeComplete cmdCmp) {
		Evenement ev = new Evenement();
		ev.setDate(LocalDateTime.now());
		ev.setAction(Action.UPDATE);
		ev.setType(Type.COMMANDE);
		Commande com = cmdCmp.getCommande();
		com.setId(this.find(id).getId());
		ev.setNom(com.getNumeroCommande());
		ev.setId(com.getId());
		for (CommandePizza cmdP : cmdCmp.getCommandesPizza()) {
			this.em.merge(cmdP);
			com.getCommandesPizzas().add(cmdP);
		}
		this.em.merge(com);
		this.event.fire(ev);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Integer id) {

		this.em.remove(this.find(id));
	}

}
