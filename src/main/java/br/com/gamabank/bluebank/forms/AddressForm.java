package br.com.gamabank.bluebank.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddressForm {
	
	@NotBlank
	@Size(min = 8)
	public  String street;
	
	@NotBlank
	@Size(min = 4)
	public  String city;
	
	@NotBlank
	@Size(min = 4)
	public  String state;
	
	@NotBlank
	@Size(min = 4)
	public  String country;
	
	@NotBlank
	@Size(min = 1)
	public  String number;

	@NotBlank
	@Size(min = 9)
	public  String zipcode;
	
	@NotBlank
	@Size(min = 1)
	public  String complement;
	
	@NotBlank
	public  String neighborhood;
}
