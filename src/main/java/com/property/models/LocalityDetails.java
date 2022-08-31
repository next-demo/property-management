package com.property.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="Locality_details")
@NoArgsConstructor
@Getter
@Setter
public class LocalityDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer locationId;
	
	@Column(name="City" , nullable= false)
	private String city;
	
	@Column(name="Locality"  , nullable= false)
	private String locality;
	
	@Column(name="Landmark"  , nullable= false)
	private String landmark;
	
	
	@OneToMany(mappedBy = "locality", cascade =CascadeType.ALL,fetch = FetchType.LAZY)
	private List<PropertyDetails> property = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name="Owner_id")
	private Owner owner;

	

}
