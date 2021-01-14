package com.compassouol.city.dto;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.Assert;
import org.junit.Test;

public class CityRequestDTOTest {

	private final static String TEXTO_COM_MAIS_DE_100_CARACTERES = "teste_1234567890_teste_1234567890_teste_1234567890_teste_1234567890_teste_1234567890_teste_1234567890";
	private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	@Test
	public void CityRequestDTO_shouldNotHasConstraintViolation() {
		CityRequestDTO param = new CityRequestDTO();
		param.setName("nome");
		param.setState("estado");

		Set<ConstraintViolation<CityRequestDTO>> violations = validator.validate(param);

		Assert.assertEquals(0, violations.size());
	}

	@Test
	public void CityRequestDTO_shouldHasConstraintViolationNameBlank() {
		CityRequestDTO param = new CityRequestDTO();
		param.setState("estado");

		Set<ConstraintViolation<CityRequestDTO>> violations = validator.validate(param);
		ConstraintViolation<CityRequestDTO> constraintViolation = violations.stream().findFirst().get();
		
		Assert.assertEquals(1, violations.size());
		Assert.assertEquals("name", constraintViolation.getPropertyPath().toString());
	}

	@Test
	public void CityRequestDTO_shouldHasConstraintViolationNameSize() {
		CityRequestDTO param = new CityRequestDTO();
		param.setName(TEXTO_COM_MAIS_DE_100_CARACTERES);
		param.setState("estado");

		Set<ConstraintViolation<CityRequestDTO>> violations = validator.validate(param);
		ConstraintViolation<CityRequestDTO> constraintViolation = violations.stream().findFirst().get();
		
		Assert.assertEquals(1, violations.size());
		Assert.assertEquals("name", constraintViolation.getPropertyPath().toString());
	}

	@Test
	public void CityRequestDTO_shouldHasConstraintViolationStateBlank() {
		CityRequestDTO param = new CityRequestDTO();
		param.setName("nome");
		
		Set<ConstraintViolation<CityRequestDTO>> violations = validator.validate(param);
		ConstraintViolation<CityRequestDTO> constraintViolation = violations.stream().findFirst().get();

		Assert.assertEquals(1, violations.size());
		Assert.assertEquals("state", constraintViolation.getPropertyPath().toString());
	}

	@Test
	public void CityRequestDTO_shouldHasConstraintViolationStateSize() {
		CityRequestDTO param = new CityRequestDTO();
		param.setName("nome");
		param.setState(TEXTO_COM_MAIS_DE_100_CARACTERES);
		
		Set<ConstraintViolation<CityRequestDTO>> violations = validator.validate(param);
		ConstraintViolation<CityRequestDTO> constraintViolation = violations.stream().findFirst().get();

		Assert.assertEquals(1, violations.size());
		Assert.assertEquals("state", constraintViolation.getPropertyPath().toString());
	}

}
