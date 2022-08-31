 package com.property.payload;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.property.models.Customer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PropertyDto {

	
	private int id;
	@NotEmpty(message = "apartmenttype is required !!")
	private String apartmenttype;
	
	@NotEmpty(message = "bhktype is required !!")
	private String bhktype; 
	
	@NotEmpty(message = "floor is required !!")
	private String floor ;
	
	@NotEmpty(message = "totalfloor is required !!")
	private String totalfloor;

	@NotEmpty(message = "propertyage is required !!")
	  private String propertyage;
	@NotEmpty(message = "facing is required !!")
	  private String facing ;
	@NotEmpty(message = "builduparea is required !!")
	  private int builduparea;

	private String imageName;
	@NotEmpty(message = "plotArea is required !!")
	private String plotArea;
	@NotEmpty(message = "pricePerSqft is required !!")
	private long pricePerSqft;
	@NotEmpty(message = "price is required !!")
	private String price;
	@NotEmpty(message = "availableFrom is required !!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date availableFrom ;
	@NotEmpty(message = "postedOn is required !!")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date postedOn;
	@NotEmpty(message = "dimensions is required !!")
	private String dimensions ;
	private String description;
	
	private OwnerDto owner ;
	
	private LocalityDto locality;
	
	private CustomerDto customer;
	
	
	
}
