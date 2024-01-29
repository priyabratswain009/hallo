package com.sunknowledge.dme.rcm.service.claimservice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
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
import com.sunknowledge.dme.rcm.commonutil.CommonUtilities;
import com.sunknowledge.dme.rcm.core.TokenOutCome;
import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import com.sunknowledge.dme.rcm.domain.SecondaryClaimsSubmissionMaster;
import com.sunknowledge.dme.rcm.domain.SecondaryServiceLinesMaster;
import com.sunknowledge.dme.rcm.pojo.HealthCheckResponse;
import com.sunknowledge.dme.rcm.pojo.common.AccessTokenError;
import com.sunknowledge.dme.rcm.pojo.common.Address;
import com.sunknowledge.dme.rcm.pojo.common.Billing;
import com.sunknowledge.dme.rcm.pojo.common.ClaimSubmissionOutput;
import com.sunknowledge.dme.rcm.pojo.common.CompositeDiagnosisCodePointers;
import com.sunknowledge.dme.rcm.pojo.common.ContactInformation;
import com.sunknowledge.dme.rcm.pojo.common.Dependent;
import com.sunknowledge.dme.rcm.pojo.common.HealthCareCodeInformation;
import com.sunknowledge.dme.rcm.pojo.common.OrderingProvider;
import com.sunknowledge.dme.rcm.pojo.common.ProfessionalClaimsErrorHandler;
import com.sunknowledge.dme.rcm.pojo.common.ProfessionalService;
import com.sunknowledge.dme.rcm.pojo.common.Receiver;
import com.sunknowledge.dme.rcm.pojo.common.ResultClaimSubmissionOutcome;
import com.sunknowledge.dme.rcm.pojo.common.Submitter;
import com.sunknowledge.dme.rcm.pojo.common.Subscriber;
import com.sunknowledge.dme.rcm.pojo.secondarytertiary.AdjustmentDetail;
import com.sunknowledge.dme.rcm.pojo.secondarytertiary.ClaimAdjustmentInformation;
import com.sunknowledge.dme.rcm.pojo.secondarytertiary.ClaimInformation;
import com.sunknowledge.dme.rcm.pojo.secondarytertiary.ClaimSubmissionInput;
import com.sunknowledge.dme.rcm.pojo.secondarytertiary.LineAdjudicationInformation;
import com.sunknowledge.dme.rcm.pojo.secondarytertiary.OtherPayerName;
import com.sunknowledge.dme.rcm.pojo.secondarytertiary.OtherSubscriberInformation;
import com.sunknowledge.dme.rcm.pojo.secondarytertiary.OtherSubscriberName;
import com.sunknowledge.dme.rcm.pojo.secondarytertiary.ServiceLine;
import com.sunknowledge.dme.rcm.repository.primaryclaimrepository.ClaimSubmissionStatusRepo;
import com.sunknowledge.dme.rcm.repository.secondaryclaimrepository.SecondaryClaimsSubmissionMasterRepo;
import com.sunknowledge.dme.rcm.repository.secondaryclaimrepository.SecondaryServiceLinesMasterRepo;
import com.sunknowledge.dme.rcm.validation.DmeUtilities;

