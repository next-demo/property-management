package com.property.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.property.exceptions.ResourceNotFoundException;
import com.property.models.Customer;
import com.property.models.LocalityDetails;
import com.property.models.Owner;
import com.property.models.PropertyDetails;
import com.property.payload.PropertyDto;
import com.property.payload.PropertyResponse;
import com.property.repository.CustomerRepo;
import com.property.repository.LocalityRepo;
import com.property.repository.OwnerRepo;
import com.property.repository.PropertyRepo;

import com.property.services.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {
	
	@Autowired
	private PropertyRepo propertyRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private OwnerRepo ownerRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	@Autowired
	private LocalityRepo localityRepo;
	
	@Override
	public PropertyDto  createProperty(PropertyDto propertyDto,Integer ownerId,Integer locationId) {
		
		Owner owner = this.ownerRepo.findById(ownerId).orElseThrow(()-> new ResourceNotFoundException("Owner","Owner id", ownerId)); 
		LocalityDetails locality =this.localityRepo.findById(locationId).orElseThrow(()-> new ResourceNotFoundException("LocalityDetails","Locality id", locationId));
		PropertyDetails property =this.modelMapper.map(propertyDto, PropertyDetails.class);
		property.setOwner(owner);
		property.setLocality(locality);
	    property.setImageName("default.png");
	    PropertyDetails newProperty= this.propertyRepo.save(property);
		return this.modelMapper.map(newProperty,PropertyDto.class);
	}
	 
	@Override
	public PropertyDto bookProperty(Integer propertyId,Integer customerId) {
		Customer customer = this.customerRepo.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer","Id",customerId));
	
		PropertyDetails property=	this.propertyRepo.findById(propertyId).orElseThrow(()-> new ResourceNotFoundException("PropertyDetails","id", propertyId));
		customer.setOwner(property.getOwner());
		property.setCustomer(customer);
		PropertyDetails newProperty= this.propertyRepo.save(property);
		return this.modelMapper.map(newProperty,PropertyDto.class);
		
	}

	@Override
	public PropertyDto updateProperty(PropertyDto propertyDto, Integer id) {
	PropertyDetails property=	this.propertyRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("PropertyDetails","id", id));
	
	property.setApartmenttype(propertyDto.getApartmenttype());
	property.setBhktype(propertyDto.getBhktype());
	property.setBuilduparea(propertyDto.getBuilduparea());
	property.setFacing(propertyDto.getFacing());
	property.setFloor(propertyDto.getFloor());
	property.setImageName(propertyDto.getImageName());
	property.setPropertyage(propertyDto.getPropertyage());
	property.setTotalfloor(propertyDto.getTotalfloor());
	property.setPlotArea(propertyDto.getPlotArea());
	property.setPricePerSqft(propertyDto.getPricePerSqft());
	property.setPrice(propertyDto.getPrice());
	property.setAvailableFrom(propertyDto.getAvailableFrom());
	property.setPostedOn(propertyDto.getPostedOn());
	property.setDimensions(propertyDto.getDimensions());
	property.setDescription(propertyDto.getDescription());
	
   PropertyDetails updatedproperty= this.propertyRepo.save(property);
		return this.modelMapper.map(updatedproperty,PropertyDto.class);
	}

	@Override
	public PropertyDto getPropertyById(Integer id) {
	   PropertyDetails property=  this.propertyRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("PropertyDetails","id", id));
		return this.modelMapper.map(property, PropertyDto.class);
	}

	@Override
	public PropertyResponse getAllProperties(Integer pageNumber ,Integer pageSize,String sortBy,String sortDir) {
		Sort sort =null;
		if(sortDir.equalsIgnoreCase("asc")){
			sort=Sort.by(sortBy).ascending();
		}else {
			
			 sort=Sort.by(sortBy).descending();
		}
		
		Pageable p= PageRequest.of(pageNumber, pageSize,sort);
		Page<PropertyDetails> pageProperty=this.propertyRepo.findAll(p);
		List<PropertyDetails> allProperties =pageProperty.getContent();
		
	List<PropertyDto> propertyDtos= allProperties.stream().map((property)-> this.modelMapper.map(property,PropertyDto.class ))
			.collect(Collectors.toList());
	
	PropertyResponse propertyResponse=new PropertyResponse();
	propertyResponse.setDetails(propertyDtos);
	propertyResponse.setPageNumber(pageProperty.getNumber());
	propertyResponse.setPageSize(pageProperty.getSize());
	propertyResponse.setTotalElements(pageProperty.getTotalElements());
	propertyResponse.setTotalPages(pageProperty.getTotalPages());
	propertyResponse.setLastPage(pageProperty.isLast());

	return propertyResponse ;
	}

	@Override
	public void deleteProperty(Integer id) {
	PropertyDetails property =this.propertyRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("PropertyDetails","id", id));
       this.propertyRepo.delete( property );
	}

	@Override
	public List<PropertyDto> getPropertyByLocalityDetails(Integer locationId) {
		LocalityDetails locality =this.localityRepo.findById(locationId).orElseThrow(()-> new ResourceNotFoundException("LocationDetails","locationId", locationId));
		List<PropertyDetails> properties=this.propertyRepo.findByLocality(locality);
		List<PropertyDto> propertyDtos=	properties.stream().map((property)-> this.modelMapper.map(property,PropertyDto.class )).collect(Collectors.toList());
		return propertyDtos;
	}
 
	@Override
	public List<PropertyDto> getPropertyByOwner(Integer ownerId) {
		Owner owner= this.ownerRepo.findById(ownerId).orElseThrow(()-> new ResourceNotFoundException("Owner","ownerId", ownerId));
		
		List<PropertyDetails> properties=this.propertyRepo.findByOwner(owner);
		List<PropertyDto> propertyDtos=	properties.stream().map((property)-> this.modelMapper.map(property,PropertyDto.class )).collect(Collectors.toList());
		return propertyDtos;
	}
	
	@Override
	public List<PropertyDto> getPropertyByCustomer(Integer customerId){
		
		Customer customer = this.customerRepo.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer","Id",customerId));
		List<PropertyDetails> properties=this.propertyRepo.findByCustomer(customer);
		List<PropertyDto> propertyDtos=	properties.stream().map((property)-> this.modelMapper.map(property,PropertyDto.class )).collect(Collectors.toList());
		return propertyDtos;
	}

	@Override
	public List<PropertyDto> searchPropertyDetails(String keyword) {
 	 List<PropertyDetails> properties= this.propertyRepo.searchByApartmenttype("%"+keyword+"%");
	List<PropertyDto>propertyDtos= properties.stream().map((property)-> this.modelMapper.map(property, PropertyDto.class)).collect(Collectors.toList());
	 
		return propertyDtos;
	}

//	@Override
//	public List<PropertyDto> getPropertyByLocalityDetails(Integer locationId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	



	

	

}
