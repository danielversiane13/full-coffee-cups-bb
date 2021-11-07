package br.com.gamabank.bluebank.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gamabank.bluebank.dto.AddressDto;
import br.com.gamabank.bluebank.dto.BankAccountDto;
import br.com.gamabank.bluebank.dto.CustomerDto;
import br.com.gamabank.bluebank.entities.Address;
import br.com.gamabank.bluebank.entities.BankAccount;
import br.com.gamabank.bluebank.entities.Customer;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.exceptions.NotAcceptableException;
import br.com.gamabank.bluebank.exceptions.NotFoundException;
import br.com.gamabank.bluebank.factories.AddressFactory;
import br.com.gamabank.bluebank.factories.BankAccountFactory;
import br.com.gamabank.bluebank.factories.CustomerFactory;
import br.com.gamabank.bluebank.forms.AddressForm;
import br.com.gamabank.bluebank.forms.BankAccountForm;
import br.com.gamabank.bluebank.forms.CustomerForm;
import br.com.gamabank.bluebank.forms.UpdateBankAccountActiveForm;
import br.com.gamabank.bluebank.forms.UpdateCustomerActiveForm;
import br.com.gamabank.bluebank.repositories.AddressRepository;
import br.com.gamabank.bluebank.repositories.BankAccountRepository;
import br.com.gamabank.bluebank.repositories.CustomerRepository;
import br.com.gamabank.bluebank.utils.PageableUtil;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private BankAccountRepository bankAccountRepository;

	public Page<CustomerDto> findAll(Pageable pageable) {
		Pageable _pageable = PageableUtil.pageRequest(pageable);

		return repository.findAll(_pageable).map(CustomerFactory::Create);
	}

	public CustomerDto findById(UUID id) throws ExceptionHandler {
		Customer customer = repository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));

		return CustomerFactory.Create(customer);
	}

	public CustomerDto create(CustomerForm form) {
		Customer customer = CustomerFactory.Create(form);
		repository.save(customer);

		return CustomerFactory.Create(customer);
	}

	public CustomerDto update(CustomerForm form, UUID id) throws ExceptionHandler {
		Customer customer = repository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));

		customer.setName(form.name);
		customer.setCpfCnpj(form.cpfCnpj);
		customer.setBirthDate(form.birthDate);
		customer.setEmail(form.email);
		customer.setPhone(form.phone);
		customer.setUpdatedAt(LocalDateTime.now());

		repository.save(customer);

		return CustomerFactory.Create(customer);
	}

	public void deleteById(UUID id) throws ExceptionHandler {
		Customer customer = repository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));
		repository.delete(customer);
	}

	public CustomerDto updateActive(UpdateCustomerActiveForm form, UUID id) throws ExceptionHandler {
		Customer customer = repository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));

		customer.setActive(form.active);
		customer.setUpdatedAt(LocalDateTime.now());

		repository.save(customer);

		return CustomerFactory.Create(customer);
	}

	public AddressDto findAddress(UUID customerId) throws ExceptionHandler {
		Customer customer = repository.findById(customerId)
				.orElseThrow(() -> new NotFoundException("Customer not found"));

		var address = customer.getAddress();

		if (address == null) {
			throw new NotFoundException("Address not found");
		}

		return AddressFactory.Create(address);
	}

	public AddressDto createAddress(UUID customerId, AddressForm form) throws ExceptionHandler {
		Customer customer = repository.findById(customerId)
				.orElseThrow(() -> new NotFoundException("Customer not found"));

		var hasAddress = customer.getAddress();

		if (hasAddress != null) {
			throw new NotAcceptableException("Customer already has an Address");
		}

		Address address = AddressFactory.Create(form);

		customer.setAddress(address);
		address.setCustomer(customer);

		repository.save(customer);

		return AddressFactory.Create(address);
	}

	public AddressDto updateAddress(UUID customerId, AddressForm form) throws ExceptionHandler {
		Customer customer = repository.findById(customerId)
				.orElseThrow(() -> new NotFoundException("Customer not found"));

		var address = customer.getAddress();

		if (address == null) {
			throw new NotFoundException("Address not found");
		}

		address.setStreet(form.street);
		address.setNumber(form.number);
		address.setNeighborhood(form.neighborhood);
		address.setComplement(form.complement);
		address.setZipcode(form.zipcode);
		address.setCity(form.city);
		address.setState(form.state);
		address.setCountry(form.country);
		address.setUpdatedAt(LocalDateTime.now());

		addressRepository.save(address);

		return AddressFactory.Create(address);
	}
	
	// Bank Account Service
	
	public BankAccountDto findBankAccount(UUID customerId) throws ExceptionHandler {
		Customer customer = repository.findById(customerId)
				.orElseThrow(() -> new NotFoundException("Customer not found"));

		var bankAccount = customer.getBankAccount();

		if (bankAccount == null) {
			throw new NotFoundException("Bank Account not found");
		}

		return BankAccountFactory.Create(bankAccount);
	}

	public BankAccountDto createBankAccount(UUID customerId, BankAccountForm form) throws ExceptionHandler {
		Customer customer = repository.findById(customerId)
				.orElseThrow(() -> new NotFoundException("Customer not found"));

		var hasBankAccount = customer.getBankAccount();

		if (hasBankAccount != null) {
			throw new NotAcceptableException("Customer already has an BankAccount");
		}

		BankAccount bankAccount = BankAccountFactory.Create(form);

		customer.setBankAccount(bankAccount);
		bankAccount.setCustomer(customer);

		repository.save(customer);

		return BankAccountFactory.Create(bankAccount);
	}

	public BankAccountDto updateBankAccount(UUID customerId, BankAccountForm form) throws ExceptionHandler {
		Customer customer = repository.findById(customerId)
				.orElseThrow(() -> new NotFoundException("Customer not found"));

		var bankAccount = customer.getBankAccount();

		if (bankAccount == null) {
			throw new NotFoundException("Bank Account not found");
		}

		bankAccount.setAccount(form.account);
		bankAccount.setUpdatedAt(LocalDateTime.now());

		bankAccountRepository.save(bankAccount);

		return BankAccountFactory.Create(bankAccount);
	}

	public BankAccountDto updateBankAccountActive(UpdateBankAccountActiveForm form, UUID id) throws ExceptionHandler {
		Customer customer = repository.findById(id).orElseThrow(() -> new NotFoundException("Customer not found"));

		var bankAccount = customer.getBankAccount();

		if (bankAccount == null) {
			throw new NotFoundException("Bank Account not found");
		}

		if (!form.active && bankAccount.getBalance() != 0) {
			throw new NotAcceptableException("It is not possible to inactivate a bank account with a balance");
		}
		
		bankAccount.setActive(form.active);
		bankAccount.setUpdatedAt(LocalDateTime.now());

		bankAccountRepository.save(bankAccount);

		return BankAccountFactory.Create(bankAccount);
		
	}
}
