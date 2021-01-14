package com.compassouol.city.service;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.compassouol.city.dto.CityRequestDTO;
import com.compassouol.city.dto.CityResponseDTO;
import com.compassouol.city.exception.CityNotFoundException;
import com.compassouol.city.exception.ExistingCityForStateException;
import com.compassouol.city.model.City;
import com.compassouol.city.repository.CityRepository;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {
	
	@InjectMocks
	private CityService cityService;
	
	@Mock
	private CityRepository cityRepository;
	
	@Test
	public void findBy_mustReturnCityList() {
	    String name = "Juiz de Fora";
	    String state = "MG";
	    PageRequest pageRequest = PageRequest.of(0, 10, Sort.Direction.ASC, "id");
	    Mockito.when(cityRepository.findBy(Mockito.anyString(), Mockito.anyString(), Mockito.any())).thenReturn(new PageImpl<>(new ArrayList<>()));
	    
	    Page<CityResponseDTO> result = cityService.findBy(name, state, pageRequest);
	    
	    Assert.assertNotNull(result);
	}
	
	@Test
	public void findBy_mustReturnCity() {
		Long id = 1l;
		City city = new City(1l, "Juiz de Fora", "MG");
		Mockito.when(cityRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(city));
		
		CityResponseDTO result = cityService.findBy(id);
		
		Assert.assertNotNull(result);
	}
	
	@Test(expected = CityNotFoundException.class)
	public void findBy_mustThrowExceptionWhenCityIsNotFound() {
		Long id = 1l;
		Mockito.when(cityRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(null));
		
		cityService.findBy(id);
	}
	
	@Test
	public void save_mustSaveCity() {
		CityRequestDTO cityRequestDTO = new CityRequestDTO("Juiz de Fora", "MG");
		City city = new City(1l, "Juiz de Fora", "MG");
		Mockito.when(cityRepository.findByNameAndState(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.ofNullable(null));
		Mockito.when(cityRepository.save(Mockito.any())).thenReturn(city);
		
		CityResponseDTO result = cityService.save(cityRequestDTO);
		
	    Assert.assertNotNull(result.getId());
	    Assert.assertNotNull(result.getName());
	    Assert.assertNotNull(result.getState());
	}
	
	@Test(expected = ExistingCityForStateException.class)
	public void save_mustThrowExceptionWhenCityExistsForState() {
		CityRequestDTO cityRequestDTO = new CityRequestDTO("Juiz de Fora", "MG");
		City city = new City(1l, "Juiz de Fora", "MG");
		Mockito.when(cityRepository.findByNameAndState(Mockito.anyString(), Mockito.anyString())).thenReturn(Optional.of(city));
		
		cityService.save(cityRequestDTO);
	}

}
