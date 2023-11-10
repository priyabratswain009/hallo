package com.sunknowledge.dme.rcm.domain.abn;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "abnDeliveryId", "abnDeliveryDataId", "abnDocumentName", "scheduleDeliveryDateTime",
		"actualDeliveryDateTime", "deliveryAgentId", "deliveryAgentName", "abnDeliveryStatus",
		"abnDeliveryDocumentAckStatus", "abnDeliveryDocPatientReplyStatus", "abnDeliveryDocPatientReplyDateTime",
		"abnResponseJsonData", "status", "createdById", "createdByName", "createdDate", "updatedById", "updatedByName",
		"updatedDate", "abnDeliveryUuid", "deliveryAbnDataId", "salesOrderId", "patientId", "primaryInsuranceName",
		"primaryInsurancePolicyNumber", "patientFirstName", "patientMiddleName", "patientLastName",
		"salesOrderDetailsAbnPrintDate", "salesOrderDetailsAbnItemName", "ProcCode", "ChargeAmount",
		"salesOrderDetailsPatientAbnResponse", "salesOrderDetailsAbnDeliveryStatus",
		"salesOrderDetailsAbnPatientSignatureStatus", "salesOrderDetailsAbnStatus", "salesOrderDetailsAbnReason",
		"salesOrderDetailsAbnModifier1", "salesOrderDetailsAbnModifier2", "branchName", "branchId", "patientIdNo",
		"deliveryAbnDataUuid", "equipmentt", "notifier", "currentDate", "signatureBy", "relationship", "reason",
		"paymentOption", "formName", "patientSignature", "deliveryAgentSignature" })
@Generated("jsonschema2pojo")
public class AbnRequestandResponseData {

	@JsonProperty("abnDeliveryId")
	private Long abnDeliveryId;
	@JsonProperty("abnDeliveryDataId")
	private Long abnDeliveryDataId;
	@JsonProperty("abnDocumentName")
	private String abnDocumentName;
	@JsonProperty("scheduleDeliveryDateTime")
	private LocalDate scheduleDeliveryDateTime;
	@JsonProperty("actualDeliveryDateTime")
	private LocalDate actualDeliveryDateTime;
	@JsonProperty("deliveryAgentId")
	private Long deliveryAgentId;
	@JsonProperty("deliveryAgentName")
	private String deliveryAgentName;
	@JsonProperty("abnDeliveryStatus")
	private String abnDeliveryStatus;
	@JsonProperty("abnDeliveryDocumentAckStatus")
	private String abnDeliveryDocumentAckStatus;
	@JsonProperty("abnDeliveryDocPatientReplyStatus")
	private String abnDeliveryDocPatientReplyStatus;
	@JsonProperty("abnDeliveryDocPatientReplyDateTime")
	private LocalDate abnDeliveryDocPatientReplyDateTime;
	@JsonProperty("abnResponseJsonData")
	private String abnResponseJsonData;
	@JsonProperty("status")
	private String status;
	@JsonProperty("createdById")
	private Long createdById;
	@JsonProperty("createdByName")
	private String createdByName;
	@JsonProperty("createdDate")
	private LocalDate createdDate;
	@JsonProperty("updatedById")
	private Long updatedById;
	@JsonProperty("updatedByName")
	private String updatedByName;
	@JsonProperty("updatedDate")
	private LocalDate updatedDate;
	@JsonProperty("abnDeliveryUuid")
	private UUID abnDeliveryUuid;
	@JsonProperty("deliveryAbnDataId")
	private Long deliveryAbnDataId;
	@JsonProperty("salesOrderId")
	private Long salesOrderId;
	@JsonProperty("patientId")
	private Long patientId;
	@JsonProperty("primaryInsuranceName")
	private String primaryInsuranceName;
	@JsonProperty("primaryInsurancePolicyNumber")
	private String primaryInsurancePolicyNumber;
	@JsonProperty("patientFirstName")
	private String patientFirstName;
	@JsonProperty("patientMiddleName")
	private String patientMiddleName;
	@JsonProperty("patientLastName")
	private String patientLastName;
	@JsonProperty("salesOrderDetailsAbnPrintDate")
	private LocalDate salesOrderDetailsAbnPrintDate;
	@JsonProperty("salesOrderDetailsAbnItemName")
	private String salesOrderDetailsAbnItemName;
	@JsonProperty("ProcCode")
	private String procCode;
	@JsonProperty("ChargeAmount")
	private String chargeAmount;
	@JsonProperty("salesOrderDetailsPatientAbnResponse")
	private String salesOrderDetailsPatientAbnResponse;
	@JsonProperty("salesOrderDetailsAbnDeliveryStatus")
	private String salesOrderDetailsAbnDeliveryStatus;
	@JsonProperty("salesOrderDetailsAbnPatientSignatureStatus")
	private String salesOrderDetailsAbnPatientSignatureStatus;
	@JsonProperty("salesOrderDetailsAbnStatus")
	private String salesOrderDetailsAbnStatus;
	@JsonProperty("salesOrderDetailsAbnReason")
	private String salesOrderDetailsAbnReason;
	@JsonProperty("salesOrderDetailsAbnModifier1")
	private String salesOrderDetailsAbnModifier1;
	@JsonProperty("salesOrderDetailsAbnModifier2")
	private String salesOrderDetailsAbnModifier2;
	@JsonProperty("branchName")
	private String branchName;
	@JsonProperty("branchId")
	private String branchId;
	@JsonProperty("patientIdNo")
	private String patientIdNo;
	@JsonProperty("deliveryAbnDataUuid")
	private UUID deliveryAbnDataUuid;
	@JsonProperty("equipment")
	private List<Equipment> equipment;
	@JsonProperty("notifier")
	private String notifier;
	@JsonProperty("currentDate")
	private String currentDate;
	@JsonProperty("signatureBy")
	private String signatureBy;
	@JsonProperty("relationship")
	private String relationship;
	@JsonProperty("reason")
	private String reason;
	@JsonProperty("formName")
	private String formName;
	@JsonProperty("patientSignature")
	private String patientSignature;
	@JsonProperty("deliveryAgentSignature")
	private String deliveryAgentSignature;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("abnDeliveryId")
	public Long getAbnDeliveryId() {
		return abnDeliveryId;
	}

