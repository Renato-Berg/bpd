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

import br.com.bpd.common.bean.RequestItem;
import br.com.bpd.common.constants.PathsApiServices;
import br.com.bpd.common.service.RequestItemService;

@RestController
@RequestMapping(value = PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.REQUEST_ITEM)
public class RequestItemRestController {

	@Autowired
	private RequestItemService requestItemService;

	@PutMapping(consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RequestItem> create(final @RequestBody RequestItem requestItem) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		RequestItem newRequestItem = requestItemService.save(requestItem);
		return new ResponseEntity<RequestItem>(newRequestItem, headers, HttpStatus.OK);
	}

	@GetMapping(value = PathsApiServices.ROOT + PathsApiServices.ID, consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RequestItem> read(final @PathVariable("id") long id) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		Optional<RequestItem> optionalRequestItem = requestItemService.findById(id);
		if (optionalRequestItem.isPresent()) {
			RequestItem requestItem = optionalRequestItem.get();
			return new ResponseEntity<RequestItem>(requestItem, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<RequestItem>(null, headers, HttpStatus.OK);
		}
	}

	@PostMapping(consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RequestItem> update(final @RequestBody RequestItem requestItem) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		RequestItem newRequestItem = requestItemService.update(requestItem);
		return new ResponseEntity<RequestItem>(newRequestItem, headers, HttpStatus.OK);
	}

	@DeleteMapping(value = PathsApiServices.ROOT + PathsApiServices.ID, consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> delete(final @PathVariable("id") long id) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		boolean deleted = requestItemService.deleteById(id);

		return new ResponseEntity<Boolean>(deleted, headers, HttpStatus.OK);
	}

}
