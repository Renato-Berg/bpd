package br.com.bpd.common.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.bpd.common.bean.Product;
import br.com.bpd.common.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product save(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product update(Product product) {
		return productRepository.update(product);
	}

	@Override
	public Optional<Product> findById(long id) {
		return productRepository.findById(id);
	}

	@Override
	public int count() {
		return productRepository.count();
	}

	@Override
	public List<Product> findAll(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public boolean deleteById(Long id) {
		return productRepository.deleteById(id);
	}

}
