package br.com.gamabank.bluebank.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gamabank.bluebank.dto.AddressDto;
import br.com.gamabank.bluebank.dto.CustomerDto;
import br.com.gamabank.bluebank.entities.Address;
import br.com.gamabank.bluebank.entities.Customer;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.exceptions.NotAcceptableException;
import br.com.gamabank.bluebank.exceptions.NotFoundException;
import br.com.gamabank.bluebank.factories.AddressFactory;
import br.com.gamabank.bluebank.factories.CustomerFactory;
import br.com.gamabank.bluebank.forms.AddressForm;
import br.com.gamabank.bluebank.forms.CustomerForm;
import br.com.gamabank.bluebank.forms.UpdateCustomerActiveForm;
import br.com.gamabank.bluebank.repositories.AddressRepository;
import br.com.gamabank.bluebank.repositories.CustomerRepository;
import br.com.gamabank.bluebank.utils.PageableUtil;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private AddressRepository addressRepository;

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

}
