package fr.pizzeria.admin.metier;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.model.Client;
import fr.pizzeria.model.Pizza;

@Stateless
public class ClientService {

	@PersistenceContext
	private EntityManager em;

	public List<Client> findAll() {
		return em.createQuery("select c from Client c", Client.class).getResultList();
	}

	public void save(Client client) {
		client.setDateCreation(LocalDateTime.now());
		client.setMotDePasse(Client.encode(client.getMotDePasse(), client.getDateCreation()));
		em.persist(client);
	}

	public void update(Integer id, Client client) {
		client.setId(id);
		client.setDateCreation(getById(id).getDateCreation());
		if(!client.getMotDePasse().isEmpty()){
			client.setMotDePasse(Client.encode(client.getMotDePasse(), client.getDateCreation()));
		} else {
			client.setMotDePasse(getById(id).getMotDePasse());
		}
		em.merge(client);
	}

	public void delete(Integer id) {
		Client client = getById(id);
		em.remove(client);
	}

	public Client getById(Integer id) {
		TypedQuery<Client> query = em.createQuery("select c from Client c where c.id = :id", Client.class);
		query.setParameter("id", id);
		List<Client> client = query.getResultList();
		if (!client.isEmpty()) {
			return client.get(0);
		}
		return null;
	}
	
	public Client getByEmail(String email) {
		TypedQuery<Client> query = em.createQuery("select c from Client c where c.email = :email", Client.class);
		query.setParameter("email", email);
		List<Client> client = query.getResultList();
		if (!client.isEmpty()) {
			return client.get(0);
		}
		return null;
	}

	public List<Client> findTopFiveClients(){
		return this.em.createQuery("select cli from Client cli where id in (select cmd.client.id from Commande cmd group by cmd.client.id order by sum(cmd.client.id) desc)", Client.class).setMaxResults(5).getResultList();
		
	}
}
