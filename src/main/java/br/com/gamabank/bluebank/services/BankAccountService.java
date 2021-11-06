package br.com.gamabank.bluebank.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gamabank.bluebank.dto.BankAccountDto;
import br.com.gamabank.bluebank.entities.BankAccount;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.exceptions.NotFoundException;
import br.com.gamabank.bluebank.factories.BankAccountFactory;
import br.com.gamabank.bluebank.forms.BankAccountForm;
import br.com.gamabank.bluebank.forms.UpdateBankAccountActiveForm;
import br.com.gamabank.bluebank.repositories.BankAccountRepository;
import br.com.gamabank.bluebank.repositories.CustomerRepository;

@Service
public class BankAccountService {

	@Autowired
	private BankAccountRepository repository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<BankAccountDto> findAllByCustomer (UUID customerId) throws ExceptionHandler {
		
		var customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not found"));
		
		return repository.findAllByCustomer(customer).stream().map(BankAccountFactory::Create).collect(Collectors.toList());
		
	}
	
	public BankAccountDto findById(UUID id) throws ExceptionHandler {
		
		BankAccount bankAccount = repository.findById(id).orElseThrow(() -> new NotFoundException("Account not found"));

		return BankAccountFactory.Create(bankAccount);
		
	}
	
	public BankAccountDto create(BankAccountForm form, UUID customerId) throws ExceptionHandler {
		
		var customer = customerRepository.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not found"));
		
		BankAccount bankAccount = BankAccountFactory.Create(form, customer);
		
		repository.save(bankAccount);

		return BankAccountFactory.Create(bankAccount);
		
	}
	
	public BankAccountDto updateActive(UpdateBankAccountActiveForm form, UUID id) throws ExceptionHandler {
		
		BankAccount bankAccount = repository.findById(id).orElseThrow(() -> new NotFoundException("Account not found"));

		bankAccount.setActive(form.active);
		bankAccount.setUpdatedAt(LocalDateTime.now());

		repository.save(bankAccount);

		return BankAccountFactory.Create(bankAccount);
		
	}
}
