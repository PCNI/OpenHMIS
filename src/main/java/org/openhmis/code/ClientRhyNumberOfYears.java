package org.openhmis.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.openhmis.code.serialization.CodeSerializer;
import org.openhmis.code.serialization.CodeLookup;

// Codes for Data Standard: RHYNumberofYears (2014, 4.31.A)
// http://www.hudhdx.info/Resources/Vendors/4_0/HMISCSVSpecifications4_0FINAL.pdf

@JsonSerialize(using = CodeSerializer.class)
public enum ClientRhyNumberOfYears implements BaseCode {
	ONE_YEAR (1, "Less than one year"),
	TWO_YEARS (2, "1 to 2 years"),
	MORE_YEARS (3, "3 to 5 or more years"),
	NOT_COLLECTED (99, "Data not collected");
	
	private final Integer code;
	private final String description;

	ClientRhyNumberOfYears(final Integer code, final String description) {
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
	private static final CodeLookup<ClientRhyNumberOfYears> enhancer = new CodeLookup<ClientRhyNumberOfYears>(values());	

	@JsonCreator
	public static ClientRhyNumberOfYears valueByCode(Integer code) {
		ClientRhyNumberOfYears value = enhancer.valueByCode(code); 
		return (value == null)?ClientRhyNumberOfYears.NOT_COLLECTED:value;
	}
}