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
@JsonPropertyOrder({"links", "id", "customerId", "controlNumber", "userId", "shortFormIndicator", "updatable", "deletable",
		"updatableFields", "status", "statusCode", "statusReasons", "createdDate", "updatedDate", "expirationDate",
		"validationMessages", "providerNotes", "payerNotes", "payer", "requestingProvider", "subscriber", "patient",
		"diagnoses", "certificationIssueDate", "certificationEffectiveDate", "certificationExpirationDate",
		"certificationNumber", "referenceNumber", "traceNumbers", "requestType", "requestTypeCode", "serviceType",
		"serviceTypeCode", "additionalServiceTypes", "placeOfService", "placeOfServiceCode", "serviceLevel",
		"serviceLevelCode", "fromDate", "toDate", "quantity", "quantityType", "quantityTypeCode", "admissionType",
		"admissionTypeCode", "admissionSource", "admissionSourceCode", "nursingHomeResidentialStatus",
		"nursingHomeResidentialStatusCode", "homeHealthStartDate", "homeHealthCertificationPeriodStartDate",
		"homeHealthCertificationPeriodEndDate", "transportType", "transportTypeCode", "transportDistance",
		"transportPurpose", "chiropracticTreatmentCount", "beginningSubluxationLevel", "beginningSubluxationLevelCode",
		"endingSubluxationLevel", "endingSubluxationLevelCode", "spinalCondition", "spinalConditionCode",
		"spinalConditionDescription", "oxygenEquipmentType", "oxygenEquipmentTypeCode", "oxygenFlowRate",
		"oxygenDailyUseCount", "oxygenUsePeriodHourCount", "oxygenOrderText", "oxygenDeliverySystemType",
		"oxygenDeliverySystemTypeCode", "transportLocations", "procedures", "renderingProviders",
		"supplementalInformation" })
@Generated("jsonschema2pojo")
public class ServiceReviewResponseDTO {

