package com.sunknowledge.changehealthcare.pojo.professionalclaims;

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
@JsonPropertyOrder({ "professionalService", "durableMedicalEquipmentService", "serviceLineSupplementalInformation",
		"durableMedicalEquipmentCertificateOfMedicalNecessity", "ambulanceTransportInformation",
		"durableMedicalEquipmentCertification", "ambulanceCertification", "conditionIndicatorDurableMedicalEquipment",
		"serviceLineDateInformation", "testResults", "contractInformation", "serviceLineReferenceInformation",
		"fileInformation", "purchasedServiceInformation", "linePricingRepricingInformation", "drugIdentification",
		"renderingProvider", "purchasedServiceProvider", "serviceFacilityLocation", "supervisingProvider",
		"referringProvider", "ambulancePickUpLocation", "ambulanceDropOffLocation", "lineAdjudicationInformation",
		"formIdentification", "assignedNumber", "serviceDate", "serviceDateEnd", "providerControlNumber",
		"hospiceEmployeeIndicator", "ambulancePatientCount", "obstetricAnesthesiaAdditionalUnits", "salesTaxAmount",
		"postageTaxAmount", "additionalNotes", "goalRehabOrDischargePlans", "thirdPartyOrganizationNotes" })
@Generated("jsonschema2pojo")
public class ServiceLine {

	@JsonProperty("professionalService")
	private ProfessionalService professionalService;
	@JsonProperty("durableMedicalEquipmentService")
	private DurableMedicalEquipmentService durableMedicalEquipmentService;
	@JsonProperty("serviceLineSupplementalInformation")
	private List<ServiceLineSupplementalInformation> serviceLineSupplementalInformation = null;
	@JsonProperty("durableMedicalEquipmentCertificateOfMedicalNecessity")
	private DurableMedicalEquipmentCertificateOfMedicalNecessity durableMedicalEquipmentCertificateOfMedicalNecessity;
	@JsonProperty("ambulanceTransportInformation")
	private AmbulanceTransportInformation ambulanceTransportInformation;
	@JsonProperty("durableMedicalEquipmentCertification")
	private DurableMedicalEquipmentCertification durableMedicalEquipmentCertification;
	@JsonProperty("ambulanceCertification")
	private List<AmbulanceCertification> ambulanceCertification = null;
	@JsonProperty("conditionIndicatorDurableMedicalEquipment")
	private ConditionIndicatorDurableMedicalEquipment conditionIndicatorDurableMedicalEquipment;
	@JsonProperty("serviceLineDateInformation")
	private ServiceLineDateInformation serviceLineDateInformation;
	@JsonProperty("testResults")
	private List<TestResult> testResults = null;
	@JsonProperty("contractInformation")
	private ContractInformation contractInformation;
	@JsonProperty("serviceLineReferenceInformation")
	private ServiceLineReferenceInformation serviceLineReferenceInformation;
	@JsonProperty("fileInformation")
	private List<String> fileInformation = null;
	@JsonProperty("purchasedServiceInformation")
	private PurchasedServiceInformation purchasedServiceInformation;
	@JsonProperty("linePricingRepricingInformation")
	private LinePricingRepricingInformation linePricingRepricingInformation;
	@JsonProperty("drugIdentification")
	private DrugIdentification drugIdentification;
	@JsonProperty("renderingProvider")
	private RenderingProvider renderingProvider;
	@JsonProperty("purchasedServiceProvider")
	private PurchasedServiceProvider purchasedServiceProvider;
	@JsonProperty("serviceFacilityLocation")
	private ServiceFacilityLocation serviceFacilityLocation;
	@JsonProperty("supervisingProvider")
	private SupervisingProvider supervisingProvider;
	@JsonProperty("referringProvider")
	private ReferringProvider referringProvider;
	@JsonProperty("ambulancePickUpLocation")
	private AmbulancePickUpLocation ambulancePickUpLocation;
	@JsonProperty("ambulanceDropOffLocation")
	private AmbulanceDropOffLocation ambulanceDropOffLocation;
	@JsonProperty("lineAdjudicationInformation")
	private List<LineAdjudicationInformation> lineAdjudicationInformation = null;
	@JsonProperty("formIdentification")
	private List<FormIdentification> formIdentification = null;
	@JsonProperty("assignedNumber")
	private String assignedNumber;
	@JsonProperty("serviceDate")
	private String serviceDate;
	@JsonProperty("serviceDateEnd")
	private String serviceDateEnd;
	@JsonProperty("providerControlNumber")
	private String providerControlNumber;
	@JsonProperty("hospiceEmployeeIndicator")
	private Boolean hospiceEmployeeIndicator;
	@JsonProperty("ambulancePatientCount")
	private Integer ambulancePatientCount;
	@JsonProperty("obstetricAnesthesiaAdditionalUnits")
	private Integer obstetricAnesthesiaAdditionalUnits;
	@JsonProperty("salesTaxAmount")
	private String salesTaxAmount;
	@JsonProperty("postageTaxAmount")
	private String postageTaxAmount;
	@JsonProperty("additionalNotes")
	private String additionalNotes;
	@JsonProperty("goalRehabOrDischargePlans")
	private String goalRehabOrDischargePlans;
	@JsonProperty("thirdPartyOrganizationNotes")
	private String thirdPartyOrganizationNotes;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("professionalService")
	public ProfessionalService getProfessionalService() {
		return professionalService;
	}

