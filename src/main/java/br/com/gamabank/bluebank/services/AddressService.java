package br.com.gamabank.bluebank.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gamabank.bluebank.dto.AddressDto;
import br.com.gamabank.bluebank.entities.Address;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.exceptions.NotFoundException;
import br.com.gamabank.bluebank.factories.AddressFactory;
import br.com.gamabank.bluebank.forms.AddressForm;
import br.com.gamabank.bluebank.forms.UpdateAddressActiveForm;
import br.com.gamabank.bluebank.repositories.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository repository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	public List<AddressDto> findAllByAddress (UUID addressId) throws ExceptionHandler{
		var address = addressRepository.findById(addressId).orElseThrow(() -> new NotFoundException("Error Address"));
		
		return repository.findAllByAddress(address).stream().map(AddressFactory::Create).collect(Collectors.toList());
	}
	
	public AddressDto findById(UUID id) throws ExceptionHandler {
		Address address = repository.findById(id).orElseThrow(() -> new NotFoundException("Address Error"));
		
		return AddressFactory.Create(address);
	}
	
	public AddressDto create(AddressForm form) {
		Address address = AddressFactory.Create(form);
		repository.save(address);
		return AddressFactory.Create(address);
	}
	
	public AddressDto update(AddressForm form, UUID id) throws ExceptionHandler {
		Address address = repository.findById(id).orElseThrow(() -> new NotFoundException("Address Error"));
		address.setStreet(form.street);
		address.setCity(form.city);
		address.setCountry(form.country);
		address.setNumber(form.number);
		address.setZipcode(form.zipcode);
		address.setComplement(form.complement);
		address.setNeighborhood(form.neighborhood);
		
		return AddressFactory.Create(address);
	}
	
	public void deleteById(UUID id) throws ExceptionHandler {
		Address address = repository.findById(id).orElseThrow(() -> new NotFoundException("Address Error"));
		repository.delete(address);
	}
	
	public AddressDto updateActive( UpdateAddressActiveForm form, UUID id) throws ExceptionHandler {
		Address address = repository.findById(id).orElseThrow(() -> new NotFoundException("Address Error"));
		address.setActive(form.active);
		address.setUpdatedAt(LocalDateTime.now());
		
		repository.save(address);
		return AddressFactory.Create(address);
	}
}








