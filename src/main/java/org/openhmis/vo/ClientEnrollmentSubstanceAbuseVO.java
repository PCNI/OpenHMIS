package org.openhmis.vo;

import java.util.Date;

import org.openhmis.code.ClientDisabilityResponse;
import org.openhmis.code.ClientPathHowConfirmed;
import org.openhmis.code.YesNo;
import org.openhmis.code.YesNoReason;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientEnrollmentSubstanceAbuseVO {
	private Long substanceAbuseId;
	private Long enrollmentId;

	// Program Specific Data Standards: Substance Abuse (2014, 4.10)
	private Date informationDate;
	private ClientDisabilityResponse response;
	private YesNoReason indefiniteAndImpairs;
	private YesNo documentationOnFile;
	private YesNoReason receivingServices;
	private ClientPathHowConfirmed pathHowConfirmed;

	public ClientEnrollmentSubstanceAbuseVO() {}

	@JsonProperty
	public Long getSubstanceAbuseId() {
		return substanceAbuseId;
	}

	@JsonProperty
	public void setSubstanceAbuseId(Long substanceAbuseId) {
		this.substanceAbuseId = substanceAbuseId;
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
	public ClientDisabilityResponse getResponse() {
		return response;
	}

	@JsonProperty
	public void setResponse(ClientDisabilityResponse response) {
		this.response = response;
	}

	@JsonProperty
	public YesNoReason getIndefiniteAndImpairs() {
		return indefiniteAndImpairs;
	}

	@JsonProperty
	public void setIndefiniteAndImpairs(YesNoReason indefiniteAndImpairs) {
		this.indefiniteAndImpairs = indefiniteAndImpairs;
	}

	@JsonProperty
	public YesNo getDocumentationOnFile() {
		return documentationOnFile;
	}

	@JsonProperty
	public void setDocumentationOnFile(YesNo documentationOnFile) {
		this.documentationOnFile = documentationOnFile;
	}

	@JsonProperty
	public YesNoReason getReceivingServices() {
		return receivingServices;
	}

	@JsonProperty
	public void setReceivingServices(YesNoReason receivingServices) {
		this.receivingServices = receivingServices;
	}

	@JsonProperty
	public ClientPathHowConfirmed getPathHowConfirmed() {
		return pathHowConfirmed;
	}

	@JsonProperty
	public void setPathHowConfirmed(ClientPathHowConfirmed pathHowConfirmed) {
		this.pathHowConfirmed = pathHowConfirmed;
	}
}