	@JsonProperty("links")
	private Links links;
	@JsonProperty("id")
	private String id;
	@JsonProperty("customerId")
	private String customerId;
	@JsonProperty("controlNumber")
	private String controlNumber;
	@JsonProperty("userId")
	private String userId;
	@JsonProperty("shortFormIndicator")
	private Boolean shortFormIndicator;
	@JsonProperty("updatable")
	private Boolean updatable;
	@JsonProperty("deletable")
	private Boolean deletable;
	@JsonProperty("updatableFields")
	private List<String> updatableFields;
	@JsonProperty("status")
	private String status;
	@JsonProperty("statusCode")
	private String statusCode;
	@JsonProperty("statusReasons")
	private List<StatusReason> statusReasons;
	@JsonProperty("createdDate")
	private String createdDate;
	@JsonProperty("updatedDate")
	private String updatedDate;
	@JsonProperty("expirationDate")
	private String expirationDate;
	@JsonProperty("validationMessages")
	private List<ValidationMessage> validationMessages;
	@JsonProperty("providerNotes")
	private List<ProviderNote> providerNotes;
	@JsonProperty("payerNotes")
	private List<PayerNote> payerNotes;
	@JsonProperty("payer")
	private Payer payer;
	@JsonProperty("requestingProvider")
	private RequestingProvider requestingProvider;
	@JsonProperty("subscriber")
	private Subscriber subscriber;
	@JsonProperty("patient")
	private Patient patient;
	@JsonProperty("diagnoses")
	private List<Diagnosis> diagnoses;
	@JsonProperty("certificationIssueDate")
	private String certificationIssueDate;
	@JsonProperty("certificationEffectiveDate")
	private String certificationEffectiveDate;
	@JsonProperty("certificationExpirationDate")
	private String certificationExpirationDate;
	@JsonProperty("certificationNumber")
	private String certificationNumber;
	@JsonProperty("referenceNumber")
	private String referenceNumber;
	@JsonProperty("traceNumbers")
	private List<String> traceNumbers;
	@JsonProperty("requestType")
	private String requestType;
	@JsonProperty("requestTypeCode")
	private String requestTypeCode;
	@JsonProperty("serviceType")
	private String serviceType;
	@JsonProperty("serviceTypeCode")
	private String serviceTypeCode;
	@JsonProperty("additionalServiceTypes")
	private List<AdditionalServiceType> additionalServiceTypes;
	@JsonProperty("placeOfService")
	private String placeOfService;
	@JsonProperty("placeOfServiceCode")
	private String placeOfServiceCode;
	@JsonProperty("serviceLevel")
	private String serviceLevel;
	@JsonProperty("serviceLevelCode")
	private String serviceLevelCode;
	@JsonProperty("fromDate")
	private String fromDate;
	@JsonProperty("toDate")
	private String toDate;
	@JsonProperty("quantity")
	private String quantity;
	@JsonProperty("quantityType")
	private String quantityType;
	@JsonProperty("quantityTypeCode")
	private String quantityTypeCode;
	@JsonProperty("admissionType")
	private String admissionType;
	@JsonProperty("admissionTypeCode")
	private String admissionTypeCode;
	@JsonProperty("admissionSource")
	private String admissionSource;
	@JsonProperty("admissionSourceCode")
	private String admissionSourceCode;
	@JsonProperty("nursingHomeResidentialStatus")
	private String nursingHomeResidentialStatus;
	@JsonProperty("nursingHomeResidentialStatusCode")
	private String nursingHomeResidentialStatusCode;
	@JsonProperty("homeHealthStartDate")
	private String homeHealthStartDate;
	@JsonProperty("homeHealthCertificationPeriodStartDate")
	private String homeHealthCertificationPeriodStartDate;
	@JsonProperty("homeHealthCertificationPeriodEndDate")
	private String homeHealthCertificationPeriodEndDate;
	@JsonProperty("transportType")
	private String transportType;
	@JsonProperty("transportTypeCode")
	private String transportTypeCode;
	@JsonProperty("transportDistance")
	private String transportDistance;
	@JsonProperty("transportPurpose")
	private String transportPurpose;
	@JsonProperty("chiropracticTreatmentCount")
	private String chiropracticTreatmentCount;
	@JsonProperty("beginningSubluxationLevel")
	private String beginningSubluxationLevel;
	@JsonProperty("beginningSubluxationLevelCode")
	private String beginningSubluxationLevelCode;
	@JsonProperty("endingSubluxationLevel")
	private String endingSubluxationLevel;
	@JsonProperty("endingSubluxationLevelCode")
	private String endingSubluxationLevelCode;
	@JsonProperty("spinalCondition")
	private String spinalCondition;
	@JsonProperty("spinalConditionCode")
	private String spinalConditionCode;
	@JsonProperty("spinalConditionDescription")
	private String spinalConditionDescription;
	@JsonProperty("oxygenEquipmentType")
	private String oxygenEquipmentType;
	@JsonProperty("oxygenEquipmentTypeCode")
	private String oxygenEquipmentTypeCode;
	@JsonProperty("oxygenFlowRate")
	private String oxygenFlowRate;
	@JsonProperty("oxygenDailyUseCount")
	private String oxygenDailyUseCount;
	@JsonProperty("oxygenUsePeriodHourCount")
	private String oxygenUsePeriodHourCount;
	@JsonProperty("oxygenOrderText")
	private String oxygenOrderText;
	@JsonProperty("oxygenDeliverySystemType")
	private String oxygenDeliverySystemType;
	@JsonProperty("oxygenDeliverySystemTypeCode")
	private String oxygenDeliverySystemTypeCode;
	@JsonProperty("transportLocations")
	private List<TransportLocation> transportLocations;
	@JsonProperty("procedures")
	private List<Procedure> procedures;
	@JsonProperty("renderingProviders")
	private List<RenderingProvider> renderingProviders;
	@JsonProperty("supplementalInformation")
	private SupplementalInformation supplementalInformation;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	
	
