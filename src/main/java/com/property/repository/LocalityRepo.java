package com.property.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.property.models.LocalityDetails;

public interface LocalityRepo extends JpaRepository<LocalityDetails, Integer>  {
	
	

}
