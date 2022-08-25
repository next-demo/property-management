package com.property.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;
import org.mockito.InjectMocks;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.property.controllers.AuthController;
import com.property.models.Customer;
import com.property.payload.CustomerDto;
import com.property.repository.CustomerRepo;
import com.property.services.impl.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
public class CutomerServiceTest {
	
	@Mock
	private CustomerRepo customerRepository;
	@Mock
	private ModelMapper modelmapper;
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	private CustomerDto customerDto;
	
	@BeforeEach
    public void setup() {
		customerDto = new CustomerDto(20,"manas","manas@gmail.com","manas123"); 
    }
	
	// JUnit test for saveEmployee method
    @DisplayName("JUnit test for saveCustomer method")
    @Test
    public void givenCustomerObject_whenSaveCustomer_thenReturnCustomerObject(){

    	Customer cust = this.modelmapper.map(customerDto, Customer.class);
    	Customer addedCat = this.customerRepository.save(cust);
    	CustomerDto createdCustomer = this.modelmapper.map(addedCat, CustomerDto.class);
        // when -  action or the behaviour that we are going test
    	when(this.modelmapper.map(customerDto, Customer.class)).thenReturn(cust);
    	when(this.customerRepository.save(cust)).thenReturn(addedCat);
    	when(this.modelmapper.map(addedCat, CustomerDto.class)).thenReturn(createdCustomer);
    	
    	
        CustomerDto savedCustomer = customerService.createCustomer(customerDto);

        System.out.println("This is the saved customer"+savedCustomer);
        // then - verify the output
        assertThat(savedCustomer).isNotNull();
    }
	
//	  // JUnit test for getEmployeeById method
//    @DisplayName("JUnit test for getCustomerById method")
//    @Test
//    public void givenCustomerId_whenGetCustomerById_thenReturnCustomerObject(){
//        // given
////        given(customerRepository.findById(20)).willReturn(Optional.of(customerDto));
//
//        // when
//        CustomerDto savedCustomer = customerService.getCustomerById(customerDto.getId());
//
//        // then
//        assertThat(savedCustomer).isNotNull();
//
//    }
	
	
	
}
