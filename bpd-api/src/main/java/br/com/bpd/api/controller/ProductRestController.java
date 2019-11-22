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

import br.com.bpd.common.bean.Product;
import br.com.bpd.common.constants.PathsApiServices;
import br.com.bpd.common.service.ProductService;

@RestController
@RequestMapping(value = PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.PRODUCT)
public class ProductRestController {

	@Autowired
	private ProductService productService;

	@PutMapping(consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> create(final @RequestBody Product product) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		Product newProduct = productService.save(product);
		return new ResponseEntity<Product>(newProduct, headers, HttpStatus.OK);
	}

	@GetMapping(value = PathsApiServices.ROOT + PathsApiServices.ID, consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> read(final @PathVariable("id") long id) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		Optional<Product> optionalProduct = productService.findById(id);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			return new ResponseEntity<Product>(product, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<Product>(null, headers, HttpStatus.OK);
		}
	}

	@PostMapping(consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> update(final @RequestBody Product product) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		Product newProduct = productService.update(product);
		return new ResponseEntity<Product>(newProduct, headers, HttpStatus.OK);
	}

	@DeleteMapping(value = PathsApiServices.ROOT + PathsApiServices.ID, consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> delete(final @PathVariable("id") long id) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		boolean deleted = productService.deleteById(id);

		return new ResponseEntity<Boolean>(deleted, headers, HttpStatus.OK);
	}

}
