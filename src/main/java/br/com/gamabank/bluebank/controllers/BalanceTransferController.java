package br.com.gamabank.bluebank.controllers;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gamabank.bluebank.dto.BalanceTransferDto;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.forms.BalanceTransferForm;
import br.com.gamabank.bluebank.services.BalanceTransferService;

@RestController
@RequestMapping("/balance-transfer")
public class BalanceTransferController {

	@Autowired
	private BalanceTransferService service;

	@GetMapping
	public ResponseEntity<Page<BalanceTransferDto>> findAll(Pageable pageable) {
		return ResponseEntity.ok(service.findAll(pageable));
	}
	
	@GetMapping(value = "/customers/{customer_id}/balance-transfer")
	public ResponseEntity<BalanceTransferDto> findById(@PathVariable UUID balanceTransferId) throws ExceptionHandler {
		var dto = service.findById(balanceTransferId);
		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<BalanceTransferDto> add(@RequestBody @Valid BalanceTransferForm form, UriComponentsBuilder uriBuilder) {
		var dto = service.create(form);
		URI uri = uriBuilder.path("/customers/{customer_id}/bank-accounts/balance-transfer").buildAndExpand(dto.id).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

}
