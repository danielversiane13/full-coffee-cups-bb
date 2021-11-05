package br.com.gamabank.bluebank.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gamabank.bluebank.dto.BalanceTransferDto;
import br.com.gamabank.bluebank.entities.BalanceTransfer;
import br.com.gamabank.bluebank.entities.BankAccount;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.exceptions.NotFoundException;
import br.com.gamabank.bluebank.factories.BalanceTransferFactory;
import br.com.gamabank.bluebank.forms.BalanceTransferForm;
import br.com.gamabank.bluebank.repositories.BalanceTransferRepository;
import br.com.gamabank.bluebank.repositories.BankAccountRepository;
import br.com.gamabank.bluebank.utils.PageableUtil;

@Service
public class BalanceTransferService {

	@Autowired
	private BalanceTransferRepository repository;
	
	@Autowired
	private BankAccountRepository bankAccountRepository;

	public Page<BalanceTransferDto> findByCustomerId(Pageable pageable, UUID id) {
		Pageable _pageable = PageableUtil.pageRequest(pageable);

		return repository.findByCustomerId(_pageable, id).map(BalanceTransferFactory::Create);
	}

	public BalanceTransferDto findById(UUID id) throws ExceptionHandler {
		BalanceTransfer balanceTransfer = repository.findById(id).orElseThrow(() -> new NotFoundException("Balance Transfer not found"));

		return BalanceTransferFactory.Create(balanceTransfer);
	}

	public BalanceTransferDto create(BalanceTransferForm form) throws ExceptionHandler {
		
		BankAccount fromBankAccount = bankAccountRepository.findById(form.fromBankAccountId).orElseThrow(() -> new NotFoundException("From BankAccount not found"));
		BankAccount toBankAccount = bankAccountRepository.findById(form.toBankAccountId).orElseThrow(() -> new NotFoundException("To BankAccount not found"));
		
		BalanceTransfer balanceTransfer = BalanceTransferFactory.Create(form, fromBankAccount, toBankAccount);
		repository.save(balanceTransfer);

		return BalanceTransferFactory.Create(balanceTransfer);
	}

}
