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

import br.com.bpd.common.bean.Client;

@Repository
public class ClientRepositoryImpl implements ClientRepository {

	@Autowired
	@PersistenceContext(unitName = "masterEntityManager")
	EntityManager masterEntityManager;

	@Autowired
	@PersistenceContext(unitName = "slaveEntityManager")
	EntityManager slaveEntityManager;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Client save(Client client) {
		masterEntityManager.persist(client);

		return client;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Client update(Client client) {
		masterEntityManager.merge(client);

		return client;
	}

	@Override
	public Optional<Client> findById(long id) {
		return Optional.of(slaveEntityManager.find(Client.class, id));
	}

	@Override
	public int count() {
		return ((Long) slaveEntityManager.createQuery("SELECT COUNT(c) FROM Client c").getSingleResult()).intValue();
	}

	@Override
	public List<Client> findAll(Pageable pageable) {
		TypedQuery<Client> query;
		
		if (pageable != null) {
			query = slaveEntityManager.createQuery("SELECT c FROM Client c", Client.class);
			query.setFirstResult(pageable.getPageNumber());
			query.setMaxResults(pageable.getPageSize());
		} else {
			query = slaveEntityManager.createQuery("SELECT c FROM Client c ORDER BY c.idCliente", Client.class);
		}
		
		return query.getResultList();
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public boolean deleteById(long id) {
		try {
			Optional<Client> optionalClient = Optional.of(masterEntityManager.find(Client.class, id));
			if (optionalClient.isPresent()) {
				Client client = optionalClient.get();
				
				masterEntityManager.remove(client);
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}

		return true;
	}

}
