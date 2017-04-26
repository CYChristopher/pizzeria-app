package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import fr.pizzeria.model.OffreMenu;

@Stateless
public class OffreMenuService {

	private final static String SELECT = "select o from OffreMenu o";
	private final static String _BY_ID = " where o.id=:id";

	@PersistenceContext
	private EntityManager em;

	public List<OffreMenu> findAll() {
		return em.createQuery(SELECT, OffreMenu.class).getResultList();
	}

	public OffreMenu find(Integer id) throws NoResultException {
		return em.createQuery(SELECT + _BY_ID, OffreMenu.class).setParameter("id", id).getSingleResult();
	}

	public void create(OffreMenu offreMenu) {
		em.persist(offreMenu);
	}

	public void update(Integer id, OffreMenu offreMenu) {
		try {
			offreMenu.setId(find(id).getId());
			em.merge(offreMenu);
		} catch (NoResultException e) {
			System.out.println("id non trouvé pour modification, création à la place");
			create(offreMenu);
		}
	}

	public void delete(Integer id) {
		try {
			em.remove(find(id));
		} catch (NoResultException e) {
			System.out.println("id non trouvé pour suppression");
		}
	}
}