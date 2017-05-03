package fr.pizzeria.spring.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.Livreur;

public interface ILivreur extends JpaRepository<Livreur, Integer> {
	
	Livreur findById(Integer id);
	
	List<Livreur> findAll();
}
