package br.com.gamabank.bluebank.factories;

import org.springframework.validation.FieldError;

import br.com.gamabank.bluebank.dto.ValidationDto;

public class ValidationDtoFactory {

	public static ValidationDto Create(FieldError error) {
		ValidationDto dto = new ValidationDto();

		dto.message = error.getDefaultMessage();
		dto.field = error.getField();

		return dto;
	}

}