	@JsonProperty("abnDeliveryId")
	public void setAbnDeliveryId(Long abnDeliveryId) {
		this.abnDeliveryId = abnDeliveryId;
	}

	@JsonProperty("abnDeliveryDataId")
	public Long getAbnDeliveryDataId() {
		return abnDeliveryDataId;
	}

	@JsonProperty("abnDeliveryDataId")
	public void setAbnDeliveryDataId(Long abnDeliveryDataId) {
		this.abnDeliveryDataId = abnDeliveryDataId;
	}

	@JsonProperty("abnDocumentName")
	public String getAbnDocumentName() {
		return abnDocumentName;
	}

	@JsonProperty("abnDocumentName")
	public void setAbnDocumentName(String abnDocumentName) {
		this.abnDocumentName = abnDocumentName;
	}

	@JsonProperty("scheduleDeliveryDateTime")
	public LocalDate getScheduleDeliveryDateTime() {
		return scheduleDeliveryDateTime;
	}

	@JsonProperty("scheduleDeliveryDateTime")
	public void setScheduleDeliveryDateTime(LocalDate scheduleDeliveryDateTime) {
		this.scheduleDeliveryDateTime = scheduleDeliveryDateTime;
	}

	@JsonProperty("actualDeliveryDateTime")
	public LocalDate getActualDeliveryDateTime() {
		return actualDeliveryDateTime;
	}

	@JsonProperty("actualDeliveryDateTime")
	public void setActualDeliveryDateTime(LocalDate actualDeliveryDateTime) {
		this.actualDeliveryDateTime = actualDeliveryDateTime;
	}

	@JsonProperty("deliveryAgentId")
	public Long getDeliveryAgentId() {
		return deliveryAgentId;
	}

	@JsonProperty("deliveryAgentId")
	public void setDeliveryAgentId(Long deliveryAgentId) {
		this.deliveryAgentId = deliveryAgentId;
	}

	@JsonProperty("deliveryAgentName")
	public String getDeliveryAgentName() {
		return deliveryAgentName;
	}

	@JsonProperty("deliveryAgentName")
	public void setDeliveryAgentName(String deliveryAgentName) {
		this.deliveryAgentName = deliveryAgentName;
	}

	@JsonProperty("abnDeliveryStatus")
	public String getAbnDeliveryStatus() {
		return abnDeliveryStatus;
	}

