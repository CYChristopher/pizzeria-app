package fr.pizzeria.spring.web.repository;


import fr.pizzeria.model.Commande;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICommandeRepository extends JpaRepository<Commande, Integer> {

	Commande findById(String id);
}
