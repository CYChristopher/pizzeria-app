package fr.pizzeria.spring.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.Boisson;

public interface IBoissonRepository extends JpaRepository<Boisson, Integer> {
	
	Boisson findById(Integer id);
	
	Boisson findByCode(String code);
	
	List<Boisson> findAll();
}
