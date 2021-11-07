package br.com.gamabank.bluebank.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gamabank.bluebank.dto.BalanceMovementDto;
import br.com.gamabank.bluebank.factories.BalanceMovementFactory;
import br.com.gamabank.bluebank.repositories.BalanceMovementRepository;
import br.com.gamabank.bluebank.utils.PageableUtil;

@Service
public class BankAccountService {

	@Autowired
	private BalanceMovementRepository repository;

	public Page<BalanceMovementDto> findByFromBankAccountIdOrToBankAccountId(Pageable pageable, UUID bankAccountId) {
		Pageable _pageable = PageableUtil.pageRequest(pageable);

		return repository.findByFromBankAccountIdOrToBankAccountId(_pageable, bankAccountId, bankAccountId)
				.map(BalanceMovementFactory::Create);
	}

}
