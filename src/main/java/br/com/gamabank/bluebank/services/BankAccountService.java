package br.com.gamabank.bluebank.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gamabank.bluebank.dto.BankAccountDto;
import br.com.gamabank.bluebank.dto.CustomerDto;
import br.com.gamabank.bluebank.entities.BankAccount;
import br.com.gamabank.bluebank.entities.Customer;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.exceptions.NotFoundException;
import br.com.gamabank.bluebank.factories.BankAccountFactory;
import br.com.gamabank.bluebank.factories.CustomerFactory;
import br.com.gamabank.bluebank.forms.BankAccountForm;
import br.com.gamabank.bluebank.forms.CustomerForm;
import br.com.gamabank.bluebank.repositories.BankAccountRepository;
import br.com.gamabank.bluebank.utils.PageableUtil;

@Service
public class BankAccountService {

	@Autowired
	private BankAccountRepository repository;
	
	public Page<BankAccountDto> findAll(Pageable pageable) {
		
		Pageable _pageable = PageableUtil.pageRequest(pageable);

		return repository.findAll(_pageable).map(BankAccountFactory::Create);
	}
	
	
	public BankAccountDto findById(UUID id) throws ExceptionHandler {
		
		BankAccount bankAccount = repository.findById(id).orElseThrow(() -> new NotFoundException("Account not found"));

		return BankAccountFactory.Create(bankAccount);
		
	}
	
	public BankAccountDto create(BankAccountForm form) {
		
		BankAccount bankAccount = BankAccountFactory.Create(form);
		
		repository.save(bankAccount);

		return BankAccountFactory.Create(bankAccount);
		
	}
}
