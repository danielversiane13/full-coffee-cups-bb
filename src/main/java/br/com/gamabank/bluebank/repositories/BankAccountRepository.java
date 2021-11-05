package br.com.gamabank.bluebank.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gamabank.bluebank.entities.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, UUID>{

}