	@JsonProperty("abnDeliveryStatus")
	public void setAbnDeliveryStatus(String abnDeliveryStatus) {
		this.abnDeliveryStatus = abnDeliveryStatus;
	}

	@JsonProperty("abnDeliveryDocumentAckStatus")
	public String getAbnDeliveryDocumentAckStatus() {
		return abnDeliveryDocumentAckStatus;
	}

	@JsonProperty("abnDeliveryDocumentAckStatus")
	public void setAbnDeliveryDocumentAckStatus(String abnDeliveryDocumentAckStatus) {
		this.abnDeliveryDocumentAckStatus = abnDeliveryDocumentAckStatus;
	}

	@JsonProperty("abnDeliveryDocPatientReplyStatus")
	public String getAbnDeliveryDocPatientReplyStatus() {
		return abnDeliveryDocPatientReplyStatus;
	}

	@JsonProperty("abnDeliveryDocPatientReplyStatus")
	public void setAbnDeliveryDocPatientReplyStatus(String abnDeliveryDocPatientReplyStatus) {
		this.abnDeliveryDocPatientReplyStatus = abnDeliveryDocPatientReplyStatus;
	}

	@JsonProperty("abnDeliveryDocPatientReplyDateTime")
	public LocalDate getAbnDeliveryDocPatientReplyDateTime() {
		return abnDeliveryDocPatientReplyDateTime;
	}

	@JsonProperty("abnDeliveryDocPatientReplyDateTime")
	public void setAbnDeliveryDocPatientReplyDateTime(LocalDate abnDeliveryDocPatientReplyDateTime) {
		this.abnDeliveryDocPatientReplyDateTime = abnDeliveryDocPatientReplyDateTime;
	}

	@JsonProperty("abnResponseJsonData")
	public String getAbnResponseJsonData() {
		return abnResponseJsonData;
	}

	@JsonProperty("abnResponseJsonData")
	public void setAbnResponseJsonData(String abnResponseJsonData) {
		this.abnResponseJsonData = abnResponseJsonData;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(String status) {
		this.status = status;
	}

	@JsonProperty("createdById")
	public Long getCreatedById() {
		return createdById;
	}

	@JsonProperty("createdById")
	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}

	@JsonProperty("createdByName")
	public String getCreatedByName() {
		return createdByName;
	}

