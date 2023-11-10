package com.sunknowledge.changehealthcare.pojo.elligibility;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "serviceTypeCodes", "referenceIdentificationQualifier", "priorAuthorizationOrReferralNumber",
		"industryCode", "procedureModifiers", "diagnosisCodePointer", "beginningDateOfService", "endDateOfService",
		"dateOfService", "productOrServiceIDQualifier", "procedureCode" })
@Generated("jsonschema2pojo")
public class Encounter {

	@JsonProperty("serviceTypeCodes")
	private List<String> serviceTypeCodes = null;
	@JsonProperty("referenceIdentificationQualifier")
	private String referenceIdentificationQualifier;
	@JsonProperty("priorAuthorizationOrReferralNumber")
	private String priorAuthorizationOrReferralNumber;
	@JsonProperty("industryCode")
	private String industryCode;
	@JsonProperty("procedureModifiers")
	private String procedureModifiers;
	@JsonProperty("diagnosisCodePointer")
	private String diagnosisCodePointer;
	@JsonProperty("beginningDateOfService")
	private String beginningDateOfService;
	@JsonProperty("endDateOfService")
	private String endDateOfService;
	@JsonProperty("dateOfService")
	private String dateOfService;
	@JsonProperty("productOrServiceIDQualifier")
	private String productOrServiceIDQualifier;
	@JsonProperty("procedureCode")
	private String procedureCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("serviceTypeCodes")
	public List<String> getServiceTypeCodes() {
		return serviceTypeCodes;
	}

	@JsonProperty("serviceTypeCodes")
	public void setServiceTypeCodes(List<String> serviceTypeCodes) {
		this.serviceTypeCodes = serviceTypeCodes;
	}

	@JsonProperty("referenceIdentificationQualifier")
	public String getReferenceIdentificationQualifier() {
		return referenceIdentificationQualifier;
	}

	@JsonProperty("referenceIdentificationQualifier")
	public void setReferenceIdentificationQualifier(String referenceIdentificationQualifier) {
		this.referenceIdentificationQualifier = referenceIdentificationQualifier;
	}

	@JsonProperty("priorAuthorizationOrReferralNumber")
	public String getPriorAuthorizationOrReferralNumber() {
		return priorAuthorizationOrReferralNumber;
	}

	@JsonProperty("priorAuthorizationOrReferralNumber")
	public void setPriorAuthorizationOrReferralNumber(String priorAuthorizationOrReferralNumber) {
		this.priorAuthorizationOrReferralNumber = priorAuthorizationOrReferralNumber;
	}

	@JsonProperty("industryCode")
	public String getIndustryCode() {
		return industryCode;
	}

	@JsonProperty("industryCode")
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	@JsonProperty("procedureModifiers")
	public String getProcedureModifiers() {
		return procedureModifiers;
	}

	@JsonProperty("procedureModifiers")
	public void setProcedureModifiers(String procedureModifiers) {
		this.procedureModifiers = procedureModifiers;
	}

	@JsonProperty("diagnosisCodePointer")
	public String getDiagnosisCodePointer() {
		return diagnosisCodePointer;
	}

	@JsonProperty("diagnosisCodePointer")
	public void setDiagnosisCodePointer(String diagnosisCodePointer) {
		this.diagnosisCodePointer = diagnosisCodePointer;
	}

	@JsonProperty("beginningDateOfService")
	public String getBeginningDateOfService() {
		return beginningDateOfService;
	}

	@JsonProperty("beginningDateOfService")
	public void setBeginningDateOfService(String beginningDateOfService) {
		this.beginningDateOfService = beginningDateOfService;
	}

	@JsonProperty("endDateOfService")
	public String getEndDateOfService() {
		return endDateOfService;
	}

	@JsonProperty("endDateOfService")
	public void setEndDateOfService(String endDateOfService) {
		this.endDateOfService = endDateOfService;
	}

	@JsonProperty("dateOfService")
	public String getDateOfService() {
		return dateOfService;
	}

	@JsonProperty("dateOfService")
	public void setDateOfService(String dateOfService) {
		this.dateOfService = dateOfService;
	}

	@JsonProperty("productOrServiceIDQualifier")
	public String getProductOrServiceIDQualifier() {
		return productOrServiceIDQualifier;
	}

	@JsonProperty("productOrServiceIDQualifier")
	public void setProductOrServiceIDQualifier(String productOrServiceIDQualifier) {
		this.productOrServiceIDQualifier = productOrServiceIDQualifier;
	}

	@JsonProperty("procedureCode")
	public String getProcedureCode() {
		return procedureCode;
	}

	@JsonProperty("procedureCode")
	public void setProcedureCode(String procedureCode) {
		this.procedureCode = procedureCode;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}