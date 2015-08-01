package org.openhmis.vo;

import java.util.Date;
import java.util.List;

import org.openhmis.code.ClientPathHowConfirmed;
import org.openhmis.code.ClientPathSmiInformation;
import org.openhmis.code.YesNo;
import org.openhmis.code.YesNoReason;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientEnrollmentMentalHealthProblemVO {
	private Long mentalHealthProblemId;
	private Long enrollmentId;

	// Program Specific Data Standards: Mental Health Problem (2014, 4.9)
	private Date informationDate;
	private YesNoReason response;
	private YesNoReason indefiniteAndImpairs;
	private YesNo documentationOnFile;
	private YesNoReason receivingServices;
	private ClientPathHowConfirmed pathHowConfirmed;
	private ClientPathSmiInformation pathSmiInformation;

	public ClientEnrollmentMentalHealthProblemVO() {}

	@JsonProperty
	public Long getMentalHealthProblemId() {
		return mentalHealthProblemId;
	}

	@JsonProperty
	public void setMentalHealthProblemId(Long mentalHealthProblemId) {
		this.mentalHealthProblemId = mentalHealthProblemId;
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
	public YesNoReason getResponse() {
		return response;
	}

	@JsonProperty
	public void setResponse(YesNoReason response) {
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

	@JsonProperty
	public ClientPathSmiInformation getPathSmiInformation() {
		return pathSmiInformation;
	}

	@JsonProperty
	public void setPathSmiInformation(ClientPathSmiInformation pathSmiInformation) {
		this.pathSmiInformation = pathSmiInformation;
	}
	
	
}

