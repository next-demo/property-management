package com.property.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.property.payload.LocalityDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="property_details")
@NoArgsConstructor
@Getter
@Setter
public class PropertyDetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="apartment_type")
    private String apartmenttype;
	
	@Column(name="BHK_type")
    private String bhktype;
	
	@Column(name="floor")
    private String floor ;
	
	@Column(name="image_name")
	private String imageName;
	
	@Column(name="total_floor")
    private String totalfloor;
	
	@Column(name="property_age")
    private String propertyage;
	
	@Column(name="facing")
    private String facing ;
	
	@Column(name="build_up_area")
    private int builduparea;
	
	private String plotArea;
	private long pricePerSqft;
	private String price;
	
	private Date availableFrom ;
	private Date postedOn;
	private String dimensions ;
	private String description;
	
	@ManyToOne
	@JoinColumn(name="Owner_id")
	private Owner owner;
	
	@ManyToOne
	@JoinColumn(name="Location_id")
	private LocalityDetails locality;
	

	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;


	
}
