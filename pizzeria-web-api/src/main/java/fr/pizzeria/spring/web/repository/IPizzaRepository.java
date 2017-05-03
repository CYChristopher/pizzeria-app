package fr.pizzeria.spring.web.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.pizzeria.model.Pizza;

public interface IPizzaRepository extends JpaRepository<Pizza, Integer> {

	Pizza findByCode(String code);

	@Modifying
	@Transactional
	@Query("select pizza from Pizza pizza where pizza.typePizza='PIZZA' and pizza.archive=false")
	List<Pizza> findPizzaActual();

	@Modifying
	@Transactional
	@Query("select pizza from Pizza pizza where pizza.typePizza='OUICHE' and pizza.archive=false")
	List<Pizza> findOuicheActual();

}
