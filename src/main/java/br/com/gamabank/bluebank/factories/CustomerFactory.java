package br.com.gamabank.bluebank.factories;

import br.com.gamabank.bluebank.dto.CustomerDto;
import br.com.gamabank.bluebank.entities.Customer;
import br.com.gamabank.bluebank.forms.CustomerForm;

public class CustomerFactory {

	public static Customer Create(CustomerForm form) {
		return new Customer(form.name, form.cpfCnpj, form.birthDate, form.email, form.phone);
	}

	public static CustomerDto Create(Customer customer) {
		CustomerDto dto = new CustomerDto();

		dto.id = customer.getId();
		dto.name = customer.getName();
		dto.cpfCnpj = customer.getCpfCnpj();
		dto.birthDate = customer.getBirthDate() != null ? customer.getBirthDate().toString() : null;
		dto.email = customer.getEmail();
		dto.phone = customer.getPhone();
		dto.active = customer.isActive();

		return dto;
	}

}
