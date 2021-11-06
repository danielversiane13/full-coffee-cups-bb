package br.com.gamabank.bluebank.controllers;

import java.awt.print.Pageable;
import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gamabank.bluebank.dto.AddressDto;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.forms.AddressForm;
import br.com.gamabank.bluebank.services.AddressService;

@RestController
@RequestMapping("/customers/{customer_id}/address")
public class AddressesController {
	
	@Autowired
	private AddressService service;
	
	@GetMapping
	public ResponseEntity<Page<AddressDto>> findById(Pageable pageable){ 
		return ResponseEntity.ok(service.findAll(pageable));
	}
	
	@PostMapping
	public ResponseEntity<AddressDto> add(@RequestBody @Valid AddressForm form,
			UriComponentsBuilder uriBuilder) {
		var dto = service.create(form);
		URI uri = uriBuilder.path("/customers/{customer_id}/address").buildAndExpand(dto.id).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping
	public ResponseEntity<AddressDto> update(@RequestBody @Valid AddressForm form,
			UriComponentsBuilder uriBuilder) {
		var dto = service.update(form);
		URI uri = uriBuilder.path("/customers/{customer_id}/address").buildAndExpand(dto.id).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

}

