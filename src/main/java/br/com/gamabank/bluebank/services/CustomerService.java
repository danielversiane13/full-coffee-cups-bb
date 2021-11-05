package br.com.gamabank.bluebank.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gamabank.bluebank.dto.CustomerDto;
import br.com.gamabank.bluebank.entities.Customer;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.exceptions.NotFoundException;
import br.com.gamabank.bluebank.factories.CustomerFactory;
import br.com.gamabank.bluebank.forms.CustomerForm;
import br.com.gamabank.bluebank.forms.UpdateCustomerActiveForm;
import br.com.gamabank.bluebank.repositories.CustomerRepository;
import br.com.gamabank.bluebank.utils.PageableUtil;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

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

}
