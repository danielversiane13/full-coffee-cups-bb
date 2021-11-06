package br.com.gamabank.bluebank.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gamabank.bluebank.entities.Customer;

public class BankAccountForm {

	@NotNull
	public String account;
	
	@NotNull
	public Double balance;
	
}
