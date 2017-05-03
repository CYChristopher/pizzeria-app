package fr.pizzeria.spring.web.repository;


import fr.pizzeria.model.Client;
import fr.pizzeria.model.Commande;
import fr.pizzeria.model.StatutCommande;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ICommandeRepository extends JpaRepository<Commande, Integer> {
	Commande findById(Integer id);
	
	Commande findBynumeroCommande(String numeroCommande);
	
	List<Commande> findAll();
	
	List<Commande> findByClient(Client client);
	
	List<Commande> findByStatut(StatutCommande statut);
	
	List<Commande> findBydateCommande(LocalDateTime dateCommande);
}
