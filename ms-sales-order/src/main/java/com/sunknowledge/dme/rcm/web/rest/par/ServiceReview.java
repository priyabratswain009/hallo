package com.sunknowledge.dme.rcm.web.rest.par;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.domain.EparResponse;
import com.sunknowledge.dme.rcm.domain.ParMaster;
import com.sunknowledge.dme.rcm.domain.ParSoMap;
import com.sunknowledge.dme.rcm.domain.SalesOrderClinicalDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderInsuranceDetails;
import com.sunknowledge.dme.rcm.domain.SalesOrderItemDetails;
import com.sunknowledge.dme.rcm.domain.ServiceReview.Diagnosis;
import com.sunknowledge.dme.rcm.domain.ServiceReview.Patient;
import com.sunknowledge.dme.rcm.domain.ServiceReview.Payer;
import com.sunknowledge.dme.rcm.domain.ServiceReview.Procedure;
import com.sunknowledge.dme.rcm.domain.ServiceReview.RenderingProvider;
import com.sunknowledge.dme.rcm.domain.ServiceReview.RequestingProvider;
import com.sunknowledge.dme.rcm.domain.ServiceReview.ServiceReviewInput;
import com.sunknowledge.dme.rcm.domain.ServiceReview.ServiceReviewResponseDTO;
import com.sunknowledge.dme.rcm.domain.ServiceReview.Subscriber;
import com.sunknowledge.dme.rcm.service.dto.EparRequestDTO;
import com.sunknowledge.dme.rcm.service.dto.ParMasterDTO;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;
import com.sunknowledge.dme.rcm.service.mapper.ParMasterMapper;
import com.sunknowledge.dme.rcm.service.par.ElectronicPARRequestService;
import com.sunknowledge.dme.rcm.service.par.ElectronicPARResponseService;
import com.sunknowledge.dme.rcm.service.par.PriorAuthorizationService;
import com.sunknowledge.dme.rcm.service.par.ServiceReviewService;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderClinicalDetailsServiceExtended;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderInsuranceDetailsSearchServiceExtended;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderItemDetailsServiceExtended;
import com.sunknowledge.dme.rcm.service.soentryandsearch.SalesOrderMasterServiceExtented;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/par")
@Slf4j
public class ServiceReview {

	@Autowired
	ServiceReviewService serviceReviewService;
	@Autowired
	SalesOrderClinicalDetailsServiceExtended salesOrderClinicalDetailsServiceExtended;
	@Autowired
	SalesOrderItemDetailsServiceExtended salesOrderItemDetailsServiceExtended;
	@Autowired
	SalesOrderMasterServiceExtented salesOrderMasterServiceExtented;
	@Autowired
	SalesOrderInsuranceDetailsSearchServiceExtended salesOrderInsuranceDetailsSearchService;
	@Autowired
	ElectronicPARRequestService electronicPARRequestService;
	@Autowired
	ElectronicPARResponseService electronicPARResponseService;
	@Autowired
	PriorAuthorizationService priorAuthorizationService;
	@Autowired
	ParMasterMapper parMasterMapper;

