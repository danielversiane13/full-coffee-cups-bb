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
			BankAccount toBankAccount = bankAccountRepository.findById(form.toBankAccountId)
					.orElseThrow(() -> new NotFoundException("To BankAccount not found"));

			balanceMovement.setOperationType(OperationTypeEnum.DEPOSIT);
			balanceMovement.setToBankAccount(toBankAccount);

			toBankAccount.balanceDeposit(form.value);
			toBankAccount.setUpdatedAt(LocalDateTime.now());

			bankAccountRepository.save(toBankAccount);

		} else if (!hasToAccount) {
			BankAccount fromBankAccount = bankAccountRepository.findById(form.fromBankAccountId)
					.orElseThrow(() -> new NotFoundException("From BankAccount not found"));

			balanceMovement.setOperationType(OperationTypeEnum.WITHDRAW);
			balanceMovement.setFromBankAccount(fromBankAccount);

			if (fromBankAccount.getBalance() < form.value) {
				throw new NotAcceptableException("Insufficient balance");
			}
			fromBankAccount.balanceWithdraw(form.value);
			fromBankAccount.setUpdatedAt(LocalDateTime.now());

			bankAccountRepository.save(fromBankAccount);

		} else {
			if (form.toBankAccountId.equals(form.fromBankAccountId)) {
				throw new NotAcceptableException("Invalid operation");
			}
			BankAccount toBankAccount = bankAccountRepository.findById(form.toBankAccountId)
					.orElseThrow(() -> new NotFoundException("To BankAccount not found"));
			BankAccount fromBankAccount = bankAccountRepository.findById(form.fromBankAccountId)
					.orElseThrow(() -> new NotFoundException("From BankAccount not found"));

			balanceMovement.setOperationType(OperationTypeEnum.TRANSFER);
			balanceMovement.setToBankAccount(toBankAccount);
			balanceMovement.setFromBankAccount(fromBankAccount);

			if (fromBankAccount.getBalance() < form.value) {
				throw new NotAcceptableException("Insufficient balance");
			}
			fromBankAccount.balanceWithdraw(form.value);
			fromBankAccount.setUpdatedAt(LocalDateTime.now());
			toBankAccount.balanceDeposit(form.value);
			toBankAccount.setUpdatedAt(LocalDateTime.now());

			bankAccountRepository.save(toBankAccount);
			bankAccountRepository.save(fromBankAccount);
		}

		repository.save(balanceMovement);

		return BalanceMovementFactory.Create(balanceMovement);
	}

}
