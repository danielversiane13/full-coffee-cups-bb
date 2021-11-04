package br.com.gamabank.bluebank.forms;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.gamabank.bluebank.entities.BankAccount;

public class BalanceTransferForm {

	@NotNull
	@NotBlank
	@NotEmpty
	public BankAccount fromBankAccount;
	
	@NotNull
	@NotBlank
	@NotEmpty
	public BankAccount toBankAccount;
	
	@NotNull
	public double value;
	
	public LocalDateTime movementedAt;
	
}
