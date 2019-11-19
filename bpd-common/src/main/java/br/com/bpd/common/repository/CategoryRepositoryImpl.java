package br.com.bpd.common.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

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
	public Category save(Category category) {
		masterEntityManager.getTransaction().begin();
		masterEntityManager.persist(category);
		masterEntityManager.getTransaction().commit();

		return category;
	}

	@Override
	public Optional<Category> findById(long id) {
		return Optional.of(slaveEntityManager.find(Category.class, id));
	}

	@Override
	public int count() {
		return ((Integer) slaveEntityManager.createQuery("SELECT COUNT(c) FROM Category c").getSingleResult()).intValue();
	}

	@Override
	public List<Category> findAll(Pageable pageable) {
		TypedQuery<Category> query = slaveEntityManager.createQuery("SELECT c FROM Category c ORDER BY c." + pageable.getSort(), Category.class);
		query.setFirstResult(pageable.getPageNumber());
		query.setMaxResults(pageable.getPageSize());
		
		return query.getResultList();
	}

	@Override
	public boolean deleteById(long id) {
		try {
			Optional<Category> optionalCategory = this.findById(id);
			if (optionalCategory.isPresent()) {
				Category category = optionalCategory.get();
				
				masterEntityManager.getTransaction().begin();
				masterEntityManager.remove(category);
				masterEntityManager.getTransaction().commit();
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
