package br.com.gamabank.bluebank.forms;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CustomerForm {

	@NotNull
	@NotBlank
	@NotEmpty
	public String name;

	@NotNull
	@NotBlank
	@NotEmpty
	public String cpfCnpj;

	public LocalDate birthDate;

	@NotNull
	@NotBlank
	@NotEmpty
	@Email
	public String email;

	@NotNull
	@NotBlank
	@NotEmpty
	public String phone;

}
