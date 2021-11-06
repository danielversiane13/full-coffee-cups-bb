package br.com.gamabank.bluebank.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddressForm {

	@NotBlank
	@Size(min = 3)
	public String street;

	@NotBlank
	@Size(min = 1)
	public String number;

	@NotBlank
	public String neighborhood;

	public String complement;

	@NotBlank
	@Size(min = 8, max = 8)
	public String zipcode;

	@NotBlank
	@Size(min = 4)
	public String city;

	@NotBlank
	@Size(min = 2, max = 2)
	public String state;

	@NotBlank
	@Size(min = 4)
	public String country;

}
