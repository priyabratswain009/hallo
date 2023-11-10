package com.sunknowledge.dme.rcm.domain.elligibility;

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
@JsonPropertyOrder({ "code", "name", "serviceTypeCodes", "serviceTypes", "benefitsAdditionalInformation",
		"benefitsDateInformation", "coverageLevelCode", "coverageLevel", "planCoverage", "insuranceType",
		"insuranceTypeCode", "timeQualifierCode", "timeQualifier", "benefitPercent", "inPlanNetworkIndicatorCode",
		"inPlanNetworkIndicator", "benefitAmount" })
@Generated("jsonschema2pojo")
public class BenefitsInformation {

	@JsonProperty("code")
	private String code;
	@JsonProperty("planCoverage")
	private String planCoverage;
	@JsonProperty("insuranceType")
	private String insuranceType;
	@JsonProperty("insuranceTypeCode")
	private String insuranceTypeCode;
	@JsonProperty("name")
	private String name;
	@JsonProperty("serviceTypeCodes")
	private List<String> serviceTypeCodes = null;
	@JsonProperty("serviceTypes")
	private List<String> serviceTypes = null;
	@JsonProperty("benefitsAdditionalInformation")
	private BenefitsAdditionalInformation benefitsAdditionalInformation;
	@JsonProperty("benefitsDateInformation")
	private BenefitsDateInformation benefitsDateInformation;
	@JsonProperty("coverageLevelCode")
	private String coverageLevelCode;
	@JsonProperty("coverageLevel")
	private String coverageLevel;
	@JsonProperty("timeQualifierCode")
	private String timeQualifierCode;
	@JsonProperty("timeQualifier")
	private String timeQualifier;
	@JsonProperty("benefitPercent")
	private String benefitPercent;
	@JsonProperty("inPlanNetworkIndicatorCode")
	private String inPlanNetworkIndicatorCode;
	@JsonProperty("inPlanNetworkIndicator")
	private String inPlanNetworkIndicator;
	@JsonProperty("benefitAmount")
	private String benefitAmount;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

	public String getPlanCoverage() {
		return planCoverage;
	}

	public void setPlanCoverage(String planCoverage) {
		this.planCoverage = planCoverage;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

	public String getInsuranceTypeCode() {
		return insuranceTypeCode;
	}

	public void setInsuranceTypeCode(String insuranceTypeCode) {
		this.insuranceTypeCode = insuranceTypeCode;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
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

	@JsonProperty("benefitsAdditionalInformation")
	public BenefitsAdditionalInformation getBenefitsAdditionalInformation() {
		return benefitsAdditionalInformation;
	}

	@JsonProperty("benefitsAdditionalInformation")
	public void setBenefitsAdditionalInformation(BenefitsAdditionalInformation benefitsAdditionalInformation) {
		this.benefitsAdditionalInformation = benefitsAdditionalInformation;
	}

	@JsonProperty("benefitsDateInformation")
	public BenefitsDateInformation getBenefitsDateInformation() {
		return benefitsDateInformation;
	}

	@JsonProperty("benefitsDateInformation")
	public void setBenefitsDateInformation(BenefitsDateInformation benefitsDateInformation) {
		this.benefitsDateInformation = benefitsDateInformation;
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

	@JsonProperty("timeQualifierCode")
	public String getTimeQualifierCode() {
		return timeQualifierCode;
	}

	@JsonProperty("timeQualifierCode")
	public void setTimeQualifierCode(String timeQualifierCode) {
		this.timeQualifierCode = timeQualifierCode;
	}

	@JsonProperty("timeQualifier")
	public String getTimeQualifier() {
		return timeQualifier;
	}

	@JsonProperty("timeQualifier")
	public void setTimeQualifier(String timeQualifier) {
		this.timeQualifier = timeQualifier;
	}

	@JsonProperty("benefitPercent")
	public String getBenefitPercent() {
		return benefitPercent;
	}

	@JsonProperty("benefitPercent")
	public void setBenefitPercent(String benefitPercent) {
		this.benefitPercent = benefitPercent;
	}

	@JsonProperty("inPlanNetworkIndicatorCode")
	public String getInPlanNetworkIndicatorCode() {
		return inPlanNetworkIndicatorCode;
	}

	@JsonProperty("inPlanNetworkIndicatorCode")
	public void setInPlanNetworkIndicatorCode(String inPlanNetworkIndicatorCode) {
		this.inPlanNetworkIndicatorCode = inPlanNetworkIndicatorCode;
	}

	@JsonProperty("inPlanNetworkIndicator")
	public String getInPlanNetworkIndicator() {
		return inPlanNetworkIndicator;
	}

	@JsonProperty("inPlanNetworkIndicator")
	public void setInPlanNetworkIndicator(String inPlanNetworkIndicator) {
		this.inPlanNetworkIndicator = inPlanNetworkIndicator;
	}

	@JsonProperty("benefitAmount")
	public String getBenefitAmount() {
		return benefitAmount;
	}

	@JsonProperty("benefitAmount")
	public void setBenefitAmount(String benefitAmount) {
		this.benefitAmount = benefitAmount;
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