@Service
public class SecondaryClaimSubmissionServiceImpl implements SecondaryClaimSubmissionService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private TokenGenerationServiceImpl tokenGenerationServiceImpl;
	@Autowired
	private SecondaryClaimsSubmissionMasterRepo secondaryClaimSubmissionRepository;
	@Autowired
	private SecondaryServiceLinesMasterRepo secondaryServiceLinesRepo;
	@Autowired
	private ClaimSubmissionStatusRepo claimSubmissionStatusRepo;

	@Override
	public ServiceOutcome<ResultClaimSubmissionOutcome> accessProfessionalClaimsSubmission(Long soId, String claimControlNo) {
		ServiceOutcome<ResultClaimSubmissionOutcome> routcome = new ServiceOutcome<ResultClaimSubmissionOutcome>();
		// setting submission date
		LocalDate currentdate = LocalDate.now();

		ResultClaimSubmissionOutcome claimOutcome = new ResultClaimSubmissionOutcome();
		TokenOutCome objTokenOutCome = new TokenOutCome();
		ClaimSubmissionStatus objClaimSubmissionStatus = new ClaimSubmissionStatus();
		ClaimSubmissionStatus objvalidateClaimSubmissionStatusData = claimSubmissionStatusRepo
				.validateSubmissionStatus(soId,claimControlNo);
		if (CommonUtilities.isStringNullOrBlank(objvalidateClaimSubmissionStatusData.getReadyForSubmissionStatus())) {
			SecondaryClaimsSubmissionMaster claims = secondaryClaimSubmissionRepository
					.getSecondarySubmissionMasterDetails(soId,claimControlNo);
			List<SecondaryServiceLinesMaster> serviceLines = secondaryServiceLinesRepo
					.getSecondarySubmissionServiceDetails(claims.getChangeHealthSecondarySubmisionMasterId());
			ClaimSubmissionInput objclaimSubmissionInput = setdata(claims, serviceLines);
			boolean isAfter = claims.getOriginalDos().isAfter(currentdate);
			if (!isAfter) {
				try {
					objTokenOutCome = tokenGenerationServiceImpl.getToken();
					String token = objTokenOutCome.getTokenResponseOutput().getAccessToken();
					if (professionalClaimHealthCheck(token).getOutcome()) {
						routcome = professionalClaimSubmission(token, objclaimSubmissionInput);
						String payorClaimNumber = routcome.getData().getClaimSubmissionOutput().getClaimReference()
								.getRhclaimNumber();
						String status = routcome.getData().getClaimSubmissionOutput().getStatus();
						String submissionNote = routcome.getData().getClaimSubmissionOutput().getClaimReference()
								.getCorrelationId()
								+ "=="
								+ routcome.getData().getClaimSubmissionOutput().getClaimReference()
										.getCustomerClaimNumber()
								+ "==" + routcome.getData().getClaimSubmissionOutput().getMeta().getTraceId();
						objClaimSubmissionStatus = claimSubmissionStatusRepo
								.getClaimSubmissionStatus(claims.getClaimControlNo());
						objClaimSubmissionStatus.setClaimSubmissionDate(currentdate);
						objClaimSubmissionStatus.setPayorClaimControlNo(payorClaimNumber);
						objClaimSubmissionStatus.setSubmissionStatus(status);
						objClaimSubmissionStatus.setSubmissionNote(submissionNote);
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
		} else {
			claimOutcome.setAccessTokenError(null);
			claimOutcome.setClaimSubmissionOutput(null);
			claimOutcome.setHealthCheckOutcome(null);
			claimOutcome.setErrorhandler(null);
			routcome.setData(claimOutcome);
			routcome.setMessage("Data is not ready for submission");
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

			System.out.println("INPUT=========== " + inputEntity);

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

	ClaimSubmissionInput setdata(SecondaryClaimsSubmissionMaster claims,
			List<SecondaryServiceLinesMaster> serviceLines) {

		ClaimSubmissionInput objclaimSubmissionInput = new ClaimSubmissionInput();

		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getClaimControlNo()))) {
			// Sand Box Value
			objclaimSubmissionInput.setControlNumber("000015436");
		}

		// Submitter Object
		Submitter objsubmitter = new Submitter();
		ContactInformation objsubmittercontactInformation = new ContactInformation();
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubmitterContactPersonName()))) {
			objsubmittercontactInformation.setName(String.valueOf(claims.getSubmitterContactPersonName()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubmitterContactNo()))) {
			objsubmittercontactInformation.setPhoneNumber(claims.getSubmitterContactNo());
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubmitterOrganizationName()))) {
			objsubmitter.setOrganizationName(String.valueOf(claims.getSubmitterOrganizationName()));
		}
		objsubmitter.setContactInformation(objsubmittercontactInformation);
		objclaimSubmissionInput.setSubmitter(objsubmitter);
		// Reciever Object
		Receiver objReceiver = new Receiver();
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getReceiverOrganizationName()))) {
			objReceiver.setOrganizationName(String.valueOf(claims.getReceiverOrganizationName()));
		}
		objclaimSubmissionInput.setReceiver(objReceiver);
		// Billing Provider
		Billing objprovider = new Billing();
		Address billProAddress = new Address();
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
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getBillingProviderAddressLine2()))) {
			billProAddress.setAddress2(String.valueOf(claims.getBillingProviderAddressLine2()));
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
		objclaimSubmissionInput.setBilling(objprovider);
		// Subscriber
		Subscriber objSubscriber = new Subscriber();
		Address subAddress = new Address();
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
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getSubscriberMemberIdNo()))) {
			// HARDCODING FOR CHC
			objSubscriber.setMemberId("0000000001");
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
		// Dependent
		if (!claims.getPatientRelationshipInsured().equalsIgnoreCase("18")) {
			Dependent objDependent = new Dependent();
			Address depAddress = new Address();
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getInsuredAddressLine1()))) {
				depAddress.setAddress1(String.valueOf(claims.getInsuredAddressLine1()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getInsuredAddressLine2()))) {
				depAddress.setAddress1(String.valueOf(claims.getInsuredAddressLine2()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getInsuredCity()))) {
				depAddress.setCity(String.valueOf(claims.getInsuredCity()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getInsuredState()))) {
				depAddress.setState(String.valueOf(claims.getInsuredState()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getInsuredZip()))) {
				depAddress.setPostalCode(String.valueOf(claims.getInsuredZip()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getInsuredFirstName()))) {
				objDependent.setFirstName(String.valueOf(claims.getInsuredFirstName()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getInsuredLastName()))) {
				objDependent.setLastName(String.valueOf(claims.getInsuredLastName()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getInsuredGender()))) {
				objDependent.setGender(String.valueOf(claims.getInsuredGender()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getInsuredDob()))) {
				objDependent.setDateOfBirth(DmeUtilities.dateConverter(String.valueOf(claims.getInsuredDob())));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getPatientRelationshipInsured()))) {
				objDependent.setRelationshipToSubscriberCode(String.valueOf(claims.getPatientRelationshipInsured()));
			}
			objDependent.setAddress(depAddress);
			objclaimSubmissionInput.setDependent(objDependent);
		}

		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getTradingPartnerServiceId()))) {
			// HCODING FOR CHC
			objclaimSubmissionInput.setTradingPartnerServiceId("9496");
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getTradingPartnerName()))) {
			objclaimSubmissionInput.setTradingPartnerName(String.valueOf(claims.getTradingPartnerName()));
		}

		// ClaimInformation
		ClaimInformation objClaimInformation = new ClaimInformation();
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getClaimFilingCode()))) {
			objClaimInformation.setClaimFilingCode(String.valueOf(claims.getClaimFilingCode()));
		}
		// need to change/update this
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getClaimControlNo()))) {
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

		// Other Subscriber Information
		List<OtherSubscriberInformation> otherSubscriberInformation = new ArrayList<>();
		OtherSubscriberInformation objOtherSubscriberInformation = new OtherSubscriberInformation();
		OtherSubscriberName otherSubscriberName = new OtherSubscriberName();
		OtherPayerName otherPayerName = new OtherPayerName();
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getPaymentResponsibilityLevelCode()))) {
			objOtherSubscriberInformation
					.setPaymentResponsibilityLevelCode(String.valueOf(claims.getPaymentResponsibilityLevelCode()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getIndividualRelationshipCode()))) {
			objOtherSubscriberInformation
					.setIndividualRelationshipCode(String.valueOf(claims.getIndividualRelationshipCode()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getClaimFilingIndicatorCode()))) {
			objOtherSubscriberInformation
					.setClaimFilingIndicatorCode(String.valueOf(claims.getClaimFilingIndicatorCode()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getPayerPaidAmount()))) {
			objOtherSubscriberInformation.setPayerPaidAmount(String.valueOf(claims.getPayerPaidAmount()));
		}
		if (DmeUtilities
				.isStringNullOrBlank(String.valueOf(claims.getOtherPayerBenefitsAssignmentCertificationIndicator()))) {
			objOtherSubscriberInformation.setBenefitsAssignmentCertificationIndicator(
					String.valueOf(claims.getOtherPayerBenefitsAssignmentCertificationIndicator()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getReleaseOfInformationCode()))) {
			objOtherSubscriberInformation
					.setReleaseOfInformationCode(String.valueOf(claims.getReleaseOfInformationCode()));
		}
		// otherSubscriberName
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getOtherInsuredQualifier()))) {
			otherSubscriberName.setOtherInsuredQualifier(String.valueOf(claims.getOtherInsuredQualifier()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getOtherInsuredLastName()))) {
			otherSubscriberName.setOtherInsuredLastName(String.valueOf(claims.getOtherInsuredLastName()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getOtherInsuredFirstName()))) {
			otherSubscriberName.setOtherInsuredFirstName(String.valueOf(claims.getOtherInsuredFirstName()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getOtherInsuredIdentifierTypeCode()))) {
			otherSubscriberName
					.setOtherInsuredIdentifierTypeCode(String.valueOf(claims.getOtherInsuredIdentifierTypeCode()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getOtherInsuredIdentifier()))) {
			otherSubscriberName.setOtherInsuredIdentifier(String.valueOf(claims.getOtherInsuredIdentifier()));
		}
		// otherPayerName
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getOtherPayerOrganizationName()))) {
			otherPayerName.setOtherPayerOrganizationName(String.valueOf(claims.getOtherPayerOrganizationName()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getOtherPayerIdentifierTypeCode()))) {
			otherPayerName.setOtherPayerIdentifierTypeCode(String.valueOf(claims.getOtherPayerIdentifierTypeCode()));
		}
		if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getOtherPayerIdentifier()))) {
			// HARDCODING FOR CHC
			otherPayerName.setOtherPayerIdentifier("16013");
		}
		objOtherSubscriberInformation.setOtherPayerName(otherPayerName);
		objOtherSubscriberInformation.setOtherSubscriberName(otherSubscriberName);
		otherSubscriberInformation.add(objOtherSubscriberInformation);
		objClaimInformation.setOtherSubscriberInformation(otherSubscriberInformation);

		// ServiceLines
		List<ServiceLine> sList = new ArrayList<>();
		for (int i = 0; i < serviceLines.size(); i++) {
			ServiceLine serviceLine = new ServiceLine();
			OrderingProvider orderingProvider = new OrderingProvider();
			ProfessionalService professionalService = new ProfessionalService();
			List<LineAdjudicationInformation> lineAdjudicationInformation = new ArrayList<>();
			LineAdjudicationInformation objLineAdjudicationInformation = new LineAdjudicationInformation();
			List<ClaimAdjustmentInformation> claimAdjustmentInformation = new ArrayList<>();
			CompositeDiagnosisCodePointers compositeDiagnosisCodePointers = new CompositeDiagnosisCodePointers();

			if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getOtherPayerIdentifier()))) {
				// HARDCODING FOR CHC
				objLineAdjudicationInformation.setOtherPayerPrimaryIdentifier("16013");
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getProviderPaymentAmount()))) {
				objLineAdjudicationInformation
						.setServiceLinePaidAmount(String.valueOf(serviceLines.get(i).getProviderPaymentAmount()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getOtherInsuredQualifier()))) {
				objLineAdjudicationInformation.setServiceIdQualifier(String.valueOf(claims.getOtherInsuredQualifier()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getProcedureIdentifier()))) {
				objLineAdjudicationInformation
						.setServiceIdQualifier(String.valueOf(serviceLines.get(i).getProcedureIdentifier()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getProcCode()))) {
				objLineAdjudicationInformation.setProcedureCode(String.valueOf(serviceLines.get(i).getProcCode()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getQty()))) {
				objLineAdjudicationInformation.setPaidServiceUnitCount(String.valueOf(serviceLines.get(i).getQty()));
			}
			double patientresponsibility = 0;
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getLineAdjustment()))) {

				String input = String.valueOf(serviceLines.get(i).getLineAdjustment());

				StringTokenizer tokenizer = new StringTokenizer(input, "#");

				while (tokenizer.hasMoreElements()) {
					String token = tokenizer.nextToken();
					ClaimAdjustmentInformation objClaimAdjustmentInformation = new ClaimAdjustmentInformation();
					System.out.println("#" + token);
					StringTokenizer tokenizer2 = new StringTokenizer(token, "|");

					List<AdjustmentDetail> adjustmentDetails = new ArrayList<>();

					String adjustmentGroupCode = "";
					while (tokenizer2.hasMoreElements()) {
						String token2 = tokenizer2.nextToken();

						System.out.println("|" + token2);
						AdjustmentDetail AdjustmentDetail = new AdjustmentDetail();
						String[] token3 = token2.split(",");

						AdjustmentDetail.setAdjustmentReasonCode(token3[1]);
						AdjustmentDetail.setAdjustmentAmount(token3[2].replace(")", ""));

						if (token3[0].replace("(", "").equalsIgnoreCase("PR")) {
							patientresponsibility = patientresponsibility
									+ Double.parseDouble(token3[2].replace(")", ""));
						}

						adjustmentDetails.add(AdjustmentDetail);
						adjustmentGroupCode = token3[0].replace("(", "");
						System.out.println("adjustmentGroupCode " + adjustmentGroupCode);

					}
					if (CommonUtilities.isStringNullOrBlank(adjustmentGroupCode)) {
						objClaimAdjustmentInformation.setAdjustmentDetails(adjustmentDetails);
						objClaimAdjustmentInformation.setAdjustmentGroupCode(adjustmentGroupCode);
						claimAdjustmentInformation.add(objClaimAdjustmentInformation);
					}
				}
				objLineAdjudicationInformation.setClaimAdjustmentInformation(claimAdjustmentInformation);
			}
			if (!(patientresponsibility == 0)) {
				objLineAdjudicationInformation.setRemainingPatientLiability(String.valueOf(patientresponsibility));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(claims.getOtherPayerAdjudicationOrPaymentDate()))) {
				objLineAdjudicationInformation.setAdjudicationOrPaymentDate(
						DmeUtilities.dateConverter(String.valueOf(claims.getOtherPayerAdjudicationOrPaymentDate())));
			}

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
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getProcCode()))) {
				professionalService.setProcedureCode(String.valueOf(serviceLines.get(i).getProcCode()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getChargeAmt()))) {
				professionalService.setLineItemChargeAmount(String.valueOf(serviceLines.get(i).getChargeAmt()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getItemUom()))) {
				// HARDCODING FOR CHC
				professionalService.setMeasurementUnit("UN");
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getQty()))) {
				professionalService.setServiceUnitCount(String.valueOf(serviceLines.get(i).getQty()));
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getPayorClaimControlNo()))) {
				// HARDCODING for CHC
				if (i == 0) {
					serviceLine.setProviderControlNumber("2552564");
				} else if (i == 1) {
					serviceLine.setProviderControlNumber("2552561");
				} else if (i == 2) {
					serviceLine.setProviderControlNumber("2552558");
				} else {
					serviceLine.setProviderControlNumber("2552555");
				}
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getReference()))) {
				serviceLine.setAdditionalNotes(String.valueOf(serviceLines.get(i).getReference()));
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
				objLineAdjudicationInformation.setProcedureModifier(modifiers);
			}
			if (DmeUtilities.isStringNullOrBlank(String.valueOf(serviceLines.get(i).getIcdPointer()))) {
				codePointers.add(String.valueOf(serviceLines.get(i).getIcdPointer()));
			}
			if (codePointers.size() > 0) {
				compositeDiagnosisCodePointers.setDiagnosisCodePointers(codePointers);
			}
			lineAdjudicationInformation.add(objLineAdjudicationInformation);
			serviceLine.setLineAdjudicationInformation(lineAdjudicationInformation);
			professionalService.setCompositeDiagnosisCodePointers(compositeDiagnosisCodePointers);
			serviceLine.setProfessionalService(professionalService);
			serviceLine.setOrderingProvider(orderingProvider);

			sList.add(serviceLine);
		}
		objClaimInformation.setServiceLines(sList);
		objclaimSubmissionInput.setClaimInformation(objClaimInformation);

		return objclaimSubmissionInput;
	}

	List<String> getdata(String data) {

		String[] sections = data.split("#");
		List<String> tokens = new ArrayList<>();

		for (String section : sections) {
			if (!section.isEmpty()) {

				String[] subsections = section.split("\\|");

				for (String subsection : subsections) {

					StringTokenizer tokenizer = new StringTokenizer(subsection, ",");
					while (tokenizer.hasMoreTokens()) {
						tokens.add(tokenizer.nextToken());
					}
				}
			}
		}
		return tokens;

	}

}
