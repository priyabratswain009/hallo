package com.sunknowledge.dme.rcm.domain.pickupExchange;

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
@JsonPropertyOrder({ "pickupExchangeId", "pickupExchangeNo", "pickupExchangeType", "soId", "soNo", "branchId",
		"branchName", "branchAddress", "currentDate", "documentId", "patientSignature",
		"technicianSignature", "inventoryLocationId", "inventoryLocationName", "patientId", "patientIdNo",
		"patientFirstName", "patientMiddleName", "patientLastName", "patientContact1", "patientContact2",
		"patientBillingAddressLine1", "patientBillingAddressLine2", "patientBillingAddressState",
		"patientBillingAddressCity", "patientBillingAddressZip", "patientDeliveyAddressLine1",
		"patientDeliveyAddressLine2", "patientDeliveyAddressState", "patientDeliveyAddressCity",
		"patientDeliveyAddressZip", "pickupExchangeScheduleDateTime", "pickupExchangeActualDateTime",
		"pickupExchangeReason", "pickupExchangeRequest", "pickupExchangeNote", "pickupExchangeAgentIdNo",
		"pickupExchangeAgentName", "pickupExchangeDocumentId", "pickupExchangeDocumentNo", "pickupExchangeDocumentName",
		"pickupExchangeStatus", "pickupExchangeComments", "isPatientSigned", "relationshipWithPatient",
		"patientSignedDateTime", "isAgentSigned", "lastBillingDate", "dateOfDeath", "pickupExchangeSupportingDocument1",
		"pickupExchangeSupportingDocument2", "status", "createdById", "createdByName", "createdDate", "updatedById",
		"updatedByName", "updatedDate", "pickupExchangeUuid", "pickupExchangeItems", "patientNotsignedReason" })
@Generated("jsonschema2pojo")
public class PickupExchangeReqandResp {

	@JsonProperty("pickupExchangeId")
	private Long pickupExchangeId;
	@JsonProperty("pickupExchangeNo")
	private String pickupExchangeNo;
	@JsonProperty("pickupExchangeType")
	private String pickupExchangeType;
	@JsonProperty("soId")
	private Long soId;
	@JsonProperty("soNo")
	private String soNo;
	@JsonProperty("branchId")
	private Long branchId;
	@JsonProperty("branchName")
	private String branchName;
	@JsonProperty("branchAddress")
	private String branchAddress;
	@JsonProperty("currentDate")
	private String currentDate;
	@JsonProperty("documentId")
	private String documentId;
	@JsonProperty("patientSignature")
	private String patientSignature;
	@JsonProperty("technicianSignature")
	private String technicianSignature;
	@JsonProperty("inventoryLocationId")
	private Long inventoryLocationId;
	@JsonProperty("inventoryLocationName")
	private String inventoryLocationName;
	@JsonProperty("patientId")
	private Long patientId;
	@JsonProperty("patientIdNo")
	private String patientIdNo;
	@JsonProperty("patientFirstName")
	private String patientFirstName;
	@JsonProperty("patientMiddleName")
	private String patientMiddleName;
	@JsonProperty("patientLastName")
	private String patientLastName;
	@JsonProperty("patientContact1")
	private String patientContact1;
	@JsonProperty("patientContact2")
	private String patientContact2;
	@JsonProperty("patientBillingAddressLine1")
	private String patientBillingAddressLine1;
	@JsonProperty("patientBillingAddressLine2")
	private String patientBillingAddressLine2;
	@JsonProperty("patientBillingAddressState")
	private String patientBillingAddressState;
	@JsonProperty("patientBillingAddressCity")
	private String patientBillingAddressCity;
	@JsonProperty("patientBillingAddressZip")
	private String patientBillingAddressZip;
	@JsonProperty("patientDeliveyAddressLine1")
	private String patientDeliveyAddressLine1;
	@JsonProperty("patientDeliveyAddressLine2")
	private String patientDeliveyAddressLine2;
	@JsonProperty("patientDeliveyAddressState")
	private String patientDeliveyAddressState;
	@JsonProperty("patientDeliveyAddressCity")
	private String patientDeliveyAddressCity;
	@JsonProperty("patientDeliveyAddressZip")
	private String patientDeliveyAddressZip;
	@JsonProperty("pickupExchangeScheduleDateTime")
	private LocalDate pickupExchangeScheduleDateTime;
	@JsonProperty("pickupExchangeActualDateTime")
	private LocalDate pickupExchangeActualDateTime;
	@JsonProperty("pickupExchangeReason")
	private String pickupExchangeReason;
	@JsonProperty("pickupExchangeRequest")
	private String pickupExchangeRequest;
	@JsonProperty("pickupExchangeNote")
	private String pickupExchangeNote;
	@JsonProperty("pickupExchangeAgentIdNo")
	private String pickupExchangeAgentIdNo;
	@JsonProperty("pickupExchangeAgentName")
	private String pickupExchangeAgentName;
	@JsonProperty("pickupExchangeDocumentId")
	private String pickupExchangeDocumentId;
	@JsonProperty("pickupExchangeDocumentNo")
	private String pickupExchangeDocumentNo;
	@JsonProperty("pickupExchangeDocumentName")
	private String pickupExchangeDocumentName;
	@JsonProperty("pickupExchangeStatus")
	private String pickupExchangeStatus;
	@JsonProperty("pickupExchangeComments")
	private String pickupExchangeComments;
	@JsonProperty("isPatientSigned")
	private String isPatientSigned;
	@JsonProperty("relationshipWithPatient")
	private String relationshipWithPatient;
	@JsonProperty("patientSignedDateTime")
	private LocalDate patientSignedDateTime;
	@JsonProperty("isAgentSigned")
	private String isAgentSigned;
	@JsonProperty("lastBillingDate")
	private LocalDate lastBillingDate;
	@JsonProperty("dateOfDeath")
	private LocalDate dateOfDeath;
	@JsonProperty("pickupExchangeSupportingDocument1")
	private String pickupExchangeSupportingDocument1;
	@JsonProperty("pickupExchangeSupportingDocument2")
	private String pickupExchangeSupportingDocument2;
	@JsonProperty("patientNotsignedReason")
	private String patientNotsignedReason;
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
	@JsonProperty("pickupExchangeUuid")
	private UUID pickupExchangeUuid;
	@JsonProperty("pickupExchangeItems")
	private List<PickupExchangeItem> pickupExchangeItems;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonProperty("pickupExchangeId")
	public Long getPickupExchangeId() {
		return pickupExchangeId;
	}

