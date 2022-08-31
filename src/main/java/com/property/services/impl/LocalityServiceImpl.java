package com.property.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.property.exceptions.ResourceNotFoundException;
import com.property.models.LocalityDetails;
import com.property.models.Owner;
import com.property.payload.LocalityDto;
import com.property.repository.LocalityRepo;
import com.property.repository.OwnerRepo;
import com.property.services.LocalityServices;


@Service
public class LocalityServiceImpl implements LocalityServices{
	
	
	@Autowired
	private LocalityRepo localityRepo;
	
	@Autowired
	private OwnerRepo ownerRepo;
	

	@Autowired
	private ModelMapper modelmapper;
	

	@Override
	public LocalityDto createLocality(LocalityDto localityDto,Integer ownerId) {

		Owner owner = this.ownerRepo.findById(ownerId).orElseThrow(()-> new ResourceNotFoundException("Owner","Owner id", ownerId)); 
		LocalityDetails location = this.modelmapper.map(localityDto,LocalityDetails.class);
		location.setOwner(owner);
		LocalityDetails addedlocation = this.localityRepo.save(location);
		return this.modelmapper.map(addedlocation, LocalityDto.class);
		
		
	}

	@Override
	public LocalityDto updateLocality(LocalityDto localityDto, Integer locationId) {
		LocalityDetails location = this.localityRepo.findById(locationId).orElseThrow(()-> new ResourceNotFoundException("LocalityDetail", "localityId", locationId));
		location.setCity(localityDto.getCity());
		location.setLandmark(localityDto.getLandmark());
		location.setLocality(localityDto.getLocality());
		
		LocalityDetails updatedLocality =this.localityRepo.save(location);
		
		return this.modelmapper.map(updatedLocality, LocalityDto.class);
	}

	@Override
	public void deleteLocality(Integer locationId) {
		
		LocalityDetails locality = this.localityRepo.findById(locationId).orElseThrow(()-> new ResourceNotFoundException("LocalityDetail", "locality id", locationId));
		
		this.localityRepo.delete(locality);
		
	}

	@Override
	public LocalityDto getLocality(Integer locationId) {
		LocalityDetails locality = this.localityRepo.findById(locationId).orElseThrow(()-> new ResourceNotFoundException("LocalityDetail", "locality id", locationId));
		
		return this.modelmapper.map(locality , LocalityDto.class);
	}

	@Override
	public List<LocalityDto> getLocalities() {
		List<LocalityDetails> localities = this.localityRepo.findAll();
		List<LocalityDto> LocDtos = localities.stream().map((location) -> this.modelmapper.map(location, LocalityDto.class)).collect(Collectors.toList());
		
		return LocDtos;
	}
	
}
	
