package br.com.gamabank.bluebank.entities;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Customer extends SuperEntity {

	private String name;
	private String cpfCnpj;
	private LocalDate birthDate;
	private String email;
	private String phone;

	public Customer() {
		super();
	}

	public Customer(String name, String cpfCnpj, LocalDate birthDate, String email, String phone) {
		super();
		this.name = name;
		this.cpfCnpj = cpfCnpj;
		this.birthDate = birthDate;
		this.email = email;
		this.phone = phone;
	}

	public Customer(String name, String cpfCnpj, LocalDate birthDate, String email, String phone, boolean active) {
		super();
		this.name = name;
		this.cpfCnpj = cpfCnpj;
		this.birthDate = birthDate;
		this.email = email;
		this.phone = phone;
		this.setActive(active);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