	@JsonProperty("id")
	public String getId() {
		return id;
	}

	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}

	public Links getLinks() {
		return links;
	}

	public void setLinks(Links links) {
		this.links = links;
	}

	@JsonProperty("customerId")
	public String getCustomerId() {
		return customerId;
	}

	@JsonProperty("customerId")
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@JsonProperty("controlNumber")
	public String getControlNumber() {
		return controlNumber;
	}

	@JsonProperty("controlNumber")
	public void setControlNumber(String controlNumber) {
		this.controlNumber = controlNumber;
	}

	@JsonProperty("userId")
	public String getUserId() {
		return userId;
	}

	@JsonProperty("userId")
	public void setUserId(String userId) {
		this.userId = userId;
	}

	@JsonProperty("shortFormIndicator")
	public Boolean getShortFormIndicator() {
		return shortFormIndicator;
	}

	@JsonProperty("shortFormIndicator")
	public void setShortFormIndicator(Boolean shortFormIndicator) {
		this.shortFormIndicator = shortFormIndicator;
	}

	@JsonProperty("updatable")
	public Boolean getUpdatable() {
		return updatable;
	}

	@JsonProperty("updatable")
	public void setUpdatable(Boolean updatable) {
		this.updatable = updatable;
	}

	@JsonProperty("deletable")
	public Boolean getDeletable() {
		return deletable;
	}

	@JsonProperty("deletable")
	public void setDeletable(Boolean deletable) {
		this.deletable = deletable;
	}

	@JsonProperty("updatableFields")
	public List<String> getUpdatableFields() {
		return updatableFields;
	}

	@JsonProperty("updatableFields")
	public void setUpdatableFields(List<String> updatableFields) {
		this.updatableFields = updatableFields;
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

	@JsonProperty("createdDate")
	public String getCreatedDate() {
		return createdDate;
	}

	@JsonProperty("createdDate")
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	@JsonProperty("updatedDate")
	public String getUpdatedDate() {
		return updatedDate;
	}

	@JsonProperty("updatedDate")
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	@JsonProperty("expirationDate")
	public String getExpirationDate() {
		return expirationDate;
	}

	@JsonProperty("expirationDate")
	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	@JsonProperty("validationMessages")
	public List<ValidationMessage> getValidationMessages() {
		return validationMessages;
	}

	@JsonProperty("validationMessages")
	public void setValidationMessages(List<ValidationMessage> validationMessages) {
		this.validationMessages = validationMessages;
	}

	@JsonProperty("providerNotes")
	public List<ProviderNote> getProviderNotes() {
		return providerNotes;
	}

	@JsonProperty("providerNotes")
	public void setProviderNotes(List<ProviderNote> providerNotes) {
		this.providerNotes = providerNotes;
	}

	@JsonProperty("payerNotes")
	public List<PayerNote> getPayerNotes() {
		return payerNotes;
	}

	@JsonProperty("payerNotes")
	public void setPayerNotes(List<PayerNote> payerNotes) {
		this.payerNotes = payerNotes;
	}

	@JsonProperty("payer")
	public Payer getPayer() {
		return payer;
	}

	@JsonProperty("payer")
	public void setPayer(Payer payer) {
		this.payer = payer;
	}

	@JsonProperty("requestingProvider")
	public RequestingProvider getRequestingProvider() {
		return requestingProvider;
	}

	@JsonProperty("requestingProvider")
	public void setRequestingProvider(RequestingProvider requestingProvider) {
		this.requestingProvider = requestingProvider;
	}

	@JsonProperty("subscriber")
	public Subscriber getSubscriber() {
		return subscriber;
	}

	@JsonProperty("subscriber")
	public void setSubscriber(Subscriber subscriber) {
		this.subscriber = subscriber;
	}

	@JsonProperty("patient")
	public Patient getPatient() {
		return patient;
	}

	@JsonProperty("patient")
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@JsonProperty("diagnoses")
	public List<Diagnosis> getDiagnoses() {
		return diagnoses;
	}

	@JsonProperty("diagnoses")
	public void setDiagnoses(List<Diagnosis> diagnoses) {
		this.diagnoses = diagnoses;
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

	@JsonProperty("referenceNumber")
	public String getReferenceNumber() {
		return referenceNumber;
	}

	@JsonProperty("referenceNumber")
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}

	@JsonProperty("traceNumbers")
	public List<String> getTraceNumbers() {
		return traceNumbers;
	}

	@JsonProperty("traceNumbers")
	public void setTraceNumbers(List<String> traceNumbers) {
		this.traceNumbers = traceNumbers;
	}

	@JsonProperty("requestType")
	public String getRequestType() {
		return requestType;
	}

	@JsonProperty("requestType")
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	@JsonProperty("requestTypeCode")
	public String getRequestTypeCode() {
		return requestTypeCode;
	}

	@JsonProperty("requestTypeCode")
	public void setRequestTypeCode(String requestTypeCode) {
		this.requestTypeCode = requestTypeCode;
	}

	@JsonProperty("serviceType")
	public String getServiceType() {
		return serviceType;
	}

	@JsonProperty("serviceType")
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	@JsonProperty("serviceTypeCode")
	public String getServiceTypeCode() {
		return serviceTypeCode;
	}

	@JsonProperty("serviceTypeCode")
	public void setServiceTypeCode(String serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}

	@JsonProperty("additionalServiceTypes")
	public List<AdditionalServiceType> getAdditionalServiceTypes() {
		return additionalServiceTypes;
	}

	@JsonProperty("additionalServiceTypes")
	public void setAdditionalServiceTypes(List<AdditionalServiceType> additionalServiceTypes) {
		this.additionalServiceTypes = additionalServiceTypes;
	}

	@JsonProperty("placeOfService")
	public String getPlaceOfService() {
		return placeOfService;
	}

	@JsonProperty("placeOfService")
	public void setPlaceOfService(String placeOfService) {
		this.placeOfService = placeOfService;
	}

	@JsonProperty("placeOfServiceCode")
	public String getPlaceOfServiceCode() {
		return placeOfServiceCode;
	}

	@JsonProperty("placeOfServiceCode")
	public void setPlaceOfServiceCode(String placeOfServiceCode) {
		this.placeOfServiceCode = placeOfServiceCode;
	}

	@JsonProperty("serviceLevel")
	public String getServiceLevel() {
		return serviceLevel;
	}

	@JsonProperty("serviceLevel")
	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	@JsonProperty("serviceLevelCode")
	public String getServiceLevelCode() {
		return serviceLevelCode;
	}

	@JsonProperty("serviceLevelCode")
	public void setServiceLevelCode(String serviceLevelCode) {
		this.serviceLevelCode = serviceLevelCode;
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

	@JsonProperty("admissionType")
	public String getAdmissionType() {
		return admissionType;
	}

	@JsonProperty("admissionType")
	public void setAdmissionType(String admissionType) {
		this.admissionType = admissionType;
	}

	@JsonProperty("admissionTypeCode")
	public String getAdmissionTypeCode() {
		return admissionTypeCode;
	}

	@JsonProperty("admissionTypeCode")
	public void setAdmissionTypeCode(String admissionTypeCode) {
		this.admissionTypeCode = admissionTypeCode;
	}

	@JsonProperty("admissionSource")
	public String getAdmissionSource() {
		return admissionSource;
	}

	@JsonProperty("admissionSource")
	public void setAdmissionSource(String admissionSource) {
		this.admissionSource = admissionSource;
	}

	@JsonProperty("admissionSourceCode")
	public String getAdmissionSourceCode() {
		return admissionSourceCode;
	}

	@JsonProperty("admissionSourceCode")
	public void setAdmissionSourceCode(String admissionSourceCode) {
		this.admissionSourceCode = admissionSourceCode;
	}

	@JsonProperty("nursingHomeResidentialStatus")
	public String getNursingHomeResidentialStatus() {
		return nursingHomeResidentialStatus;
	}

	@JsonProperty("nursingHomeResidentialStatus")
	public void setNursingHomeResidentialStatus(String nursingHomeResidentialStatus) {
		this.nursingHomeResidentialStatus = nursingHomeResidentialStatus;
	}

	@JsonProperty("nursingHomeResidentialStatusCode")
	public String getNursingHomeResidentialStatusCode() {
		return nursingHomeResidentialStatusCode;
	}

	@JsonProperty("nursingHomeResidentialStatusCode")
	public void setNursingHomeResidentialStatusCode(String nursingHomeResidentialStatusCode) {
		this.nursingHomeResidentialStatusCode = nursingHomeResidentialStatusCode;
	}

	@JsonProperty("homeHealthStartDate")
	public String getHomeHealthStartDate() {
		return homeHealthStartDate;
	}

	@JsonProperty("homeHealthStartDate")
	public void setHomeHealthStartDate(String homeHealthStartDate) {
		this.homeHealthStartDate = homeHealthStartDate;
	}

	@JsonProperty("homeHealthCertificationPeriodStartDate")
	public String getHomeHealthCertificationPeriodStartDate() {
		return homeHealthCertificationPeriodStartDate;
	}

	@JsonProperty("homeHealthCertificationPeriodStartDate")
	public void setHomeHealthCertificationPeriodStartDate(String homeHealthCertificationPeriodStartDate) {
		this.homeHealthCertificationPeriodStartDate = homeHealthCertificationPeriodStartDate;
	}

	@JsonProperty("homeHealthCertificationPeriodEndDate")
	public String getHomeHealthCertificationPeriodEndDate() {
		return homeHealthCertificationPeriodEndDate;
	}

	@JsonProperty("homeHealthCertificationPeriodEndDate")
	public void setHomeHealthCertificationPeriodEndDate(String homeHealthCertificationPeriodEndDate) {
		this.homeHealthCertificationPeriodEndDate = homeHealthCertificationPeriodEndDate;
	}

	@JsonProperty("transportType")
	public String getTransportType() {
		return transportType;
	}

	@JsonProperty("transportType")
	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}

	@JsonProperty("transportTypeCode")
	public String getTransportTypeCode() {
		return transportTypeCode;
	}

	@JsonProperty("transportTypeCode")
	public void setTransportTypeCode(String transportTypeCode) {
		this.transportTypeCode = transportTypeCode;
	}

	@JsonProperty("transportDistance")
	public String getTransportDistance() {
		return transportDistance;
	}

	@JsonProperty("transportDistance")
	public void setTransportDistance(String transportDistance) {
		this.transportDistance = transportDistance;
	}

	@JsonProperty("transportPurpose")
	public String getTransportPurpose() {
		return transportPurpose;
	}

	@JsonProperty("transportPurpose")
	public void setTransportPurpose(String transportPurpose) {
		this.transportPurpose = transportPurpose;
	}

	@JsonProperty("chiropracticTreatmentCount")
	public String getChiropracticTreatmentCount() {
		return chiropracticTreatmentCount;
	}

	@JsonProperty("chiropracticTreatmentCount")
	public void setChiropracticTreatmentCount(String chiropracticTreatmentCount) {
		this.chiropracticTreatmentCount = chiropracticTreatmentCount;
	}

	@JsonProperty("beginningSubluxationLevel")
	public String getBeginningSubluxationLevel() {
		return beginningSubluxationLevel;
	}

	@JsonProperty("beginningSubluxationLevel")
	public void setBeginningSubluxationLevel(String beginningSubluxationLevel) {
		this.beginningSubluxationLevel = beginningSubluxationLevel;
	}

	@JsonProperty("beginningSubluxationLevelCode")
	public String getBeginningSubluxationLevelCode() {
		return beginningSubluxationLevelCode;
	}

	@JsonProperty("beginningSubluxationLevelCode")
	public void setBeginningSubluxationLevelCode(String beginningSubluxationLevelCode) {
		this.beginningSubluxationLevelCode = beginningSubluxationLevelCode;
	}

	@JsonProperty("endingSubluxationLevel")
	public String getEndingSubluxationLevel() {
		return endingSubluxationLevel;
	}

	@JsonProperty("endingSubluxationLevel")
	public void setEndingSubluxationLevel(String endingSubluxationLevel) {
		this.endingSubluxationLevel = endingSubluxationLevel;
	}

	@JsonProperty("endingSubluxationLevelCode")
	public String getEndingSubluxationLevelCode() {
		return endingSubluxationLevelCode;
	}

	@JsonProperty("endingSubluxationLevelCode")
	public void setEndingSubluxationLevelCode(String endingSubluxationLevelCode) {
		this.endingSubluxationLevelCode = endingSubluxationLevelCode;
	}

	@JsonProperty("spinalCondition")
	public String getSpinalCondition() {
		return spinalCondition;
	}

	@JsonProperty("spinalCondition")
	public void setSpinalCondition(String spinalCondition) {
		this.spinalCondition = spinalCondition;
	}

	@JsonProperty("spinalConditionCode")
	public String getSpinalConditionCode() {
		return spinalConditionCode;
	}

	@JsonProperty("spinalConditionCode")
	public void setSpinalConditionCode(String spinalConditionCode) {
		this.spinalConditionCode = spinalConditionCode;
	}

	@JsonProperty("spinalConditionDescription")
	public String getSpinalConditionDescription() {
		return spinalConditionDescription;
	}

	@JsonProperty("spinalConditionDescription")
	public void setSpinalConditionDescription(String spinalConditionDescription) {
		this.spinalConditionDescription = spinalConditionDescription;
	}

	@JsonProperty("oxygenEquipmentType")
	public String getOxygenEquipmentType() {
		return oxygenEquipmentType;
	}

	@JsonProperty("oxygenEquipmentType")
	public void setOxygenEquipmentType(String oxygenEquipmentType) {
		this.oxygenEquipmentType = oxygenEquipmentType;
	}

	@JsonProperty("oxygenEquipmentTypeCode")
	public String getOxygenEquipmentTypeCode() {
		return oxygenEquipmentTypeCode;
	}

	@JsonProperty("oxygenEquipmentTypeCode")
	public void setOxygenEquipmentTypeCode(String oxygenEquipmentTypeCode) {
		this.oxygenEquipmentTypeCode = oxygenEquipmentTypeCode;
	}

	@JsonProperty("oxygenFlowRate")
	public String getOxygenFlowRate() {
		return oxygenFlowRate;
	}

	@JsonProperty("oxygenFlowRate")
	public void setOxygenFlowRate(String oxygenFlowRate) {
		this.oxygenFlowRate = oxygenFlowRate;
	}

	@JsonProperty("oxygenDailyUseCount")
	public String getOxygenDailyUseCount() {
		return oxygenDailyUseCount;
	}

	@JsonProperty("oxygenDailyUseCount")
	public void setOxygenDailyUseCount(String oxygenDailyUseCount) {
		this.oxygenDailyUseCount = oxygenDailyUseCount;
	}

	@JsonProperty("oxygenUsePeriodHourCount")
	public String getOxygenUsePeriodHourCount() {
		return oxygenUsePeriodHourCount;
	}

	@JsonProperty("oxygenUsePeriodHourCount")
	public void setOxygenUsePeriodHourCount(String oxygenUsePeriodHourCount) {
		this.oxygenUsePeriodHourCount = oxygenUsePeriodHourCount;
	}

	@JsonProperty("oxygenOrderText")
	public String getOxygenOrderText() {
		return oxygenOrderText;
	}

	@JsonProperty("oxygenOrderText")
	public void setOxygenOrderText(String oxygenOrderText) {
		this.oxygenOrderText = oxygenOrderText;
	}

	@JsonProperty("oxygenDeliverySystemType")
	public String getOxygenDeliverySystemType() {
		return oxygenDeliverySystemType;
	}

	@JsonProperty("oxygenDeliverySystemType")
	public void setOxygenDeliverySystemType(String oxygenDeliverySystemType) {
		this.oxygenDeliverySystemType = oxygenDeliverySystemType;
	}

	@JsonProperty("oxygenDeliverySystemTypeCode")
	public String getOxygenDeliverySystemTypeCode() {
		return oxygenDeliverySystemTypeCode;
	}

	@JsonProperty("oxygenDeliverySystemTypeCode")
	public void setOxygenDeliverySystemTypeCode(String oxygenDeliverySystemTypeCode) {
		this.oxygenDeliverySystemTypeCode = oxygenDeliverySystemTypeCode;
	}

	@JsonProperty("transportLocations")
	public List<TransportLocation> getTransportLocations() {
		return transportLocations;
	}

	@JsonProperty("transportLocations")
	public void setTransportLocations(List<TransportLocation> transportLocations) {
		this.transportLocations = transportLocations;
	}

	@JsonProperty("procedures")
	public List<Procedure> getProcedures() {
		return procedures;
	}

	@JsonProperty("procedures")
	public void setProcedures(List<Procedure> procedures) {
		this.procedures = procedures;
	}

	@JsonProperty("renderingProviders")
	public List<RenderingProvider> getRenderingProviders() {
		return renderingProviders;
	}

	@JsonProperty("renderingProviders")
	public void setRenderingProviders(List<RenderingProvider> renderingProviders) {
		this.renderingProviders = renderingProviders;
	}

	@JsonProperty("supplementalInformation")
	public SupplementalInformation getSupplementalInformation() {
		return supplementalInformation;
	}

	@JsonProperty("supplementalInformation")
	public void setSupplementalInformation(SupplementalInformation supplementalInformation) {
		this.supplementalInformation = supplementalInformation;
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