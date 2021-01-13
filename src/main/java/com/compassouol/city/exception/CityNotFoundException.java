package com.compassouol.city.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Cidade não encontrada")
public class CityNotFoundException extends GenericException {

    private static final long serialVersionUID = 1L;

}

