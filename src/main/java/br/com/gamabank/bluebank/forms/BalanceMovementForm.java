package br.com.gamabank.bluebank.forms;

import java.util.UUID;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import br.com.gamabank.bluebank.entities.OperationTypeEnum;

public class BalanceMovementForm {

	public UUID toBankAccountId;

	public UUID fromBankAccountId;

	@NotNull
	@Min(value = 1)
	public double value;

	public OperationTypeEnum operationType;

}
