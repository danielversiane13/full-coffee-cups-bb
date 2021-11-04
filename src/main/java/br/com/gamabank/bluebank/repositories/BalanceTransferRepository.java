package br.com.gamabank.bluebank.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gamabank.bluebank.entities.BalanceTransfer;

@Repository
public interface BalanceTransferRepository extends JpaRepository<BalanceTransfer, UUID> {}
