package br.com.gamabank.bluebank.forms;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class BankAccountForm {

	@NotNull
	public UUID customerId;
	
	@NotNull
	public String account;
	
	@NotNull
	public Double balance;
	
}
