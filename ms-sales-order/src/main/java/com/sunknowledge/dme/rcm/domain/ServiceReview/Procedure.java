package com.sunknowledge.dme.rcm.domain.ServiceReview;

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
@JsonPropertyOrder({ "revenueValue", "revenueCode", "qualifier", "qualifierCode", "value", "code", "modifier1",
		"modifierCode1", "modifier2", "modifierCode2", "modifier3", "modifierCode3", "modifier4", "modifierCode4",
		"description", "quantity", "quantityqualifier", "quantityType", "quantityTypeCode", "fromDate", "toDate", "status", "statusCode",
		"statusReasons", "certificationIssueDate", "certificationEffectiveDate", "certificationExpirationDate",
		"certificationNumber", "traceNumbers", "notes" })
@Generated("jsonschema2pojo")
public class Procedure {

	@JsonProperty("revenueValue")
	private String revenueValue;
	@JsonProperty("revenueCode")
	private String revenueCode;
	@JsonProperty("qualifier")
	private String qualifier;
	@JsonProperty("qualifierCode")
	private String qualifierCode;
	@JsonProperty("value")
	private String value;
	@JsonProperty("code")
	private String code;
	@JsonProperty("modifier1")
	private String modifier1;
	@JsonProperty("modifierCode1")
	private String modifierCode1;
	@JsonProperty("modifier2")
	private String modifier2;
	@JsonProperty("modifierCode2")
	private String modifierCode2;
	@JsonProperty("modifier3")
	private String modifier3;
	@JsonProperty("modifierCode3")
	private String modifierCode3;
	@JsonProperty("modifier4")
	private String modifier4;
	@JsonProperty("modifierCode4")
	private String modifierCode4;
	@JsonProperty("description")
	private String description;
	@JsonProperty("quantity")
	private String quantity;
	@JsonProperty("quantityqualifier")
	private String quantityqualifier;
	@JsonProperty("quantityType")
	private String quantityType;
	@JsonProperty("quantityTypeCode")
	private String quantityTypeCode;
	@JsonProperty("fromDate")
	private String fromDate;
	@JsonProperty("toDate")
	private String toDate;
	@JsonProperty("status")
	private String status;
	@JsonProperty("statusCode")
	private String statusCode;
	@JsonProperty("statusReasons")
	private List<StatusReason> statusReasons;
	@JsonProperty("certificationIssueDate")
	private String certificationIssueDate;
	@JsonProperty("certificationEffectiveDate")
	private String certificationEffectiveDate;
	@JsonProperty("certificationExpirationDate")
	private String certificationExpirationDate;
	@JsonProperty("certificationNumber")
	private String certificationNumber;
	@JsonProperty("traceNumbers")
	private List<String> traceNumbers;
	@JsonProperty("notes")
	private List<Note> notes;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("revenueValue")
	public String getRevenueValue() {
		return revenueValue;
	}

	@JsonProperty("revenueValue")
	public void setRevenueValue(String revenueValue) {
		this.revenueValue = revenueValue;
	}

	public String getQuantityqualifier() {
		return quantityqualifier;
	}

	public void setQuantityqualifier(String quantityqualifier) {
		this.quantityqualifier = quantityqualifier;
	}

	@JsonProperty("revenueCode")
	public String getRevenueCode() {
		return revenueCode;
	}

	@JsonProperty("revenueCode")
	public void setRevenueCode(String revenueCode) {
		this.revenueCode = revenueCode;
	}

	@JsonProperty("qualifier")
	public String getQualifier() {
		return qualifier;
	}

	@JsonProperty("qualifier")
	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	@JsonProperty("qualifierCode")
	public String getQualifierCode() {
		return qualifierCode;
	}

	@JsonProperty("qualifierCode")
	public void setQualifierCode(String qualifierCode) {
		this.qualifierCode = qualifierCode;
	}

	@JsonProperty("value")
	public String getValue() {
		return value;
	}

	@JsonProperty("value")
	public void setValue(String value) {
		this.value = value;
	}

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	@JsonProperty("code")
	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("modifier1")
	public String getModifier1() {
		return modifier1;
	}

	@JsonProperty("modifier1")
	public void setModifier1(String modifier1) {
		this.modifier1 = modifier1;
	}

	@JsonProperty("modifierCode1")
	public String getModifierCode1() {
		return modifierCode1;
	}

	@JsonProperty("modifierCode1")
	public void setModifierCode1(String modifierCode1) {
		this.modifierCode1 = modifierCode1;
	}

