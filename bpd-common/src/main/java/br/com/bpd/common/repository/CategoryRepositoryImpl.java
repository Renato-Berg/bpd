package br.com.bpd.common.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.bpd.common.bean.Category;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

	@Autowired
	@PersistenceContext(unitName = "masterEntityManager")
	EntityManager masterEntityManager;

	@Autowired
	@PersistenceContext(unitName = "slaveEntityManager")
	EntityManager slaveEntityManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Category save(Category category) {
		masterEntityManager.persist(category);

		return category;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Category update(long idCategoria, Category category) {
		if (category != null && category.getIdCategoria() == idCategoria) {
			masterEntityManager.merge(category);
		} else {
			masterEntityManager.remove(category);
			masterEntityManager.persist(category);
		}

		return category;
	}

	@Override
	public Optional<Category> findById(long id) {
		return Optional.of(slaveEntityManager.find(Category.class, id));
	}

	@Override
	public int count() {
		return ((Long) slaveEntityManager.createQuery("SELECT COUNT(c) FROM Category c").getSingleResult()).intValue();
	}

	@Override
	public List<Category> findAll(Pageable pageable) {
		TypedQuery<Category> query;
		
		if (pageable != null) {
			query = slaveEntityManager.createQuery("SELECT c FROM Category c", Category.class);
			query.setFirstResult(pageable.getPageNumber());
			query.setMaxResults(pageable.getPageSize());
		} else {
			query = slaveEntityManager.createQuery("SELECT c FROM Category c ORDER BY c.idCategoria", Category.class);
		}
		
		return query.getResultList();
	}

	@Override
	@Transactional
	public boolean deleteById(long idCategoria) {
		try {
			Optional<Category> optionalCategory = Optional.of(masterEntityManager.find(Category.class, idCategoria));
			if (optionalCategory.isPresent()) {
				Category category = optionalCategory.get();
				
				masterEntityManager.remove(category);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
