package br.com.gamabank.bluebank.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gamabank.bluebank.dto.CustomerDto;
import br.com.gamabank.bluebank.entities.Customer;
import br.com.gamabank.bluebank.factories.CustomerFactory;
import br.com.gamabank.bluebank.forms.CustomerForm;
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

	public CustomerDto findById(UUID id) {
		var result = repository.findById(id);

		return result.isPresent() ? CustomerFactory.Create(result.get()) : null;
	}

	public CustomerDto create(CustomerForm form) {
		Customer customer = CustomerFactory.Create(form);
		repository.save(customer);

		return CustomerFactory.Create(customer);
	}

	public CustomerDto update(CustomerForm form, UUID id) {
		var result = repository.findById(id);

		if (!result.isPresent()) {
			return null;
		}

		var customer = result.get();

		customer.setName(form.name);
		customer.setCpfCnpj(form.cpfCnpj);
		customer.setBirthDate(form.birthDate);
		customer.setEmail(form.email);
		customer.setPhone(form.phone);
		customer.setUpdatedAt(LocalDateTime.now());

		repository.save(customer);

		return CustomerFactory.Create(customer);
	}

	public void deleteById(UUID id) {
		repository.deleteById(id);
	}

}
