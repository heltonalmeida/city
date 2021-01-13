package com.compassouol.city.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.compassouol.city.dto.CityRequestDTO;
import com.compassouol.city.dto.CityResponseDTO;
import com.compassouol.city.service.CityService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/city")
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	@ApiOperation(value = "Realiza a inserção de uma cidade.",
            consumes = "application/json",
            produces = "application/json",
            httpMethod = "POST",
            response = CityResponseDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Dados obrigatórios não informados | Tamanho do campo inválido"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
	@PostMapping
	public CityResponseDTO save(@Valid @RequestBody CityRequestDTO cityRequestDTO) {
		return cityService.save(cityRequestDTO);
	}

}
