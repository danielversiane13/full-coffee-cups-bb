package br.com.gamabank.bluebank.factories;

import br.com.gamabank.bluebank.dto.BalanceTransferDto;
import br.com.gamabank.bluebank.entities.BalanceTransfer;
import br.com.gamabank.bluebank.forms.BalanceTransferForm;

public class BalanceTransferFactory {

	public static BalanceTransfer Create(BalanceTransferForm form) {
		return new BalanceTransfer(form.fromBankAccount, form.toBankAccount, form.value, form.movementedAt);
	}
	
	public static BalanceTransferDto Create(BalanceTransfer balanceTransfer) {
		var dto = new BalanceTransferDto();

		dto.id = balanceTransfer.getId();
		dto.fromBankAccount = balanceTransfer.getFromBankAccount();
		dto.toBankAccount = balanceTransfer.getToBankAccount();
		dto.value = balanceTransfer.getValue();
		dto.movementedAt = balanceTransfer.getMovementedAt();

		return dto;
	}

}
