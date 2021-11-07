package br.com.gamabank.bluebank.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddressForm {

	@NotBlank
	public String street;

	@NotBlank
	public String number;

	@NotBlank
	public String neighborhood;

	public String complement;

	@NotBlank
	@Size(min = 8, max = 8)
	public String zipcode;

	@NotBlank
	public String city;

	@NotBlank
	@Size(min = 2, max = 2)
	public String state;

	@NotBlank
	public String country;

}
