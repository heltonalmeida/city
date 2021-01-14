package com.compassouol.city.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	private static final String SORT_BY_FIELD_ID = "id";
	
	@Autowired
	private CityService cityService;
	
	@ApiOperation(value = "Recupera as informações dos cliente(s).",
            consumes = "application/json",
            produces = "application/json",
            httpMethod = "GET",
            response = Page.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Parâmetro page inválido | Parâmetro size inválido"),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
	@GetMapping
	public ResponseEntity<Page<CityResponseDTO>> findBy(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String state,
			@PageableDefault(page = 0, size = 10, sort = SORT_BY_FIELD_ID, direction = Sort.Direction.ASC) final Pageable page) {
		return ResponseEntity.ok(cityService.findBy(name, state, page));
	}
	
	@ApiOperation(value = "Realiza a consulta de uma cidade pelo identificador.",
            consumes = "application/json",
            produces = "application/json",
            httpMethod = "GET",
            response = CityResponseDTO.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Cidade não encontrada."),
            @ApiResponse(code = 500, message = "Erro interno no servidor"),
    })
	@GetMapping("/{id}")
	public ResponseEntity<CityResponseDTO> findBy(@PathVariable Long id) {
		return ResponseEntity.ok(cityService.findBy(id));
	}
	
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
	public ResponseEntity<CityResponseDTO> save(@Valid @RequestBody CityRequestDTO cityRequestDTO) {
		return ResponseEntity.ok(cityService.save(cityRequestDTO));
	}

}
