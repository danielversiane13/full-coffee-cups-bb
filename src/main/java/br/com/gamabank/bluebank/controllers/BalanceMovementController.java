package br.com.gamabank.bluebank.controllers;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gamabank.bluebank.dto.BalanceMovementDto;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.forms.BalanceMovementForm;
import br.com.gamabank.bluebank.services.BalanceMovementService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/balance-movements")
public class BalanceMovementController {

	@Autowired
	private BalanceMovementService service;

	@GetMapping(value = "/{balanceTransferId}")
	@ApiOperation("Find by id a balance movement")
	public ResponseEntity<BalanceMovementDto> findById(@PathVariable UUID balanceTransferId) throws ExceptionHandler {
		var dto = service.findById(balanceTransferId);
		return ResponseEntity.ok(dto);
	}

	@PostMapping
	@ApiOperation("Create a deposit, withdrawal or transfer between accounts")
	public ResponseEntity<BalanceMovementDto> add(@RequestBody @Valid BalanceMovementForm form,
			UriComponentsBuilder uriBuilder) throws ExceptionHandler {
		var dto = service.create(form);
		URI uri = uriBuilder.path("/balance-transfer/{balanceTransferId}").buildAndExpand(dto.id).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

}