	@JsonProperty("professionalService")
	public void setProfessionalService(ProfessionalService professionalService) {
		this.professionalService = professionalService;
	}

	@JsonProperty("durableMedicalEquipmentService")
	public DurableMedicalEquipmentService getDurableMedicalEquipmentService() {
		return durableMedicalEquipmentService;
	}

	@JsonProperty("durableMedicalEquipmentService")
	public void setDurableMedicalEquipmentService(DurableMedicalEquipmentService durableMedicalEquipmentService) {
		this.durableMedicalEquipmentService = durableMedicalEquipmentService;
	}

	@JsonProperty("serviceLineSupplementalInformation")
	public List<ServiceLineSupplementalInformation> getServiceLineSupplementalInformation() {
		return serviceLineSupplementalInformation;
	}

	@JsonProperty("serviceLineSupplementalInformation")
	public void setServiceLineSupplementalInformation(
			List<ServiceLineSupplementalInformation> serviceLineSupplementalInformation) {
		this.serviceLineSupplementalInformation = serviceLineSupplementalInformation;
	}

	@JsonProperty("durableMedicalEquipmentCertificateOfMedicalNecessity")
	public DurableMedicalEquipmentCertificateOfMedicalNecessity getDurableMedicalEquipmentCertificateOfMedicalNecessity() {
		return durableMedicalEquipmentCertificateOfMedicalNecessity;
	}

	@JsonProperty("durableMedicalEquipmentCertificateOfMedicalNecessity")
	public void setDurableMedicalEquipmentCertificateOfMedicalNecessity(
			DurableMedicalEquipmentCertificateOfMedicalNecessity durableMedicalEquipmentCertificateOfMedicalNecessity) {
		this.durableMedicalEquipmentCertificateOfMedicalNecessity = durableMedicalEquipmentCertificateOfMedicalNecessity;
	}

	@JsonProperty("ambulanceTransportInformation")
	public AmbulanceTransportInformation getAmbulanceTransportInformation() {
		return ambulanceTransportInformation;
	}

	@JsonProperty("ambulanceTransportInformation")
	public void setAmbulanceTransportInformation(AmbulanceTransportInformation ambulanceTransportInformation) {
		this.ambulanceTransportInformation = ambulanceTransportInformation;
	}

	@JsonProperty("durableMedicalEquipmentCertification")
	public DurableMedicalEquipmentCertification getDurableMedicalEquipmentCertification() {
		return durableMedicalEquipmentCertification;
	}

	@JsonProperty("durableMedicalEquipmentCertification")
	public void setDurableMedicalEquipmentCertification(
			DurableMedicalEquipmentCertification durableMedicalEquipmentCertification) {
		this.durableMedicalEquipmentCertification = durableMedicalEquipmentCertification;
	}

	@JsonProperty("ambulanceCertification")
	public List<AmbulanceCertification> getAmbulanceCertification() {
		return ambulanceCertification;
	}

	@JsonProperty("ambulanceCertification")
	public void setAmbulanceCertification(List<AmbulanceCertification> ambulanceCertification) {
		this.ambulanceCertification = ambulanceCertification;
	}

	@JsonProperty("conditionIndicatorDurableMedicalEquipment")
	public ConditionIndicatorDurableMedicalEquipment getConditionIndicatorDurableMedicalEquipment() {
		return conditionIndicatorDurableMedicalEquipment;
	}

	@JsonProperty("conditionIndicatorDurableMedicalEquipment")
	public void setConditionIndicatorDurableMedicalEquipment(
			ConditionIndicatorDurableMedicalEquipment conditionIndicatorDurableMedicalEquipment) {
		this.conditionIndicatorDurableMedicalEquipment = conditionIndicatorDurableMedicalEquipment;
	}

	@JsonProperty("serviceLineDateInformation")
	public ServiceLineDateInformation getServiceLineDateInformation() {
		return serviceLineDateInformation;
	}

	@JsonProperty("serviceLineDateInformation")
	public void setServiceLineDateInformation(ServiceLineDateInformation serviceLineDateInformation) {
		this.serviceLineDateInformation = serviceLineDateInformation;
	}