	@PostMapping("/createServiceReview")
	public Mono<ServiceOutcome<String>> createServiceReview(@RequestParam("salesOrderId") Long salesOrderId)
			throws InterruptedException, ExecutionException, JsonProcessingException {

		ServiceOutcome<String> outCome = new ServiceOutcome<String>();
		SalesOrderInsuranceDetails salesOrderInsuranceDetails = null;
		SalesOrderMasterDTO salesOrderMasterDTO = null;
		SalesOrderClinicalDetails salesOrderClinicalDetailsDTO = null;
		List<SalesOrderItemDetails> salesOrderItemDetails = null;

		if (salesOrderId != null) {

			try {
				ServiceOutcome serviceOutcomeclinical = salesOrderClinicalDetailsServiceExtended
						.getSOClinicalBySOID(salesOrderId).toFuture().get();
				ServiceOutcome serviceOutcomeMaster = salesOrderMasterServiceExtented.getSOBySoId(salesOrderId)
						.toFuture().get();
				salesOrderItemDetails = salesOrderItemDetailsServiceExtended.findBySalesOrderId(salesOrderId)
						.collectList().toFuture().get();
				salesOrderInsuranceDetails = salesOrderInsuranceDetailsSearchService.findBySalesOrderId(salesOrderId)
						.toFuture().get();
				if (serviceOutcomeMaster.getOutcome()) {
					salesOrderMasterDTO = (SalesOrderMasterDTO) serviceOutcomeMaster.getData();
				}
				if (serviceOutcomeclinical.getOutcome()) {
					salesOrderClinicalDetailsDTO = (SalesOrderClinicalDetails) serviceOutcomeclinical.getData();
				}

			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} catch (ExecutionException e) {
				throw new RuntimeException(e);
			}

		}

		ServiceReviewInput serviceReviewInput = new ServiceReviewInput();

		// Payer
		Payer payer = new Payer();
		payer.setId(salesOrderInsuranceDetails.getPrimaryInsurerId().toString());
		payer.setName(salesOrderInsuranceDetails.getPrimaryInsurerName());
		serviceReviewInput.setPayer(payer);
		// Requesting Provider
		RequestingProvider requestingProvider = new RequestingProvider();
		requestingProvider.setLastName(salesOrderMasterDTO.getBillingBranchName());
		requestingProvider.setNpi(salesOrderMasterDTO.getBillingProviderNpi());
		requestingProvider.setSubmitterId(salesOrderMasterDTO.getBranchEin());
		requestingProvider.setAddressLine1(salesOrderMasterDTO.getBillingProviderAddressLine1() + " "
				+ salesOrderMasterDTO.getBillingProviderAddressLine2());
		requestingProvider.setCity(salesOrderMasterDTO.getBillingProviderCity());
		requestingProvider.setStateCode(salesOrderMasterDTO.getBillingProviderState());
		requestingProvider.setZipCode(salesOrderMasterDTO.getBillingProviderZipCode());
		requestingProvider.setContactName(salesOrderMasterDTO.getBranchContactPersonName());
		requestingProvider.setPhone(salesOrderMasterDTO.getBranchContactNo1());
		requestingProvider.setState(salesOrderMasterDTO.getBillingProviderState());
		serviceReviewInput.setRequestingProvider(requestingProvider);
		// Subscriber
		Subscriber subscriber = new Subscriber();
		subscriber.setFirstName(salesOrderMasterDTO.getInsuredFirstName());
		subscriber.setLastName(salesOrderMasterDTO.getInsuredLastName());
		subscriber.setAddressLine1(salesOrderMasterDTO.getInsuredAddressLine1());
		subscriber.setAddressLine2(salesOrderMasterDTO.getInsuredAddressLine2());
		subscriber.setCity(salesOrderMasterDTO.getInsuredCity());
		subscriber.setState(salesOrderMasterDTO.getInsuredState());
		subscriber.setZipCode(salesOrderMasterDTO.getInsuredZip());
		serviceReviewInput.setSubscriber(subscriber);
		// Patient
		Patient patient = new Patient();
		patient.setFirstName(salesOrderMasterDTO.getPatientFirstName());
		patient.setMiddleName(salesOrderMasterDTO.getPatientMiddleName());
		patient.setLastName(salesOrderMasterDTO.getPatientLastName());
		patient.setSubscriberRelationshipCode(salesOrderMasterDTO.getPatientRelationshipInsured());
		patient.setBirthDate(salesOrderMasterDTO.getPatientDob().toString());
		patient.setGender(salesOrderMasterDTO.getPatientGender());
		patient.setAddressLine1(salesOrderMasterDTO.getPatientAddressLine1());
		patient.setAddressLine2(salesOrderMasterDTO.getPatientAddressLine2());
		patient.setCity(salesOrderMasterDTO.getPatientDeliveryCity());
		patient.setState(salesOrderMasterDTO.getPatientDeliveryState());
		patient.setZipCode(salesOrderMasterDTO.getDeliveryZipCode());
		serviceReviewInput.setPatient(patient);
		// Diagnosis
		Diagnosis diagnosis = new Diagnosis();
		List<Diagnosis> diagnosisList = new ArrayList<>();
		diagnosis.setQualifierCode(salesOrderClinicalDetailsDTO.getDiagnosisCodeType());
		diagnosis.setCode(salesOrderClinicalDetailsDTO.getIcd10DiagnosisCode1());
		diagnosisList.add(diagnosis);
		serviceReviewInput.setDiagnoses(diagnosisList);

		// Procedure
		List<Procedure> procedurelist = new ArrayList<>();

		LocalDate dateOfService = salesOrderItemDetails.get(0).getSalesOrderDetailsOriginalDos();
		for (SalesOrderItemDetails obj : salesOrderItemDetails) {

			Procedure procedure = new Procedure();
			// For HCPCS Code
			procedure.setQualifierCode("HC");
			procedure.setValue(obj.getSalesOrderDetailsChargeAmt().toString());
			procedure.setCode(obj.getSalesOrderDetailsProcCode());
			procedure.setDescription(obj.getSalesOrderDetailsItemDescription());
			procedure.setQuantity(obj.getSalesOrderDetailsQty().toString());
			procedure.setQuantityqualifier(obj.getSalesOrderDetailsStockingUom());
			procedurelist.add(procedure);

			if (obj.getSalesOrderDetailsOriginalDos().isBefore(dateOfService)) {
				dateOfService = obj.getSalesOrderDetailsOriginalDos();
			}
		}

		serviceReviewInput.setProcedures(procedurelist);

		serviceReviewInput.setRequestTypeCode("HS");
		// validate from item details
		serviceReviewInput.setServiceTypeCode("12");
		serviceReviewInput.setPlaceOfServiceCode(salesOrderMasterDTO.getPosCode());
		serviceReviewInput.setFromDate(dateOfService.toString());

		// Rendering Provider
		RenderingProvider renderingProvider = new RenderingProvider();
		List<RenderingProvider> renderingProviderList = new ArrayList<>();
		renderingProvider.setFirstName(salesOrderClinicalDetailsDTO.getRenderingProviderFirstName());
		renderingProvider.setLastName(salesOrderClinicalDetailsDTO.getRenderingProviderLastName());
		renderingProvider.setNpi(salesOrderClinicalDetailsDTO.getRenderingProviderNpi());
		renderingProvider.setSpecialtyCode(salesOrderMasterDTO.getBranchTaxonomy());
		renderingProvider.setAddressLine1(salesOrderClinicalDetailsDTO.getRenderingProviderAddressLine1());
		renderingProvider.setCity(salesOrderClinicalDetailsDTO.getRenderingProviderCity());
		renderingProvider.setState(salesOrderClinicalDetailsDTO.getRenderingProviderState());
		renderingProvider.setZipCode(salesOrderClinicalDetailsDTO.getRenderingProviderZip());
		renderingProviderList.add(renderingProvider);

		serviceReviewInput.setRenderingProviders(renderingProviderList);

		SalesOrderMasterDTO finalSalesOrderMasterDTO = salesOrderMasterDTO;
		return serviceReviewService.createServiceReview(serviceReviewInput).flatMap(data -> {
			try {
				return electronicPARRequestService
						.createServiceReview(serviceReviewInput, salesOrderId,
								finalSalesOrderMasterDTO.getSalesOrderNo(), data.getMessage(), data.getData())
						.map(data2 -> {
							System.out.println("data2 " + data2);
							return data;
						});
			} catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
		});

		// return Mono.just(outCome);

	}

