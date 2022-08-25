package com.property.services;

import java.util.List;

import com.property.payload.OwnerDto;


public interface OwnerService {
	
    OwnerDto createOwner(OwnerDto customer);
	
	OwnerDto updateOwner(OwnerDto customer, Integer customerId);
	OwnerDto getOwnerById(Integer customerId);
	
	List<OwnerDto> getAllOwners();
	
	void deleteOwner(Integer customerId);
}
