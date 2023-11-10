package com.sunknowledge.dme.rcm.domain.patientelligibility;

import java.util.LinkedHashMap;
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
@JsonPropertyOrder({ "code", "name", "coverageLevelCode", "coverageLevel", "serviceTypeCodes", "serviceTypes",
		"insuranceTypeCode", "insuranceType", "planCoverage", "benefitsDateInformation" })
@Generated("jsonschema2pojo")
public class BenefitsInformation {

	@JsonProperty("code")
	private String code;
	@JsonProperty("name")
	private String name;
	@JsonProperty("coverageLevelCode")
	private String coverageLevelCode;
	@JsonProperty("coverageLevel")
	private String coverageLevel;
	@JsonProperty("serviceTypeCodes")
	private List<String> serviceTypeCodes;
	@JsonProperty("serviceTypes")
	private List<String> serviceTypes;
	@JsonProperty("insuranceTypeCode")
	private String insuranceTypeCode;
	@JsonProperty("insuranceType")
	private String insuranceType;
	@JsonProperty("planCoverage")
	private String planCoverage;
	@JsonProperty("benefitsDateInformation")
	private BenefitsDateInformation benefitsDateInformation;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	@JsonProperty("coverageLevelCode")
	public String getCoverageLevelCode() {
		return coverageLevelCode;
	}

	@JsonProperty("coverageLevelCode")
	public void setCoverageLevelCode(String coverageLevelCode) {
		this.coverageLevelCode = coverageLevelCode;
	}

	@JsonProperty("coverageLevel")
	public String getCoverageLevel() {
		return coverageLevel;
	}

	@JsonProperty("coverageLevel")
	public void setCoverageLevel(String coverageLevel) {
		this.coverageLevel = coverageLevel;
	}

	@JsonProperty("serviceTypeCodes")
	public List<String> getServiceTypeCodes() {
		return serviceTypeCodes;
	}

	@JsonProperty("serviceTypeCodes")
	public void setServiceTypeCodes(List<String> serviceTypeCodes) {
		this.serviceTypeCodes = serviceTypeCodes;
	}

	@JsonProperty("serviceTypes")
	public List<String> getServiceTypes() {
		return serviceTypes;
	}

	@JsonProperty("serviceTypes")
	public void setServiceTypes(List<String> serviceTypes) {
		this.serviceTypes = serviceTypes;
	}

	@JsonProperty("insuranceTypeCode")
	public String getInsuranceTypeCode() {
		return insuranceTypeCode;
	}

	@JsonProperty("insuranceTypeCode")
	public void setInsuranceTypeCode(String insuranceTypeCode) {
		this.insuranceTypeCode = insuranceTypeCode;
	}

	@JsonProperty("insuranceType")
	public String getInsuranceType() {
		return insuranceType;
	}

	@JsonProperty("insuranceType")
	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	@JsonProperty("planCoverage")
	public String getPlanCoverage() {
		return planCoverage;
	}

	@JsonProperty("planCoverage")
	public void setPlanCoverage(String planCoverage) {
		this.planCoverage = planCoverage;
	}

	@JsonProperty("benefitsDateInformation")
	public BenefitsDateInformation getBenefitsDateInformation() {
		return benefitsDateInformation;
	}

	@JsonProperty("benefitsDateInformation")
	public void setBenefitsDateInformation(BenefitsDateInformation benefitsDateInformation) {
		this.benefitsDateInformation = benefitsDateInformation;
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