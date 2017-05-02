package fr.pizzeria.spring.web.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import fr.pizzeria.model.Pizza;


public interface IPizzaRepository extends JpaRepository<Pizza,Integer> {

    Pizza findByCode(String code);
    
   
    @Query("select pizza from Pizza pizza where pizza.typePizza='PIZZA' and pizza.actif=true")
    List<Pizza> findPizzaActual();
    
    
    @Query("select pizza from Pizza pizza where pizza.typePizza='OUICHE' and pizza.actif=true")
    List<Pizza> findOuicheActual();
    
}
