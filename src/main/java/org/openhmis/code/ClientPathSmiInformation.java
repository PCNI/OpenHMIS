package org.openhmis.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.openhmis.code.serialization.CodeSerializer;
import org.openhmis.code.serialization.CodeLookup;

// Codes for Data Standard: PATHSMIInformation (2014, 4.9.E)
// http://www.hudhdx.info/Resources/Vendors/4_0/HMISCSVSpecifications4_0FINAL.pdf

@JsonSerialize(using = CodeSerializer.class)
public enum ClientPathSmiInformation implements BaseCode {
	NO (0, "No"),
	UNCONFIRMED (1, "Unconfirmed; presumptive or self-report"),
	EVALUATION (2, "Confirmed through assessment and clinical evaluation"),
	PRIOR_EVALUATION (3, "Confirmed by prior evaluation or clinical records"),
	UNKNOWN (8, "Client doesn't know"),
	REFUSED (9, "Client refused"),
	NOT_COLLECTED (99, "Data not collected");
	
	private final Integer code;
	private final String description;

	ClientPathSmiInformation(final Integer code, final String description) {
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
	private static final CodeLookup<ClientPathSmiInformation> enhancer = new CodeLookup<ClientPathSmiInformation>(values());	

	@JsonCreator
	public static ClientPathSmiInformation valueByCode(Integer code) {
		ClientPathSmiInformation value = enhancer.valueByCode(code); 
		return (value == null)?ClientPathSmiInformation.NOT_COLLECTED:value;
	}
}