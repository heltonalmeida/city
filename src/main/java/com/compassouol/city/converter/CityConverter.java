package com.compassouol.city.converter;

import com.compassouol.city.dto.CityRequestDTO;
import com.compassouol.city.dto.CityResponseDTO;
import com.compassouol.city.model.City;

public class CityConverter {
	
	private CityConverter() {
		
	}
	
	public static City convert(CityRequestDTO cityRequestDTO) {
		return City.builder().name(cityRequestDTO.getName()).state(cityRequestDTO.getState()).build();
	}
	
	public static CityResponseDTO convert(City city) {
		return CityResponseDTO.builder().id(city.getId()).name(city.getName()).state(city.getState()).build();
	}

}
