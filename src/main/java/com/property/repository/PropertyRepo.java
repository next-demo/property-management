package com.property.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.property.models.Customer;
import com.property.models.LocalityDetails;
import com.property.models.Owner;
import com.property.models.PropertyDetails;

public interface PropertyRepo extends JpaRepository<PropertyDetails,Integer>{

	List<PropertyDetails> findByOwner(Owner owner);
	List<PropertyDetails> findByLocality(LocalityDetails locality);
	List<PropertyDetails> findByCustomer(Customer customer);
	
	@Query("select p from PropertyDetails p where p.apartmenttype like :key")
	List<PropertyDetails> searchByApartmenttype( @Param("key") String apartmenttype);
	
	
}
 