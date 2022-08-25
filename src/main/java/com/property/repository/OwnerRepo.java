package com.property.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.property.models.Customer;
import com.property.models.Owner;


public interface OwnerRepo extends JpaRepository<Owner, Integer>  {
	
	Optional<Owner> findBycontactdetail(String contactdetail);

}
