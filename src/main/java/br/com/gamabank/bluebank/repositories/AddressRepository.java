package br.com.gamabank.bluebank.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gamabank.bluebank.entities.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID>{
	List<Address> findAllByAddress (Address address);
		
}
