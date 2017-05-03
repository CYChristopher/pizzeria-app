package fr.pizzeria.spring.web.repository;


import fr.pizzeria.model.CommandePizza;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICommandePizzaRepository extends JpaRepository<CommandePizza, Integer> {

}