	@JsonProperty("pickupExchangeId")
	public void setPickupExchangeId(Long pickupExchangeId) {
		this.pickupExchangeId = pickupExchangeId;
	}

	@JsonProperty("pickupExchangeNo")
	public String getPickupExchangeNo() {
		return pickupExchangeNo;
	}

	public String getPatientNotsignedReason() {
		return patientNotsignedReason;
	}

	public void setPatientNotsignedReason(String patientNotsignedReason) {
		this.patientNotsignedReason = patientNotsignedReason;
	}

	@JsonProperty("pickupExchangeNo")
	public void setPickupExchangeNo(String pickupExchangeNo) {
		this.pickupExchangeNo = pickupExchangeNo;
	}

	@JsonProperty("pickupExchangeType")
	public String getPickupExchangeType() {
		return pickupExchangeType;
	}

	@JsonProperty("pickupExchangeType")
	public void setPickupExchangeType(String pickupExchangeType) {
		this.pickupExchangeType = pickupExchangeType;
	}

	@JsonProperty("soId")
	public Long getSoId() {
		return soId;
	}

	@JsonProperty("soId")
	public void setSoId(Long soId) {
		this.soId = soId;
	}

	@JsonProperty("soNo")
	public String getSoNo() {
		return soNo;
	}

	@JsonProperty("soNo")
	public void setSoNo(String soNo) {
		this.soNo = soNo;
	}

	@JsonProperty("branchId")
	public Long getBranchId() {
		return branchId;
	}

	@JsonProperty("branchId")
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	@JsonProperty("branchName")
	public String getBranchName() {
		return branchName;
	}

	@JsonProperty("branchName")
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	@JsonProperty("branchAddress")
	public String getBranchAddress() {
		return branchAddress;
	}

	@JsonProperty("branchAddress")
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	@JsonProperty("currentDate")
	public String getCurrentDate() {
		return currentDate;
	}

	@JsonProperty("currentDate")
	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	@JsonProperty("documentId")
	public String getDocumentId() {
		return documentId;
	}

