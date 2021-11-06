package br.com.gamabank.bluebank.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gamabank.bluebank.entities.BankAccount;
import br.com.gamabank.bluebank.entities.Customer;

@Repository 
public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {

	List<BankAccount> findAllByCustomer (Customer customer);

}