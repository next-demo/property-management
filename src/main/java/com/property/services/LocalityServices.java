package com.property.services;

import java.util.List;


import com.property.payload.LocalityDto;



public interface LocalityServices {

	
	//create
	LocalityDto createLocality(LocalityDto localityDto ,Integer ownerId);
	
	//update
	LocalityDto updateLocality(LocalityDto localityDto , Integer localionId);
	
	//delete
	
	void deleteLocality(Integer localionId);
	
	//get
	LocalityDto getLocality( Integer localionId);
	
	
	//get All
	List<LocalityDto> getLocalities();
	
	
}
