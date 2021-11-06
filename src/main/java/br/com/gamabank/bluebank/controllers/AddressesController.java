package br.com.gamabank.bluebank.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.gamabank.bluebank.dto.AddressDto;
import br.com.gamabank.bluebank.forms.AddressForm;
import br.com.gamabank.bluebank.services.AddressService;

@RestController
@RequestMapping("/customers/{customer_id}/address")
public class AddressesController {
	
	@Autowired
	private AddressService service;
	
	@GetMapping
	public ResponseEntity<AddressDto>getById(@PathVariable String id){ 
		return new ResponseEntity<AddressDto>(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<AddressDto> post(){
		return new ResponseEntity<AddressDto>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<AddressDto> put(){
		return new ResponseEntity<AddressDto>(HttpStatus.OK);
	}

}

