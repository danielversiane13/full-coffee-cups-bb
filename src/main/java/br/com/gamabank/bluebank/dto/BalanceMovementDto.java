package br.com.gamabank.bluebank.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.gamabank.bluebank.entities.BankAccount;

public class BalanceMovementDto {

	public UUID id;
	public BankAccount toBankAccount;
	public BankAccount fromBankAccount;
	public double value;
	public LocalDateTime createdAt;

}
