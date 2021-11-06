package br.com.gamabank.bluebank.services;

import java.awt.print.Pageable;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import CustomerFactory.AddressFactory;
import br.com.gamabank.bluebank.dto.AddressDto;
import br.com.gamabank.bluebank.entities.Address;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.exceptions.NotFoundException;
import br.com.gamabank.bluebank.forms.AddressForm;
import br.com.gamabank.bluebank.repositories.AddressRepository;
import br.com.gamabank.bluebank.utils.PageableUtil;

@Service
public class AddressService {
	
	private AddressRepository repository;
	
	public Page<AddressDto> findAll(Pageable pageable){
		Pageable _pageable = PageableUtil.pageRequest(pageable);
		
		return repository.findAll(_pageable).map(AddressFactory::Create);
	}
	
	public Object create(@Valid AddressForm form) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object update(AddressForm form,  UUID id) throws ExceptionHandler{
		// TODO Auto-generated method stub
		Address address = repository.findById(id).orElseThrow(() -> new NotFoundException("Address not found"));

		address.setStreet(form.street);
		address.setCity(form.city);
		address.setState(form.state);
		address.setCountry(form.country);
		address.setZipcode(form.zipcode);
		address.setComplement(form.complement);
		address.setNeighborhood(form.neighborhood);
		
		repository.save(address);

		return AddressFactory.Create(address);
	}
}








