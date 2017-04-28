package fr.pizzeria.admin.metier;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import fr.pizzeria.admin.exception.StockageException;
import fr.pizzeria.model.OffreMenu;

@Stateless
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class OffreMenuService {
	
	@PersistenceContext(unitName = "pizzeria-admin-web")
	private EntityManager em;
	
	private TypedQuery<OffreMenu> query;

	public List<OffreMenu> findAll() {
		this.query = em.createQuery("select om from OffreMenu om", OffreMenu.class);
		List<OffreMenu> menus = query.getResultList();
		return menus;
	}
	
	public OffreMenu findByCode(String code) throws NoResultException {
		this.query = em.createQuery("SELECT om FROM OffreMenu om WHERE om.code=:codeM", OffreMenu.class)
				.setParameter("codeM", code);
		return query.getSingleResult();
	}

	public OffreMenu findById(Integer id) throws NoResultException {
		this.query = em.createQuery("SELECT om FROM OffreMenu om WHERE om.id=:idM", OffreMenu.class)
				.setParameter("idM", id);
		return query.getSingleResult();
	}

	public void create(OffreMenu offreMenu) {
		em.persist(offreMenu);
	}

	public void update(Integer id, OffreMenu menu) throws NoResultException {
		OffreMenu old = this.findById(id);
		menu.setId(old.getId());
		em.merge(menu);
		
	}
	
	/**
	 * archive le menu
	 * @param id
	 * @throws StockageException
	 */
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void archive(Integer id) throws NoResultException {
		OffreMenu menu = this.findById(id);
		menu.setArchive(true);
		em.merge(menu);
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void delete(Integer id) throws NoResultException {
		OffreMenu menu = this.findById(id);
		em.remove(menu);
	}
}