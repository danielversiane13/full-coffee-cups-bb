package br.com.gamabank.bluebank.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Address extends SuperEntity {

	public String street;
	public String city;
	public String state;
	public String country;
	public String zipcode;
	public String complement;
	public String neighborhood;

	@ManyToOne 
	private Customer customer;

	public Address() { 
		super(); 
	}

	public Address(Customer customer, String street, String city, String state, String country, String zipcode,String complement, String neighborhood) {
		super();
		this.customer = customer;
		this.street = street;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipcode = zipcode;
		this.complement = complement;
		this.neighborhood = neighborhood;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