	@GetMapping("/getServiceReviewById")
	public Mono<ServiceOutcome<ServiceReviewResponseDTO>> getServiceReviewById(@RequestParam("soId") Long soId)
			throws InterruptedException, ExecutionException, JsonProcessingException {

		ServiceOutcome<EparRequestDTO> requestDTO = electronicPARRequestService.getEpaRequestonSoId(soId).toFuture()
				.get();
		System.out.println("Testing - " + requestDTO.getData().getEparRequestId());
		String serviceId = requestDTO.getData().getResponseUrl().substring(
				requestDTO.getData().getResponseUrl().lastIndexOf('/') + 1,
				requestDTO.getData().getResponseUrl().length());
		ServiceOutcome<ServiceReviewResponseDTO> outCome = serviceReviewService.getServiceReviewById(serviceId)
				.toFuture().get();
		ServiceReviewResponseDTO serviceReviewResponseDTO = outCome.getData();
		SalesOrderInsuranceDetails salesOrderInsuranceDetails = null;
		SalesOrderMasterDTO salesOrderMasterDTO = null;
		SalesOrderClinicalDetails salesOrderClinicalDetailsDTO = null;
		List<SalesOrderItemDetails> salesOrderItemDetails = null;

		if (soId != null) {

			try {
				ServiceOutcome serviceOutcomeclinical = salesOrderClinicalDetailsServiceExtended
						.getSOClinicalBySOID(soId).toFuture().get();
				ServiceOutcome serviceOutcomeMaster = salesOrderMasterServiceExtented.getSOBySoId(soId).toFuture()
						.get();
				salesOrderItemDetails = salesOrderItemDetailsServiceExtended.findBySalesOrderId(soId).collectList()
						.toFuture().get();
				salesOrderInsuranceDetails = salesOrderInsuranceDetailsSearchService.findBySalesOrderId(soId).toFuture()
						.get();
				if (serviceOutcomeMaster.getOutcome()) {
					salesOrderMasterDTO = (SalesOrderMasterDTO) serviceOutcomeMaster.getData();
				}
				if (serviceOutcomeclinical.getOutcome()) {
					salesOrderClinicalDetailsDTO = (SalesOrderClinicalDetails) serviceOutcomeclinical.getData();
				}

			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} catch (ExecutionException e) {
				throw new RuntimeException(e);
			}

		}

		if (outCome != null) {

			ParMaster parMaster = new ParMaster();
			String parIdNo = priorAuthorizationService.getParNo();
			String recievedparNo = "123";
			// should be
			// serviceReviewResponseDTO.getSupplementalInformation().getRefAuthNumber()
			System.out.println("PAR NO - " + parIdNo);

			ParMasterDTO parMasterDTO = priorAuthorizationService.getParMasterbyParNo(recievedparNo).toFuture().get()
					.getData();

			parMaster.setPatientId(salesOrderMasterDTO.getPatientId());
			parMaster.setPatientIdNumber(salesOrderMasterDTO.getPatientIdNo());
			parMaster.setPatientFirstName(salesOrderMasterDTO.getPatientFirstName());
			parMaster.setPatientLastName(salesOrderMasterDTO.getPatientLastName());
			parMaster.setPatientDob(salesOrderMasterDTO.getPatientDob());
			parMaster.setPatientGender(salesOrderMasterDTO.getPatientGender());
			parMaster.setInsuranceId(salesOrderInsuranceDetails.getPrimaryInsurerId());
			parMaster.setInsuranceName(salesOrderInsuranceDetails.getPrimaryInsurerName());
			parMaster.setPayerIdNo(salesOrderInsuranceDetails.getPrimaryInsurancePayerId());
			parMaster.setPayerLevel("P");
			parMaster.setPolicyNumber(salesOrderInsuranceDetails.getPrimaryInsurerPolicyNo());
			parMaster.setPolicyStartDate(salesOrderInsuranceDetails.getPrimaryInsurerEffectiveDate());
			parMaster.setPolicyEndDate(salesOrderInsuranceDetails.getPrimaryInsurerPolicyEndDate());
			parMaster.setPayerContactName(salesOrderInsuranceDetails.getPrimaryInsurerContactName());
			parMaster.setPayerContactNumber(salesOrderInsuranceDetails.getPrimaryInsurerContact1());
			parMaster.setDateOfContact(LocalDate.now());
			if (CommonUtilities.isStringNullOrBlank(serviceReviewResponseDTO.getCertificationIssueDate())) {
				parMaster.setInitialDate(CommonUtilities
						.stringwithhyphentodateConverter(serviceReviewResponseDTO.getCertificationIssueDate()));
			}
			// validating if PAR already exists with this par no
			if (parMasterDTO == null) {
				parMaster.setParNo(recievedparNo);
				if (CommonUtilities.isStringNullOrBlank(serviceReviewResponseDTO.getCertificationEffectiveDate())) {
					parMaster.setEffectiveStartDate(CommonUtilities
							.stringwithhyphentodateConverter(serviceReviewResponseDTO.getCertificationEffectiveDate()));
				}
				if (CommonUtilities.isStringNullOrBlank(serviceReviewResponseDTO.getCertificationExpirationDate())) {
					parMaster.setExpirationDate(CommonUtilities.stringwithhyphentodateConverter(
							serviceReviewResponseDTO.getCertificationExpirationDate()));
				}
			} else {
				parMaster.setExtensionStatus("Extended");
				parMaster.setExtensionApprovalDate(CommonUtilities
						.stringwithhyphentodateConverter(serviceReviewResponseDTO.getCertificationIssueDate()));
			}
			parMaster.setParStatus("Active");
			parMaster.setLogInStatus("Logged");
			parMaster.setLogInDate(LocalDate.now());
			parMaster.setStatus("Active");
			parMaster.setCreatedById(Long.valueOf("5501"));
			parMaster.setCreatedByName("ANDROKTASIAI");
			parMaster.setCreatedDate(LocalDate.now());
			parMaster.setParIdNo(parIdNo);

			priorAuthorizationService.saveParMaster(parMaster);

			ParMaster parMasterbyIdNo = parMasterMapper
					.toEntity(priorAuthorizationService.getParMasterbyParIdNo(parIdNo).toFuture().get().getData());

			ParSoMap parSoMap = new ParSoMap();
			parSoMap.setParId(parMasterbyIdNo.getParId());
			parSoMap.setParNo(parMasterbyIdNo.getParNo());
			parSoMap.setSoId(soId);
			parSoMap.setSoNo(salesOrderMasterDTO.getSalesOrderNo());
			parSoMap.setStatus("Active");
			parSoMap.setCreatedById(Long.valueOf("5501"));
			parSoMap.setCreatedByName("ANDROKTASIAI");
			parSoMap.setCreatedDate(LocalDate.now());

			priorAuthorizationService.saveParSoMap(parSoMap);
			EparResponse eParResponse = new EparResponse();
			ObjectMapper objectMapper = new ObjectMapper();

			eParResponse.setEparRequestId(requestDTO.getData().getEparRequestId());
			eParResponse.setParId(parMasterbyIdNo.getParId());
			eParResponse.setParNo(serviceReviewResponseDTO.getSupplementalInformation().getRefAuthNumber());
			eParResponse.setPayerIdNo(serviceReviewResponseDTO.getPayer().getId());
			eParResponse.setPayerName(serviceReviewResponseDTO.getPayer().getName());
			eParResponse.setPatientFirstName(serviceReviewResponseDTO.getPatient().getFirstName());
			eParResponse.setPatientLastName(serviceReviewResponseDTO.getPatient().getLastName());
			eParResponse.setSubscriberRelationship(serviceReviewResponseDTO.getPatient().getSubscriberRelationship());
			eParResponse.setCertificationEffectiveDate(CommonUtilities
					.stringwithhyphentodateConverter(serviceReviewResponseDTO.getCertificationEffectiveDate()));
			eParResponse.setCertificationExpirationDate(CommonUtilities
					.stringwithhyphentodateConverter(serviceReviewResponseDTO.getCertificationExpirationDate()));
			eParResponse.setRequestType(serviceReviewResponseDTO.getRequestType());
			eParResponse.setPlaceOfService(serviceReviewResponseDTO.getPlaceOfService());
			eParResponse.setServiceLevel(serviceReviewResponseDTO.getServiceLevel());
			eParResponse.setQuantity(serviceReviewResponseDTO.getQuantity());
			eParResponse.setQuantityType(serviceReviewResponseDTO.getQuantityType());
			eParResponse.setResponseCreateDate(
					CommonUtilities.stringwithhyphentodateConverter(serviceReviewResponseDTO.getCreatedDate()));
			eParResponse.setResponseResponseDate(LocalDate.now());
			eParResponse.setJsonData(objectMapper.writeValueAsString(serviceReviewResponseDTO));
			eParResponse.setStatus("Active");
			eParResponse.setCreatedById(Long.valueOf("5501"));
			eParResponse.setCreatedByName("ANDROKTASIAI");
			eParResponse.setCreatedDate(LocalDate.now());

			electronicPARResponseService.saveElectronicPar(eParResponse);

		}

		return Mono.just(outCome);

	}
}
