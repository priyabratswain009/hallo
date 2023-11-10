package com.sunknowledge.dme.rcm.service.claimservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.dme.rcm.application.utils.ApplicationConstants;
import com.sunknowledge.dme.rcm.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.core.TokenOutCome;
import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import com.sunknowledge.dme.rcm.domain.PrimaryClaimsReSubmissionMaster;
import com.sunknowledge.dme.rcm.domain.PrimaryResubmissionServiceLinesMaster;
import com.sunknowledge.dme.rcm.pojo.HealthCheckResponse;
import com.sunknowledge.dme.rcm.pojo.Resubmission.ClaimInformation;
import com.sunknowledge.dme.rcm.pojo.Resubmission.ClaimSubmissionInput;
import com.sunknowledge.dme.rcm.pojo.Resubmission.ClaimSupplementalInformation;
import com.sunknowledge.dme.rcm.pojo.common.AccessTokenError;
import com.sunknowledge.dme.rcm.pojo.common.Address;
import com.sunknowledge.dme.rcm.pojo.common.Billing;
import com.sunknowledge.dme.rcm.pojo.common.ClaimSubmissionOutput;
import com.sunknowledge.dme.rcm.pojo.common.CompositeDiagnosisCodePointers;
import com.sunknowledge.dme.rcm.pojo.common.ContactInformation;
import com.sunknowledge.dme.rcm.pojo.common.HealthCareCodeInformation;
import com.sunknowledge.dme.rcm.pojo.common.OrderingProvider;
import com.sunknowledge.dme.rcm.pojo.common.ProfessionalClaimsErrorHandler;
import com.sunknowledge.dme.rcm.pojo.common.ProfessionalService;
import com.sunknowledge.dme.rcm.pojo.common.Receiver;
import com.sunknowledge.dme.rcm.pojo.common.ResultClaimSubmissionOutcome;
import com.sunknowledge.dme.rcm.pojo.common.Submitter;
import com.sunknowledge.dme.rcm.pojo.common.Subscriber;
import com.sunknowledge.dme.rcm.pojo.primary.ServiceLine;
import com.sunknowledge.dme.rcm.repository.primaryclaimrepository.ClaimSubmissionStatusRepo;
import com.sunknowledge.dme.rcm.repository.primaryresubmissionclaimrepository.PrimaryClaimsReSubmissionMasterRepo;
import com.sunknowledge.dme.rcm.repository.primaryresubmissionclaimrepository.PrimaryResubmissionServiceLinesMasterRepo;
import com.sunknowledge.dme.rcm.validation.DmeUtilities;

