package com.compassouol.city.service;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.compassouol.city.converter.CityConverter;
import com.compassouol.city.dto.CityRequestDTO;
import com.compassouol.city.dto.CityResponseDTO;
import com.compassouol.city.exception.CityNotFoundException;
import com.compassouol.city.exception.ExistingCityForStateException;
import com.compassouol.city.model.City;
import com.compassouol.city.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	public Page<CityResponseDTO> findBy(String name, String state, Pageable page) {
		return cityRepository.findBy(treatFilter(name), treatFilter(state), page);
	}

	public CityResponseDTO findBy(Long id) {
		City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException());
		return CityConverter.convert(city);
	}
	
	public CityResponseDTO save(CityRequestDTO cityRequestDTO) {
		validateExistingCityForState(cityRequestDTO);
		City city = CityConverter.convert(cityRequestDTO);
		return CityConverter.convert(cityRepository.save(city));
	}
	
	private void validateExistingCityForState(CityRequestDTO cityRequestDTO) {
		Optional<City> city = cityRepository.findByNameAndState(cityRequestDTO.getName(), cityRequestDTO.getState());
		if (city.isPresent()) {
			throw new ExistingCityForStateException();
		}
	}

	private String treatFilter(String value) {
		return StringUtils.isNotBlank(value) ? value.toLowerCase() : null;
	}

}
