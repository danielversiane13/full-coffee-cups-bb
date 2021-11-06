package br.com.gamabank.bluebank.factories;

import br.com.gamabank.bluebank.dto.AddressDto;
import br.com.gamabank.bluebank.entities.Address;
import br.com.gamabank.bluebank.forms.AddressForm;

public class AddressFactory {
	
	public static Address Create(AddressForm form) {
		return new Address(form.street,form.city, form.state, form.country, form.number, form.zipcode, form.complement,form.neighborhood);
	}
	
	public static AddressDto Create(Address address) {
			AddressDto dto = new AddressDto();
			
			dto.street = address.getStreet(); 
			dto.city = address.getCity();
			dto.state = address.getState();
			dto.country = address.getCountry();
			dto.number = address.getNumber();
			dto.zipcode = address.getStreet();
			dto.complement = address.getStreet();
			dto.neighborhood = address.getStreet();
		return dto;
	}
}