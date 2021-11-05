package br.com.gamabank.bluebank.forms;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BalanceTransferForm {

	@NotBlank
	public UUID fromBankAccountId;
	
	@NotBlank
	public UUID toBankAccountId;
	
	@NotNull
	public double value;
	
	public LocalDateTime movementedAt;
	
}