@Service
public class PrimaryClaimReSubmissionServiceImpl implements PrimaryClaimReSubmissionService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private TokenGenerationServiceImpl tokenGenerationServiceImpl;
	@Autowired
	private PrimaryClaimsReSubmissionMasterRepo primaryClaimsReSubmissionMasterRepository;
	@Autowired
	private PrimaryResubmissionServiceLinesMasterRepo primaryResubmissionServiceLinesMasterRepository;
	@Autowired
	private ClaimSubmissionStatusRepo claimSubmissionStatusRepo;

	@Override
	public ServiceOutcome<ResultClaimSubmissionOutcome> accessProfessionalClaimsSubmission(String soId, String claimControlNumber) {
		ServiceOutcome<ResultClaimSubmissionOutcome> routcome = new ServiceOutcome<ResultClaimSubmissionOutcome>();
		// setting submission date
		LocalDate currentdate = LocalDate.now();
		ResultClaimSubmissionOutcome claimOutcome = new ResultClaimSubmissionOutcome();
		TokenOutCome objTokenOutCome = new TokenOutCome();
		PrimaryClaimsReSubmissionMaster claims = primaryClaimsReSubmissionMasterRepository
				.getPrimarySubmissionDetails(Long.valueOf(soId));
		ClaimSubmissionStatus objClaimSubmissionStatus = claimSubmissionStatusRepo
				.getClaimSubmissionStatus(claims.getClaimControlNo());
		System.out.println("Before Original Claim No");
		ClaimSubmissionStatus originalClaimNo = claimSubmissionStatusRepo
				.getOriginalClaimControlNumber(objClaimSubmissionStatus.getSalesOrderNo(), objClaimSubmissionStatus.getPeriodNo());
		System.out.println("After Original Claim No");
		List<PrimaryResubmissionServiceLinesMaster> serviceLines = primaryResubmissionServiceLinesMasterRepository
				.getServiceLineMasterDataOnClaimSubmission(claims.getChangeHealthPrimaryResubmisionMasterId());
		boolean isAfter = claims.getOriginalDos().isAfter(currentdate);
		if (!isAfter) {
			ClaimSubmissionInput objclaimSubmissionInput = setdata(claims, serviceLines);
			try {
				objTokenOutCome = tokenGenerationServiceImpl.getToken();
				String token = objTokenOutCome.getTokenResponseOutput().getAccessToken();
				if (professionalClaimHealthCheck(token).getOutcome()) {
					routcome = professionalClaimSubmission(token, objclaimSubmissionInput);
					String payorClaimNumber = routcome.getData().getClaimSubmissionOutput().getClaimReference()
							.getRhclaimNumber();
					String status = routcome.getData().getClaimSubmissionOutput().getStatus();
					String submissionNote = routcome.getData().getClaimSubmissionOutput().getClaimReference()
							.getCorrelationId() + "=="
							+ routcome.getData().getClaimSubmissionOutput().getClaimReference().getCustomerClaimNumber()
							+ "==" + routcome.getData().getClaimSubmissionOutput().getMeta().getTraceId();
					objClaimSubmissionStatus.setClaimSubmissionDate(currentdate);
					objClaimSubmissionStatus.setPayorClaimControlNo(payorClaimNumber);
					objClaimSubmissionStatus.setSubmissionStatus(status);
					objClaimSubmissionStatus.setSubmissionNote(submissionNote);
					objClaimSubmissionStatus.setOriginalClaimControlNo(originalClaimNo.getIntClaimNo());
					claimSubmissionStatusRepo.save(objClaimSubmissionStatus);
				} else {
					claimOutcome.setAccessTokenError(null);
					claimOutcome.setClaimSubmissionOutput(null);
					claimOutcome.setHealthCheckOutcome(null);
					claimOutcome.setErrorhandler(null);
					routcome.setData(claimOutcome);
					routcome.setMessage("Unauthorized token access.");
					routcome.setOutcome(false);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			claimOutcome.setAccessTokenError(null);
			claimOutcome.setClaimSubmissionOutput(null);
			claimOutcome.setHealthCheckOutcome(null);
			claimOutcome.setErrorhandler(null);
			routcome.setData(claimOutcome);
			routcome.setMessage("Date Of Service cannot be After CurrentDate");
			routcome.setOutcome(false);
		}
		return routcome;
	}

	@Override
	public ServiceOutcome<ResultClaimSubmissionOutcome> professionalClaimSubmission(String token,
			ClaimSubmissionInput claimSubmissionInput) {
		String inputEntity = "";
		ClaimSubmissionOutput professionalClaimSubmissionOutcome = null;
		String url = ApplicationConstants.PROFESSIONALCLAIMS_SUBMISSION_URL;
		ServiceOutcome<ResultClaimSubmissionOutcome> routcome = new ServiceOutcome<ResultClaimSubmissionOutcome>();
		ResultClaimSubmissionOutcome claimOutcome = new ResultClaimSubmissionOutcome();
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.set("Authorization", "Bearer " + token);
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			inputEntity = ow.writeValueAsString(claimSubmissionInput);
			
			System.out.println("INPUT========= "+inputEntity);

			HttpEntity<String> inputCriteriaEntity = new HttpEntity<String>(inputEntity, headers);
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, inputCriteriaEntity,
					String.class);
			if (response.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				professionalClaimSubmissionOutcome = mapper.readValue(response.getBody(), ClaimSubmissionOutput.class);
				if (professionalClaimSubmissionOutcome.getStatus().equals("SUCCESS")) {
					ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
					claimOutcome.setClaimSubmissionOutput(professionalClaimSubmissionOutcome);
					routcome.setData(claimOutcome);
					routcome.setMessage("Successfully get the data");
					routcome.setOutcome(true);
				}
			} else {
				routcome.setData(null);
				routcome.setMessage("Host Unreachable");
				routcome.setOutcome(false);
			}
		} catch (HttpStatusCodeException e) {
			ResponseEntity<String> responseException = ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(e.getResponseBodyAsString());
			if (responseException.getStatusCodeValue() == 400) {
				try {
					ObjectMapper mapper = new ObjectMapper();
					String errorResponse = responseException.getBody();
					ProfessionalClaimsErrorHandler errorHandler = mapper.readValue(errorResponse,
							ProfessionalClaimsErrorHandler.class);

					claimOutcome.setErrorhandler(errorHandler);
					routcome.setData(claimOutcome);
					routcome.setMessage("Bad request");
					routcome.setOutcome(false);
				} catch (Exception a) {
					a.printStackTrace();
				}
			} else {
				routcome.setData(null);
				routcome.setMessage("Host Unreachable");
				routcome.setOutcome(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return routcome;
	}

	@Override
	public ServiceOutcome<ResultClaimSubmissionOutcome> professionalClaimHealthCheck(String token) {
		boolean response = false;
		ServiceOutcome<ResultClaimSubmissionOutcome> routcome = new ServiceOutcome<ResultClaimSubmissionOutcome>();
		ResultClaimSubmissionOutcome claimOutcome = new ResultClaimSubmissionOutcome();
		try {
			String url = ApplicationConstants.PROFESSIONALCLAIMS_HEALTHCHECK_URL;
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json");
			headers.add("Accept", "application/json");
			headers.set("Authorization", "Bearer " + token);
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity,
					String.class);
			if (responseEntity.getStatusCode() == HttpStatus.OK) {
				ObjectMapper mapper = new ObjectMapper();
				HealthCheckResponse healthCheckResponse = mapper.readValue(responseEntity.getBody(),
						HealthCheckResponse.class);
				if (healthCheckResponse.getVersion().equalsIgnoreCase("v3")
						&& healthCheckResponse.getStatus().equalsIgnoreCase("ok")) {
					response = true;
					claimOutcome.setHealthCheckOutcome(null);
					routcome.setData(claimOutcome);
					routcome.setMessage("Professional health check! OK.");
					routcome.setOutcome(response);
				} else {
					response = false;
					claimOutcome.setHealthCheckOutcome(null);
					routcome.setData(claimOutcome);
					routcome.setMessage("Professional health check! NOT OK.");
					routcome.setOutcome(response);
				}
			}
		} catch (HttpStatusCodeException hsce) {
			ResponseEntity<String> responseException = ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(hsce.getResponseBodyAsString());
			try {
				ObjectMapper mapper = new ObjectMapper();
				String errorResponse = responseException.getBody();
				AccessTokenError accessTokenError = mapper.readValue(errorResponse, AccessTokenError.class);
				claimOutcome.setAccessTokenError(accessTokenError);
				routcome.setData(claimOutcome);
				routcome.setMessage("Unauthorized token access.");
				routcome.setOutcome(response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return routcome;
	}

	@SuppressWarnings("null")
	ClaimSubmissionInput setdata(PrimaryClaimsReSubmissionMaster claims,
			List<PrimaryResubmissionServiceLinesMaster> serviceLines) {

		ClaimSubmissionInput objclaimSubmissionInput = new ClaimSubmissionInput();
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getClaimControlNo()))) {
			// Sand Box Value
			objclaimSubmissionInput.setControlNumber("123456789");
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getTradingPartnerServiceId()))) {
			objclaimSubmissionInput.setTradingPartnerServiceId(String.valueOf(claims.getTradingPartnerServiceId()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getTradingPartnerName()))) {
			objclaimSubmissionInput.setTradingPartnerName(String.valueOf(claims.getTradingPartnerName()));
		}
		// Submitter Object
		Submitter objsubmitter = new Submitter();
		ContactInformation objsubmittercontactInformation = new ContactInformation();
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubmitterContactPersonName()))) {
			objsubmittercontactInformation.setName(String.valueOf(claims.getSubmitterContactPersonName()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubmitterContactNo()))) {
			objsubmittercontactInformation.setPhoneNumber(String.valueOf(claims.getSubmitterContactNo()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubmitterOrganizationName()))) {
			objsubmitter.setOrganizationName(claims.getSubmitterOrganizationName());
		}
		objsubmitter.setContactInformation(objsubmittercontactInformation);
		objclaimSubmissionInput.setSubmitter(objsubmitter);
		// Reciever Object
		Receiver objReceiver = new Receiver();
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getReceiverOrganizationName()))) {
			objReceiver.setOrganizationName(String.valueOf(claims.getReceiverOrganizationName()));
		}
		objclaimSubmissionInput.setReceiver(objReceiver);
		// Subscriber
		Subscriber objSubscriber = new Subscriber();
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubscriberMemberIdNo()))) {
			//HARDCODING FOR CHC 
			objSubscriber.setMemberId("0000000001");
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubscriberPaymentResponsibilityLevelCode()))) {
			objSubscriber.setPaymentResponsibilityLevelCode(
					String.valueOf(claims.getSubscriberPaymentResponsibilityLevelCode()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubscriberFirstName()))) {
			objSubscriber.setFirstName(String.valueOf(claims.getSubscriberFirstName()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubscriberLastName()))) {
			objSubscriber.setLastName(String.valueOf(claims.getSubscriberLastName()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubscriberGender()))) {
			objSubscriber.setGender(String.valueOf(claims.getSubscriberGender()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubscriberDob()))) {
			objSubscriber.setDateOfBirth(DmeUtilities.dateConverter(String.valueOf(claims.getSubscriberDob())));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getPrimaryInsurerPolicyNo()))) {
			objSubscriber.setPolicyNumber(String.valueOf(claims.getPrimaryInsurerPolicyNo()));
		}
		// Subscriber Address
		Address subAddress = new Address();
		Address billProAddress = new Address();
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubscriberAddressLine1()))) {
			subAddress.setAddress1(String.valueOf(claims.getSubscriberAddressLine1()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubscriberCity()))) {
			subAddress.setCity(String.valueOf(claims.getSubscriberCity()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubscriberState()))) {
			subAddress.setState(String.valueOf(claims.getSubscriberState()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubscriberZipCode()))) {
			subAddress.setPostalCode(String.valueOf(claims.getSubscriberZipCode()));
		}
		objSubscriber.setAddress(subAddress);
		objclaimSubmissionInput.setSubscriber(objSubscriber);
		// Billing Provider
		Billing objprovider = new Billing();
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getBillingProviderNpi()))) {
			objprovider.setProviderType("BillingProvider");
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getBillingProviderNpi()))) {
			objprovider.setNpi(String.valueOf(claims.getBillingProviderNpi()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getBillingProviderEin()))) {
			objprovider.setEmployerId(String.valueOf(claims.getBillingProviderEin()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getBillingProviderOrganizationName()))) {
			objprovider.setOrganizationName(String.valueOf(claims.getBillingProviderOrganizationName()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getBillingProviderAddressLine1()))) {
			billProAddress.setAddress1(String.valueOf(claims.getBillingProviderAddressLine1()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getBillingProviderCity()))) {
			billProAddress.setCity(String.valueOf(claims.getBillingProviderCity()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getBillingProviderState()))) {
			billProAddress.setState(String.valueOf(claims.getBillingProviderState()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getBillingProviderZipCode()))) {
			billProAddress.setPostalCode(String.valueOf(claims.getBillingProviderZipCode()));
		}
		objprovider.setAddress(billProAddress);
		ContactInformation objprovcontactInformation = new ContactInformation();
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getBillingProviderContactPersonName()))) {
			objprovcontactInformation.setName(String.valueOf(claims.getBillingProviderContactPersonName()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getBillingProviderContactNo()))) {
			objprovcontactInformation.setPhoneNumber(String.valueOf(claims.getBillingProviderContactNo()));
		}
		objclaimSubmissionInput.setBilling(objprovider);
		// ClaimInformation
		ClaimInformation objClaimInformation = new ClaimInformation();
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getClaimFilingCode()))) {
			objClaimInformation.setClaimFilingCode(String.valueOf(claims.getClaimFilingCode()));
		}
		// need to change/update this
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getClaimControlNo()))) {
			//HARDCODING FOR CHC 
			objClaimInformation.setPatientControlNumber("12345");
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getClaimChargeAmount()))) {
			objClaimInformation.setClaimChargeAmount(String.valueOf(claims.getClaimChargeAmount()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getPosCode()))) {
			objClaimInformation.setPlaceOfServiceCode(String.valueOf(claims.getPosCode()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getClaimFrequencyCode()))) {
			objClaimInformation.setClaimFrequencyCode(String.valueOf(claims.getClaimFrequencyCode()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSignatureIndicator()))) {
			objClaimInformation.setSignatureIndicator(String.valueOf(claims.getSignatureIndicator()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getPlanParticipationCode()))) {
			objClaimInformation.setPlanParticipationCode(String.valueOf(claims.getPlanParticipationCode()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getBenefitsAssignmentCertificationIndicator()))) {
			objClaimInformation
					.setBenefitsAssignmentCertificationIndicator(claims.getBenefitsAssignmentCertificationIndicator());
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getReleaseInformationCode()))) {
			objClaimInformation.setReleaseInformationCode(String.valueOf(claims.getReleaseInformationCode()));
		}

		// claimSupplementalInformation
		ClaimSupplementalInformation ClaimSupplementalInformation = new ClaimSupplementalInformation();
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getPayerClaimControlNumber()))) {
			ClaimSupplementalInformation.setClaimControlNumber("12345");
		}
		objClaimInformation.setClaimSupplementalInformation(ClaimSupplementalInformation);
		// HealthCareCodeInformation
		List<HealthCareCodeInformation> hList = new ArrayList<>();
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getIcd10diagnosisCode1()))) {
			HealthCareCodeInformation healthCareCodeInformation1 = new HealthCareCodeInformation();
			String[] codes = String.valueOf(claims.getIcd10diagnosisCode1()).split(",");
			healthCareCodeInformation1.setDiagnosisTypeCode(String.valueOf(codes[2]));
			healthCareCodeInformation1.setDiagnosisCode(codes[0]);
			hList.add(0, healthCareCodeInformation1);
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getIcd10diagnosisCode2()))) {
			HealthCareCodeInformation healthCareCodeInformation2 = new HealthCareCodeInformation();
			String[] codes = String.valueOf(claims.getIcd10diagnosisCode2()).split(",");
			healthCareCodeInformation2.setDiagnosisTypeCode(String.valueOf(codes[2]));
			healthCareCodeInformation2.setDiagnosisCode(codes[0]);
			hList.add(1, healthCareCodeInformation2);
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getIcd10diagnosisCode3()))) {
			HealthCareCodeInformation healthCareCodeInformation3 = new HealthCareCodeInformation();
			String[] codes = String.valueOf(claims.getIcd10diagnosisCode3()).split(",");
			healthCareCodeInformation3.setDiagnosisTypeCode(String.valueOf(codes[2]));
			healthCareCodeInformation3.setDiagnosisCode(codes[0]);
			hList.add(2, healthCareCodeInformation3);
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getIcd10diagnosisCode4()))) {
			HealthCareCodeInformation healthCareCodeInformation4 = new HealthCareCodeInformation();
			String[] codes = String.valueOf(claims.getIcd10diagnosisCode4()).split(",");
			healthCareCodeInformation4.setDiagnosisTypeCode(String.valueOf(codes[2]));
			healthCareCodeInformation4.setDiagnosisCode(codes[0]);
			hList.add(3, healthCareCodeInformation4);
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getIcd10diagnosisCode5()))) {
			HealthCareCodeInformation healthCareCodeInformation5 = new HealthCareCodeInformation();
			String[] codes = String.valueOf(claims.getIcd10diagnosisCode5()).split(",");
			healthCareCodeInformation5.setDiagnosisTypeCode(String.valueOf(codes[2]));
			healthCareCodeInformation5.setDiagnosisCode(codes[0]);
			hList.add(4, healthCareCodeInformation5);
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getIcd10diagnosisCode6()))) {
			HealthCareCodeInformation healthCareCodeInformation6 = new HealthCareCodeInformation();
			String[] codes = String.valueOf(claims.getIcd10diagnosisCode6()).split(",");
			healthCareCodeInformation6.setDiagnosisTypeCode(String.valueOf(codes[2]));
			healthCareCodeInformation6.setDiagnosisCode(codes[0]);
			hList.add(5, healthCareCodeInformation6);
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getIcd10diagnosisCode7()))) {
			HealthCareCodeInformation healthCareCodeInformation7 = new HealthCareCodeInformation();
			String[] codes = String.valueOf(claims.getIcd10diagnosisCode7()).split(",");
			healthCareCodeInformation7.setDiagnosisTypeCode(String.valueOf(codes[2]));
			healthCareCodeInformation7.setDiagnosisCode(codes[0]);
			hList.add(6, healthCareCodeInformation7);
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getIcd10diagnosisCode8()))) {
			HealthCareCodeInformation healthCareCodeInformation8 = new HealthCareCodeInformation();
			String[] codes = String.valueOf(claims.getIcd10diagnosisCode8()).split(",");
			healthCareCodeInformation8.setDiagnosisTypeCode(String.valueOf(codes[2]));
			healthCareCodeInformation8.setDiagnosisCode(codes[0]);
			hList.add(7, healthCareCodeInformation8);
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getIcd10diagnosisCode9()))) {
			HealthCareCodeInformation healthCareCodeInformation9 = new HealthCareCodeInformation();
			String[] codes = String.valueOf(claims.getIcd10diagnosisCode9()).split(",");
			healthCareCodeInformation9.setDiagnosisTypeCode(String.valueOf(codes[2]));
			healthCareCodeInformation9.setDiagnosisCode(codes[0]);
			hList.add(8, healthCareCodeInformation9);
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getIcd10diagnosisCode10()))) {
			HealthCareCodeInformation healthCareCodeInformation10 = new HealthCareCodeInformation();
			String[] codes = String.valueOf(claims.getIcd10diagnosisCode10()).split(",");
			healthCareCodeInformation10.setDiagnosisTypeCode(String.valueOf(codes[2]));
			healthCareCodeInformation10.setDiagnosisCode(codes[0]);
			hList.add(9, healthCareCodeInformation10);
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getIcd10diagnosisCode11()))) {
			HealthCareCodeInformation healthCareCodeInformation11 = new HealthCareCodeInformation();
			String[] codes = String.valueOf(claims.getIcd10diagnosisCode11()).split(",");
			healthCareCodeInformation11.setDiagnosisTypeCode(String.valueOf(codes[2]));
			healthCareCodeInformation11.setDiagnosisCode(codes[0]);
			hList.add(10, healthCareCodeInformation11);
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getIcd10diagnosisCode12()))) {
			HealthCareCodeInformation healthCareCodeInformation12 = new HealthCareCodeInformation();
			String[] codes = String.valueOf(claims.getIcd10diagnosisCode12()).split(",");
			healthCareCodeInformation12.setDiagnosisTypeCode(String.valueOf(codes[2]));
			healthCareCodeInformation12.setDiagnosisCode(codes[0]);
			hList.add(11, healthCareCodeInformation12);
		}
		objClaimInformation.setHealthCareCodeInformation(hList);
		// ServiceLines
		List<ServiceLine> sList = new ArrayList<>();
		for (int i = 0; i < serviceLines.size(); i++) {
			ServiceLine serviceLine = new ServiceLine();
			OrderingProvider orderingProvider = new OrderingProvider();
			ProfessionalService professionalService = new ProfessionalService();
			CompositeDiagnosisCodePointers compositeDiagnosisCodePointers = new CompositeDiagnosisCodePointers();

			serviceLine.setAssignedNumber(String.valueOf(i + 1));
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getOriginalDos()))) {
				serviceLine.setServiceDate(
						DmeUtilities.dateConverter(String.valueOf(serviceLines.get(i).getOriginalDos())));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getOrderingProviderNpi()))) {
				orderingProvider.setNpi(String.valueOf(serviceLines.get(i).getOrderingProviderNpi()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getOrderingProviderFirstName()))) {
				orderingProvider.setFirstName(String.valueOf(serviceLines.get(i).getOrderingProviderFirstName()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getOrderingProviderLastName()))) {
				orderingProvider.setLastName(String.valueOf(serviceLines.get(i).getOrderingProviderLastName()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getProcedureIdentifier()))) {
				professionalService
						.setProcedureIdentifier(String.valueOf(serviceLines.get(i).getProcedureIdentifier()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getChargeAmt()))) {
				professionalService.setLineItemChargeAmount(String.valueOf(serviceLines.get(i).getChargeAmt()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getProcCode()))) {
				professionalService.setProcedureCode(String.valueOf(serviceLines.get(i).getProcCode()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getItemUom()))) {
				//HARDCODING FOR CHC 
				professionalService.setMeasurementUnit("UN");
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getQty()))) {
				professionalService.setServiceUnitCount(String.valueOf(serviceLines.get(i).getQty()));
			}

			List<String> modifiers = new ArrayList<>();
			List<String> codePointers = new ArrayList<>();
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getModifier1()))) {
				if (!String.valueOf(serviceLines.get(i).getModifier1()).equalsIgnoreCase("null")) {
					modifiers.add(String.valueOf(serviceLines.get(i).getModifier1()));
				}
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getModifier2()))) {
				if (!String.valueOf(serviceLines.get(i).getModifier2()).equalsIgnoreCase("null")) {
					modifiers.add(String.valueOf(serviceLines.get(i).getModifier2()));
				}
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getModifier3()))) {
				if (!String.valueOf(serviceLines.get(i).getModifier3()).equalsIgnoreCase("null")) {
					modifiers.add(String.valueOf(serviceLines.get(i).getModifier3()));
				}
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getModifier4()))) {
				if (!String.valueOf(serviceLines.get(i).getModifier4()).equalsIgnoreCase("null")) {
					modifiers.add(String.valueOf(serviceLines.get(i).getModifier4()));
				}
			}
			if (modifiers.size() > 0) {
				professionalService.setProcedureModifiers(modifiers);
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getIcdPointer()))) {
				codePointers.add(String.valueOf(serviceLines.get(i).getIcdPointer()));
			}
			if (codePointers.size() > 0) {
				compositeDiagnosisCodePointers.setDiagnosisCodePointers(codePointers);
			}
			professionalService.setCompositeDiagnosisCodePointers(compositeDiagnosisCodePointers);
			serviceLine.setProfessionalService(professionalService);
			serviceLine.setOrderingProvider(orderingProvider);

			sList.add(serviceLine);
		}
		objClaimInformation.setServiceLines(sList);
		objclaimSubmissionInput.setClaimInformation(objClaimInformation);

		return objclaimSubmissionInput;
	}
}
