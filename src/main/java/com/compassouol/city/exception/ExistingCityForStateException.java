package com.compassouol.city.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Cidade já cadastrada para o estado informado.")
public class ExistingCityForStateException extends GenericException {

    private static final long serialVersionUID = 1L;

}
