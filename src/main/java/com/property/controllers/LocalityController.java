package com.property.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.property.payload.ApiResponse;
import com.property.payload.LocalityDto;
import com.property.services.LocalityServices;


@RestController
@RequestMapping("/api/locality")
public class LocalityController {

	@Autowired
	private LocalityServices localityServices;

	// POST-create property
	@PostMapping("/")
	public ResponseEntity<LocalityDto> createProperty(@Valid @RequestBody LocalityDto propertyDto) {

		LocalityDto createPropertyDto = this.localityServices.createLocality(propertyDto);
		return new ResponseEntity<>(createPropertyDto, HttpStatus.CREATED);
	}

	// update

	@PutMapping("/{locationId}")
	public ResponseEntity<LocalityDto> updateProperty(@Valid @RequestBody LocalityDto propertyDto,
			@PathVariable Integer locationId) {

		LocalityDto updatedPropertyDto = this.localityServices.updateLocality(propertyDto, locationId);
		return  ResponseEntity.ok(updatedPropertyDto);
	}

	// Delete
	@DeleteMapping("/{locationId}")
	public ResponseEntity<ApiResponse> deleteProperty(@PathVariable Integer locationId) {
		this.localityServices.deleteLocality(locationId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Locality Deleted Successfully", true), HttpStatus.OK);
	}

	// Get -user get
	@GetMapping("/")
	public ResponseEntity<List<LocalityDto>> getLocalities() {
		return ResponseEntity.ok(this.localityServices.getLocalities());
	}

	// Get -user get
	@GetMapping("/{locationId}")
	public ResponseEntity<LocalityDto> getLocality(@PathVariable Integer locationId) {
		LocalityDto localityDto = this.localityServices.getLocality(locationId);
		return new ResponseEntity<LocalityDto>(localityDto,HttpStatus.OK);

	}

}
