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

import br.com.bpd.common.bean.RequestItem;

@Repository
public class RequestItemRepositoryImpl implements RequestItemRepository {

	@Autowired
	@PersistenceContext(unitName = "masterEntityManager")
	EntityManager masterEntityManager;

	@Autowired
	@PersistenceContext(unitName = "slaveEntityManager")
	EntityManager slaveEntityManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public RequestItem save(RequestItem requestItem) {
		masterEntityManager.persist(requestItem);

		return requestItem;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public RequestItem update(RequestItem requestItem) {
		masterEntityManager.merge(requestItem);

		return requestItem;
	}

	@Override
	public Optional<RequestItem> findById(long id) {
		return Optional.of(slaveEntityManager.find(RequestItem.class, id));
	}

	@Override
	public int count() {
		return ((Long) slaveEntityManager.createQuery("SELECT COUNT(c) FROM Category c").getSingleResult()).intValue();
	}

	@Override
	public List<RequestItem> findAll(Pageable pageable) {
		TypedQuery<RequestItem> query;
		
		if (pageable != null) {
			query = slaveEntityManager.createQuery("SELECT c FROM Category c", RequestItem.class);
			query.setFirstResult(pageable.getPageNumber());
			query.setMaxResults(pageable.getPageSize());
		} else {
			query = slaveEntityManager.createQuery("SELECT c FROM Category c ORDER BY c.idCategoria", RequestItem.class);
		}
		
		return query.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean deleteById(long id) {
		try {
			Optional<RequestItem> optionalRequestItem = Optional.of(masterEntityManager.find(RequestItem.class, id));
			if (optionalRequestItem.isPresent()) {
				RequestItem requestItem = optionalRequestItem.get();
				
				masterEntityManager.remove(requestItem);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
