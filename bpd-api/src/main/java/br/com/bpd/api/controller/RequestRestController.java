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

import br.com.bpd.common.bean.Request;
import br.com.bpd.common.constants.PathsApiServices;
import br.com.bpd.common.service.RequestService;

@RestController
@RequestMapping(value = PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.REQUEST)
public class RequestRestController {

	@Autowired
	private RequestService requestService;

	@PutMapping(consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Request> create(final @RequestBody Request request) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		Request newRequest = requestService.save(request);
		return new ResponseEntity<Request>(newRequest, headers, HttpStatus.OK);
	}

	@GetMapping(value = PathsApiServices.ROOT + PathsApiServices.ID, consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Request> read(final @PathVariable("id") long id) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		Optional<Request> optionalRequest = requestService.findById(id);
		if (optionalRequest.isPresent()) {
			Request request = optionalRequest.get();
			return new ResponseEntity<Request>(request, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<Request>(null, headers, HttpStatus.OK);
		}
	}

	@PostMapping(consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Request> update(final @RequestBody Request request) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		Request newRequest = requestService.update(request);
		return new ResponseEntity<Request>(newRequest, headers, HttpStatus.OK);
	}

	@DeleteMapping(value = PathsApiServices.ROOT + PathsApiServices.ID, consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> delete(final @PathVariable("id") long id) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		boolean deleted = requestService.deleteById(id);

		return new ResponseEntity<Boolean>(deleted, headers, HttpStatus.OK);
	}

}
