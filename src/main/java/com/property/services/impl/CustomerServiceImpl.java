package com.property.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.property.config.AppConstants;
import com.property.exceptions.ResourceNotFoundException;
import com.property.models.Customer;
import com.property.models.Role;
import com.property.payload.CustomerDto;
import com.property.repository.CustomerRepo;
import com.property.repository.RoleRepo;
import com.property.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		Customer cust = this.modelMapper.map(customerDto, Customer.class);
		Customer addedCus = this.customerRepo.save(cust);
		return this.modelMapper.map(addedCus, CustomerDto.class);
	}
	


	@Override
	public CustomerDto updateCustomer(CustomerDto customerDto, Integer customerId) {
		
		Customer customer = this.customerRepo.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer","Id",customerId));
		
		customer.setName(customerDto.getName());
		customer.setContactdetail(customerDto.getContactdetail());
		customer.setPassword(customerDto.getPassword());
		
		
		Customer updatedCustomer = this.customerRepo.save(customer);
		CustomerDto customerDto1 = this.customerToDto(updatedCustomer);
		
		return customerDto1;
	}

	@Override
	public CustomerDto getCustomerById(Integer customerId) {
		
		Customer customer = this.customerRepo.findById(customerId)
				.orElseThrow(()-> new ResourceNotFoundException("Customer","Id",customerId));
		return this.customerToDto(customer);
	}

	@Override
	public List<CustomerDto> getAllCustomers() {
		
		List<Customer> customers  = this.customerRepo.findAll();
	    List<CustomerDto> customerDtos = customers.stream()
			.map(customer -> this.customerToDto(customer)).collect(Collectors.toList());
	    
	    return customerDtos;
		
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		
		Customer customer = this.customerRepo.findById(customerId)
		.orElseThrow(()-> new ResourceNotFoundException("Customer","Id",customerId));
		
		this.customerRepo.delete(customer);

	}
	
	private Customer dtoToCustomer(CustomerDto customerDto) {
		
		Customer customer = this.modelMapper.map(customerDto, Customer.class);
//		customer.setId(customerDto.getId());
//		customer.setName(customerDto.getName());
//		customer.setContact_detail(customerDto.getContact_detail());
//		customer.setPassword(customerDto.getPassword());
		
	
		
		return customer;
	}
	
	public CustomerDto customerToDto(Customer customer) {
		
		CustomerDto customerDto = this.modelMapper.map(customer, CustomerDto.class); 	
		return customerDto;
	}

	@Override
	public CustomerDto registerNewCustomer(CustomerDto customerDto) {
		System.out.println(customerDto.getPassword());
		Customer customer = this.modelMapper.map(customerDto, Customer.class);
		System.out.println(customer.getName());
		System.out.println(customer.getPassword());
		//encoded the password
		customer.setPassword(this.passwordEncoder.encode(customer.getPassword()));
		System.out.println(customer.getPassword());
		
		//roles
		Role role = this.roleRepo.findById(AppConstants.CUSTOMER_USER).get();
		
		customer.getRoles().add(role);
		
		Customer newCustomer = this.customerRepo.save(customer);

		return this.modelMapper.map(newCustomer, CustomerDto.class);
	}

}