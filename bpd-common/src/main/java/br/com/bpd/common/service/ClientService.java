package br.com.bpd.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import br.com.bpd.common.bean.Client;

public interface ClientService {

	Client save(Client client);

	Client update(Client client);

	Optional<Client> findById(long id);

	int count();

	List<Client> findAll(Pageable pageable);

	boolean deleteById(Long id);

}
