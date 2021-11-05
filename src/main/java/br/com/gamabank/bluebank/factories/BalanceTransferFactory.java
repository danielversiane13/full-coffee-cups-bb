package br.com.gamabank.bluebank.factories;

import java.time.LocalDateTime;

import br.com.gamabank.bluebank.dto.BalanceTransferDto;
import br.com.gamabank.bluebank.entities.BalanceTransfer;
import br.com.gamabank.bluebank.entities.BankAccount;
import br.com.gamabank.bluebank.forms.BalanceTransferForm;

public class BalanceTransferFactory {

	public static BalanceTransfer Create(BalanceTransferForm form, BankAccount fromBankAccount,
			BankAccount toBankAccount) {
		return new BalanceTransfer(fromBankAccount, toBankAccount, form.value, LocalDateTime.now());
	}

	public static BalanceTransferDto Create(BalanceTransfer balanceTransfer) {
		BalanceTransferDto dto = new BalanceTransferDto();

		dto.id = balanceTransfer.getId();
		dto.fromBankAccount = balanceTransfer.getFromBankAccount();
		dto.toBankAccount = balanceTransfer.getToBankAccount();
		dto.value = balanceTransfer.getValue();
		dto.movementedAt = balanceTransfer.getMovementedAt();

		return dto;
	}

}
