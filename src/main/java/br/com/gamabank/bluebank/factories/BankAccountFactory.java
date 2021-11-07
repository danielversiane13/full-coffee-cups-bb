package br.com.gamabank.bluebank.factories;

import br.com.gamabank.bluebank.dto.BankAccountDto;
import br.com.gamabank.bluebank.entities.BankAccount;
import br.com.gamabank.bluebank.forms.BankAccountForm;

public class BankAccountFactory {
	
	public static BankAccount Create(BankAccountForm form) {
		
		return new BankAccount(form.account);
	}

	public static BankAccountDto Create(BankAccount bankAccount) {
		
		var dto = new BankAccountDto();

		dto.id = bankAccount.getId();
		dto.account = bankAccount.getAccount();
		dto.balance = bankAccount.getBalance();

		return dto;
	}
}
