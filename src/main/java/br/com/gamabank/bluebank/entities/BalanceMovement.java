package br.com.gamabank.bluebank.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class BalanceMovement extends SuperEntity {

	@ManyToOne
	private BankAccount toBankAccount;

	@ManyToOne
	private BankAccount fromBankAccount;

	private double value;

	private OperationTypeEnum operationType;

	public BalanceMovement() {
		super();
	}

	public BalanceMovement(BankAccount toBankAccount, BankAccount fromBankAccount, double value,
			OperationTypeEnum operationType) {
		super();
		this.toBankAccount = toBankAccount;
		this.fromBankAccount = fromBankAccount;
		this.value = value;
		this.operationType = operationType;
	}

	public BalanceMovement(double value) {
		super();
		this.value = value;
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

	public OperationTypeEnum getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationTypeEnum operationType) {
		this.operationType = operationType;
	}

}
