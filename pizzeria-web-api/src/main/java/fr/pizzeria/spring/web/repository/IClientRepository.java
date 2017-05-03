package fr.pizzeria.spring.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.Client;

public interface IClientRepository extends JpaRepository<Client, Integer> {
	
	Client findById(Integer id) ;

	Client findByEmailAndMotDePasse(String email, String motDePasse);
	
	List<Client> findAll();
}
