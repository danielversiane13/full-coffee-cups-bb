package br.com.gamabank.bluebank.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gamabank.bluebank.dto.BalanceMovementDto;
import br.com.gamabank.bluebank.services.BankAccountService;

@RestController
@RequestMapping("/bank-accounts")
public class BankAccountController {

	@Autowired
	private BankAccountService service;

	@GetMapping(value = "/{bankAccountId}/balance-movements")
	public ResponseEntity<Page<BalanceMovementDto>> findByFromBankAccountIdOrToBankAccountId(Pageable pageable,
			@PathVariable UUID bankAccountId) {
		return ResponseEntity.ok(service.findByFromBankAccountIdOrToBankAccountId(pageable, bankAccountId));
	}

}
