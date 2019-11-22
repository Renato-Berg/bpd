package br.com.bpd.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bpd.common.bean.RequestItem;
import br.com.bpd.common.repository.RequestItemRepository;

@Service
public class RequestItemServiceImpl implements RequestItemService {

	@Autowired
	private RequestItemRepository requestItemRepository;

	@Override
	public RequestItem save(RequestItem requestItem) {
		return requestItemRepository.save(requestItem);
	}

	@Override
	public RequestItem update(RequestItem requestItem) {
		return requestItemRepository.update(requestItem);
	}

	@Override
	public Optional<RequestItem> findById(long id) {
		return requestItemRepository.findById(id);
	}

	@Override
	public int count() {
		return requestItemRepository.count();
	}

	@Override
	public List<RequestItem> findAll(Pageable pageable) {
		return requestItemRepository.findAll(pageable);
	}

	@Override
	public boolean deleteById(Long id) {
		return requestItemRepository.deleteById(id);
	}

}
