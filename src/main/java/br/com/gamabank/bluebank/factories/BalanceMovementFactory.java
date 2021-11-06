package br.com.gamabank.bluebank.factories;

import br.com.gamabank.bluebank.dto.BalanceMovementDto;
import br.com.gamabank.bluebank.entities.BalanceMovement;
import br.com.gamabank.bluebank.forms.BalanceMovementForm;

public class BalanceMovementFactory {

	public static BalanceMovement Create(BalanceMovementForm form) {
		return new BalanceMovement(form.value);
	}

	public static BalanceMovementDto Create(BalanceMovement balanceTransfer) {
		BalanceMovementDto dto = new BalanceMovementDto();

		dto.id = balanceTransfer.getId();
		dto.toBankAccount = balanceTransfer.getToBankAccount();
		dto.fromBankAccount = balanceTransfer.getFromBankAccount();
		dto.value = balanceTransfer.getValue();
		dto.createdAt = balanceTransfer.getCreatedAt();

		return dto;
	}

}
