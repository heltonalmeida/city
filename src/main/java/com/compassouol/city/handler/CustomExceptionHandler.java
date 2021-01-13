package com.compassouol.city.handler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.compassouol.city.dto.ExceptionDTO;
import com.compassouol.city.dto.FieldErrorDTO;
import com.compassouol.city.dto.FiledExceptionDTO;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	private Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleException(Exception ex) {
		log.error("GlobalExceptionHandler", ex);
		ExceptionDTO responseMessage = new ExceptionDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				Arrays.asList("Erro interno no servidor"));
		return new ResponseEntity<>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	log.error("MethodArgumentNotValidExceptionHandler", ex);
    	List<FieldErrorDTO> erros = getMessagesOfField(ex);
        FiledExceptionDTO apiError = new FiledExceptionDTO(HttpStatus.BAD_REQUEST.value(), erros);
        return new ResponseEntity<>(apiError, status);
    }
    
    private List<FieldErrorDTO> getMessagesOfField(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new FieldErrorDTO(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
    }

}
