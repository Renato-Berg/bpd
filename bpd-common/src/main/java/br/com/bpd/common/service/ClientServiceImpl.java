package br.com.bpd.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bpd.common.bean.Client;
import br.com.bpd.common.repository.ClientRepository;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Client save(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public Client update(Client client) {
		return clientRepository.update(client);
	}

	@Override
	public Optional<Client> findById(long id) {
		return clientRepository.findById(id);
	}

	@Override
	public int count() {
		return clientRepository.count();
	}

	@Override
	public List<Client> findAll(Pageable pageable) {
		return clientRepository.findAll(pageable);
	}

	@Override
	public boolean deleteById(Long id) {
		return clientRepository.deleteById(id);
	}

}
