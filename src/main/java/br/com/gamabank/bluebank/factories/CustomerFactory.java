package br.com.gamabank.bluebank.factories;

import java.time.LocalDateTime;

import br.com.gamabank.bluebank.dto.CustomerDto;
import br.com.gamabank.bluebank.entities.Customer;
import br.com.gamabank.bluebank.forms.CustomerForm;

public class CustomerFactory {

	public static Customer Create(CustomerForm form) {
		form.cpfCnpj = CustomerFactory.replaceCpfCnpj(form.cpfCnpj);

		return new Customer(form.name, form.cpfCnpj, form.birthDate, form.email, form.phone);
	}

	public static Customer Update(Customer customer, CustomerForm form) {
		form.cpfCnpj = CustomerFactory.replaceCpfCnpj(form.cpfCnpj);

		customer.setName(form.name);
		customer.setCpfCnpj(form.cpfCnpj);
		customer.setBirthDate(form.birthDate);
		customer.setEmail(form.email);
		customer.setPhone(form.phone);
		customer.setUpdatedAt(LocalDateTime.now());

		return customer;
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

	private static String replaceCpfCnpj(String cpfCnpj) {
		return cpfCnpj.replace(".", "").replace("-", "").replace("/", "");
	}

}
