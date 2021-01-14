package com.compassouol.city.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.compassouol.city.dto.CityResponseDTO;
import com.compassouol.city.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	@Query(value = "SELECT new com.compassouol.city.dto.CityResponseDTO(c.id, c.name, c.state) "
					+ "FROM City c "
					+ "WHERE (:name IS NULL OR LOWER(c.name) LIKE %:name%) "
					+ "AND (:state IS NULL OR LOWER(c.state) = :state)")
	Page<CityResponseDTO> findBy(@Param("name") String name, @Param("state") String state, Pageable page);
	
	Optional<City> findByNameAndState(String name, String state);

}
