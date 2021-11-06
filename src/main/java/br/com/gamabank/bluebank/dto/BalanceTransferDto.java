package br.com.gamabank.bluebank.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.gamabank.bluebank.entities.BankAccount;

public class BalanceTransferDto {

	public UUID id;
	public BankAccount fromBankAccount;
	public BankAccount toBankAccount;
	public double value;
	public LocalDateTime movementedAt;

}
