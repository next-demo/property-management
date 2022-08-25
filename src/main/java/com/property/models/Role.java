package com.property.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;



@Entity
@Data
@Getter
@Setter
public class Role {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	 @ManyToMany(mappedBy = "roles", cascade = { CascadeType.ALL })
	    private Set<Customer> customer = new HashSet<Customer>();
	
}