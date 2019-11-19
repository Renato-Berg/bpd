package br.com.bpd.common.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import br.com.bpd.common.bean.Category;

public interface CategoryRepository extends Repository<Category, Long> {

	Category save(Category category);

	Optional<Category> findById(long id);

	int count();

	List<Category> findAll(Pageable pageable);

	boolean deleteById(long id);

}
