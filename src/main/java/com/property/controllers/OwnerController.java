package com.property.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.property.models.Customer;
import com.property.models.Owner;
import com.property.payload.ApiResponse;
import com.property.payload.CustomerDto;
import com.property.payload.JwtAuthRequest;
import com.property.payload.JwtAuthResponse;
import com.property.payload.OwnerDto;
import com.property.services.OwnerService;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin(origins="*")
public class OwnerController {
	
	@Autowired
	private OwnerService OwnerService;
	
	//POST -create user
	@PostMapping("/")
	public ResponseEntity<OwnerDto> createOwner(@Valid @RequestBody OwnerDto OwnerDto ){
		
		OwnerDto createOwnerDto = this.OwnerService.createOwner(OwnerDto);
		return new ResponseEntity<>(createOwnerDto, HttpStatus.CREATED);
	}
	
	
	//PUT -update user
	@CrossOrigin
	@RequestMapping(method = RequestMethod.PUT, path = "/{OwnerId}")
	public ResponseEntity<OwnerDto> updateOwner(@Valid @RequestBody OwnerDto OwnerDto,@PathVariable Integer OwnerId){
		
		OwnerDto updatedOwner =	this.OwnerService.updateOwner(OwnerDto, OwnerId);
		return ResponseEntity.ok(updatedOwner);
	}
	
	//Delete - delete user
	@DeleteMapping("/{OwnerId}")
	public ResponseEntity<ApiResponse> deleteOwner(@PathVariable("OwnerId") Integer uid){
		this.OwnerService.deleteOwner(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Owner deleted Successfully",true), HttpStatus.OK);
	}
 
	//Get -user get
	@GetMapping("/")
	public ResponseEntity<List<OwnerDto>> getAllOwners(){
		return ResponseEntity.ok(this.OwnerService.getAllOwners());
	}
	
	//Get -user get
	@GetMapping("/{OwnerId}")
	public ResponseEntity<OwnerDto> getSingleOwner(@PathVariable Integer OwnerId){
		return ResponseEntity.ok(this.OwnerService.getOwnerById(OwnerId));
		
	}	
	
	//Get -user get
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, path = "/login/{contactdetail}")
	public ResponseEntity<OwnerDto> loadOwner(@PathVariable String contactdetail){
		return ResponseEntity.ok(this.OwnerService.loadUserByUsername(contactdetail));
			
	}	
	
}

