package br.com.bpd.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import br.com.bpd.common.bean.Request;

public interface RequestService {

	Request save(Request request);

	Request update(Request request);

	Optional<Request> findById(long id);

	int count();

	List<Request> findAll(Pageable pageable);

	boolean deleteById(Long id);

}
