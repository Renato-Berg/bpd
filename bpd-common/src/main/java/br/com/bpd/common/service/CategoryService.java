package br.com.bpd.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import br.com.bpd.common.bean.Category;

public interface CategoryService {

	Category save(Category category);

	Optional<Category> findById(long id);

	int count();

	List<Category> findAll(Pageable pageable);

	boolean deleteById(long id);

}