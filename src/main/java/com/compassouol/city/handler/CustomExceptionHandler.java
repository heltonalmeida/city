package com.compassouol.city.handler;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.compassouol.city.dto.ExceptionResponseDTO;
import com.compassouol.city.dto.FieldErrorDTO;
import com.compassouol.city.dto.FiledExceptionResponseDTO;
import com.compassouol.city.exception.GenericException;
import com.compassouol.city.exception.ValidationException;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		log.error("MethodArgumentNotValidExceptionHandler", ex);
		List<FieldErrorDTO> erros = getMessagesOfField(ex);
		FiledExceptionResponseDTO fieldExceptionResponseDTO = new FiledExceptionResponseDTO(HttpStatus.BAD_REQUEST.value(), erros);
		return new ResponseEntity<>(fieldExceptionResponseDTO, status);
	}

	@ExceptionHandler(ValidationException.class)
	protected ResponseEntity<Object> handleException(ValidationException ex) {
		log.error("ValidationExceptionHandler", ex);
		ExceptionResponseDTO exceptionResponse = new ExceptionResponseDTO(
				ValidationException.class.getAnnotation(ResponseStatus.class).code().value(), ex.getErrors());
		return new ResponseEntity<>(exceptionResponse, ValidationException.class.getAnnotation(ResponseStatus.class).code());
	}

	@ExceptionHandler(GenericException.class)
	protected HttpEntity<ExceptionResponseDTO> handleException(GenericException ex) {
		log.error("GenericExceptionHandler", ex);
		ResponseStatus response = ex.getClass().getAnnotation(ResponseStatus.class);
		HttpStatus status = Objects.nonNull(response) ? response.code() : HttpStatus.INTERNAL_SERVER_ERROR;
		String message = getMessage(ex, response);
		ExceptionResponseDTO exeptionResponseDTO = new ExceptionResponseDTO(status.value(), Arrays.asList(message));
		return new ResponseEntity<>(exeptionResponseDTO, status);
	}

	private List<FieldErrorDTO> getMessagesOfField(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors().stream()
				.map(error -> new FieldErrorDTO(error.getField(), error.getDefaultMessage()))
				.collect(Collectors.toList());
	}

	private String getMessage(GenericException ex, ResponseStatus response) {
		return Objects.nonNull(response) && StringUtils.isNotBlank(response.reason()) ? response.reason()
				: ex.getMessage();
	}

}
