package com.property.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LocalityDto {
	
	private Integer locationId;
	@NotEmpty
	private String city;
	@NotEmpty
	private String locality;
	@NotEmpty
	private String landmark; 

	private OwnerDto owner ;
}
