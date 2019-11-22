package br.com.bpd.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import br.com.bpd.common.bean.RequestItem;

public interface RequestItemService {

	RequestItem save(RequestItem requestItem);

	RequestItem update(RequestItem requestItem);

	Optional<RequestItem> findById(long id);

	int count();

	List<RequestItem> findAll(Pageable pageable);

	boolean deleteById(Long id);

}
