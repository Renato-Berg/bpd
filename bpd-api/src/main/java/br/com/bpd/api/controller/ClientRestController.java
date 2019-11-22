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

import br.com.bpd.common.bean.Client;
import br.com.bpd.common.constants.PathsApiServices;
import br.com.bpd.common.service.ClientService;

@RestController
@RequestMapping(value = PathsApiServices.ROOT + PathsApiServices.VERSION + PathsApiServices.ROOT + PathsApiServices.CLIENT)
public class ClientRestController {

	@Autowired
	private ClientService clientService;

	@PutMapping(consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> create(final @RequestBody Client client) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		Client newClient = clientService.save(client);
		return new ResponseEntity<Client>(newClient, headers, HttpStatus.OK);
	}

	@GetMapping(value = PathsApiServices.ROOT + PathsApiServices.ID, consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> read(final @PathVariable("id") long id) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		Optional<Client> optionalClient = clientService.findById(id);
		if (optionalClient.isPresent()) {
			Client client = optionalClient.get();
			return new ResponseEntity<Client>(client, headers, HttpStatus.OK);
		} else {
			return new ResponseEntity<Client>(null, headers, HttpStatus.OK);
		}
	}

	@PostMapping(consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Client> update(final @RequestBody Client client) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		Client newClient = clientService.update(client);
		return new ResponseEntity<Client>(newClient, headers, HttpStatus.OK);
	}

	@DeleteMapping(value = PathsApiServices.ROOT + PathsApiServices.ID, consumes = { MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> delete(final @PathVariable("id") long id) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		
		boolean deleted = clientService.deleteById(id);

		return new ResponseEntity<Boolean>(deleted, headers, HttpStatus.OK);
	}

}