	@JsonProperty("testResults")
	public List<TestResult> getTestResults() {
		return testResults;
	}

	@JsonProperty("testResults")
	public void setTestResults(List<TestResult> testResults) {
		this.testResults = testResults;
	}

	@JsonProperty("contractInformation")
	public ContractInformation getContractInformation() {
		return contractInformation;
	}

	@JsonProperty("contractInformation")
	public void setContractInformation(ContractInformation contractInformation) {
		this.contractInformation = contractInformation;
	}

	@JsonProperty("serviceLineReferenceInformation")
	public ServiceLineReferenceInformation getServiceLineReferenceInformation() {
		return serviceLineReferenceInformation;
	}

	@JsonProperty("serviceLineReferenceInformation")
	public void setServiceLineReferenceInformation(ServiceLineReferenceInformation serviceLineReferenceInformation) {
		this.serviceLineReferenceInformation = serviceLineReferenceInformation;
	}

	@JsonProperty("fileInformation")
	public List<String> getFileInformation() {
		return fileInformation;
	}

	@JsonProperty("fileInformation")
	public void setFileInformation(List<String> fileInformation) {
		this.fileInformation = fileInformation;
	}

	@JsonProperty("purchasedServiceInformation")
	public PurchasedServiceInformation getPurchasedServiceInformation() {
		return purchasedServiceInformation;
	}

	@JsonProperty("purchasedServiceInformation")
	public void setPurchasedServiceInformation(PurchasedServiceInformation purchasedServiceInformation) {
		this.purchasedServiceInformation = purchasedServiceInformation;
	}

	@JsonProperty("linePricingRepricingInformation")
	public LinePricingRepricingInformation getLinePricingRepricingInformation() {
		return linePricingRepricingInformation;
	}

	@JsonProperty("linePricingRepricingInformation")
	public void setLinePricingRepricingInformation(LinePricingRepricingInformation linePricingRepricingInformation) {
		this.linePricingRepricingInformation = linePricingRepricingInformation;
	}

	@JsonProperty("drugIdentification")
	public DrugIdentification getDrugIdentification() {
		return drugIdentification;
	}

	@JsonProperty("drugIdentification")
	public void setDrugIdentification(DrugIdentification drugIdentification) {
		this.drugIdentification = drugIdentification;
	}

	@JsonProperty("renderingProvider")
	public RenderingProvider getRenderingProvider() {
		return renderingProvider;
	}

	@JsonProperty("renderingProvider")
	public void setRenderingProvider(RenderingProvider renderingProvider) {
		this.renderingProvider = renderingProvider;
	}

	@JsonProperty("purchasedServiceProvider")
	public PurchasedServiceProvider getPurchasedServiceProvider() {
		return purchasedServiceProvider;
	}

	@JsonProperty("purchasedServiceProvider")
	public void setPurchasedServiceProvider(PurchasedServiceProvider purchasedServiceProvider) {
		this.purchasedServiceProvider = purchasedServiceProvider;
	}

	@JsonProperty("serviceFacilityLocation")
	public ServiceFacilityLocation getServiceFacilityLocation() {
		return serviceFacilityLocation;
	}

	@JsonProperty("serviceFacilityLocation")
	public void setServiceFacilityLocation(ServiceFacilityLocation serviceFacilityLocation) {
		this.serviceFacilityLocation = serviceFacilityLocation;
	}

	@JsonProperty("supervisingProvider")
	public SupervisingProvider getSupervisingProvider() {
		return supervisingProvider;
	}

	@JsonProperty("supervisingProvider")
	public void setSupervisingProvider(SupervisingProvider supervisingProvider) {
		this.supervisingProvider = supervisingProvider;
	}

	@JsonProperty("referringProvider")
	public ReferringProvider getReferringProvider() {
		return referringProvider;
	}

	@JsonProperty("referringProvider")
	public void setReferringProvider(ReferringProvider referringProvider) {
		this.referringProvider = referringProvider;
	}

	@JsonProperty("ambulancePickUpLocation")
	public AmbulancePickUpLocation getAmbulancePickUpLocation() {
		return ambulancePickUpLocation;
	}

	@JsonProperty("ambulancePickUpLocation")
	public void setAmbulancePickUpLocation(AmbulancePickUpLocation ambulancePickUpLocation) {
		this.ambulancePickUpLocation = ambulancePickUpLocation;
	}

	@JsonProperty("ambulanceDropOffLocation")
	public AmbulanceDropOffLocation getAmbulanceDropOffLocation() {
		return ambulanceDropOffLocation;
	}

	@JsonProperty("ambulanceDropOffLocation")
	public void setAmbulanceDropOffLocation(AmbulanceDropOffLocation ambulanceDropOffLocation) {
		this.ambulanceDropOffLocation = ambulanceDropOffLocation;
	}

