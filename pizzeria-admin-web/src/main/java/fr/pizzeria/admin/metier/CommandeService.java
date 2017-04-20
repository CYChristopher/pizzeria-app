package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pizzeria.model.Commande;

@Stateless
public class CommandeService {

	@PersistenceContext
	private EntityManager em;

	public List<Commande> findAll() {
		return em.createQuery("select c from Commande c", Commande.class).getResultList();
	}

}
