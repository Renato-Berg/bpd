package br.com.bpd.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bpd.common.bean.Category;
import br.com.bpd.common.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category save(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public Category update(Long idCategoria, Category category) {
		return categoryRepository.update(idCategoria, category);
	}

	@Override
	public Optional<Category> findById(long id) {
		return categoryRepository.findById(id);
	}

	@Override
	public int count() {
		return categoryRepository.count();
	}

	@Override
	public List<Category> findAll(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	@Override
	public boolean deleteById(Long idCategoria) {
		return categoryRepository.deleteById(idCategoria);
	}

}
