package br.com.gamabank.bluebank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class BankAccount extends SuperEntity {

	@Column(length = 30, nullable = false)
	private String account;
	@Column(nullable = false)
	private Double balance;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JsonBackReference
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	public BankAccount() {
		super();
	}

	public BankAccount(String account, Double balance, Customer customer) {
		super();
		this.account = account;
		this.balance = balance;
		this.customer = customer;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Double getBalance() {
		return balance;
	}

	public void balanceDeposit(Double value) {
		this.balance += value;
	}

	public void balanceWithdraw(Double value) {
		this.balance -= value;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
