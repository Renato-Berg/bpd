package br.com.bpd.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bpd.common.bean.Category;
import br.com.bpd.common.constants.PathsApiServices;
import br.com.bpd.common.service.CategoryService;
import br.com.bpd.common.validator.GenericValidator;

@RestController
public class CategoriesRestController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping(value = PathsApiServices.CATEGORIES, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> list(@RequestParam(value = "qtyItens", required = false) int qtyItens, @RequestParam(value = "indexPagination", required = false) int indexPagination, @RequestParam(value = "orderField", required = false) String orderField, @RequestParam(value = "order", required = false) String order) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		int totalOfRegisters = categoryService.count();
		PageRequest pageRequest = GenericValidator.validateListFields(qtyItens, indexPagination, totalOfRegisters, orderField, order);

		List<Category> categories;
		if (pageRequest != null) {
			categories = categoryService.findAll(pageRequest);
		} else {
			categories = categoryService.findAll(null);
		}
		
		return new ResponseEntity<Object>(categories, headers, HttpStatus.OK);
	}

}
