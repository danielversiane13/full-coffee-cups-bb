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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gamabank.bluebank.dto.BalanceTransferDto;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.forms.BalanceTransferForm;
import br.com.gamabank.bluebank.services.BalanceTransferService;

@RestController
public class BalanceTransferController {

	@Autowired
	private BalanceTransferService service;

	@GetMapping(value = "/bank-accounts/{bankAccountId}/balance-transfer")
	public ResponseEntity<Page<BalanceTransferDto>> findByFromBankAccountId(Pageable pageable,
			@PathVariable UUID bankAccountId) {
		return ResponseEntity.ok(service.findByFromBankAccountId(pageable, bankAccountId));
	}

	@GetMapping(value = "/balance-transfer/{balanceTransferId}")
	public ResponseEntity<BalanceTransferDto> findById(@PathVariable UUID balanceTransferId) throws ExceptionHandler {
		var dto = service.findById(balanceTransferId);
		return ResponseEntity.ok(dto);
	}

	@PostMapping(value = "/balance-transfer")
	public ResponseEntity<BalanceTransferDto> add(@RequestBody @Valid BalanceTransferForm form,
			UriComponentsBuilder uriBuilder) throws ExceptionHandler {
		var dto = service.create(form);
		URI uri = uriBuilder.path("/customers/{customerId}/bank-accounts/balance-transfer").buildAndExpand(dto.id)
				.toUri();
		return ResponseEntity.created(uri).body(dto);
	}

}
