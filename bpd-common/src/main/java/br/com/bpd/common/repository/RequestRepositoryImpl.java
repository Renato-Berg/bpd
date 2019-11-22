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

import br.com.bpd.common.bean.Request;

@Repository
public class RequestRepositoryImpl implements RequestRepository {

	@Autowired
	@PersistenceContext(unitName = "masterEntityManager")
	EntityManager masterEntityManager;

	@Autowired
	@PersistenceContext(unitName = "slaveEntityManager")
	EntityManager slaveEntityManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Request save(Request request) {
		masterEntityManager.persist(request);

		return request;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Request update(Request request) {
		masterEntityManager.merge(request);

		return request;
	}

	@Override
	public Optional<Request> findById(long id) {
		return Optional.of(slaveEntityManager.find(Request.class, id));
	}

	@Override
	public int count() {
		return ((Long) slaveEntityManager.createQuery("SELECT COUNT(r) FROM Request r").getSingleResult()).intValue();
	}

	@Override
	public List<Request> findAll(Pageable pageable) {
		TypedQuery<Request> query;
		
		if (pageable != null) {
			query = slaveEntityManager.createQuery("SELECT r FROM Request r", Request.class);
			query.setFirstResult(pageable.getPageNumber());
			query.setMaxResults(pageable.getPageSize());
		} else {
			query = slaveEntityManager.createQuery("SELECT r FROM Request r ORDER BY r.idRequest", Request.class);
		}
		
		return query.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean deleteById(long id) {
		try {
			Optional<Request> optionalRequest = Optional.of(masterEntityManager.find(Request.class, id));
			if (optionalRequest.isPresent()) {
				Request request = optionalRequest.get();
				
				masterEntityManager.remove(request);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
