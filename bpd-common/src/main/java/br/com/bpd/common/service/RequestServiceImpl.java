package br.com.bpd.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bpd.common.bean.Request;
import br.com.bpd.common.repository.RequestRepository;

@Service
public class RequestServiceImpl implements RequestService {

	@Autowired
	private RequestRepository requestRepository;

	@Override
	public Request save(Request request) {
		return requestRepository.save(request);
	}

	@Override
	public Request update(Request request) {
		return requestRepository.update(request);
	}

	@Override
	public Optional<Request> findById(long id) {
		return requestRepository.findById(id);
	}

	@Override
	public int count() {
		return requestRepository.count();
	}

	@Override
	public List<Request> findAll(Pageable pageable) {
		return requestRepository.findAll(pageable);
	}

	@Override
	public boolean deleteById(Long id) {
		return requestRepository.deleteById(id);
	}

}
