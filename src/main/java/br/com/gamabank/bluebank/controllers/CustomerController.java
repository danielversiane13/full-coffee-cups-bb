package br.com.gamabank.bluebank.controllers;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gamabank.bluebank.forms.CustomerForm;
import br.com.gamabank.bluebank.dto.CustomerDto;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.services.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@GetMapping
	public ResponseEntity<Page<CustomerDto>> findAll(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}

	@PostMapping
	public ResponseEntity<CustomerDto> add(@RequestBody @Valid CustomerForm form, UriComponentsBuilder uriBuilder) {
		var dto = service.create(form);
		URI uri = uriBuilder.path("/customers/{customerId}").buildAndExpand(dto.id).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping(value = "/{customerId}")
	public ResponseEntity<CustomerDto> findById(@PathVariable UUID customerId) throws ExceptionHandler {
		var dto = service.findById(customerId);
		return ResponseEntity.ok(dto);
	}

	@PutMapping(value = "/{customerId}")
	public ResponseEntity<CustomerDto> update(@PathVariable UUID customerId, @RequestBody @Valid CustomerForm form)
			throws ExceptionHandler {
		var dto = service.update(form, customerId);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping(value = "/{customerId}")
	public ResponseEntity<Void> destroy(@PathVariable UUID customerId) throws ExceptionHandler {
		service.deleteById(customerId);
		return ResponseEntity.noContent().build();
	}

}
