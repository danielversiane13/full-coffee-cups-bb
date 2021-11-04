package br.com.gamabank.bluebank.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gamabank.bluebank.entities.BankAccount;

@Repository 
public interface BankAccountRepository extends JpaRepository<BankAccount, UUID> {

}
