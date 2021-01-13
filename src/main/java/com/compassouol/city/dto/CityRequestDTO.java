package com.compassouol.city.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CityRequestDTO {
	
    @NotBlank
    @Size(max = 255, min = 1)
	private String name;
    
    @NotBlank
    @Size(max = 100, min = 1)
	private String state;
	
	@Builder
	public CityRequestDTO(String name, String state) {
		this.name = name;
		this.state = state;
	}

}
