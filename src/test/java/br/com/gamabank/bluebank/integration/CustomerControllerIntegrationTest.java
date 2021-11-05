package br.com.gamabank.bluebank.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.gamabank.bluebank.BluebankApplication;
import br.com.gamabank.bluebank.dto.CustomerDto;
import br.com.gamabank.bluebank.entities.Customer;
import br.com.gamabank.bluebank.forms.CustomerForm;
import br.com.gamabank.bluebank.forms.UpdateCustomerActiveForm;
import br.com.gamabank.bluebank.repositories.CustomerRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = BluebankApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class CustomerControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CustomerRepository customerRepository;

	private Customer baseCustomer = new Customer("Customer Aaa", "11111111111", LocalDate.parse("2000-01-01"),
			"c1@email.com", "11900000000");

	@AfterEach
	void cleanup() {
		// TODO: this is temporary
		customerRepository.deleteAll();
	}

	@Test
	void it_can_show_empty_list_of_customers() throws Exception {
		// Act & Assert
		mockMvc.perform(get("/customers")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.pageable").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.content").isEmpty());
	}

	@Test
	void it_can_show_a_list_of_customers() throws Exception {
		// Arrange
		customerRepository.save(this.baseCustomer);

		// Act & Assert
		mockMvc.perform(get("/customers")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.pageable").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.content").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.content").isNotEmpty());
	}

	@Test
	void it_can_show_a_list_of_customers_with_paginate() throws Exception {
		// Arrange
		LocalDate birthDate = LocalDate.parse("2000-01-01");
		customerRepository.save(new Customer("Customer Aaa", "11111111111", birthDate, "c1@email.com", "11900000000"));
		customerRepository.save(new Customer("Customer Bbb", "22222222222", null, "c2@email.com", "11911111111"));
		customerRepository.save(new Customer("Customer Ccc", "33333333333", birthDate, "c3@email.com", "11922222222"));

		// Act & Assert
		mockMvc.perform(get("/customers").queryParam("page", "1").queryParam("size", "2")).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.content").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Customer Ccc"));
	}

	@Test
	void it_can_create_a_customer() throws Exception {
		// Arrange
		CustomerForm customer = new CustomerForm();
		customer.name = "Customer Aaa";
		customer.cpfCnpj = "11111111111";
		customer.email = "c1@email.com";
		customer.phone = "11900000000";

		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(customer);

		// Act & Assert
		MvcResult mvcResult = mockMvc
				.perform(post("/customers").contentType(MediaType.APPLICATION_JSON).content(jsonContent))
				.andExpect(status().isCreated()).andReturn();

		// Assert
		CustomerDto content = mapper.readValue(mvcResult.getResponse().getContentAsString(), CustomerDto.class);

		Assertions.assertEquals(customer.name, content.name);
		Assertions.assertEquals(customer.cpfCnpj, content.cpfCnpj);
		Assertions.assertEquals(customer.email, content.email);
		Assertions.assertEquals(customer.phone, content.phone);
		Assertions.assertTrue(content.active);
	}

	@Test
	void it_can_update_a_customer() throws Exception {
		// Arrange
		Customer customer = customerRepository.save(this.baseCustomer);
		CustomerForm customerForm = new CustomerForm();
		customerForm.name = "Customer Bbb";
		customerForm.cpfCnpj = "22222222222";
		customerForm.email = "c2@email.com";
		customerForm.phone = "22900000000";

		ObjectMapper mapper = new ObjectMapper();
		String jsonContent = mapper.writeValueAsString(customerForm);

		// Act & Assert
		MvcResult mvcResult = mockMvc.perform(
				put("/customers/" + customer.getId()).contentType(MediaType.APPLICATION_JSON).content(jsonContent))
				.andExpect(status().isOk()).andReturn();

		// Assert
		CustomerDto content = mapper.readValue(mvcResult.getResponse().getContentAsString(), CustomerDto.class);

		Assertions.assertEquals(customerForm.name, content.name);
		Assertions.assertEquals(customerForm.cpfCnpj, content.cpfCnpj);
		Assertions.assertEquals(customerForm.email, content.email);
		Assertions.assertEquals(customerForm.phone, content.phone);
		Assertions.assertTrue(content.active);
	}

	@Test
	void it_can_delete_a_customer() throws Exception {
		// Arrange
		Customer customer = customerRepository.save(this.baseCustomer);

		// Act & Assert
		mockMvc.perform(delete("/customers/" + customer.getId())).andExpect(status().isNoContent());

		// Assert
		Assertions.assertFalse(customerRepository.findById(customer.getId()).isPresent());
	}

}
