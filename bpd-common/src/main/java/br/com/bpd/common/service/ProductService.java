package br.com.bpd.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import br.com.bpd.common.bean.Product;

public interface ProductService {

	Product save(Product product);

	Product update(Product product);

	Optional<Product> findById(long id);

	int count();

	List<Product> findAll(Pageable pageable);

	boolean deleteById(Long id);

}
