package org.openhmis.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.openhmis.code.serialization.CodeSerializer;
import org.openhmis.code.serialization.CodeLookup;

// Codes for Universal Data Standard: Gender (2014, 3.6.1)
// http://www.hudhdx.info/Resources/Vendors/4_0/HMISCSVSpecifications4_0FINAL.pdf

@JsonSerialize(using = CodeSerializer.class)
public enum ClientGender implements BaseCode {
	FEMALE (0, "Female"),
	MALE (1, "Male"),
	TRANSGENDER_MALE_TO_FEMALE (2, "Transgender Male to Female"),
	TRANSGENDER_FEMALE_TO_MALE (3, "Transgender Female to Male"),
	OTHER (4, "Other"),
	UNKNOWN (8, "Client doesn't know"),
	REFUSED (9, "Client refused"),
	NOT_COLLECTED (99, "Data not collected");
	
	private final Integer code;
	private final String description;

	ClientGender(final Integer code, final String description) {
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
	private static final CodeLookup<ClientGender> enhancer = new CodeLookup<ClientGender>(values());	

	@JsonCreator
	public static ClientGender valueByCode(Integer code) {
		ClientGender value = enhancer.valueByCode(code); 
		return (value == null)?ClientGender.NOT_COLLECTED:value;
	}
}