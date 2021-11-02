package br.com.gamabank.bluebank.dto;

import java.time.LocalDate;
import java.util.UUID;

public class CustomerDto {

	public UUID id;
	public String name;
	public String cpfCnpj;
	public LocalDate birthDate;
	public String email;
	public String phone;

}
