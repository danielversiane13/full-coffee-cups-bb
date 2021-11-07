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
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gamabank.bluebank.dto.AddressDto;
import br.com.gamabank.bluebank.dto.BankAccountDto;
import br.com.gamabank.bluebank.dto.CustomerDto;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.forms.AddressForm;
import br.com.gamabank.bluebank.forms.BankAccountForm;
import br.com.gamabank.bluebank.forms.CustomerForm;
import br.com.gamabank.bluebank.forms.UpdateCustomerActiveForm;
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

	@PatchMapping(value = "/{customerId}/active")
	public ResponseEntity<CustomerDto> update(@PathVariable UUID customerId,
			@RequestBody @Valid UpdateCustomerActiveForm form) throws ExceptionHandler {
		var dto = service.updateActive(form, customerId);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/{customerId}/address")
	public ResponseEntity<AddressDto> findAddress(@PathVariable UUID customerId) throws ExceptionHandler {
		var dto = service.findAddress(customerId);
		return ResponseEntity.ok(dto);
	}

	@PostMapping("/{customerId}/address")
	public ResponseEntity<AddressDto> createAddress(@PathVariable UUID customerId, @RequestBody @Valid AddressForm form,
			UriComponentsBuilder uriBuilder) throws ExceptionHandler {
		var dto = service.createAddress(customerId, form);
		URI uri = uriBuilder.path("/customers/{customerId}/address").buildAndExpand(dto.id).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping("/{customerId}/address")
	public ResponseEntity<AddressDto> updateAddress(@PathVariable UUID customerId, @RequestBody @Valid AddressForm form)
			throws ExceptionHandler {
		var dto = service.updateAddress(customerId, form);
		return ResponseEntity.ok(dto);
	}
	
	// Bank Account Controller

	@GetMapping("/{customerId}/bank-accounts")
	public ResponseEntity<BankAccountDto> findBankAccount(@PathVariable UUID customerId) throws ExceptionHandler {
		var dto = service.findBankAccount(customerId);
		return ResponseEntity.ok(dto);
	}

	@PostMapping("/{customerId}/bank-accounts")
	public ResponseEntity<BankAccountDto> createBankAccount(@PathVariable UUID customerId, @RequestBody @Valid BankAccountForm form,
			UriComponentsBuilder uriBuilder) throws ExceptionHandler {
		var dto = service.createBankAccount(customerId, form);
		URI uri = uriBuilder.path("/customers/{customerId}/bank-account").buildAndExpand(dto.id).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping("/{customerId}/bank-accounts")
	public ResponseEntity<BankAccountDto> updateBankAccount(@PathVariable UUID customerId, @RequestBody @Valid BankAccountForm form)
			throws ExceptionHandler {
		var dto = service.updateBankAccount(customerId, form);
		return ResponseEntity.ok(dto);
	}

}
