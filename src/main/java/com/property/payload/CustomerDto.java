package com.property.payload;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.UniqueElements;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {
	
	
	

	private int id;
	@NotEmpty
	@Size(min=4, message = "Username must be min of 4 characters !!")
	private String name;
	
	private String contactdetail;
	@NotEmpty
	@Size(min  = 3 , max = 10 , message = "Password must be min of 3 chars and max of 10")
	private String password;	
	
	private Set<RoleDto> role = new HashSet<>();
	
//	@JsonIgnore
//	public String getPassword() {
//		return this.password;
//	}
	public CustomerDto(int id,
			@NotEmpty @Size(min = 4, message = "Username must be min of 4 characters !!") String name,
			String contactdetail,
			@NotEmpty @Size(min = 3, max = 10, message = "Password must be min of 3 chars and max of 10") String password) {
		super();
		this.id = id;
		this.name = name;
		this.contactdetail = contactdetail;
		this.password = password;
	}
	
	
}