package com.compassouol.city.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ValidationException extends GenericException {

	private static final long serialVersionUID = 1L;

	private final List<String> errors;

}
