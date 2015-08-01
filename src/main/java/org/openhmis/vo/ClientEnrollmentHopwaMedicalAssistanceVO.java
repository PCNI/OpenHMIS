package org.openhmis.vo;

import java.util.Date;
import java.util.List;

import org.openhmis.code.ClientNoAssistanceReason;
import org.openhmis.code.YesNoReason;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ClientEnrollmentHopwaMedicalAssistanceVO {
	private Long hopwaMedicalAssistanceId;
	private Long enrollmentId;

	// HOPWA Specific Data Standards: Medical Assistance (2014, 4.39)
	private Date informationDate;
	private YesNoReason hivAidsAssistance;
	private ClientNoAssistanceReason noHivAidsAssistanceReason;
	private YesNoReason adap;
	private ClientNoAssistanceReason noAdapReason;

	public ClientEnrollmentHopwaMedicalAssistanceVO() {}

	@JsonProperty
	public Long getHopwaMedicalAssistanceId() {
		return hopwaMedicalAssistanceId;
	}

	@JsonProperty
	public void setHopwaMedicalAssistanceId(Long hopwaMedicalAssistanceId) {
		this.hopwaMedicalAssistanceId = hopwaMedicalAssistanceId;
	}

	@JsonProperty
	public Long getEnrollmentId() {
		return enrollmentId;
	}

	@JsonProperty
	public void setEnrollmentId(Long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}

	@JsonProperty
	public Date getInformationDate() {
		return informationDate;
	}

	@JsonProperty
	public void setInformationDate(Date informationDate) {
		this.informationDate = informationDate;
	}

	@JsonProperty
	public YesNoReason getHivAidsAssistance() {
		return hivAidsAssistance;
	}

	@JsonProperty
	public void setHivAidsAssistance(YesNoReason hivAidsAssistance) {
		this.hivAidsAssistance = hivAidsAssistance;
	}

	@JsonProperty
	public ClientNoAssistanceReason getNoHivAidsAssistanceReason() {
		return noHivAidsAssistanceReason;
	}

	@JsonProperty
	public void setNoHivAidsAssistanceReason(
			ClientNoAssistanceReason noHivAidsAssistanceReason) {
		this.noHivAidsAssistanceReason = noHivAidsAssistanceReason;
	}

	@JsonProperty
	public YesNoReason getAdap() {
		return adap;
	}

	@JsonProperty
	public void setAdap(YesNoReason adap) {
		this.adap = adap;
	}

	@JsonProperty
	public ClientNoAssistanceReason getNoAdapReason() {
		return noAdapReason;
	}

	@JsonProperty
	public void setNoAdapReason(ClientNoAssistanceReason noAdapReason) {
		this.noAdapReason = noAdapReason;
	}
	
	
}

