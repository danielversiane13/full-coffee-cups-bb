package br.com.gamabank.bluebank.forms;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import br.com.gamabank.bluebank.annotations.CpfCnpj;

public class CustomerForm {

	@NotBlank
	public String name;

	@NotBlank
	@CpfCnpj
	public String cpfCnpj;

	public LocalDate birthDate;

	@NotBlank
	@Email
	public String email;

	@NotBlank
	public String phone;

}
