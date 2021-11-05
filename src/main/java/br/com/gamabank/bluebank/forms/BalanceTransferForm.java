package br.com.gamabank.bluebank.forms;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class BalanceTransferForm {

	@NotNull
	public UUID fromBankAccountId;

	@NotNull
	public UUID toBankAccountId;

	@NotNull
	public double value;

}
