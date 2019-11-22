package br.com.bpd.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import br.com.bpd.common.bean.Request;

public interface RequestRepository extends Repository<Request, Long> {

	Request save(Request request);

	Request update(Request request);

	Optional<Request> findById(long id);

	int count();

	List<Request> findAll(Pageable pageable);

	boolean deleteById(long id);

}
