package com.compassouol.city.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class FiledExceptionDTO {
	
	private int status;
	private List<FieldErrorDTO> erros;

}
