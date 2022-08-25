package com.property.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.property.exceptions.ResourceNotFoundException;
import com.property.models.Customer;
import com.property.models.Owner;
import com.property.repository.CustomerRepo;
import com.property.repository.OwnerRepo;



@Service
public class CustomUserDetailService implements UserDetailsService {

	@Autowired
	private CustomerRepo customerRepo;
	
	

	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Customer customer = this.customerRepo.findBycontactdetail(username).orElseThrow(()-> new ResourceNotFoundException("Customer", "contactDetail", username));
		
		return customer;
	}
	
}