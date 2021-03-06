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
import br.com.gamabank.bluebank.forms.UpdateBankAccountActiveForm;
import br.com.gamabank.bluebank.forms.UpdateCustomerActiveForm;
import br.com.gamabank.bluebank.services.CustomerService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private CustomerService service;

	@GetMapping
	@ApiOperation("Find all customers")
	public ResponseEntity<Page<CustomerDto>> findAll(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}

	@PostMapping
	@ApiOperation("Create a customer")
	public ResponseEntity<CustomerDto> add(@RequestBody @Valid CustomerForm form, UriComponentsBuilder uriBuilder)
			throws ExceptionHandler {
		var dto = service.create(form);
		URI uri = uriBuilder.path("/customers/{customerId}").buildAndExpand(dto.id).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@GetMapping(value = "/{customerId}")
	@ApiOperation("Find customer by id")
	public ResponseEntity<CustomerDto> findById(@PathVariable UUID customerId) throws ExceptionHandler {
		var dto = service.findById(customerId);
		return ResponseEntity.ok(dto);
	}

	@PutMapping(value = "/{customerId}")
	@ApiOperation("Update a customer")
	public ResponseEntity<CustomerDto> update(@PathVariable UUID customerId, @RequestBody @Valid CustomerForm form)
			throws ExceptionHandler {
		var dto = service.update(form, customerId);
		return ResponseEntity.ok(dto);
	}

	@DeleteMapping(value = "/{customerId}")
	@ApiOperation("Delete a customer")
	public ResponseEntity<Void> destroy(@PathVariable UUID customerId) throws ExceptionHandler {
		service.deleteById(customerId);
		return ResponseEntity.noContent().build();
	}

	@PatchMapping(value = "/{customerId}/active")
	@ApiOperation("Update status active of customer by id")
	public ResponseEntity<CustomerDto> update(@PathVariable UUID customerId,
			@RequestBody @Valid UpdateCustomerActiveForm form) throws ExceptionHandler {
		var dto = service.updateActive(form, customerId);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/{customerId}/address")
	@ApiOperation("Find address by customer id")
	public ResponseEntity<AddressDto> findAddress(@PathVariable UUID customerId) throws ExceptionHandler {
		var dto = service.findAddress(customerId);
		return ResponseEntity.ok(dto);
	}

	@PostMapping("/{customerId}/address")
	@ApiOperation("Create an address by customer id")
	public ResponseEntity<AddressDto> createAddress(@PathVariable UUID customerId, @RequestBody @Valid AddressForm form,
			UriComponentsBuilder uriBuilder) throws ExceptionHandler {
		var dto = service.createAddress(customerId, form);
		URI uri = uriBuilder.path("/customers/{customerId}/address").buildAndExpand(dto.id).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping("/{customerId}/address")
	@ApiOperation("Update an address by customer id")
	public ResponseEntity<AddressDto> updateAddress(@PathVariable UUID customerId, @RequestBody @Valid AddressForm form)
			throws ExceptionHandler {
		var dto = service.updateAddress(customerId, form);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/{customerId}/bank-accounts")
	@ApiOperation("Find bank account by customer id")
	public ResponseEntity<BankAccountDto> findBankAccount(@PathVariable UUID customerId) throws ExceptionHandler {
		var dto = service.findBankAccount(customerId);
		return ResponseEntity.ok(dto);
	}

	@PostMapping("/{customerId}/bank-accounts")
	@ApiOperation("Create a bank account by customer id")
	public ResponseEntity<BankAccountDto> createBankAccount(@PathVariable UUID customerId,
			@RequestBody @Valid BankAccountForm form, UriComponentsBuilder uriBuilder) throws ExceptionHandler {
		var dto = service.createBankAccount(customerId, form);
		URI uri = uriBuilder.path("/customers/{customerId}/bank-account").buildAndExpand(dto.id).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PatchMapping(value = "/{customerId}/bank-accounts/active")
	@ApiOperation("Update status active of bank account by customer id")
	public ResponseEntity<BankAccountDto> update(@PathVariable UUID customerId,
			@RequestBody @Valid UpdateBankAccountActiveForm form) throws ExceptionHandler {
		var dto = service.updateBankAccountActive(form, customerId);
		return ResponseEntity.ok(dto);
	}

}
