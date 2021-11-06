package br.com.gamabank.bluebank.factories;

import br.com.gamabank.bluebank.dto.BankAccountDto;
import br.com.gamabank.bluebank.entities.BankAccount;
import br.com.gamabank.bluebank.entities.Customer;
import br.com.gamabank.bluebank.forms.BankAccountForm;

public class BankAccountFactory {
	
	public static BankAccount Create(BankAccountForm form, Customer customer) {
		
		return new BankAccount(form.account, form.balance, customer);
	}

	public static BankAccountDto Create(BankAccount bankAccount) {
		
		var dto = new BankAccountDto();

		dto.id = bankAccount.getId();
		dto.account = bankAccount.getAccount();
		dto.balance = bankAccount.getBalance();
		dto.customer = bankAccount.getCustomer();

		return dto;
	}
}
