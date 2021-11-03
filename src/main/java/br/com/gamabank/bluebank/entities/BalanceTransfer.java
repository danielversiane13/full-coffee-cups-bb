package br.com.gamabank.bluebank.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public class BalanceTransfer extends SuperEntity{
	
	private BankAccount fromBankAccount;
	private BankAccount toBankAccount;
	private double value;
	private LocalDateTime movementedAt;
	
	public BalanceTransfer() {
		super();
	}
	
	public BalanceTransfer(
			BankAccount fromBankAccount, 
			BankAccount toBankAccount, 
			double value,
			LocalDateTime movementedAt) {
		
		super();
		
		this.fromBankAccount = fromBankAccount;
		this.toBankAccount = toBankAccount;
		this.value = value;
		this.movementedAt = movementedAt;
	}

	public BankAccount getFromBankAccount() {
		return fromBankAccount;
	}

	public void setFromBankAccount(BankAccount fromBankAccount) {
		this.fromBankAccount = fromBankAccount;
	}

	public BankAccount getToBankAccount() {
		return toBankAccount;
	}

	public void setToBankAccount(BankAccount toBankAccount) {
		this.toBankAccount = toBankAccount;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public LocalDateTime getMovementedAt() {
		return movementedAt;
	}

	public void setMovementedAt(LocalDateTime movementedAt) {
		this.movementedAt = movementedAt;
	}
	
}
