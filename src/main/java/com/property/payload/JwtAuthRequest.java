package com.property.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class JwtAuthRequest {

	//UserName as contact_detail
	private String username;
	private String password;
	

}