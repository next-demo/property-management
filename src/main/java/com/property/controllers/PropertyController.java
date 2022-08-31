package com.property.controllers;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.property.config.AppConstants;
import com.property.payload.ApiResponse;
import com.property.payload.PropertyDto;
import com.property.payload.PropertyResponse;
import com.property.services.FileService;
import com.property.services.PropertyService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")
public class PropertyController {

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	// create property
	@PostMapping("/owner/{ownerId}/locality/{locationId}/properties")
	public ResponseEntity<PropertyDto> createProperty(@RequestBody PropertyDto propertyDto,
			@PathVariable Integer ownerId, @PathVariable Integer locationId)

	{
		PropertyDto createProperty = this.propertyService.createProperty(propertyDto, ownerId, locationId);
		return new ResponseEntity<PropertyDto>(createProperty, HttpStatus.CREATED);
	}
	
	//Book Property
	@PostMapping("/customer/{customerId}/property/{propertyId}")
	public ResponseEntity<PropertyDto> bookProperty(@PathVariable Integer customerId, @PathVariable Integer propertyId){
		PropertyDto BookedProperty = this.propertyService.bookProperty(propertyId, customerId);

		return new ResponseEntity<PropertyDto>(BookedProperty, HttpStatus.OK);
		
	}

	// get by owner
	@GetMapping("/owner/{ownerId}/properties")
	public ResponseEntity<List<PropertyDto>> getPropertyByOwner(@PathVariable Integer ownerId) {
		List<PropertyDto> properties = this.propertyService.getPropertyByOwner(ownerId);

		return new ResponseEntity<List<PropertyDto>>(properties, HttpStatus.OK);
	}
	// get by customer
		@GetMapping("/customer/{customerId}/properties")
		public ResponseEntity<List<PropertyDto>> getPropertyByCustomer(@PathVariable Integer customerId) {
			List<PropertyDto> properties = this.propertyService.getPropertyByCustomer(customerId);

			return new ResponseEntity<List<PropertyDto>>(properties, HttpStatus.OK);
		}

	// get by Locality
	@GetMapping("/locality/{locationId}/properties")
	public ResponseEntity<List<PropertyDto>> getPropertyByLocalityDetails(@PathVariable Integer locationId) {
		List<PropertyDto> properties = this.propertyService.getPropertyByLocalityDetails(locationId);

		return new ResponseEntity<List<PropertyDto>>(properties, HttpStatus.OK);
	}

	// get all property
	@GetMapping("/properties")
	public ResponseEntity<PropertyResponse> getAllProperties(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false) String sortDir) {
		PropertyResponse propertyResponse = this.propertyService.getAllProperties(pageNumber, pageSize, sortBy,
				sortDir);
		return new ResponseEntity<PropertyResponse>(propertyResponse, HttpStatus.OK);
	}

	// get property by id

	@GetMapping("/properties/{id}")
	public ResponseEntity<PropertyDto> getPropertyById(@PathVariable Integer id) {
		PropertyDto property = this.propertyService.getPropertyById(id);
		return new ResponseEntity<PropertyDto>(property, HttpStatus.OK);

	}

	// delete property

	@DeleteMapping("/properties/{id}")
	public ApiResponse deleteProperty(@PathVariable Integer id) {

		this.propertyService.deleteProperty(id);
		return new ApiResponse("property is successfully deleted !!" + id, true);

	}

	// update property
	@PutMapping("/properties/{id}")
	public ResponseEntity<PropertyDto> updateProperty(@RequestBody PropertyDto propertyDto, @PathVariable Integer id) {
		PropertyDto updatedProperty = this.propertyService.updateProperty(propertyDto, id);

		return new ResponseEntity<PropertyDto>(updatedProperty, HttpStatus.OK);
	}

	// search
	@GetMapping("/properties/search/{keywords}")
	public ResponseEntity<List<PropertyDto>> serachPropertyByApartmenttype(@PathVariable("keywords") String keywords

	) {
		List<PropertyDto> result = this.propertyService.searchPropertyDetails(keywords);
		return new ResponseEntity<List<PropertyDto>>(result, HttpStatus.OK);
	}

	// property image upload
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST, path = "/property/image/upload/{id}")
	public ResponseEntity<PropertyDto> uploadPostImage(@RequestParam("image") MultipartFile image,
			@PathVariable Integer id) throws IOException {
         PropertyDto propertyDto = this.propertyService.getPropertyById(id);
		String fileName = this.fileService.UploadImage(path, image);
				
		propertyDto.setImageName(fileName);
		System.out.println("The file Name is "+fileName);

	PropertyDto updateProperty=	this.propertyService.updateProperty(propertyDto, id);
	return new ResponseEntity<PropertyDto>(updateProperty,HttpStatus.OK);
	}
	
	// serve image files 
	
	@GetMapping(value="/property/image/{imageName}", produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response
			
			) throws IOException {
		
		InputStream resource =this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
		
	}

}
