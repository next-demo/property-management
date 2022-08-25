package com.property.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.property.payload.CustomerDto;


public interface CustomerService {
	
	CustomerDto registerNewCustomer(CustomerDto customer);
	
	
	
	CustomerDto updateCustomer(CustomerDto customer, Integer customerId);
	CustomerDto getCustomerById(Integer customerId);
	
	List<CustomerDto> getAllCustomers();
	
	void deleteCustomer(Integer customerId);



	CustomerDto createCustomer(CustomerDto customerDto);

}