	@JsonProperty("modifier2")
	public String getModifier2() {
		return modifier2;
	}

	@JsonProperty("modifier2")
	public void setModifier2(String modifier2) {
		this.modifier2 = modifier2;
	}

	@JsonProperty("modifierCode2")
	public String getModifierCode2() {
		return modifierCode2;
	}

	@JsonProperty("modifierCode2")
	public void setModifierCode2(String modifierCode2) {
		this.modifierCode2 = modifierCode2;
	}

	@JsonProperty("modifier3")
	public String getModifier3() {
		return modifier3;
	}

	@JsonProperty("modifier3")
	public void setModifier3(String modifier3) {
		this.modifier3 = modifier3;
	}

	@JsonProperty("modifierCode3")
	public String getModifierCode3() {
		return modifierCode3;
	}

	@JsonProperty("modifierCode3")
	public void setModifierCode3(String modifierCode3) {
		this.modifierCode3 = modifierCode3;
	}

	@JsonProperty("modifier4")
	public String getModifier4() {
		return modifier4;
	}

	@JsonProperty("modifier4")
	public void setModifier4(String modifier4) {
		this.modifier4 = modifier4;
	}

	@JsonProperty("modifierCode4")
	public String getModifierCode4() {
		return modifierCode4;
	}

	@JsonProperty("modifierCode4")
	public void setModifierCode4(String modifierCode4) {
		this.modifierCode4 = modifierCode4;
	}

	@JsonProperty("description")
	public String getDescription() {
		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {
		this.description = description;
	}

	@JsonProperty("quantity")
	public String getQuantity() {
		return quantity;
	}

	@JsonProperty("quantity")
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	@JsonProperty("quantityType")
	public String getQuantityType() {
		return quantityType;
	}

	@JsonProperty("quantityType")
	public void setQuantityType(String quantityType) {
		this.quantityType = quantityType;
	}

	@JsonProperty("quantityTypeCode")
	public String getQuantityTypeCode() {
		return quantityTypeCode;
	}

	@JsonProperty("quantityTypeCode")
	public void setQuantityTypeCode(String quantityTypeCode) {
		this.quantityTypeCode = quantityTypeCode;
	}

	@JsonProperty("fromDate")
	public String getFromDate() {
		return fromDate;
	}

	@JsonProperty("fromDate")
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	@JsonProperty("toDate")
	public String getToDate() {
		return toDate;
	}

	@JsonProperty("toDate")
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("statusCode")
	public String getStatusCode() {
		return statusCode;
	}

	@JsonProperty("statusCode")
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	@JsonProperty("statusReasons")
	public List<StatusReason> getStatusReasons() {
		return statusReasons;
	}

	@JsonProperty("statusReasons")
	public void setStatusReasons(List<StatusReason> statusReasons) {
		this.statusReasons = statusReasons;
	}

	@JsonProperty("certificationIssueDate")
	public String getCertificationIssueDate() {
		return certificationIssueDate;
	}

	@JsonProperty("certificationIssueDate")
	public void setCertificationIssueDate(String certificationIssueDate) {
		this.certificationIssueDate = certificationIssueDate;
	}

	@JsonProperty("certificationEffectiveDate")
	public String getCertificationEffectiveDate() {
		return certificationEffectiveDate;
	}

	@JsonProperty("certificationEffectiveDate")
	public void setCertificationEffectiveDate(String certificationEffectiveDate) {
		this.certificationEffectiveDate = certificationEffectiveDate;
	}

	@JsonProperty("certificationExpirationDate")
	public String getCertificationExpirationDate() {
		return certificationExpirationDate;
	}

	@JsonProperty("certificationExpirationDate")
	public void setCertificationExpirationDate(String certificationExpirationDate) {
		this.certificationExpirationDate = certificationExpirationDate;
	}

	@JsonProperty("certificationNumber")
	public String getCertificationNumber() {
		return certificationNumber;
	}

	@JsonProperty("certificationNumber")
	public void setCertificationNumber(String certificationNumber) {
		this.certificationNumber = certificationNumber;
	}

	@JsonProperty("traceNumbers")
	public List<String> getTraceNumbers() {
		return traceNumbers;
	}

	@JsonProperty("traceNumbers")
	public void setTraceNumbers(List<String> traceNumbers) {
		this.traceNumbers = traceNumbers;
	}

	@JsonProperty("notes")
	public List<Note> getNotes() {
		return notes;
	}

	@JsonProperty("notes")
	public void setNotes(List<Note> notes) {
		this.notes = notes;
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