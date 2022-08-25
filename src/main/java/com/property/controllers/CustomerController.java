package com.property.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.property.payload.ApiResponse;
import com.property.payload.CustomerDto;
import com.property.services.CustomerService;



@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins="*")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	//POST -create user
	@PostMapping("/")
	public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto ){
		
		CustomerDto createCustomerDto = this.customerService.registerNewCustomer(customerDto);
		return new ResponseEntity<>(createCustomerDto, HttpStatus.CREATED);
	}
	
	
	//PUT -update user
	
	@PutMapping("/{customerId}")
	public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto,@PathVariable Integer customerId){
		
		CustomerDto updatedCustomer =	this.customerService.updateCustomer(customerDto, customerId);
		return ResponseEntity.ok(updatedCustomer);
	}
	
	//Delete - delete user
	@DeleteMapping("/{customerId}")
	public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable("customerId") Integer uid){
		this.customerService.deleteCustomer(uid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Customer deleted Successfully",true), HttpStatus.OK);
	}
 
	//Get -user get
	@GetMapping("/")
	public ResponseEntity<List<CustomerDto>> getAllCustomers(){
		return ResponseEntity.ok(this.customerService.getAllCustomers());
	}
	
	//Get -user get
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getSingleCustomer(@PathVariable Integer customerId){
		return ResponseEntity.ok(this.customerService.getCustomerById(customerId));
		
	}	
}
