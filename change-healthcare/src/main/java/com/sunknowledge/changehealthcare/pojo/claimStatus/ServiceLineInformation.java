package com.sunknowledge.changehealthcare.pojo.claimStatus;

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
@JsonPropertyOrder({ "procedureModifiers", "productOrServiceIDQualifier", "procedureCode", "lineItemChargeAmount",
		"revenueCode", "unitsOfServiceCount", "lineItemControlNumber", "serviceLineDate", "serviceLineEndDate" })
@Generated("jsonschema2pojo")
public class ServiceLineInformation {

	@JsonProperty("procedureModifiers")
	private List<String> procedureModifiers = null;
	@JsonProperty("productOrServiceIDQualifier")
	private String productOrServiceIDQualifier;
	@JsonProperty("procedureCode")
	private String procedureCode;
	@JsonProperty("lineItemChargeAmount")
	private String lineItemChargeAmount;
	@JsonProperty("revenueCode")
	private String revenueCode;
	@JsonProperty("unitsOfServiceCount")
	private String unitsOfServiceCount;
	@JsonProperty("lineItemControlNumber")
	private String lineItemControlNumber;
	@JsonProperty("serviceLineDate")
	private String serviceLineDate;
	@JsonProperty("serviceLineEndDate")
	private String serviceLineEndDate;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("procedureModifiers")
	public List<String> getProcedureModifiers() {
		return procedureModifiers;
	}

	@JsonProperty("procedureModifiers")
	public void setProcedureModifiers(List<String> procedureModifiers) {
		this.procedureModifiers = procedureModifiers;
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

	@JsonProperty("lineItemChargeAmount")
	public String getLineItemChargeAmount() {
		return lineItemChargeAmount;
	}

	@JsonProperty("lineItemChargeAmount")
	public void setLineItemChargeAmount(String lineItemChargeAmount) {
		this.lineItemChargeAmount = lineItemChargeAmount;
	}

	@JsonProperty("revenueCode")
	public String getRevenueCode() {
		return revenueCode;
	}

	@JsonProperty("revenueCode")
	public void setRevenueCode(String revenueCode) {
		this.revenueCode = revenueCode;
	}

	@JsonProperty("unitsOfServiceCount")
	public String getUnitsOfServiceCount() {
		return unitsOfServiceCount;
	}

	@JsonProperty("unitsOfServiceCount")
	public void setUnitsOfServiceCount(String unitsOfServiceCount) {
		this.unitsOfServiceCount = unitsOfServiceCount;
	}

	@JsonProperty("lineItemControlNumber")
	public String getLineItemControlNumber() {
		return lineItemControlNumber;
	}

	@JsonProperty("lineItemControlNumber")
	public void setLineItemControlNumber(String lineItemControlNumber) {
		this.lineItemControlNumber = lineItemControlNumber;
	}

	@JsonProperty("serviceLineDate")
	public String getServiceLineDate() {
		return serviceLineDate;
	}

	@JsonProperty("serviceLineDate")
	public void setServiceLineDate(String serviceLineDate) {
		this.serviceLineDate = serviceLineDate;
	}

	@JsonProperty("serviceLineEndDate")
	public String getServiceLineEndDate() {
		return serviceLineEndDate;
	}

	@JsonProperty("serviceLineEndDate")
	public void setServiceLineEndDate(String serviceLineEndDate) {
		this.serviceLineEndDate = serviceLineEndDate;
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