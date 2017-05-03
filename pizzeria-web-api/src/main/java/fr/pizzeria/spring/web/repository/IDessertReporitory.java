package fr.pizzeria.spring.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.Dessert;

public interface IDessertReporitory extends JpaRepository<Dessert, Integer> {
	
	Dessert findById(Integer id);
	
	Dessert findByCode(String code);
	
	List<Dessert> findAll();
}
