package br.com.gamabank.bluebank.factories;

import br.com.gamabank.bluebank.dto.AddressDto;
import br.com.gamabank.bluebank.entities.Address;
import br.com.gamabank.bluebank.forms.AddressForm;

public class AddressFactory {

	public static Address Create(AddressForm form) {
		return new Address(form.street, form.number, form.neighborhood, form.complement, form.zipcode, form.city,
				form.state, form.country);
	}

	public static AddressDto Create(Address address) {
		AddressDto dto = new AddressDto();

		dto.id = address.getId();
		dto.street = address.getStreet();
		dto.number = address.getNumber();
		dto.neighborhood = address.getNeighborhood();
		dto.complement = address.getComplement();
		dto.zipcode = address.getZipcode();
		dto.city = address.getCity();
		dto.state = address.getState();
		dto.country = address.getCountry();

		return dto;
	}

}