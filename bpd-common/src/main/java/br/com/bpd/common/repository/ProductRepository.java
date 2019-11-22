package br.com.bpd.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import br.com.bpd.common.bean.Product;

public interface ProductRepository extends Repository<Product, Long> {

	Product save(Product product);

	Product update(Product product);

	Optional<Product> findById(long id);

	int count();

	List<Product> findAll(Pageable pageable);

	boolean deleteById(long id);

}
