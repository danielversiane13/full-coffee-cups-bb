package br.com.gamabank.bluebank.controllers;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gamabank.bluebank.dto.BankAccountDto;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.forms.BankAccountForm;
import br.com.gamabank.bluebank.forms.UpdateBankAccountActiveForm;
import br.com.gamabank.bluebank.services.BankAccountService;

@RestController
public class BankAccountController {

	@Autowired
	private BankAccountService service;
	
	@GetMapping(value = "/bank-accounts/{id}")
	public ResponseEntity<BankAccountDto> findById(@PathVariable UUID id) throws ExceptionHandler {
		var dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping(value = "/customers/{customerId}/bank-accounts")
	public ResponseEntity<List<BankAccountDto>> findAllByCustomerId(@PathVariable UUID customerId) throws ExceptionHandler {
		var dto = service.findAllByCustomer(customerId);
		return ResponseEntity.ok(dto);
	}
	
	@PostMapping(value = "/customers/{customerId}/bank-accounts")
	public ResponseEntity<BankAccountDto> add(@PathVariable UUID customerId, 
			                                  @RequestBody @Valid BankAccountForm form, 
			                                  UriComponentsBuilder uriBuilder) throws ExceptionHandler {
		var dto = service.create(form, customerId);
		URI uri = uriBuilder.path("/customers/{customerId}/bank-accounts").buildAndExpand(dto.id).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@PatchMapping(value = "/bank-accounts/{id}/active")
	public ResponseEntity<BankAccountDto> updateActive(@PathVariable UUID id,
			@RequestBody @Valid UpdateBankAccountActiveForm form) throws ExceptionHandler {
		var dto = service.updateActive(form, id);
		return ResponseEntity.ok(dto);
	}
		
}
