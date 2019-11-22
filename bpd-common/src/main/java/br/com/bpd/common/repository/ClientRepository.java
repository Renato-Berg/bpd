package br.com.bpd.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import br.com.bpd.common.bean.Client;

public interface ClientRepository extends Repository<Client, Long> {

	Client save(Client client);

	Client update(Client client);

	Optional<Client> findById(long id);

	int count();

	List<Client> findAll(Pageable pageable);

	boolean deleteById(long id);

}
