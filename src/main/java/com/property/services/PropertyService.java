package com.property.services;

import java.util.List;
import com.property.payload.PropertyDto;
import com.property.payload.PropertyResponse;

public interface PropertyService {
	
	
	//create
	PropertyDto  createProperty(PropertyDto property ,Integer ownerId,Integer locationId);
	
	// update 
	PropertyDto updateProperty(PropertyDto property , Integer id);
	
	
	// get by id 
	PropertyDto getPropertyById(Integer id);
	
	// get all property
    PropertyResponse getAllProperties(Integer pageNumber ,Integer pageSize,String sortBy,String sortDir );
	
	// delete property
	void deleteProperty(Integer id);
	
	// get property by locality 
	
	List<PropertyDto> getPropertyByLocalityDetails(Integer locationId);
	
	// get post by owner 
	
	List<PropertyDto> getPropertyByOwner(Integer ownerId);
	
	// search property 
	
	List<PropertyDto> searchPropertyDetails(String keyword);
	
	
	
	
	
	

}
