package br.com.bpd.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bpd.common.bean.Category;
import br.com.bpd.common.constants.PathsApiServices;
import br.com.bpd.common.service.CategoryService;

@RestController
@RequestMapping(value = PathsApiServices.ROOT + PathsApiServices.CATEGORY)
public class CategoryRestController {

	@Autowired
	private CategoryService categoryService;

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> create(final @RequestBody Category category) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		Category newCategory = categoryService.save(category);
		return new ResponseEntity<Category>(newCategory, headers, HttpStatus.OK);
	}

	@GetMapping(value = PathsApiServices.ROOT + "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> read(final @PathVariable("id") long id) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		Optional<Category> optionalCategory = categoryService.findById(id);
		if (optionalCategory.isPresent()) {
			Category category = optionalCategory.get();
			return new ResponseEntity<Category>(category, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<Category>(null, headers, HttpStatus.OK);
		}
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Category> update(final @RequestBody Category category) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		Category newCategory = categoryService.save(category);
		return new ResponseEntity<Category>(newCategory, headers, HttpStatus.OK);
	}

	@DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> delete(final @PathVariable("id") long id) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		Boolean deleted = categoryService.deleteById(id);
		return new ResponseEntity<Boolean>(deleted, headers, HttpStatus.OK);
	}

}
