package org.openhmis.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.openhmis.code.serialization.CodeSerializer;
import org.openhmis.code.serialization.CodeLookup;

// Codes for Data Standard: IncarceratedParentStatus (2014, 4.33.A)
// http://www.hudhdx.info/Resources/Vendors/4_0/HMISCSVSpecifications4_0FINAL.pdf

@JsonSerialize(using = CodeSerializer.class)
public enum ClientIncarceratedParentStatus implements BaseCode {
	ONE_PARENT (1, "One parent / legal guardian is incarcerated"),
	BOTH_PARENTS (2, "Both parents / legal guardians are incarcerated"),
	ONLY_PARENT (3, "The only parent / legal guardian is incarcerated"),
	NOT_COLLECTED (99, "Data not collected");
	
	private final Integer code;
	private final String description;

	ClientIncarceratedParentStatus(final Integer code, final String description) {
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
	private static final CodeLookup<ClientIncarceratedParentStatus> enhancer = new CodeLookup<ClientIncarceratedParentStatus>(values());	

	@JsonCreator
	public static ClientIncarceratedParentStatus valueByCode(Integer code) {
		ClientIncarceratedParentStatus value = enhancer.valueByCode(code); 
		return (value == null)?ClientIncarceratedParentStatus.NOT_COLLECTED:value;
	}
}