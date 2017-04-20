package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.pizzeria.model.Client;

@Stateless
public class ClientService {
	
	@PersistenceContext private EntityManager em;

	  public List<Client> findAll() {
	    return em.createQuery("select c from Client c", Client.class).getResultList();
	  }

}
