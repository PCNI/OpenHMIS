package org.openhmis.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.openhmis.code.serialization.CodeSerializer;
import org.openhmis.code.serialization.CodeLookup;

// Codes for Universal Data Standard: Ethnicity (2014, 3.5.1)
// http://www.hudhdx.info/Resources/Vendors/4_0/HMISCSVSpecifications4_0FINAL.pdf

@JsonSerialize(using = CodeSerializer.class)
public enum ClientEthnicity implements BaseCode {
	NON_HISPANIC (0, "Non-Hispanic/Non-Latino"),
	HISPANIC (1, "Hispanic/Latino"),
	UNKNOWN (8, "Client doesn't know"),
	REFUSED (9, "Client refused"),
	NOT_COLLECTED (99, "Data not collected");
	
	private final Integer code;
	private final String description;

	ClientEthnicity(final Integer code, final String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}
	public String getDescription() {
		return description;
	}
	
	// Enable lookups by code
	private static final CodeLookup<ClientEthnicity> enhancer = new CodeLookup<ClientEthnicity>(values());  

	@JsonCreator
	public static ClientEthnicity valueByCode(Integer code) {
		ClientEthnicity value = enhancer.valueByCode(code); 
		return (value == null)?ClientEthnicity.NOT_COLLECTED:value;
	}
}