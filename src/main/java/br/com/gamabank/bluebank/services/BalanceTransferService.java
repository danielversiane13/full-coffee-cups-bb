package br.com.gamabank.bluebank.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gamabank.bluebank.dto.BalanceTransferDto;
import br.com.gamabank.bluebank.entities.BalanceTransfer;
import br.com.gamabank.bluebank.exceptions.ExceptionHandler;
import br.com.gamabank.bluebank.exceptions.NotFoundException;
import br.com.gamabank.bluebank.factories.BalanceTransferFactory;
import br.com.gamabank.bluebank.forms.BalanceTransferForm;
import br.com.gamabank.bluebank.repositories.BalanceTransferRepository;
import br.com.gamabank.bluebank.utils.PageableUtil;

@Service
public class BalanceTransferService {

	@Autowired
	private BalanceTransferRepository repository;

	public Page<BalanceTransferDto> findAll(Pageable pageable) {
		Pageable _pageable = PageableUtil.pageRequest(pageable);

		return repository.findAll(_pageable).map(BalanceTransferFactory::Create);
	}

	public BalanceTransferDto findById(UUID id) throws ExceptionHandler {
		BalanceTransfer balanceTransfer = repository.findById(id).orElseThrow(() -> new NotFoundException("Balance Transfer not found"));

		return BalanceTransferFactory.Create(balanceTransfer);
	}

	public BalanceTransferDto create(BalanceTransferForm form) {
		BalanceTransfer balanceTransfer = BalanceTransferFactory.Create(form);
		repository.save(balanceTransfer);

		return BalanceTransferFactory.Create(balanceTransfer);
	}

}
