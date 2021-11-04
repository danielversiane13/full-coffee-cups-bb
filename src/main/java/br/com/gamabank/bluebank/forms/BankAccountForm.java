package br.com.gamabank.bluebank.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gamabank.bluebank.entities.Customer;

public class BankAccountForm {

	@NotNull
	@NotBlank
	@NotEmpty
	public String account;
	
	@NotNull
	@NotBlank
	@NotEmpty
	public Double balance;
	
	@NotNull
	@NotBlank
	@NotEmpty
	public Customer customer;
	
}
