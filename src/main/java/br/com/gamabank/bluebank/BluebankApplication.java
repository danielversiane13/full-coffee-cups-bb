package br.com.gamabank.bluebank;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.gamabank.bluebank.entities.Address;
import br.com.gamabank.bluebank.entities.BankAccount;
import br.com.gamabank.bluebank.entities.Customer;
import br.com.gamabank.bluebank.repositories.BankAccountRepository;
import br.com.gamabank.bluebank.repositories.CustomerRepository;

@SpringBootApplication
public class BluebankApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Value("${bluebank.app.env}")
	private String APP_ENV;

	public static void main(String[] args) {
		SpringApplication.run(BluebankApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (!this.APP_ENV.equals("development")) {
			return;
		}

		Customer customerA = new Customer("Agatha Lívia dos Santos", "95313390660", LocalDate.parse("1977-01-12"),
				"agatha@email.com", "11997431482");
		Address addressA = new Address("Rua Raimundo Macedo Melo", "832", "Santana", null, "55645706", "Gravatá", "PE",
				"Brasil");
		BankAccount bankAccountA = new BankAccount("123", 546564.67, customerA);
		customerA.setAddress(addressA);
		addressA.setCustomer(customerA);
		customerRepository.save(customerA);
		bankAccountRepository.save(bankAccountA);

		Customer customerB = new Customer("Lucas Edson das Neves", "26156030000", LocalDate.parse("1963-06-04"),
				"lucas@email.com", "67987386413");
		Address addressB = new Address("Rua Onofre Pereira de Matos", "896", "Jardim Climax", null, "79820130",
				"Dourados", "MS", "Brasil");
		BankAccount bankAccountB = new BankAccount("456", 546564.67, customerB);
		customerB.setAddress(addressB);
		addressB.setCustomer(customerB);
		customerRepository.save(customerB);
		bankAccountRepository.save(bankAccountB);

		Customer customerC = new Customer("Anthony e Emanuel Padaria Ltda", "38839012000187", null, "padarialtda",
				"11996059961");
		customerRepository.save(customerC);
	}

}
