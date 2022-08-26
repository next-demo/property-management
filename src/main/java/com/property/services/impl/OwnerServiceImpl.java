package com.property.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.property.repository.OwnerRepo;
import com.property.repository.RoleRepo;
import com.property.models.Customer;
import com.property.models.Owner;
import com.property.models.Role;
import com.property.config.AppConstants;
import com.property.exceptions.ResourceNotFoundException;
import com.property.payload.OwnerDto;
import com.property.services.OwnerService;

@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
	private OwnerRepo OwnerRepo;
	
	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public OwnerDto createOwner(OwnerDto OwnerDto) {

		Owner Owner = this.dtoToOwner(OwnerDto);

//		// encoded the password
//		Owner.setPassword(this.passwordEncoder.encode(Owner.getPassword()));


		Owner savedOwner = this.OwnerRepo.save(Owner);
		return this.ownerToDto(savedOwner);
	}

	@Override
	public OwnerDto updateOwner(OwnerDto OwnerDto, Integer OwnerId) {

		Owner Owner = this.OwnerRepo.findById(OwnerId)
				.orElseThrow(() -> new ResourceNotFoundException("Owner", "Id", OwnerId));

		Owner.setName(OwnerDto.getName());
		Owner.setContactdetail(OwnerDto.getContactdetail());
		Owner.setPassword(OwnerDto.getPassword());

		Owner updatedOwner = this.OwnerRepo.save(Owner);
		OwnerDto OwnerDto1 = this.ownerToDto(updatedOwner);

		return OwnerDto1;
	}

	@Override
	public OwnerDto getOwnerById(Integer ownerId) {

		Owner owner = this.OwnerRepo.findById(ownerId)
				.orElseThrow(() -> new ResourceNotFoundException("Owner", "ownerId", ownerId));
		return this.ownerToDto(owner);
	}

	@Override
	public List<OwnerDto> getAllOwners() {

		List<Owner> owners = this.OwnerRepo.findAll();
		List<OwnerDto> ownerDtos = owners.stream().map(owner -> this.ownerToDto(owner)).collect(Collectors.toList());

		return ownerDtos;

	}

	@Override
	public void deleteOwner(Integer ownerId) {

		Owner owner = this.OwnerRepo.findById(ownerId)
				.orElseThrow(() -> new ResourceNotFoundException("Owner", "ownerId", ownerId));

		this.OwnerRepo.delete(owner);

	}
	
	@Override
	public OwnerDto loadUserByUsername(String username) throws UsernameNotFoundException {

		Owner owner = this.OwnerRepo.findBycontactdetail(username).orElseThrow(()-> new ResourceNotFoundException("Owner", "contactDetail", username));
		
		return this.modelMapper.map(owner, OwnerDto.class) ;
	}

//	
	private Owner dtoToOwner(OwnerDto ownerDto) {

		Owner owner = this.modelMapper.map(ownerDto, Owner.class);
//		customer.setId(customerDto.getId());
//		customer.setName(customerDto.getName());
//		customer.setContact_detail(customerDto.getContact_detail());
//		customer.setPassword(customerDto.getPassword());
		return owner;
	}

	public OwnerDto ownerToDto(Owner owner) {

		OwnerDto customerDto = this.modelMapper.map(owner, OwnerDto.class);
		return customerDto;
	}
	

}