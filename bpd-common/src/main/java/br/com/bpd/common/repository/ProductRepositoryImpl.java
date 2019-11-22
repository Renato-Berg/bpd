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

import br.com.bpd.common.bean.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	@PersistenceContext(unitName = "masterEntityManager")
	EntityManager masterEntityManager;

	@Autowired
	@PersistenceContext(unitName = "slaveEntityManager")
	EntityManager slaveEntityManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Product save(Product product) {
		masterEntityManager.persist(product);

		return product;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Product update(Product product) {
		masterEntityManager.merge(product);

		return product;
	}

	@Override
	public Optional<Product> findById(long id) {
		return Optional.of(slaveEntityManager.find(Product.class, id));
	}

	@Override
	public int count() {
		return ((Long) slaveEntityManager.createQuery("SELECT COUNT(c) FROM Category c").getSingleResult()).intValue();
	}

	@Override
	public List<Product> findAll(Pageable pageable) {
		TypedQuery<Product> query;
		
		if (pageable != null) {
			query = slaveEntityManager.createQuery("SELECT c FROM Category c", Product.class);
			query.setFirstResult(pageable.getPageNumber());
			query.setMaxResults(pageable.getPageSize());
		} else {
			query = slaveEntityManager.createQuery("SELECT c FROM Category c ORDER BY c.idCategoria", Product.class);
		}
		
		return query.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean deleteById(long id) {
		try {
			Optional<Product> optionalProduct = Optional.of(masterEntityManager.find(Product.class, id));
			if (optionalProduct.isPresent()) {
				Product product = optionalProduct.get();
				
				masterEntityManager.remove(product);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
