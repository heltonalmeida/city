package com.compassouol.city.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.compassouol.city.converter.CityConverter;
import com.compassouol.city.dto.CityRequestDTO;
import com.compassouol.city.dto.CityResponseDTO;
import com.compassouol.city.model.City;
import com.compassouol.city.repository.CityRepository;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;

	public CityResponseDTO save(CityRequestDTO cityRequestDTO) {
		City city = CityConverter.convert(cityRequestDTO);
		return CityConverter.convert(cityRepository.save(city));
	}

}
