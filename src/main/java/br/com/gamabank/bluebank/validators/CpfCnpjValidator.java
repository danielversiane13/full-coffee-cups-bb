package br.com.gamabank.bluebank.validators;

import java.util.InputMismatchException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.gamabank.bluebank.annotations.CpfCnpj;

public class CpfCnpjValidator implements ConstraintValidator<CpfCnpj, String> {

	@Override
	public void initialize(CpfCnpj constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || value.isEmpty() || isCpf(value) || isCnpj(value);
	}

	private boolean isCpf(String cpf) {
		cpf = cpf.replace(".", "").replace("-", "");

		if (cpf.length() != 11) {
			return false;
		}

		try {
			Long.parseLong(cpf);
		} catch (NumberFormatException e) {
			return false;
		}

		int d1, d2;
		int digit1, digit2, rest;
		int digitCPF;
		String nDigResult;

		d1 = d2 = 0;
		digit1 = digit2 = rest = 0;

		for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
			digitCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();
			d1 = d1 + (11 - nCount) * digitCPF;
			d2 = d2 + (12 - nCount) * digitCPF;
		}
		;

		rest = (d1 % 11);

		if (rest < 2) {

			digit1 = 0;
		} else {

			digit1 = 11 - rest;
		}

		d2 += 2 * digit1;

		rest = (d2 % 11);

		if (rest < 2) {
			digit2 = 0;
		} else {
			digit2 = 11 - rest;
		}

		String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());

		nDigResult = String.valueOf(digit1) + String.valueOf(digit2);

		return nDigVerific.equals(nDigResult);
	}

	private boolean isCnpj(String cnpj) {
		cnpj = cnpj.replace(".", "").replace("-", "").replace("/", "");

		if (cnpj.length() != 14) {
			return false;
		}

		try {
			Long.parseLong(cnpj);
		} catch (NumberFormatException e) {
			return false;
		}

		if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") || cnpj.equals("22222222222222")
				|| cnpj.equals("33333333333333") || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
				|| cnpj.equals("66666666666666") || cnpj.equals("77777777777777") || cnpj.equals("88888888888888")
				|| cnpj.equals("99999999999999") || (cnpj.length() != 14)) {

			return (false);
		}

		char dig13, dig14;
		int sum, i, r, num, weight;

		try {
			sum = 0;
			weight = 2;

			for (i = 11; i >= 0; i--) {
				num = (int) (cnpj.charAt(i) - 48);
				sum = sum + (num * weight);
				weight = weight + 1;
				if (weight == 10)
					weight = 2;
			}

			r = sum % 11;

			if ((r == 0) || (r == 1)) {
				dig13 = '0';
			} else {
				dig13 = (char) ((11 - r) + 48);
			}

			sum = 0;
			weight = 2;

			for (i = 12; i >= 0; i--) {
				num = (int) (cnpj.charAt(i) - 48);
				sum = sum + (num * weight);
				weight = weight + 1;
				if (weight == 10)
					weight = 2;
			}

			r = sum % 11;

			if ((r == 0) || (r == 1)) {
				dig14 = '0';
			} else {
				dig14 = (char) ((11 - r) + 48);
			}

			return (dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)) ? (true) : (false);
		} catch (InputMismatchException erro) {
			return (false);
		}
	}

}