package br.com.gamabank.bluebank.forms;

import javax.validation.constraints.NotBlank;

public class BankAccountForm {

	@NotBlank
	public String account;

}