	@JsonProperty("lineAdjudicationInformation")
	public List<LineAdjudicationInformation> getLineAdjudicationInformation() {
		return lineAdjudicationInformation;
	}

	@JsonProperty("lineAdjudicationInformation")
	public void setLineAdjudicationInformation(List<LineAdjudicationInformation> lineAdjudicationInformation) {
		this.lineAdjudicationInformation = lineAdjudicationInformation;
	}

	@JsonProperty("formIdentification")
	public List<FormIdentification> getFormIdentification() {
		return formIdentification;
	}

	@JsonProperty("formIdentification")
	public void setFormIdentification(List<FormIdentification> formIdentification) {
		this.formIdentification = formIdentification;
	}

	@JsonProperty("assignedNumber")
	public String getAssignedNumber() {
		return assignedNumber;
	}

	@JsonProperty("assignedNumber")
	public void setAssignedNumber(String assignedNumber) {
		this.assignedNumber = assignedNumber;
	}

	@JsonProperty("serviceDate")
	public String getServiceDate() {
		return serviceDate;
	}

	@JsonProperty("serviceDate")
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}

	@JsonProperty("serviceDateEnd")
	public String getServiceDateEnd() {
		return serviceDateEnd;
	}

	@JsonProperty("serviceDateEnd")
	public void setServiceDateEnd(String serviceDateEnd) {
		this.serviceDateEnd = serviceDateEnd;
	}

	@JsonProperty("providerControlNumber")
	public String getProviderControlNumber() {
		return providerControlNumber;
	}

	@JsonProperty("providerControlNumber")
	public void setProviderControlNumber(String providerControlNumber) {
		this.providerControlNumber = providerControlNumber;
	}

	@JsonProperty("hospiceEmployeeIndicator")
	public Boolean getHospiceEmployeeIndicator() {
		return hospiceEmployeeIndicator;
	}

	@JsonProperty("hospiceEmployeeIndicator")
	public void setHospiceEmployeeIndicator(Boolean hospiceEmployeeIndicator) {
		this.hospiceEmployeeIndicator = hospiceEmployeeIndicator;
	}

	@JsonProperty("ambulancePatientCount")
	public Integer getAmbulancePatientCount() {
		return ambulancePatientCount;
	}

	@JsonProperty("ambulancePatientCount")
	public void setAmbulancePatientCount(Integer ambulancePatientCount) {
		this.ambulancePatientCount = ambulancePatientCount;
	}

	@JsonProperty("obstetricAnesthesiaAdditionalUnits")
	public Integer getObstetricAnesthesiaAdditionalUnits() {
		return obstetricAnesthesiaAdditionalUnits;
	}

	@JsonProperty("obstetricAnesthesiaAdditionalUnits")
	public void setObstetricAnesthesiaAdditionalUnits(Integer obstetricAnesthesiaAdditionalUnits) {
		this.obstetricAnesthesiaAdditionalUnits = obstetricAnesthesiaAdditionalUnits;
	}

	@JsonProperty("salesTaxAmount")
	public String getSalesTaxAmount() {
		return salesTaxAmount;
	}

	@JsonProperty("salesTaxAmount")
	public void setSalesTaxAmount(String salesTaxAmount) {
		this.salesTaxAmount = salesTaxAmount;
	}

	@JsonProperty("postageTaxAmount")
	public String getPostageTaxAmount() {
		return postageTaxAmount;
	}

	@JsonProperty("postageTaxAmount")
	public void setPostageTaxAmount(String postageTaxAmount) {
		this.postageTaxAmount = postageTaxAmount;
	}

	@JsonProperty("additionalNotes")
	public String getAdditionalNotes() {
		return additionalNotes;
	}

	@JsonProperty("additionalNotes")
	public void setAdditionalNotes(String additionalNotes) {
		this.additionalNotes = additionalNotes;
	}

	@JsonProperty("goalRehabOrDischargePlans")
	public String getGoalRehabOrDischargePlans() {
		return goalRehabOrDischargePlans;
	}

	@JsonProperty("goalRehabOrDischargePlans")
	public void setGoalRehabOrDischargePlans(String goalRehabOrDischargePlans) {
		this.goalRehabOrDischargePlans = goalRehabOrDischargePlans;
	}

	@JsonProperty("thirdPartyOrganizationNotes")
	public String getThirdPartyOrganizationNotes() {
		return thirdPartyOrganizationNotes;
	}

	@JsonProperty("thirdPartyOrganizationNotes")
	public void setThirdPartyOrganizationNotes(String thirdPartyOrganizationNotes) {
		this.thirdPartyOrganizationNotes = thirdPartyOrganizationNotes;
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