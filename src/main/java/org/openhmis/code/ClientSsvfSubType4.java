package org.openhmis.code;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.openhmis.code.serialization.CodeSerializer;
import org.openhmis.code.serialization.CodeLookup;

// Codes for Data Standard: SSVFSubType4 (2014, 4.14.D4)
// http://www.hudhdx.info/Resources/Vendors/4_0/HMISCSVSpecifications4_0FINAL.pdf

@JsonSerialize(using = CodeSerializer.class)
public enum ClientSsvfSubType4 implements BaseCode {
	HEALTH_CARE (1, "Health care services"),
	DAILY_LIVING (2, "Daily living services"),
	FINANCIAL_PLANNING (3, "Personal financial planning services"),
	TRANSPORTATION (4, "Transportation services"),
	INCOME_SUPPORT (5, "Income support services"),
	FIDUCIARY (6, "Fiduciary and representative payee services"),
	CHILD_SUPPORT (7, "Legal services - child support"),
	EVICTION_PREVENTION (8, "Legal services - eviction prevention"),
	OUTSTANDING_FINES (9, "Legal services - outstanding fines and penalties"),
	DRIVERS_LICENSE (10, "Legal services - restore / acquire driver’s license"),
	LEGAL_OTHER (11, "Legal services - other"),
	CHILD_CARE (12, "Child care"),
	HOUSING_COUNSELING (13, "Housing counseling"),
	NOT_COLLECTED (99, "Data not collected");
	
	private final Integer code;
	private final String description;

	ClientSsvfSubType4(final Integer code, final String description) {
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
	private static final CodeLookup<ClientSsvfSubType4> enhancer = new CodeLookup<ClientSsvfSubType4>(values());	

	@JsonCreator
	public static ClientSsvfSubType4 valueByCode(Integer code) {
		ClientSsvfSubType4 value = enhancer.valueByCode(code); 
		return (value == null)?ClientSsvfSubType4.NOT_COLLECTED:value;
	}
}