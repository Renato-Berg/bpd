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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.bpd.common.bean.Product;
import br.com.bpd.common.constants.PathsApiServices;
import br.com.bpd.common.service.ProductService;
import br.com.bpd.common.validator.GenericValidator;

@RestController
@RequestMapping(value = PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.PRODUCTS)
public class ProductsRestController {

	@Autowired
	private ProductService productService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> list(@RequestParam(value = "qtyItens", required = false) Integer qtyItens, @RequestParam(value = "indexPagination", required = false) Integer indexPagination, @RequestParam(value = "orderField", required = false) String orderField, @RequestParam(value = "order", required = false) String order) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		int totalOfRegisters = productService.count();
		PageRequest pageRequest = GenericValidator.validateListFields(qtyItens, indexPagination, totalOfRegisters, orderField, order);

		List<Product> products;
		if (pageRequest != null) {
			products = productService.findAll(pageRequest);
		} else {
			products = productService.findAll(null);
		}
		
		return new ResponseEntity<Object>(products, headers, HttpStatus.OK);
	}

}