	@JsonProperty("createdByName")
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}

	@JsonProperty("createdDate")
	public LocalDate getCreatedDate() {
		return createdDate;
	}

	@JsonProperty("createdDate")
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	@JsonProperty("updatedById")
	public Long getUpdatedById() {
		return updatedById;
	}

	@JsonProperty("updatedById")
	public void setUpdatedById(Long updatedById) {
		this.updatedById = updatedById;
	}

	@JsonProperty("updatedByName")
	public String getUpdatedByName() {
		return updatedByName;
	}

	@JsonProperty("updatedByName")
	public void setUpdatedByName(String updatedByName) {
		this.updatedByName = updatedByName;
	}

	@JsonProperty("updatedDate")
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	@JsonProperty("updatedDate")
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

	@JsonProperty("abnDeliveryUuid")
	public UUID getAbnDeliveryUuid() {
		return abnDeliveryUuid;
	}

	@JsonProperty("abnDeliveryUuid")
	public void setAbnDeliveryUuid(UUID abnDeliveryUuid) {
		this.abnDeliveryUuid = abnDeliveryUuid;
	}

	@JsonProperty("deliveryAbnDataId")
	public Long getDeliveryAbnDataId() {
		return deliveryAbnDataId;
	}

	@JsonProperty("deliveryAbnDataId")
	public void setDeliveryAbnDataId(Long deliveryAbnDataId) {
		this.deliveryAbnDataId = deliveryAbnDataId;
	}

	@JsonProperty("salesOrderId")
	public Long getSalesOrderId() {
		return salesOrderId;
	}

	@JsonProperty("salesOrderId")
	public void setSalesOrderId(Long salesOrderId) {
		this.salesOrderId = salesOrderId;
	}

	@JsonProperty("patientId")
	public Long getPatientId() {
		return patientId;
	}

	@JsonProperty("patientId")
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	@JsonProperty("primaryInsuranceName")
	public String getPrimaryInsuranceName() {
		return primaryInsuranceName;
	}

	@JsonProperty("primaryInsuranceName")
	public void setPrimaryInsuranceName(String primaryInsuranceName) {
		this.primaryInsuranceName = primaryInsuranceName;
	}

	@JsonProperty("primaryInsurancePolicyNumber")
	public String getPrimaryInsurancePolicyNumber() {
		return primaryInsurancePolicyNumber;
	}

	@JsonProperty("primaryInsurancePolicyNumber")
	public void setPrimaryInsurancePolicyNumber(String primaryInsurancePolicyNumber) {
		this.primaryInsurancePolicyNumber = primaryInsurancePolicyNumber;
	}

	@JsonProperty("patientFirstName")
	public String getPatientFirstName() {
		return patientFirstName;
	}

	@JsonProperty("patientFirstName")
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}

	@JsonProperty("patientMiddleName")
	public String getPatientMiddleName() {
		return patientMiddleName;
	}

	@JsonProperty("patientMiddleName")
	public void setPatientMiddleName(String patientMiddleName) {
		this.patientMiddleName = patientMiddleName;
	}

	@JsonProperty("patientLastName")
	public String getPatientLastName() {
		return patientLastName;
	}

	@JsonProperty("patientLastName")
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}

	@JsonProperty("salesOrderDetailsAbnPrintDate")
	public LocalDate getSalesOrderDetailsAbnPrintDate() {
		return salesOrderDetailsAbnPrintDate;
	}

	@JsonProperty("salesOrderDetailsAbnPrintDate")
	public void setSalesOrderDetailsAbnPrintDate(LocalDate salesOrderDetailsAbnPrintDate) {
		this.salesOrderDetailsAbnPrintDate = salesOrderDetailsAbnPrintDate;
	}

	@JsonProperty("salesOrderDetailsAbnItemName")
	public String getSalesOrderDetailsAbnItemName() {
		return salesOrderDetailsAbnItemName;
	}

	@JsonProperty("salesOrderDetailsAbnItemName")
	public void setSalesOrderDetailsAbnItemName(String salesOrderDetailsAbnItemName) {
		this.salesOrderDetailsAbnItemName = salesOrderDetailsAbnItemName;
	}

	@JsonProperty("ProcCode")
	public String getProcCode() {
		return procCode;
	}

	@JsonProperty("ProcCode")
	public void setProcCode(String procCode) {
		this.procCode = procCode;
	}

	@JsonProperty("ChargeAmount")
	public String getChargeAmount() {
		return chargeAmount;
	}

	@JsonProperty("ChargeAmount")
	public void setChargeAmount(String chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	@JsonProperty("salesOrderDetailsPatientAbnResponse")
	public String getSalesOrderDetailsPatientAbnResponse() {
		return salesOrderDetailsPatientAbnResponse;
	}

	@JsonProperty("salesOrderDetailsPatientAbnResponse")
	public void setSalesOrderDetailsPatientAbnResponse(String salesOrderDetailsPatientAbnResponse) {
		this.salesOrderDetailsPatientAbnResponse = salesOrderDetailsPatientAbnResponse;
	}

	@JsonProperty("salesOrderDetailsAbnDeliveryStatus")
	public String getSalesOrderDetailsAbnDeliveryStatus() {
		return salesOrderDetailsAbnDeliveryStatus;
	}

	@JsonProperty("salesOrderDetailsAbnDeliveryStatus")
	public void setSalesOrderDetailsAbnDeliveryStatus(String salesOrderDetailsAbnDeliveryStatus) {
		this.salesOrderDetailsAbnDeliveryStatus = salesOrderDetailsAbnDeliveryStatus;
	}

	@JsonProperty("salesOrderDetailsAbnPatientSignatureStatus")
	public String getSalesOrderDetailsAbnPatientSignatureStatus() {
		return salesOrderDetailsAbnPatientSignatureStatus;
	}

	@JsonProperty("salesOrderDetailsAbnPatientSignatureStatus")
	public void setSalesOrderDetailsAbnPatientSignatureStatus(String salesOrderDetailsAbnPatientSignatureStatus) {
		this.salesOrderDetailsAbnPatientSignatureStatus = salesOrderDetailsAbnPatientSignatureStatus;
	}

	@JsonProperty("salesOrderDetailsAbnStatus")
	public String getSalesOrderDetailsAbnStatus() {
		return salesOrderDetailsAbnStatus;
	}

	@JsonProperty("salesOrderDetailsAbnStatus")
	public void setSalesOrderDetailsAbnStatus(String salesOrderDetailsAbnStatus) {
		this.salesOrderDetailsAbnStatus = salesOrderDetailsAbnStatus;
	}

	@JsonProperty("salesOrderDetailsAbnReason")
	public String getSalesOrderDetailsAbnReason() {
		return salesOrderDetailsAbnReason;
	}

	@JsonProperty("salesOrderDetailsAbnReason")
	public void setSalesOrderDetailsAbnReason(String salesOrderDetailsAbnReason) {
		this.salesOrderDetailsAbnReason = salesOrderDetailsAbnReason;
	}

	@JsonProperty("salesOrderDetailsAbnModifier1")
	public String getSalesOrderDetailsAbnModifier1() {
		return salesOrderDetailsAbnModifier1;
	}

	@JsonProperty("salesOrderDetailsAbnModifier1")
	public void setSalesOrderDetailsAbnModifier1(String salesOrderDetailsAbnModifier1) {
		this.salesOrderDetailsAbnModifier1 = salesOrderDetailsAbnModifier1;
	}

	@JsonProperty("salesOrderDetailsAbnModifier2")
	public String getSalesOrderDetailsAbnModifier2() {
		return salesOrderDetailsAbnModifier2;
	}

	@JsonProperty("salesOrderDetailsAbnModifier2")
	public void setSalesOrderDetailsAbnModifier2(String salesOrderDetailsAbnModifier2) {
		this.salesOrderDetailsAbnModifier2 = salesOrderDetailsAbnModifier2;
	}

	@JsonProperty("branchName")
	public String getBranchName() {
		return branchName;
	}

	@JsonProperty("branchName")
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@JsonProperty("branchId")
	public String getBranchId() {
		return branchId;
	}

	@JsonProperty("branchId")
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	@JsonProperty("patientIdNo")
	public String getPatientIdNo() {
		return patientIdNo;
	}

	@JsonProperty("patientIdNo")
	public void setPatientIdNo(String patientIdNo) {
		this.patientIdNo = patientIdNo;
	}

	@JsonProperty("deliveryAbnDataUuid")
	public UUID getDeliveryAbnDataUuid() {
		return deliveryAbnDataUuid;
	}

	@JsonProperty("deliveryAbnDataUuid")
	public void setDeliveryAbnDataUuid(UUID deliveryAbnDataUuid) {
		this.deliveryAbnDataUuid = deliveryAbnDataUuid;
	}

	@JsonProperty("equipment")
	public List<Equipment> getEquipment() {
		return equipment;
	}

	@JsonProperty("equipment")
	public void setEquipment(List<Equipment> equipment) {
		this.equipment = equipment;
	}

	@JsonProperty("notifier")
	public String getNotifier() {
		return notifier;
	}

	@JsonProperty("notifier")
	public void setNotifier(String notifier) {
		this.notifier = notifier;
	}

	@JsonProperty("currentDate")
	public String getCurrentDate() {
		return currentDate;
	}

	@JsonProperty("currentDate")
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	@JsonProperty("signatureBy")
	public String getSignatureBy() {
		return signatureBy;
	}

	@JsonProperty("signatureBy")
	public void setSignatureBy(String signatureBy) {
		this.signatureBy = signatureBy;
	}

	@JsonProperty("relationship")
	public String getRelationship() {
		return relationship;
	}

	@JsonProperty("relationship")
	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	@JsonProperty("reason")
	public String getReason() {
		return reason;
	}

	@JsonProperty("reason")
	public void setReason(String reason) {
		this.reason = reason;
	}

	@JsonProperty("formName")
	public String getFormName() {
		return formName;
	}

	@JsonProperty("formName")
	public void setFormName(String formName) {
		this.formName = formName;
	}

	@JsonProperty("patientSignature")
	public String getPatientSignature() {
		return patientSignature;
	}

	@JsonProperty("patientSignature")
	public void setPatientSignature(String patientSignature) {
		this.patientSignature = patientSignature;
	}

	@JsonProperty("deliveryAgentSignature")
	public String getDeliveryAgentSignature() {
		return deliveryAgentSignature;
	}

	@JsonProperty("deliveryAgentSignature")
	public void setDeliveryAgentSignature(String deliveryAgentSignature) {
		this.deliveryAgentSignature = deliveryAgentSignature;
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