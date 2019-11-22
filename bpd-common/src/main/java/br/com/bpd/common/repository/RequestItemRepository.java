package br.com.bpd.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import br.com.bpd.common.bean.RequestItem;

public interface RequestItemRepository extends Repository<RequestItem, Long> {

	RequestItem save(RequestItem requestItem);

	RequestItem update(RequestItem requestItem);

	Optional<RequestItem> findById(long id);

	int count();

	List<RequestItem> findAll(Pageable pageable);

	boolean deleteById(long id);

}
