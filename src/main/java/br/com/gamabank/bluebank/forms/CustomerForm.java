package br.com.gamabank.bluebank.forms;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CustomerForm {

	@NotBlank
	public String name;

	@NotBlank
	public String cpfCnpj;

	public LocalDate birthDate;

	@NotBlank
	@Email
	public String email;

	@NotBlank
	public String phone;

}
