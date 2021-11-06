package br.com.gamabank.bluebank.repositories;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gamabank.bluebank.entities.BalanceMovement;

@Repository
public interface BalanceMovementRepository extends JpaRepository<BalanceMovement, UUID> {

	public Page<BalanceMovement> findByFromBankAccountIdOrToBankAccountId(Pageable _pageable, UUID fromBankAccountId,
			UUID toBankAccountId);

}
