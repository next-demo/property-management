package com.property.exceptions;

public class ResourceNotFoundException extends RuntimeException {
	
	String resourceName;
	String fieldName;
	long fieldValue;
	String fieldValue1;
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s  : %s",resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	public ResourceNotFoundException(String resouceName, String fieldName, String fieldValue1) {
		super(String.format("%s not found with %s : %s", resouceName, fieldName, fieldValue1));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue1 = fieldValue1;
	}
	
	

}
 