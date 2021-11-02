package br.com.gamabank.bluebank.factories;

import br.com.gamabank.bluebank.dto.ExceptionHandlerDto;

public class ExceptionHandlerFactory {

	public static ExceptionHandlerDto Create(String message) {
		ExceptionHandlerDto dto = new ExceptionHandlerDto();

		dto.message = message;

		return dto;
	}

}