	@JsonProperty("documentId")
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}

	@JsonProperty("patientSignature")
	public String getPatientSignature() {
		return patientSignature;
	}

	@JsonProperty("patientSignature")
	public void setPatientSignature(String patientSignature) {
		this.patientSignature = patientSignature;
	}

	@JsonProperty("technicianSignature")
	public String getTechnicianSignature() {
		return technicianSignature;
	}

	@JsonProperty("technicianSignature")
	public void setTechnicianSignature(String technicianSignature) {
		this.technicianSignature = technicianSignature;
	}

	@JsonProperty("inventoryLocationId")
	public Long getInventoryLocationId() {
		return inventoryLocationId;
	}

	@JsonProperty("inventoryLocationId")
	public void setInventoryLocationId(Long inventoryLocationId) {
		this.inventoryLocationId = inventoryLocationId;
	}

	@JsonProperty("inventoryLocationName")
	public String getInventoryLocationName() {
		return inventoryLocationName;
	}

	@JsonProperty("inventoryLocationName")
	public void setInventoryLocationName(String inventoryLocationName) {
		this.inventoryLocationName = inventoryLocationName;
	}

	@JsonProperty("patientId")
	public Long getPatientId() {
		return patientId;
	}

	@JsonProperty("patientId")
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	@JsonProperty("patientIdNo")
	public String getPatientIdNo() {
		return patientIdNo;
	}

	@JsonProperty("patientIdNo")
	public void setPatientIdNo(String patientIdNo) {
		this.patientIdNo = patientIdNo;
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

	@JsonProperty("patientContact1")
	public String getPatientContact1() {
		return patientContact1;
	}

	@JsonProperty("patientContact1")
	public void setPatientContact1(String patientContact1) {
		this.patientContact1 = patientContact1;
	}

	@JsonProperty("patientContact2")
	public String getPatientContact2() {
		return patientContact2;
	}

	@JsonProperty("patientContact2")
	public void setPatientContact2(String patientContact2) {
		this.patientContact2 = patientContact2;
	}

	@JsonProperty("patientBillingAddressLine1")
	public String getPatientBillingAddressLine1() {
		return patientBillingAddressLine1;
	}

	@JsonProperty("patientBillingAddressLine1")
	public void setPatientBillingAddressLine1(String patientBillingAddressLine1) {
		this.patientBillingAddressLine1 = patientBillingAddressLine1;
	}

	@JsonProperty("patientBillingAddressLine2")
	public String getPatientBillingAddressLine2() {
		return patientBillingAddressLine2;
	}

	@JsonProperty("patientBillingAddressLine2")
	public void setPatientBillingAddressLine2(String patientBillingAddressLine2) {
		this.patientBillingAddressLine2 = patientBillingAddressLine2;
	}

	@JsonProperty("patientBillingAddressState")
	public String getPatientBillingAddressState() {
		return patientBillingAddressState;
	}

	@JsonProperty("patientBillingAddressState")
	public void setPatientBillingAddressState(String patientBillingAddressState) {
		this.patientBillingAddressState = patientBillingAddressState;
	}

	@JsonProperty("patientBillingAddressCity")
	public String getPatientBillingAddressCity() {
		return patientBillingAddressCity;
	}

	@JsonProperty("patientBillingAddressCity")
	public void setPatientBillingAddressCity(String patientBillingAddressCity) {
		this.patientBillingAddressCity = patientBillingAddressCity;
	}

	@JsonProperty("patientBillingAddressZip")
	public String getPatientBillingAddressZip() {
		return patientBillingAddressZip;
	}

	@JsonProperty("patientBillingAddressZip")
	public void setPatientBillingAddressZip(String patientBillingAddressZip) {
		this.patientBillingAddressZip = patientBillingAddressZip;
	}

	@JsonProperty("patientDeliveyAddressLine1")
	public String getPatientDeliveyAddressLine1() {
		return patientDeliveyAddressLine1;
	}

	@JsonProperty("patientDeliveyAddressLine1")
	public void setPatientDeliveyAddressLine1(String patientDeliveyAddressLine1) {
		this.patientDeliveyAddressLine1 = patientDeliveyAddressLine1;
	}

	@JsonProperty("patientDeliveyAddressLine2")
	public String getPatientDeliveyAddressLine2() {
		return patientDeliveyAddressLine2;
	}

	@JsonProperty("patientDeliveyAddressLine2")
	public void setPatientDeliveyAddressLine2(String patientDeliveyAddressLine2) {
		this.patientDeliveyAddressLine2 = patientDeliveyAddressLine2;
	}

	@JsonProperty("patientDeliveyAddressState")
	public String getPatientDeliveyAddressState() {
		return patientDeliveyAddressState;
	}

	@JsonProperty("patientDeliveyAddressState")
	public void setPatientDeliveyAddressState(String patientDeliveyAddressState) {
		this.patientDeliveyAddressState = patientDeliveyAddressState;
	}

	@JsonProperty("patientDeliveyAddressCity")
	public String getPatientDeliveyAddressCity() {
		return patientDeliveyAddressCity;
	}

	@JsonProperty("patientDeliveyAddressCity")
	public void setPatientDeliveyAddressCity(String patientDeliveyAddressCity) {
		this.patientDeliveyAddressCity = patientDeliveyAddressCity;
	}

	@JsonProperty("patientDeliveyAddressZip")
	public String getPatientDeliveyAddressZip() {
		return patientDeliveyAddressZip;
	}

	@JsonProperty("patientDeliveyAddressZip")
	public void setPatientDeliveyAddressZip(String patientDeliveyAddressZip) {
		this.patientDeliveyAddressZip = patientDeliveyAddressZip;
	}

	@JsonProperty("pickupExchangeScheduleDateTime")
	public LocalDate getPickupExchangeScheduleDateTime() {
		return pickupExchangeScheduleDateTime;
	}

	@JsonProperty("pickupExchangeScheduleDateTime")
	public void setPickupExchangeScheduleDateTime(LocalDate pickupExchangeScheduleDateTime) {
		this.pickupExchangeScheduleDateTime = pickupExchangeScheduleDateTime;
	}

	@JsonProperty("pickupExchangeActualDateTime")
	public LocalDate getPickupExchangeActualDateTime() {
		return pickupExchangeActualDateTime;
	}

	@JsonProperty("pickupExchangeActualDateTime")
	public void setPickupExchangeActualDateTime(LocalDate pickupExchangeActualDateTime) {
		this.pickupExchangeActualDateTime = pickupExchangeActualDateTime;
	}

	@JsonProperty("pickupExchangeReason")
	public String getPickupExchangeReason() {
		return pickupExchangeReason;
	}

	@JsonProperty("pickupExchangeReason")
	public void setPickupExchangeReason(String pickupExchangeReason) {
		this.pickupExchangeReason = pickupExchangeReason;
	}

	@JsonProperty("pickupExchangeRequest")
	public String getPickupExchangeRequest() {
		return pickupExchangeRequest;
	}

	@JsonProperty("pickupExchangeRequest")
	public void setPickupExchangeRequest(String pickupExchangeRequest) {
		this.pickupExchangeRequest = pickupExchangeRequest;
	}

	@JsonProperty("pickupExchangeNote")
	public String getPickupExchangeNote() {
		return pickupExchangeNote;
	}

	@JsonProperty("pickupExchangeNote")
	public void setPickupExchangeNote(String pickupExchangeNote) {
		this.pickupExchangeNote = pickupExchangeNote;
	}

	@JsonProperty("pickupExchangeAgentIdNo")
	public String getPickupExchangeAgentIdNo() {
		return pickupExchangeAgentIdNo;
	}

	@JsonProperty("pickupExchangeAgentIdNo")
	public void setPickupExchangeAgentIdNo(String pickupExchangeAgentIdNo) {
		this.pickupExchangeAgentIdNo = pickupExchangeAgentIdNo;
	}

	@JsonProperty("pickupExchangeAgentName")
	public String getPickupExchangeAgentName() {
		return pickupExchangeAgentName;
	}

	@JsonProperty("pickupExchangeAgentName")
	public void setPickupExchangeAgentName(String pickupExchangeAgentName) {
		this.pickupExchangeAgentName = pickupExchangeAgentName;
	}

	@JsonProperty("pickupExchangeDocumentId")
	public String getPickupExchangeDocumentId() {
		return pickupExchangeDocumentId;
	}

	@JsonProperty("pickupExchangeDocumentId")
	public void setPickupExchangeDocumentId(String pickupExchangeDocumentId) {
		this.pickupExchangeDocumentId = pickupExchangeDocumentId;
	}

	@JsonProperty("pickupExchangeDocumentNo")
	public String getPickupExchangeDocumentNo() {
		return pickupExchangeDocumentNo;
	}

	@JsonProperty("pickupExchangeDocumentNo")
	public void setPickupExchangeDocumentNo(String pickupExchangeDocumentNo) {
		this.pickupExchangeDocumentNo = pickupExchangeDocumentNo;
	}

	@JsonProperty("pickupExchangeDocumentName")
	public String getPickupExchangeDocumentName() {
		return pickupExchangeDocumentName;
	}

	@JsonProperty("pickupExchangeDocumentName")
	public void setPickupExchangeDocumentName(String pickupExchangeDocumentName) {
		this.pickupExchangeDocumentName = pickupExchangeDocumentName;
	}

	@JsonProperty("pickupExchangeStatus")
	public String getPickupExchangeStatus() {
		return pickupExchangeStatus;
	}

	@JsonProperty("pickupExchangeStatus")
	public void setPickupExchangeStatus(String pickupExchangeStatus) {
		this.pickupExchangeStatus = pickupExchangeStatus;
	}

	@JsonProperty("pickupExchangeComments")
	public String getPickupExchangeComments() {
		return pickupExchangeComments;
	}

	@JsonProperty("pickupExchangeComments")
	public void setPickupExchangeComments(String pickupExchangeComments) {
		this.pickupExchangeComments = pickupExchangeComments;
	}

	@JsonProperty("isPatientSigned")
	public String getIsPatientSigned() {
		return isPatientSigned;
	}

	@JsonProperty("isPatientSigned")
	public void setIsPatientSigned(String isPatientSigned) {
		this.isPatientSigned = isPatientSigned;
	}

	@JsonProperty("relationshipWithPatient")
	public String getRelationshipWithPatient() {
		return relationshipWithPatient;
	}

	@JsonProperty("relationshipWithPatient")
	public void setRelationshipWithPatient(String relationshipWithPatient) {
		this.relationshipWithPatient = relationshipWithPatient;
	}

	@JsonProperty("patientSignedDateTime")
	public LocalDate getPatientSignedDateTime() {
		return patientSignedDateTime;
	}

	@JsonProperty("patientSignedDateTime")
	public void setPatientSignedDateTime(LocalDate patientSignedDateTime) {
		this.patientSignedDateTime = patientSignedDateTime;
	}

	@JsonProperty("isAgentSigned")
	public String getIsAgentSigned() {
		return isAgentSigned;
	}

	@JsonProperty("isAgentSigned")
	public void setIsAgentSigned(String isAgentSigned) {
		this.isAgentSigned = isAgentSigned;
	}

	@JsonProperty("lastBillingDate")
	public LocalDate getLastBillingDate() {
		return lastBillingDate;
	}

	@JsonProperty("lastBillingDate")
	public void setLastBillingDate(LocalDate lastBillingDate) {
		this.lastBillingDate = lastBillingDate;
	}

	@JsonProperty("dateOfDeath")
	public LocalDate getDateOfDeath() {
		return dateOfDeath;
	}

	@JsonProperty("dateOfDeath")
	public void setDateOfDeath(LocalDate dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	@JsonProperty("pickupExchangeSupportingDocument1")
	public String getPickupExchangeSupportingDocument1() {
		return pickupExchangeSupportingDocument1;
	}

	@JsonProperty("pickupExchangeSupportingDocument1")
	public void setPickupExchangeSupportingDocument1(String pickupExchangeSupportingDocument1) {
		this.pickupExchangeSupportingDocument1 = pickupExchangeSupportingDocument1;
	}

	@JsonProperty("pickupExchangeSupportingDocument2")
	public String getPickupExchangeSupportingDocument2() {
		return pickupExchangeSupportingDocument2;
	}

	@JsonProperty("pickupExchangeSupportingDocument2")
	public void setPickupExchangeSupportingDocument2(String pickupExchangeSupportingDocument2) {
		this.pickupExchangeSupportingDocument2 = pickupExchangeSupportingDocument2;
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

	@JsonProperty("pickupExchangeUuid")
	public UUID getPickupExchangeUuid() {
		return pickupExchangeUuid;
	}

	@JsonProperty("pickupExchangeUuid")
	public void setPickupExchangeUuid(UUID pickupExchangeUuid) {
		this.pickupExchangeUuid = pickupExchangeUuid;
	}

	@JsonProperty("pickupExchangeItems")
	public List<PickupExchangeItem> getPickupExchangeItems() {
		return pickupExchangeItems;
	}

	@JsonProperty("pickupExchangeItems")
	public void setPickupExchangeItems(List<PickupExchangeItem> pickupExchangeItems) {
		this.pickupExchangeItems = pickupExchangeItems;
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