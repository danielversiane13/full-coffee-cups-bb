package br.com.gamabank.bluebank.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gamabank.bluebank.dto.BalanceMovementDto;
import br.com.gamabank.bluebank.entities.BalanceMovement;
import br.com.gamabank.bluebank.entities.BankAccount;
import br.com.gamabank.bluebank.entities.OperationTypeEnum;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.exceptions.NotAcceptableException;
import br.com.gamabank.bluebank.exceptions.NotFoundException;
import br.com.gamabank.bluebank.factories.BalanceMovementFactory;
import br.com.gamabank.bluebank.forms.BalanceMovementForm;
import br.com.gamabank.bluebank.repositories.BalanceMovementRepository;
import br.com.gamabank.bluebank.repositories.BankAccountRepository;

@Service
public class BalanceMovementService {

	@Autowired
	private BalanceMovementRepository repository;

	@Autowired
	private BankAccountRepository bankAccountRepository;

	public BalanceMovementDto findById(UUID id) throws ExceptionHandler {
		BalanceMovement balanceMovement = repository.findById(id)
				.orElseThrow(() -> new NotFoundException("Balance Movement not found"));

		return BalanceMovementFactory.Create(balanceMovement);
	}

	public BalanceMovementDto create(BalanceMovementForm form) throws ExceptionHandler {
		boolean hasToAccount = form.toBankAccountId != null;
		boolean hasFromAccount = form.fromBankAccountId != null;

		if (!hasToAccount && !hasFromAccount) {
			throw new NotAcceptableException("Invalid operation");
		}

		BalanceMovement balanceMovement = BalanceMovementFactory.Create(form);

		if (!hasFromAccount) {
			this.operationDeposit(balanceMovement, form);
		} else if (!hasToAccount) {
			this.operationWithdraw(balanceMovement, form);
		} else {
			this.operationTransfer(balanceMovement, form);
		}

		repository.save(balanceMovement);

		return BalanceMovementFactory.Create(balanceMovement);
	}

	private void operationDeposit(BalanceMovement balanceMovement, BalanceMovementForm form) throws ExceptionHandler {
		BankAccount toBankAccount = bankAccountRepository.findById(form.toBankAccountId)
				.orElseThrow(() -> new NotFoundException("To BankAccount not found"));

		if (toBankAccount.isActive() == false) {
			throw new NotAcceptableException("To BankAccount is inactive");
		}

		balanceMovement.setOperationType(OperationTypeEnum.DEPOSIT);
		balanceMovement.setToBankAccount(toBankAccount);

		toBankAccount.balanceDeposit(form.value);
		toBankAccount.setUpdatedAt(LocalDateTime.now());

		bankAccountRepository.save(toBankAccount);
	}

	private void operationWithdraw(BalanceMovement balanceMovement, BalanceMovementForm form) throws ExceptionHandler {

		BankAccount fromBankAccount = bankAccountRepository.findById(form.fromBankAccountId)
				.orElseThrow(() -> new NotFoundException("From BankAccount not found"));

		if (fromBankAccount.isActive() == false) {
			throw new NotAcceptableException("From BankAccount is inactive");
		}

		balanceMovement.setOperationType(OperationTypeEnum.WITHDRAW);
		balanceMovement.setFromBankAccount(fromBankAccount);

		if (fromBankAccount.getBalance() < form.value) {
			throw new NotAcceptableException("Insufficient balance");
		}

		fromBankAccount.balanceWithdraw(form.value);
		fromBankAccount.setUpdatedAt(LocalDateTime.now());

		bankAccountRepository.save(fromBankAccount);
	}

	private void operationTransfer(BalanceMovement balanceMovement, BalanceMovementForm form) throws ExceptionHandler {
		if (form.toBankAccountId.equals(form.fromBankAccountId)) {
			throw new NotAcceptableException("Invalid operation");
		}

		BankAccount toBankAccount = bankAccountRepository.findById(form.toBankAccountId)
				.orElseThrow(() -> new NotFoundException("To BankAccount not found"));
		if (toBankAccount.isActive() == false) {
			throw new NotAcceptableException("To BankAccount is inactive");
		}

		BankAccount fromBankAccount = bankAccountRepository.findById(form.fromBankAccountId)
				.orElseThrow(() -> new NotFoundException("From BankAccount not found"));
		if (fromBankAccount.isActive() == false) {
			throw new NotAcceptableException("From BankAccount is inactive");
		}

		balanceMovement.setOperationType(OperationTypeEnum.TRANSFER);
		balanceMovement.setToBankAccount(toBankAccount);
		balanceMovement.setFromBankAccount(fromBankAccount);

		if (fromBankAccount.getBalance() < form.value) {
			throw new NotAcceptableException("Insufficient balance");
		}

		toBankAccount.balanceDeposit(form.value);
		toBankAccount.setUpdatedAt(LocalDateTime.now());

		fromBankAccount.balanceWithdraw(form.value);
		fromBankAccount.setUpdatedAt(LocalDateTime.now());

		bankAccountRepository.save(toBankAccount);
		bankAccountRepository.save(fromBankAccount);
	}

}
