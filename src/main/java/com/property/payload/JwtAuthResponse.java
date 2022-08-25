package com.property.payload;

import com.property.models.Customer;

import lombok.Data;

@Data
public class JwtAuthResponse {
	
	private String token;
	
	private CustomerDto customer;
	
	private OwnerDto owner;
}