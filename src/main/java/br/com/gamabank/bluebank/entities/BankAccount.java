package br.com.gamabank.bluebank.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class BankAccount extends SuperEntity {

	@Column(length=30, nullable=false)
	private String account;
	@Column(nullable=false)
	private Double balance;
	
	@ManyToOne 
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

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
