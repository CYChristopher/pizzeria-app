package fr.pizzeria.spring.web.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.pizzeria.model.Ingredient;

public interface IIngredientRepository extends JpaRepository<Ingredient, Integer> {
	
	Ingredient findById(Integer id);
	
	@Modifying
	@Transactional
	@Query("select i from Ingredient i where i.archive='false';")
	List<Ingredient> findAll();
}