package br.com.gamabank.bluebank.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import com.sun.istack.NotNull;

public class AddressForm {
	
	@NotBlank
	public  String street;
	

	@NotBlank
	public  String city;
	
	@NotBlank
	public  String state;
	
	@NotBlank
	public  String country;
	
	@NotBlank
	public  String number;
	

	@NotBlank
	public  String zipcode;
	
	@NotBlank
	public  String complement;
	
	@NotBlank
	public  String neighborhood;
}
