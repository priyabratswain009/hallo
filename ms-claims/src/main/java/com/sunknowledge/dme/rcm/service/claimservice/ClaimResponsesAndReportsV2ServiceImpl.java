package com.sunknowledge.dme.rcm.service.claimservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.Document;
import com.itextpdf.text.io.RandomAccessSourceFactory;
import com.itextpdf.text.pdf.*;
import com.sunknowledge.dme.rcm.application.applicationstatus.DefineStatus;
import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.application.properties.FileConfigUtility;
import com.sunknowledge.dme.rcm.application.properties.FileUploadConfigProperties;
import com.sunknowledge.dme.rcm.application.utils.ApplicationDateUtility;
import com.sunknowledge.dme.rcm.commonutil.AccessTokenUtilities;
import com.sunknowledge.dme.rcm.core.TokenOutCome;
import com.sunknowledge.dme.rcm.domain.*;
import com.sunknowledge.dme.rcm.pojo.claimreports.ActualClaimResponseReport;
import com.sunknowledge.dme.rcm.pojo.claimreports.ClaimReports;
import com.sunknowledge.dme.rcm.pojo.claimreports.ClaimsTransactionReport;
import com.sunknowledge.dme.rcm.pojo.claimreports.X12EDIReportContents;
import com.sunknowledge.dme.rcm.pojo.claimreports.claimsremittance835.ClaimsRemittanceTransaction;
import com.sunknowledge.dme.rcm.pojo.claimreports.claimsremittance835.PaymentInfo;
import com.sunknowledge.dme.rcm.pojo.claimreports.claimstatusresponse277.Claim;
import com.sunknowledge.dme.rcm.pojo.claimreports.claimstatusresponse277.ClaimStatusResponseTransaction;
import com.sunknowledge.dme.rcm.pojo.claimreports.submissionstatus.LineAdjustmentProjection;
import com.sunknowledge.dme.rcm.pojo.claimreports.transaction.*;
import com.sunknowledge.dme.rcm.repository.invoice.InvoiceLineItemDetailsRepo;
import com.sunknowledge.dme.rcm.repository.primaryclaimrepository.*;
import com.sunknowledge.dme.rcm.repository.primaryresubmissionclaimrepository.PrimaryClaimsReSubmissionMasterRepo;
import com.sunknowledge.dme.rcm.repository.primaryresubmissionclaimrepository.PrimaryResubmissionServiceLinesMasterRepo;
import com.sunknowledge.dme.rcm.repository.secondaryclaimrepository.SecondaryClaimsSubmissionMasterRepo;
import com.sunknowledge.dme.rcm.repository.secondaryclaimrepository.SecondaryServiceLinesMasterRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClaimResponsesAndReportsV2ServiceImpl implements ClaimResponsesAndReportsV2Service {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private FileUploadConfigProperties fileUploadConfigProperties;
    @Autowired
    private FileConfigUtility fileConfigUtility;
    @Autowired
	private TokenGenerationServiceImpl tokenGenerationServiceImpl;
	@Autowired
	private ClaimsCOB835DetailsRepo claimsCOB835DetailsRepository;
	@Autowired
	private ClaimsCOB835MasterRepo claimsCOB835MasterRepository;
	@Autowired
	private ClaimsStatus277MasterRepo claimsStatus277MasterRepository;
	@Autowired
	private ClaimsStatus277DetailsRepo claimsStatus277DetailsRepository;

	@Autowired
	private ClaimsReportFileProcessStatusRepo claimsReportFileProcessStatusRepository;
	@Autowired
	private ClaimSubmissionStatusRepo claimSubmissionStatusRepository;
	@Autowired
	private Claims835277ExceptionRepo claims835277ExceptionRepository;
    @Autowired
    private DepositMasterDetailsRepo depositMasterDetailsRepository;
    @Autowired
    private ReceiptMasterDetailsRepo receiptMasterDetailsRepository;

    @Autowired
    private Transaction835MasterDetailsRepo transaction835MasterDetailsRepository;
    @Autowired
    private ClaimsSubmissionMasterRepo claimsSubmissionMasterRepository;
    @Autowired
    private ServiceLinesMasterRepo serviceLinesMasterRepository;
    @Autowired
    private SecondaryClaimsSubmissionMasterRepo secondaryClaimsSubmissionMasterRepository;
    @Autowired
    private SecondaryServiceLinesMasterRepo secondaryServiceLinesMasterRepository;
    @Autowired
    private PrimaryClaimsReSubmissionMasterRepo primaryClaimsReSubmissionMasterRepository;
    @Autowired
    private PrimaryResubmissionServiceLinesMasterRepo primaryResubmissionServiceLinesMasterRepository;
    @Autowired
    private Claims835CrossoverExceptionRepo claims835CrossoverExceptionRepository;
    @Autowired
    private Claims835CrossoverProcessedRepo claims835CrossoverProcessedRepository;
    @Autowired
    private InvoiceLineItemDetailsRepo invoiceLineItemDetailsRepository;
	@Autowired
	private RestTemplate restTemplate;
    private final WebClient webClient;

    public ClaimResponsesAndReportsV2ServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080/services").build();
    }


    @Override
	public ServiceOutcome<ArrayList<ActualClaimResponseReport>> processActualClaimResponsesReport() {
		log.info("=======================POST(SERVICE) Get Medical Network Claims Responses and Reports V2 API(Change Healthcare)==========================");
		ServiceOutcome<ClaimReports> routcome;
		ServiceOutcome<ArrayList<ActualClaimResponseReport>> claimResponseReportOutcome = new ServiceOutcome<ArrayList<ActualClaimResponseReport>>();
		ArrayList<ActualClaimResponseReport> claimResponseReport = new ArrayList<ActualClaimResponseReport>();
		TokenOutCome tokenOutCome;
		try {
			tokenOutCome = tokenGenerationServiceImpl.getToken();
			if(tokenOutCome.getTokenResponseOutput().getAccessToken() != null) {
				System.out.println("TOKEN=>"+tokenOutCome.getTokenResponseOutput().getAccessToken());
				String token = tokenOutCome.getTokenResponseOutput().getAccessToken();
				//String token = "eyJraWQiOiIxIiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJhY2Nlc3NfdG9rZW4iOiJlUWJ1bFh0MkxaRzVIbU12anIxQUVOeVlvcGcyIiwiYXVkIjoiYXBpUGxhdGZvcm0iLCJhcGlfcHJvZHVjdF9saXN0IjpbIk1OX1Byb2R1Y3RfUmVwb3J0c192MSIsIk1OX1Byb2R1Y3RfQXR0YWNobWVudHNfdjEiLCJNTl9Qcm9kdWN0X0NsYWltU3RhdHVzX3YyIiwiTU5fUHJvZHVjdF9FbGlnaWJpbGl0eV92MyIsIk1OX1Byb2R1Y3RfUHJvZmVzc2lvbmFsQ2xhaW1zX3YzIiwiTU5fUHJvZHVjdF9JbnN0aXR1dGlvbmFsQ2xhaW1zX3YxIl0sImFwcGxpY2F0aW9uX25hbWUiOiJNTl9TdW4gS25vd2xlZGdlX0FQUCIsIm5iZiI6MTY1MzY1NzgyNywiZGV2ZWxvcGVyX2VtYWlsIjoicmFuamFuLmtvbGV5QHN1bmtub3dsZWRnZS5jb20iLCJpc3MiOiJodHRwczpcL1wvc2FuZGJveC5hcGlndy5jaGFuZ2VoZWFsdGhjYXJlLmNvbSIsInNjb3BlcyI6IiIsImV4cCI6MTY1MzY2MTQyNywiaWF0IjoxNjUzNjU3ODI3LCJjbGllbnRfaWQiOiI5TW90cjgxZTZsU212N2JDdDIxZm5oU2lYN1FlNjVaTCIsImp0aSI6IjM3YTc4YzMwLWRiNjgtNDVjZC1iNTE0LTQ4NmFiOWY5MzFjMiJ9.nupyJJbHxcQEoVL9MYfF5xYQrfxx3g86jBSoLr3PVpQei6kkeQF95e3_4PLsC82wsDg0HBw_6YfbiXzomDuJbo7kdkvp-hIEpeZyTEGUDpywvHahF3Q5TvxiaktTz6gPvjt2Z1QTC9BQC81wYTxj-xTWZozA46dd_58RmEfejqcciCUdlirsE0d2vofwzS8HmRTVOmy5RN2LjoF1dOMHNBefv7O6q2oWVFZMxY9sJRGMOn6el17QWxvU_kfzX60FpaIljzcp5jJFX_tf7GKS3VkK4vskrwe67iy86uTIZnptgynzDQ3uM2lcS3gnT5fA2-8-hCiZzUFKsfZqsI6xow";
				routcome = completeAvailableCustomerDocumentsList(tokenOutCome.getTokenResponseOutput().getAccessToken());
				if(routcome.getData().getReports().size() > 0) {
					routcome.getData().getReports().forEach(reportType -> {
						ServiceOutcome<X12EDIReportContents> x12EDIReportContent = getX12EDIReportContent(reportType, token);
						System.out.println("File Content:"+x12EDIReportContent.getData().getReportContent());
						ServiceOutcome<ClaimsTransactionReport> reportContents = getActualReadableReportContent(reportType, token);
						System.out.println("Translated Report Contents:"+reportContents.getData());

						ActualClaimResponseReport actualClaimResponseReport = new ActualClaimResponseReport();
						actualClaimResponseReport.setFileName(reportType);
						actualClaimResponseReport.setX12ediReportContents(x12EDIReportContent.getData());
						actualClaimResponseReport.setClaimsTransactionReport(reportContents.getData());
						claimResponseReport.add(actualClaimResponseReport);
					});
					claimResponseReportOutcome.setData(claimResponseReport);
					claimResponseReportOutcome.setMessage("Successfully retrieved the report");
					claimResponseReportOutcome.setOutcome(true);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return claimResponseReportOutcome;
	}

	public ServiceOutcome<ClaimReports> completeAvailableCustomerDocumentsList(String accessToken){
		int type = 1;
		String fileName = "";
		ServiceOutcome<ClaimReports> routcome = new ServiceOutcome<>();
		ClaimReports claimOutcome = new ClaimReports();
		ResponseEntity<String> responseEntity = generateResponse(accessToken, fileName, type);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();
			ClaimReports claimReports;
			try {
				claimReports = mapper.readValue(responseEntity.getBody(), ClaimReports.class);
				if(claimReports.getReports() != null) {
					routcome.setData(claimReports);
					routcome.setMessage("Successfully accessed complete available customer document list.");
					routcome.setOutcome(true);
				}
				else {
					routcome.setData(claimOutcome);
					routcome.setMessage("Failed to access complete available customer document list.");
					routcome.setOutcome(false);
				}
			}
			catch (JsonMappingException e) {
				e.printStackTrace();
			}
			catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return routcome;
	}

	public ServiceOutcome<X12EDIReportContents> getX12EDIReportContent(String fileName, String accessToken) {
		int type = 2;
		ServiceOutcome<X12EDIReportContents> routcome = new ServiceOutcome<>();
		ResponseEntity<String> responseEntity = generateResponse(accessToken, fileName, type);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();
			X12EDIReportContents contents;
			try {
				contents = mapper.readValue(responseEntity.getBody(), X12EDIReportContents.class);
				if(contents.getReportContent() != null) {
					routcome.setData(contents);
					routcome.setMessage("Successfully retrieved the X12 EDI report contents.");
					routcome.setOutcome(true);
				}
				else {
					routcome.setData(contents);
					routcome.setMessage("Failed to retrieved the X12 EDI report contents.");
					routcome.setOutcome(false);
				}
			}
			catch (JsonMappingException e) {
				e.printStackTrace();
			}
			catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return routcome;
	}

	public ServiceOutcome<ClaimsTransactionReport> getActualReadableReportContent(String fileName, String accessToken) {
		int type = 3;
		ServiceOutcome<ClaimStatusResponseTransaction> csrtOutcome = new ServiceOutcome<>();
		ServiceOutcome<ClaimsRemittanceTransaction> crtOutcome = new ServiceOutcome<>();
		ServiceOutcome<ClaimsTransactionReport> ctrOutcome = new ServiceOutcome<>();
		ClaimsTransactionReport claimsTransactionReport = new ClaimsTransactionReport();

		ResponseEntity<String> responseEntity = generateResponse(accessToken, fileName, type);
		String reportType = fileName.substring(0, 2);

		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();
			ClaimStatusResponseTransaction claimStatusResponseTransaction;
			ClaimsRemittanceTransaction claimsRemittanceTransaction;
			try {
				if(reportType.equals("R5")) {
					claimsRemittanceTransaction = mapper.readValue(responseEntity.getBody(), ClaimsRemittanceTransaction.class);
					if(claimsRemittanceTransaction.getTransactions() != null) {
						crtOutcome.setData(claimsRemittanceTransaction);
						crtOutcome.setMessage("Successfully retrieved the actual report contents.");
						crtOutcome.setOutcome(true);

						claimsTransactionReport.setClaimsRemittanceTransaction(crtOutcome.getData());
						ctrOutcome.setData(claimsTransactionReport);
					}
					else {
						crtOutcome.setData(claimsRemittanceTransaction);
						crtOutcome.setMessage("Failed to retrieved the actual report contents.");
						crtOutcome.setOutcome(false);
					}
				}
				if(reportType.equals("X3")) {
					claimStatusResponseTransaction = mapper.readValue(responseEntity.getBody(), ClaimStatusResponseTransaction.class);
					if(claimStatusResponseTransaction.getTransactions() != null) {
						csrtOutcome.setData(claimStatusResponseTransaction);
						csrtOutcome.setMessage("Successfully retrieved the actual report contents.");
						csrtOutcome.setOutcome(true);

						claimsTransactionReport.setClaimStatusResponseTransaction(csrtOutcome.getData());
						ctrOutcome.setData(claimsTransactionReport);
					}
					else {
						csrtOutcome.setData(claimStatusResponseTransaction);
						csrtOutcome.setMessage("Failed to retrieved the actual report contents.");
						csrtOutcome.setOutcome(false);
					}
				}
			}
			catch (JsonMappingException e) {
				e.printStackTrace();
			}
			catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return ctrOutcome;
	}

	public ResponseEntity<String> generateResponse(String accessToken, String fileName, int type) {
		String url = "";
		if(type == 1)
			url = fileConfigUtility.getProperty("available.customer.document.url");
		if(type == 2)
			url = fileConfigUtility.getProperty("available.customer.document.url")+"/"+fileName;
		if(type == 3) {
			String reportType = fileName.substring(0, 2);
			int responseType;
			if(reportType.equals("X3")) {
				responseType = 277;
				url = fileConfigUtility.getProperty("available.customer.document.url")+"/"+fileName+"/"+responseType;
			}
			if(reportType.equals("R5")) {
				responseType = 835;
				url = fileConfigUtility.getProperty("available.customer.document.url")+"/"+fileName+"/"+responseType;
			}
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type","application/json");
		headers.add("Accept","application/json");
		headers.set("Authorization", "Bearer "+accessToken);
		HttpEntity<String> requestEntity = new HttpEntity<>(headers);
		ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
		return responseEntity;
	}

    @Override
    @Transactional
    public ClaimTransactionOutcome saveClaimTransaction(List<ActualClaimResponseReport> claimTransactionInput) {
        System.out.println("==================Save================");
        ClaimTransactionOutcome claimTransactionOutcome = new ClaimTransactionOutcome();
        List<ClaimsCOB835Master> claimsCOB835Masters = new ArrayList<>();
        List<ClaimsStatus277Master> claimsStatus277Masters = new ArrayList<>();
        claimTransactionInput.forEach(list -> {
            String fileName = list.getFileName();
            List<ClaimsReportFileProcessStatus> claimFileProcessStatus = claimsReportFileProcessStatusRepository.getClaimFileProcessStatusOnFileName(fileName);
            if(claimFileProcessStatus.size() == 0) {
                String reportType = fileName.substring(0, 2);
                if(list.getClaimsTransactionReport().getClaimsRemittanceTransaction() != null && reportType.equals("R5")) {
                    list.getClaimsTransactionReport().getClaimsRemittanceTransaction().getTransactions().stream()
                        .forEach(trans -> {
                            System.out.println("===>"+trans.getControlNumber());
                            trans.getDetailInfo().stream()
                                .forEach(detailInfo -> {
                                    System.out.println("====>"+detailInfo.getAssignedNumber());
                                    detailInfo.getPaymentInfo().stream()
                                        .forEach(paymentInfo -> {
                                            ClaimsCOB835Master claimTransactionMaster = new ClaimsCOB835Master();
                                            claimTransactionMaster.setFileName(list.getFileName());
                                            claimTransactionMaster.setPayerName(trans.getPayer().getName());
                                            claimTransactionMaster.setPayeeName(trans.getPayee().getName());
                                            claimTransactionMaster.setPayeeNpi(trans.getPayee().getNpi());

                                            claimTransactionMaster.setCheckOrEFTTraceNumber(trans.getPaymentAndRemitReassociationDetails().getCheckOrEFTTraceNumber());
                                            claimTransactionMaster.setCheckIssueOrEFTEffectiveDate(ApplicationDateUtility.convertStringToDateOnSpecific(trans.getFinancialInformation().getCheckIssueOrEFTEffectiveDate()));
                                            claimTransactionMaster.setCreditOrDebitFlagCode(trans.getFinancialInformation().getCreditOrDebitFlagCode());
                                            claimTransactionMaster.setPaymentMethodCode(trans.getFinancialInformation().getPaymentMethodCode());
                                            claimTransactionMaster.setChequeEftAmount(Double.parseDouble(trans.getFinancialInformation().getTotalActualProviderPaymentAmount()));
                                            claimTransactionMaster.setCobType("E");
                                            if(paymentInfo.getCrossoverCarrier() != null) {
                                                claimTransactionMaster.setCrossoverCarrierName(true);
                                                if(paymentInfo.getCrossoverCarrier().getPayorId() != null)
                                                    claimTransactionMaster.setCrossoverCarrierPayerId(paymentInfo.getCrossoverCarrier().getPayorId());
                                                else
                                                    claimTransactionMaster.setCrossoverCarrierPayerId(null);
                                                if(paymentInfo.getCrossoverCarrier().getOrganizationName() != null)
                                                    claimTransactionMaster.setCrossoverCarrierPayerName(paymentInfo.getCrossoverCarrier().getOrganizationName());
                                                else
                                                    claimTransactionMaster.setCrossoverCarrierPayerName(null);
                                            }
                                            else
                                                claimTransactionMaster.setCrossoverCarrierName(false);
                                            System.out.println("=====>"+paymentInfo.getPatientName().getFirstName());
                                            log.info("PATIENT CONTROL NUMBER=>"+paymentInfo.getClaimPaymentInfo().getPatientControlNumber());
                                            ClaimsCOB835Master claimsCOB835Master = claimsCOB835MasterRepository.getClaimTransactionMasterOnClaimControlNumber(paymentInfo.getClaimPaymentInfo().getPatientControlNumber());//
                                            if(claimsCOB835Master == null) {//END If for claim Control Number
                                                operationOnClaimsCOB835(claimTransactionMaster, paymentInfo, claimsCOB835Masters, fileName, "P");
                                            }
                                            else {
                                                System.out.println("========================NOT ENTRY-Data Already EXISTS(Secondary Cross-over Payment)=========================");
                                                operationOnClaimsCOB835(claimTransactionMaster, paymentInfo, claimsCOB835Masters, fileName, "S");
                                            }
                                        });//END paymentInfo
                                });
                        });
                }
                else if(list.getClaimsTransactionReport().getClaimStatusResponseTransaction() != null && reportType.equals("X3")) {
                    log.info("======================Enter to Claim Transaction Status======================");
                    list.getClaimsTransactionReport().getClaimStatusResponseTransaction().getTransactions().stream()
                        .forEach(trans -> {
                            System.out.println("======>"+trans.getControlNumber());
                            trans.getPayers().stream()
                                .forEach(payer -> {
                                    payer.getClaimStatusTransactions().stream()
                                        .forEach(claimStatusTransaction -> {
                                            claimStatusTransaction.getClaimStatusDetails().stream()
                                                .forEach(claimStatusDetail -> {
                                                    claimStatusDetail.getPatientClaimStatusDetails().stream()
                                                        .forEach(patientClaimStatusDetails -> {
                                                            patientClaimStatusDetails.getClaims().stream()
                                                                .forEach(claims -> {
                                                                    ClaimsStatus277Master claimTransactionStatusMaster = new ClaimsStatus277Master();
                                                                    claimTransactionStatusMaster.setFileName(list.getFileName());
                                                                    claimTransactionStatusMaster.setControlNumber(trans.getControlNumber());
                                                                    claimTransactionStatusMaster.setServiceProviderName(claimStatusDetail.getServiceProvider().getOrganizationName());
                                                                    claimTransactionStatusMaster.setServiceProviderNpi(claimStatusDetail.getServiceProvider().getNpi());

                                                                    if(patientClaimStatusDetails.getSubscriber() != null) {
                                                                        claimTransactionStatusMaster.setPatientFirstName(patientClaimStatusDetails.getSubscriber().getFirstName());
                                                                        claimTransactionStatusMaster.setPatientLastName(patientClaimStatusDetails.getSubscriber().getLastName());
                                                                        claimTransactionStatusMaster.setPatientMemberId(patientClaimStatusDetails.getSubscriber().getMemberId());
                                                                    }

                                                                    claimTransactionStatusMaster.setTradingPartnerClaimNumber(claims.getClaimStatus().getTradingPartnerClaimNumber());
                                                                    claimTransactionStatusMaster.setPatientAccountNumber(claims.getClaimStatus().getPatientAccountNumber());

                                                                    if(claims.getClaimStatus().getClaimServiceBeginDate() != null)
                                                                        claimTransactionStatusMaster.setClaimServiceBeginDate(ApplicationDateUtility.convertStringToDateOnSpecific(claims.getClaimStatus().getClaimServiceBeginDate()));
                                                                    else
                                                                        claimTransactionStatusMaster.setClaimServiceBeginDate(null);
                                                                    if(claims.getClaimStatus().getClaimServiceEndDate() != null)
                                                                        claimTransactionStatusMaster.setClaimServiceEndDate(ApplicationDateUtility.convertStringToDateOnSpecific(claims.getClaimStatus().getClaimServiceEndDate()));
                                                                    else
                                                                        claimTransactionStatusMaster.setClaimServiceEndDate(null);
                                                                    claimTransactionStatusMaster.setTradingPartnerClaimNumber(claims.getClaimStatus().getTradingPartnerClaimNumber());
                                                                    claimTransactionStatusMaster.setClearinghouseTraceNumber(claims.getClaimStatus().getClearinghouseTraceNumber());
                                                                    claimTransactionStatusMaster.setPatientAccountNumber(claims.getClaimStatus().getPatientAccountNumber());
                                                                    claimTransactionStatusMaster.setStatus(DefineStatus.Active.name());
                                                                    claimTransactionStatusMaster.setCreatedById(0L);
                                                                    claimTransactionStatusMaster.setCreatedByName("System");
                                                                    claimTransactionStatusMaster.setCreatedDate(LocalDate.now());
                                                                    claimTransactionStatusMaster.setUpdatedDate(LocalDate.now());
                                                                    claimTransactionStatusMaster.setUpdatedById(0L);
                                                                    claimTransactionStatusMaster.setUpdatedByName("System");
                                                                    claimTransactionStatusMaster.setClaimsStatus277MasterUuid(UUID.randomUUID());

                                                                    Optional<LocalDate> effectiveDate = Optional.ofNullable(claims.getClaimStatus().getInformationClaimStatuses().stream()
                                                                        .map(infoClaimStatuses -> ApplicationDateUtility.convertStringToDateOnSpecific(infoClaimStatuses.getStatusInformationEffectiveDate())).max(LocalDate::compareTo).get());
                                                                    System.out.println("=======>Effective Date=>"+effectiveDate.get());

                                                                    claims.getClaimStatus().getInformationClaimStatuses().stream()
                                                                        .forEach(informationClaimStatuses -> {
                                                                            ClaimsStatus277Master transactionStatusMaster = claimsStatus277MasterRepository.getInformationClaimStatusByEffectiveDateNClaimNumber(claims.getClaimStatus().getTradingPartnerClaimNumber(), effectiveDate.get());
                                                                            if(transactionStatusMaster == null) {
                                                                                System.out.println("###############Transaction Status Master is NULL###################");
                                                                                if(informationClaimStatuses.getTotalClaimChargeAmount() != null)
                                                                                    claimTransactionStatusMaster.setTotalClaimChargeAmount(Double.parseDouble(informationClaimStatuses.getTotalClaimChargeAmount()));
                                                                                else
                                                                                    claimTransactionStatusMaster.setTotalClaimChargeAmount(0.00);
                                                                                if(informationClaimStatuses.getClaimPaymentAmount() != null)
                                                                                    claimTransactionStatusMaster.setTotalClaimPaymentAmount(Double.parseDouble(informationClaimStatuses.getClaimPaymentAmount()));
                                                                                else
                                                                                    claimTransactionStatusMaster.setTotalClaimPaymentAmount(0.00);
                                                                                if(informationClaimStatuses.getAdjudicatedFinalizedDate() != null)
                                                                                    claimTransactionStatusMaster.setAdjudicatedFinalizedDate(ApplicationDateUtility.convertStringToDateOnSpecific(informationClaimStatuses.getAdjudicatedFinalizedDate()));
                                                                                else
                                                                                    claimTransactionStatusMaster.setAdjudicatedFinalizedDate(null);
                                                                                if(informationClaimStatuses.getRemittanceDate() != null)
                                                                                    claimTransactionStatusMaster.setRemittanceDate(ApplicationDateUtility.convertStringToDateOnSpecific(informationClaimStatuses.getRemittanceDate()));
                                                                                else
                                                                                    claimTransactionStatusMaster.setRemittanceDate(null);
                                                                                claimTransactionStatusMaster.setRemittanceTraceNumber(informationClaimStatuses.getRemittanceTraceNumber());
                                                                                claimTransactionStatusMaster.setStatusInformationEffectiveDate(ApplicationDateUtility.convertStringToDateOnSpecific(informationClaimStatuses.getStatusInformationEffectiveDate()));
                                                                                informationClaimStatuses.getInformationStatuses().stream()
                                                                                    .forEach(informationStatuses -> {
                                                                                        claimTransactionStatusMaster.setClaimStatusCategoryCode(informationStatuses.getHealthCareClaimStatusCategoryCode());
                                                                                        claimTransactionStatusMaster.setClaimStatusCategoryCodeValue(informationStatuses.getHealthCareClaimStatusCategoryCodeValue());
                                                                                        claimTransactionStatusMaster.setStatusCode(informationStatuses.getStatusCode());
                                                                                        claimTransactionStatusMaster.setStatusCodeValue(informationStatuses.getStatusCodeValue());
                                                                                    });
                                                                                ClaimsStatus277Master saveTransactionStatusMaster = saveTransactionStatusDetails(claimTransactionStatusMaster, claims, effectiveDate, fileName);
                                                                                claimsStatus277Masters.add(saveTransactionStatusMaster);
                                                                            }
                                                                            else {
                                                                                System.out.println("##########################ELSE##############################");
                                                                            }
                                                                        });
                                                                });//claims
                                                        });
                                                });
                                        });
                                });
                        });
                }
                List<ClaimsCOB835Data> claimsCOB835Data = new ArrayList<>();
                List<ClaimsStatus277Data> claimsStatus277Data = new ArrayList<>();
                try {
                    for(ClaimsCOB835Master claimsCOB835 : claimsCOB835Masters) {
                        ClaimsCOB835Data claimsCOBData = new ClaimsCOB835Data();
                        BeanUtils.copyProperties(claimsCOB835, claimsCOBData);
                        claimsCOB835Data.add(claimsCOBData);
                    }
                    for(ClaimsStatus277Master claimsStatus277 : claimsStatus277Masters) {
                        ClaimsStatus277Data claimsStatusData = new ClaimsStatus277Data();
                        BeanUtils.copyProperties(claimsStatus277, claimsStatusData);
                        claimsStatus277Data.add(claimsStatusData);
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
                claimTransactionOutcome.setClaimsCOB835MasterList(claimsCOB835Data);
                claimTransactionOutcome.setClaimsStatus277MasterList(claimsStatus277Data);
            }//First if
        });//Main forEach
        return claimTransactionOutcome;
    }

    private void operationOnClaimsCOB835(ClaimsCOB835Master claimTransactionMaster, PaymentInfo paymentInfo, List<ClaimsCOB835Master> claimsCOB835Masters, String fileName, String operation){
        claimTransactionMaster.setPatientFirstName(paymentInfo.getPatientName().getFirstName());
        claimTransactionMaster.setPatientLastName(paymentInfo.getPatientName().getLastName());
        claimTransactionMaster.setPatientMemberId(paymentInfo.getPatientName().getMemberId());

        claimTransactionMaster.setReceivedOn(LocalDate.now());
        claimTransactionMaster.setStatus(DefineStatus.Active.name());

        claimTransactionMaster.setCreatedById(0L);
        claimTransactionMaster.setCreatedByName("System");
        claimTransactionMaster.setCreatedDate(LocalDate.now());
        claimTransactionMaster.setUpdatedDate(LocalDate.now());
        claimTransactionMaster.setUpdatedById(0L);
        claimTransactionMaster.setUpdatedByName("System");
        claimTransactionMaster.setClaimsCob835MasterUuid(UUID.randomUUID());

        if(operation.equals("P"))
            claimTransactionMaster.setPatientControlNumber(paymentInfo.getClaimPaymentInfo().getPatientControlNumber());
        if(operation.equals("S")) {
            String patientControlNumber = "CLS"+paymentInfo.getClaimPaymentInfo().getPatientControlNumber().substring(3);
            claimTransactionMaster.setPatientControlNumber(patientControlNumber);
        }

        claimTransactionMaster.setPayerClaimControlNumber(paymentInfo.getClaimPaymentInfo().getPayerClaimControlNumber());
        if(paymentInfo.getClaimReceivedDate() != null)
            claimTransactionMaster.setClaimReceivedDate(ApplicationDateUtility.convertStringToDateOnSpecific(paymentInfo.getClaimReceivedDate()));
        else
            claimTransactionMaster.setClaimReceivedDate(null);
        claimTransactionMaster.setTotalClaimChargeAmount(Double.parseDouble(paymentInfo.getClaimPaymentInfo().getTotalClaimChargeAmount()));
        claimTransactionMaster.setTotalClaimPaymentAmount(Double.parseDouble(paymentInfo.getClaimPaymentInfo().getClaimPaymentAmount()));
        if(paymentInfo.getClaimPaymentInfo().getPatientResponsibilityAmount() != null)
            claimTransactionMaster.setTotalPatientResponsibilityAmount(Double.parseDouble(paymentInfo.getClaimPaymentInfo().getPatientResponsibilityAmount()));
        else
            claimTransactionMaster.setTotalPatientResponsibilityAmount(0.00);
        ClaimsCOB835Master saveClaimTransactionMaster = claimsCOB835MasterRepository.save(claimTransactionMaster);
        DepositMasterDetails depositMasterDetails = createDepositOnClaimsCOB835(saveClaimTransactionMaster);
        ReceiptMasterDetails receiptMasterDetails = createReceiptOnClaimsCOB835(saveClaimTransactionMaster, depositMasterDetails);

        System.out.println("=====>CLAIM COB 835 MASTER ID===>"+saveClaimTransactionMaster.getClaimCob835MasterId());
        claimsCOB835Masters.add(saveClaimTransactionMaster);
        List<ClaimsCOB835Details> claimsCOB835DetailsList = new ArrayList<>();
        paymentInfo.getServiceLines().stream()
            .forEach(serviceLine -> {
                ServiceLineDetails serviceLineDetails = new ServiceLineDetails();
                serviceLineDetails.setServiceDate(serviceLine.getServiceDate());
                serviceLineDetails.setAdjudicatedProcedureCode(serviceLine.getServicePaymentInformation().getAdjudicatedProcedureCode());

                String modifierCodes = "";
                if(serviceLine.getServicePaymentInformation().getAdjudicatedProcedureModifierCodes() != null) {
                    for(int i = 0; i < serviceLine.getServicePaymentInformation().getAdjudicatedProcedureModifierCodes().size(); i++) {
                        if(modifierCodes != "")
                            modifierCodes = modifierCodes +","+serviceLine.getServicePaymentInformation().getAdjudicatedProcedureModifierCodes().get(i);
                        else
                            modifierCodes = serviceLine.getServicePaymentInformation().getAdjudicatedProcedureModifierCodes().get(i);
                    }
                }
                serviceLineDetails.setAdjudicatedProcedureModifierCodes(modifierCodes);
                serviceLineDetails.setChargeAmount(Double.parseDouble(serviceLine.getServicePaymentInformation().getLineItemChargeAmount()));
                if(serviceLine.getServiceSupplementalAmounts() != null)
                    serviceLineDetails.setAllowedAmount(Double.parseDouble(serviceLine.getServiceSupplementalAmounts().getAllowedActual()));
                else
                    serviceLineDetails.setAllowedAmount(0.00);
                serviceLineDetails.setProviderPaymentAmount(Double.parseDouble(serviceLine.getServicePaymentInformation().getLineItemProviderPaymentAmount()));

                ClaimsCOB835Details claimTransactionDetails = new ClaimsCOB835Details();
                serviceLine.getServiceAdjustments().stream()
                    .forEach(serviceAdj -> {
                        if(serviceAdj.getClaimAdjustmentGroupCode().equalsIgnoreCase("PR") && serviceAdj.getClaimAdjustmentGroupCode() != null) {
                            claimTransactionDetails.setAdjustmentPrCode1(serviceAdj.getAdjustmentReasonCode1());
                            if(serviceAdj.getAdjustmentAmount1() != null)
                                claimTransactionDetails.setAdjustmentPrCode1Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount1()));
                            else
                                claimTransactionDetails.setAdjustmentPrCode1Amount(0.00);

                            claimTransactionDetails.setAdjustmentPrCode2(serviceAdj.getAdjustmentReasonCode2());
                            if(serviceAdj.getAdjustmentAmount2() != null)
                                claimTransactionDetails.setAdjustmentPrCode2Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount2()));
                            else
                                claimTransactionDetails.setAdjustmentPrCode2Amount(0.00);

                            claimTransactionDetails.setAdjustmentPrCode3(serviceAdj.getAdjustmentReasonCode3());
                            if(serviceAdj.getAdjustmentAmount3() != null)
                                claimTransactionDetails.setAdjustmentPrCode3Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount3()));
                            else
                                claimTransactionDetails.setAdjustmentPrCode3Amount(0.00);

                            claimTransactionDetails.setAdjustmentPrCode4(serviceAdj.getAdjustmentReasonCode4());
                            if(serviceAdj.getAdjustmentAmount4() != null)
                                claimTransactionDetails.setAdjustmentPrCode4Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount4()));
                            else
                                claimTransactionDetails.setAdjustmentPrCode4Amount(0.00);
                        }
                        if(serviceAdj.getClaimAdjustmentGroupCode().equalsIgnoreCase("CO") && serviceAdj.getClaimAdjustmentGroupCode() != null) {
                            claimTransactionDetails.setAdjustmentCoCode1(serviceAdj.getAdjustmentReasonCode1());
                            if(serviceAdj.getAdjustmentAmount1() != null)
                                claimTransactionDetails.setAdjustmentCoCode1Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount1()));
                            else
                                claimTransactionDetails.setAdjustmentCoCode1Amount(0.00);

                            claimTransactionDetails.setAdjustmentCoCode2(serviceAdj.getAdjustmentReasonCode2());
                            if(serviceAdj.getAdjustmentAmount2() != null)
                                claimTransactionDetails.setAdjustmentCoCode2Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount2()));
                            else
                                claimTransactionDetails.setAdjustmentCoCode2Amount(0.00);

                            claimTransactionDetails.setAdjustmentCoCode3(serviceAdj.getAdjustmentReasonCode3());
                            if(serviceAdj.getAdjustmentAmount3() != null)
                                claimTransactionDetails.setAdjustmentCoCode3Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount3()));
                            else
                                claimTransactionDetails.setAdjustmentCoCode3Amount(0.00);

                            claimTransactionDetails.setAdjustmentCoCode4(serviceAdj.getAdjustmentReasonCode4());
                            if(serviceAdj.getAdjustmentAmount4() != null)
                                claimTransactionDetails.setAdjustmentCoCode4Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount4()));
                            else
                                claimTransactionDetails.setAdjustmentCoCode4Amount(0.00);
                        }
                        if(serviceAdj.getClaimAdjustmentGroupCode().equalsIgnoreCase("CR") && serviceAdj.getClaimAdjustmentGroupCode() != null) {
                            claimTransactionDetails.setAdjustmentCrCode1(serviceAdj.getAdjustmentReasonCode1());
                            if(serviceAdj.getAdjustmentAmount1() != null)
                                claimTransactionDetails.setAdjustmentCrCode1Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount1()));
                            else
                                claimTransactionDetails.setAdjustmentCrCode1Amount(0.00);

                            claimTransactionDetails.setAdjustmentCrCode2(serviceAdj.getAdjustmentReasonCode2());
                            if(serviceAdj.getAdjustmentAmount2() != null)
                                claimTransactionDetails.setAdjustmentCrCode2Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount2()));
                            else
                                claimTransactionDetails.setAdjustmentCrCode2Amount(0.00);

                            claimTransactionDetails.setAdjustmentCrCode3(serviceAdj.getAdjustmentReasonCode3());
                            if(serviceAdj.getAdjustmentAmount3() != null)
                                claimTransactionDetails.setAdjustmentCrCode3Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount3()));
                            else
                                claimTransactionDetails.setAdjustmentCrCode3Amount(0.00);

                            claimTransactionDetails.setAdjustmentCrCode4(serviceAdj.getAdjustmentReasonCode4());
                            if(serviceAdj.getAdjustmentAmount4() != null)
                                claimTransactionDetails.setAdjustmentCrCode4Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount4()));
                            else
                                claimTransactionDetails.setAdjustmentCrCode4Amount(0.00);
                        }
                        if(serviceAdj.getClaimAdjustmentGroupCode().equalsIgnoreCase("PI") && serviceAdj.getClaimAdjustmentGroupCode() != null) {
                            claimTransactionDetails.setAdjustmentPiCode1(serviceAdj.getAdjustmentReasonCode1());
                            if(serviceAdj.getAdjustmentAmount1() != null)
                                claimTransactionDetails.setAdjustmentPiCode1Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount1()));
                            else
                                claimTransactionDetails.setAdjustmentPiCode1Amount(0.00);

                            claimTransactionDetails.setAdjustmentPiCode2(serviceAdj.getAdjustmentReasonCode2());
                            if(serviceAdj.getAdjustmentAmount2() != null)
                                claimTransactionDetails.setAdjustmentPiCode2Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount2()));
                            else
                                claimTransactionDetails.setAdjustmentPiCode2Amount(0.00);

                            claimTransactionDetails.setAdjustmentPiCode3(serviceAdj.getAdjustmentReasonCode3());
                            if(serviceAdj.getAdjustmentAmount3() != null)
                                claimTransactionDetails.setAdjustmentPiCode3Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount3()));
                            else
                                claimTransactionDetails.setAdjustmentPiCode3Amount(0.00);

                            claimTransactionDetails.setAdjustmentPiCode4(serviceAdj.getAdjustmentReasonCode4());
                            if(serviceAdj.getAdjustmentAmount4() != null)
                                claimTransactionDetails.setAdjustmentPiCode4Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount4()));
                            else
                                claimTransactionDetails.setAdjustmentPiCode4Amount(0.00);
                        }
                        if(serviceAdj.getClaimAdjustmentGroupCode().equalsIgnoreCase("OA") && serviceAdj.getClaimAdjustmentGroupCode() != null) {
                            claimTransactionDetails.setAdjustmentOaCode1(serviceAdj.getAdjustmentReasonCode1());
                            if(serviceAdj.getAdjustmentAmount1() != null)
                                claimTransactionDetails.setAdjustmentOaCode1Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount1()));
                            else
                                claimTransactionDetails.setAdjustmentOaCode1Amount(0.00);

                            claimTransactionDetails.setAdjustmentOaCode2(serviceAdj.getAdjustmentReasonCode2());
                            if(serviceAdj.getAdjustmentAmount2() != null)
                                claimTransactionDetails.setAdjustmentOaCode2Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount2()));
                            else
                                claimTransactionDetails.setAdjustmentOaCode2Amount(0.00);

                            claimTransactionDetails.setAdjustmentOaCode3(serviceAdj.getAdjustmentReasonCode3());
                            if(serviceAdj.getAdjustmentAmount3() != null)
                                claimTransactionDetails.setAdjustmentOaCode3Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount3()));
                            else
                                claimTransactionDetails.setAdjustmentOaCode3Amount(0.00);

                            claimTransactionDetails.setAdjustmentOaCode4(serviceAdj.getAdjustmentReasonCode4());
                            if(serviceAdj.getAdjustmentAmount4() != null)
                                claimTransactionDetails.setAdjustmentOaCode4Amount(Double.parseDouble(serviceAdj.getAdjustmentAmount4()));
                            else
                                claimTransactionDetails.setAdjustmentOaCode4Amount(0.00);
                        }
                    });
                claimTransactionDetails.setServiceDate(ApplicationDateUtility.convertStringToDateOnSpecific(serviceLineDetails.getServiceDate()));

                claimTransactionDetails.setAdjudicatedProcedureCode(serviceLineDetails.getAdjudicatedProcedureCode());
                claimTransactionDetails.setAdjudicatedProcedureModifierCodes(serviceLineDetails.getAdjudicatedProcedureModifierCodes());
                claimTransactionDetails.setChargeAmount(serviceLineDetails.getChargeAmount());
                claimTransactionDetails.setAllowedAmount(serviceLineDetails.getAllowedAmount());

                claimTransactionDetails.setProviderPaymentAmount(serviceLineDetails.getProviderPaymentAmount());
                claimTransactionDetails.setClaimCob835MasterId(saveClaimTransactionMaster.getClaimCob835MasterId());
                claimTransactionDetails.setStatus(DefineStatus.Active.name());
                claimTransactionDetails.setClaimsCob835DetailsUuid(UUID.randomUUID());
                claimsCOB835DetailsList.add(claimTransactionDetails);
                claimsCOB835DetailsRepository.saveAll(claimsCOB835DetailsList);
            });
        createTransaction835(claimsCOB835DetailsList, receiptMasterDetails);
        ClaimsReportFileProcessStatus fileProcessStatus = new ClaimsReportFileProcessStatus();
        fileProcessStatus.setFileName(fileName);
        //fileProcessStatus.setInternalClaimControlNumber(paymentInfo.getClaimPaymentInfo().getPatientControlNumber());
        fileProcessStatus.setProcessDate(LocalDate.now());
        fileProcessStatus.setProcessStatus(DefineStatus.Active.name());
        fileProcessStatus.setRunDate(LocalDate.now());
        fileProcessStatus.setClaimsReportFileProcessStatusUuid(UUID.randomUUID());
        claimsReportFileProcessStatusRepository.save(fileProcessStatus);
    }

    public ClaimsStatus277Master saveTransactionStatusDetails(ClaimsStatus277Master claimTransactionStatusMaster, Claim claims, Optional<LocalDate> effectiveDate, String fileName) {
        ClaimsStatus277Master saveTransactionStatusMaster = claimsStatus277MasterRepository.save(claimTransactionStatusMaster);
        if(claims.getServiceLines() != null) {
            List<ClaimsStatus277Details> claimTransactionStatusDetailsList = new ArrayList<>();
            claims.getServiceLines().stream()
                .forEach(serviceLines -> {
                    ClaimsStatus277Details claimTransactionStatusDetails = new ClaimsStatus277Details();
                    claimTransactionStatusDetails.setProcedureCode(serviceLines.getService().getProcedureCode());
                    String modifierCodes = "";
                    if(serviceLines.getService().getProcedureModifiers() != null) {
                        for(int i = 0; i < serviceLines.getService().getProcedureModifiers().size(); i++) {
                            if(modifierCodes != "")
                                modifierCodes = modifierCodes +","+serviceLines.getService().getProcedureModifiers().get(i);
                            else
                                modifierCodes = serviceLines.getService().getProcedureModifiers().get(i);
                        }
                    }
                    claimTransactionStatusDetails.setProcedureModifiers(modifierCodes);
                    claimTransactionStatusDetails.setSubmittedUnits(serviceLines.getService().getSubmittedUnits());
                    claimTransactionStatusDetails.setChargeAmount(Double.parseDouble(serviceLines.getService().getChargeAmount()));
                    claimTransactionStatusDetails.setPaidAmount(Double.parseDouble(serviceLines.getService().getAmountPaid()));
                    claimTransactionStatusDetails.setServiceLineBeginDate(ApplicationDateUtility.convertStringToDateOnSpecific(serviceLines.getBeginServiceLineDate()));
                    claimTransactionStatusDetails.setServiceLineEndDate(ApplicationDateUtility.convertStringToDateOnSpecific(serviceLines.getEndServiceLineDate()));

                    serviceLines.getServiceClaimStatuses().stream()
                        .forEach(serviceClaimStatuses -> {
                            claimTransactionStatusDetails.setEffectiveDate(ApplicationDateUtility.convertStringToDateOnSpecific(serviceClaimStatuses.getEffectiveDate()));
                            serviceClaimStatuses.getServiceStatuses().stream()
                                .forEach(serviceStatuses -> {
                                    claimTransactionStatusDetails.setClaimStatusCategoryCode(serviceStatuses.getHealthCareClaimStatusCategoryCode());
                                    claimTransactionStatusDetails.setClaimStatusCategoryCodeValue(serviceStatuses.getHealthCareClaimStatusCategoryCodeValue());
                                    claimTransactionStatusDetails.setStatusCode(serviceStatuses.getStatusCode());
                                    claimTransactionStatusDetails.setStatusCodeValue(serviceStatuses.getStatusCodeValue());
                                });
                        });
                    claimTransactionStatusDetails.setStatus(DefineStatus.Active.name());
                    claimTransactionStatusDetails.setClaimStatus277MasterId(saveTransactionStatusMaster.getClaimStatus277MasterId());
                    claimTransactionStatusDetails.setClaimsStatus277DetailsUuid(UUID.randomUUID());
                    claimTransactionStatusDetailsList.add(claimTransactionStatusDetails);
                });
            claimsStatus277DetailsRepository.saveAll(claimTransactionStatusDetailsList);
        }
        ClaimsReportFileProcessStatus fileProcessStatus = new ClaimsReportFileProcessStatus();
        fileProcessStatus.setFileName(fileName);
        //fileProcessStatus.setInternalClaimControlNumber(saveTransactionStatusMaster.getPatientAccountNumber());
        fileProcessStatus.setProcessDate(LocalDate.now());
        fileProcessStatus.setProcessStatus(DefineStatus.Active.name());
        fileProcessStatus.setRunDate(LocalDate.now());
        fileProcessStatus.setClaimsReportFileProcessStatusUuid(UUID.randomUUID());
        claimsReportFileProcessStatusRepository.save(fileProcessStatus);
        return saveTransactionStatusMaster;
    }

    @Override
    @Transactional
    public void claimSubmissionStatusOperation(ClaimTransactionOutcome transactionOutcome) throws ExecutionException, InterruptedException {
        log.info("===============================Claim Submission Status==============================");
        List<Long> salesOrderId = new ArrayList<>();
        if(transactionOutcome.getClaimsCOB835MasterList() != null) {
            transactionOutcome.getClaimsCOB835MasterList().stream()
                .forEach(claimRemittance -> {
                    System.out.println("X5==========>"+claimRemittance.getFileName());
                    if(claimRemittance.getCrossoverCarrierName()){
                        System.out.println("==============YES:There is Cross-over in claim========================");
                        String payerClaimControlNumber = claimRemittance.getPayerClaimControlNumber();
                        if(Objects.isNull(claimRemittance.getPayerClaimControlNumber()) || claimRemittance.getPayerClaimControlNumber().equals(""))
                            payerClaimControlNumber = "DUMMY";
                        log.info("=========payerClaimControlNumber======"+payerClaimControlNumber);
                        log.info("=========claimRemittance.getPatientControlNumber()======"+claimRemittance.getPatientControlNumber());
                        ClaimSubmissionStatus claimSubmissionStatus = claimSubmissionStatusRepository.getClaimSubmissionStatusByPayorClaimControlNumber(payerClaimControlNumber, claimRemittance.getPatientControlNumber());
                        if(claimSubmissionStatus != null) {
                            Optional<ClaimsCOB835Master> claimsCOB835Master = claimsCOB835MasterRepository.findById(claimRemittance.getClaimCob835MasterId());
                            claimSubmissionStatus.setClaimCob835MasterId(claimsCOB835Master.get().getClaimCob835MasterId());
                            claimSubmissionStatus.setEraDate(LocalDate.now());
                            if (claimRemittance.getCrossoverCarrierName())
                                claimSubmissionStatus.setReadyForSubmissionStatus(null);
                            claimSubmissionStatus.setUpdatedById(0L);
                            claimSubmissionStatus.setUpdatedByName("Bimal");
                            claimSubmissionStatus.setUpdatedDate(LocalDate.now());
                            claimSubmissionStatusRepository.save(claimSubmissionStatus);
                        }
                    }
                    else{
                        System.out.println("==============NO:There is no Cross-over in claim======================");
                        String payerClaimControlNumber = "";
                        if(Objects.isNull(claimRemittance.getPayerClaimControlNumber()) || claimRemittance.getPayerClaimControlNumber().equals(""))
                            payerClaimControlNumber = "DUMMY";
                        ClaimSubmissionStatus claimSubmissionStatus = claimSubmissionStatusRepository.getClaimSubmissionStatusByPayorClaimControlNumber(payerClaimControlNumber, claimRemittance.getPatientControlNumber());
                        if(claimSubmissionStatus != null && claimSubmissionStatus.getClaimCob835MasterId() != null) {
                            Optional<ClaimsCOB835Master> claimsCOB835Master = claimsCOB835MasterRepository.findById(claimRemittance.getClaimCob835MasterId());
                            claimSubmissionStatus.setClaimCob835MasterId(claimsCOB835Master.get().getClaimCob835MasterId());
                            claimSubmissionStatus.setEraDate(LocalDate.now());
                            if(claimRemittance.getCrossoverCarrierName())
                                claimSubmissionStatus.setReadyForSubmissionStatus(null);
                            else
                                claimSubmissionStatus.setReadyForSubmissionStatus("Y");
                            claimSubmissionStatus.setUpdatedById(0L);
                            claimSubmissionStatus.setUpdatedByName("Bimal");
                            claimSubmissionStatus.setUpdatedDate(LocalDate.now());
                            ClaimSubmissionStatus submissionStatus = claimSubmissionStatusRepository.save(claimSubmissionStatus);
                            String claimControlNumber = submissionStatus.getIntClaimNo().substring(0, 3);
                            log.info("================claimControlNumber==============="+claimControlNumber);
                            String internalClaimControlNumber = null;
                            if(claimControlNumber.equalsIgnoreCase("CLP") || claimControlNumber.equalsIgnoreCase("CLR"))
                                internalClaimControlNumber = "CLS"+submissionStatus.getIntClaimNo().substring(3);
                            log.info("================internalClaimControlNumber==============="+internalClaimControlNumber);
                            SecondaryClaimsSubmissionMaster secondaryClaimsSubmissionMaster = secondaryClaimsSubmissionMasterRepository.getSecondaryClaimSubmissionDetailsOnInternalClaimSlNo(submissionStatus.getSalesOrderId(), internalClaimControlNumber);
                            if(secondaryClaimsSubmissionMaster != null){
                                log.info("================Update secondaryClaimsSubmissionMaster===============");
                                secondaryClaimsSubmissionMaster.setOtherPayerAdjudicationOrPaymentDate(claimRemittance.getCheckIssueOrEFTEffectiveDate());
                                secondaryClaimsSubmissionMaster.setOtherPayerClaimControlNumber(claimRemittance.getPayerClaimControlNumber());
                                secondaryClaimsSubmissionMaster.setPayerPaidAmount(claimRemittance.getTotalClaimPaymentAmount());
                                secondaryClaimsSubmissionMaster.setRemainingPatientLiability(claimRemittance.getTotalPatientResponsibilityAmount());
                                SecondaryClaimsSubmissionMaster submissionMaster = secondaryClaimsSubmissionMasterRepository.save(secondaryClaimsSubmissionMaster);
                                if(submissionMaster != null){
                                    log.info("================Update updateServiceLineMasterDataOnSalesOrder===============");
                                    ModelMapper modelMapper = new ModelMapper();
                                    List<LineAdjustmentProjection> lineAdjustments = secondaryServiceLinesMasterRepository
                                        .getServiceLineMasterDataOnSalesOrder(submissionMaster.getSalesOrderId(), submissionMaster.getClaimControlNo()).stream()
                                        .map(lineAdjustmentProjection -> modelMapper.map(lineAdjustmentProjection, LineAdjustmentProjection.class))
                                        .collect(Collectors.toList());
                                    log.info("================lineAdjustments.size()================"+lineAdjustments.size());
                                    if(lineAdjustments != null && lineAdjustments.size() > 0){
                                        lineAdjustments.stream().forEach(line -> {
                                            log.info("==========>>>>>ID==================>>>>"+submissionMaster.getChangeHealthSecondarySubmisionMasterId());
                                            log.info("==========>>>>>line.getAdjudicatedprocedure()==================>>>>"+line.getAdjudicatedprocedurecode());
                                            SecondaryServiceLinesMaster secondaryServiceLinesMaster = secondaryServiceLinesMasterRepository.getServiceLineMasterDataOnSubmissionMasterNProcCode(
                                                submissionMaster.getChangeHealthSecondarySubmisionMasterId(), line.getAdjudicatedprocedurecode());
                                            if(secondaryServiceLinesMaster != null){
                                                secondaryServiceLinesMaster.setPayorClaimControlNo(line.getPayerclaimcontrolnumber());
                                                secondaryServiceLinesMaster.setProviderPaymentAmount(line.getProviderpaymentamount());
                                                secondaryServiceLinesMaster.setLineAdjustment(line.getPr()+"#"+line.getCo()+"#"+line.getCr()+"#"+line.getOa()+"#"+line.getPi()+"#");
                                                secondaryServiceLinesMaster.setSecondaryClaimsSubmissionMaster(submissionMaster);
                                                secondaryServiceLinesMasterRepository.save(secondaryServiceLinesMaster);
                                            }
                                        });
                                        log.info("=================>submissionMaster============="+submissionMaster.getClaimControlNo());
                                        claimSubmissionStatus = claimSubmissionStatusRepository.getClaimSubmissionStatusByInternalClaimControlNumber(submissionMaster.getClaimControlNo());
                                        if(claimSubmissionStatus != null) {
                                            log.info("=================>If submissionMaster============="+claimSubmissionStatus.getPatientAccountNo());
                                            log.info("=================>If submissionMaster============="+claimSubmissionStatus.getIntClaimNo());
                                            claimSubmissionStatus.setReadyForSubmissionStatus("Y");
                                            claimSubmissionStatusRepository.save(claimSubmissionStatus);
                                        }
                                    }
                                }
                            }
                        }
                        else if(claimSubmissionStatus != null){
                            System.out.println("==============NO:There is no Cross-over in claim and Secondary Cross-over Payment======================");
                            String internalClaimControlNumber = "CLS"+claimRemittance.getPatientControlNumber().substring(3);
                            log.info("===========internalClaimControlNumber===============>"+internalClaimControlNumber);
                            log.info("===========claimRemittance.getPayerClaimControlNumber()===============>"+claimRemittance.getPayerClaimControlNumber());
                            if(Objects.isNull(claimRemittance.getPayerClaimControlNumber()) || claimRemittance.getPayerClaimControlNumber().equals(""))
                                payerClaimControlNumber = "DUMMY";
                            claimSubmissionStatus = claimSubmissionStatusRepository.getClaimSubmissionStatusByPayorClaimControlNumber(payerClaimControlNumber, internalClaimControlNumber);
                            if(claimSubmissionStatus != null) {
                                log.info("===========#######################===============>");
                                ClaimsCOB835Master claimsCOB835Master = claimsCOB835MasterRepository.getClaimTransactionMasterOnClaimControlNumber(claimSubmissionStatus.getPatientAccountNo());
                                claimSubmissionStatus.setClaimCob835MasterId(claimsCOB835Master.getClaimCob835MasterId());
                                claimSubmissionStatus.setEraDate(LocalDate.now());
                                claimSubmissionStatus.setUpdatedById(0L);
                                claimSubmissionStatus.setUpdatedByName("Bimal");
                                claimSubmissionStatus.setUpdatedDate(LocalDate.now());
                                claimSubmissionStatusRepository.save(claimSubmissionStatus);
                            }
                        }
                        else {
                            saveOn835Exception(claimRemittance);
                        }
                    }
                });
        }
        if(transactionOutcome.getClaimsStatus277MasterList() != null) {
            transactionOutcome.getClaimsStatus277MasterList().stream()
                .forEach(claimStatus -> {
                    System.out.println("X3==========>"+claimStatus.getFileName());
                    ClaimSubmissionStatus claimSubmissionStatus = claimSubmissionStatusRepository.getClaimSubmissionStatusByPatientControlNumberNPatientMemberId(claimStatus.getPatientAccountNumber());
                    if(claimSubmissionStatus != null) {
                        Optional<ClaimsStatus277Master> claimsStatus277Master = claimsStatus277MasterRepository.findById(claimStatus.getClaimStatus277MasterId());
                        claimSubmissionStatus.setClaimStatus277MasterId(claimsStatus277Master.get().getClaimStatus277MasterId());
                        claimSubmissionStatus.setResponseStatus(claimStatus.getClaimStatusCategoryCode());
                        claimSubmissionStatus.setResponseStatusDate(claimStatus.getRemittanceDate());
                        claimSubmissionStatus.setResponseStatusNotes(claimStatus.getClaimStatusCategoryCodeValue());
                        claimSubmissionStatus.setClaimSubmissionStatusUuid(UUID.randomUUID());
                        claimSubmissionStatus = claimSubmissionStatusRepository.save(claimSubmissionStatus);
                        if(claimStatus.getClaimStatusCategoryCode().equalsIgnoreCase("R1") || claimStatus.getClaimStatusCategoryCode().equalsIgnoreCase("D1")
                            || claimStatus.getClaimStatusCategoryCode().equalsIgnoreCase("D2")
                            || claimStatus.getClaimStatusCategoryCode().equalsIgnoreCase("X2")) {
                            salesOrderId.add(claimSubmissionStatus.getSalesOrderId());
                        }
                    }
                    else {
                        saveOn277Exception(claimStatus);
                    }
                });
            log.info("============>salesOrderId.size()<============="+salesOrderId.size());
            if(salesOrderId.size() > 0){
                String token = AccessTokenUtilities.getOtherwaytoFindAccessToken();
                log.info("============><============="+token);
                MultiValueMap<String, List<Long>> formData = new LinkedMultiValueMap<>();
                formData.add("salesOrderId", salesOrderId);

                String url = "http://localhost:8080/services/salesorder/api/updateHoldStatus";
                log.info("=================url=====================================>"+url);
                ServiceOutcome externalServiceOutcome = webClientBuilder.build().post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer "+token)
                    .body(Mono.just(salesOrderId), Long.class)
                    .retrieve()
                    .bodyToMono(new ParameterizedTypeReference<ServiceOutcome>() {}).toFuture().get();
                log.info("===================>OUTPUT======================>"+externalServiceOutcome.getOutcome()+"===="+externalServiceOutcome.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public void process835CrossoverInsurance(){
        log.info("===================>process835CrossoverInsurance======================>");
        ModelMapper mapper = new ModelMapper();
        List<ClaimsCOB835CrossoverProjection> claimsCOB835CrossoverProjections = claimsCOB835MasterRepository.getClaimsCOB835CrossoverProjectionDetails()
            .stream()
            .map(claimsCOB835CrossoverProjection -> mapper.map(claimsCOB835CrossoverProjection, ClaimsCOB835CrossoverProjection.class)).collect(Collectors.toList());
        log.info("========>SIZE======="+claimsCOB835CrossoverProjections.size());
        String token = AccessTokenUtilities.getOtherwaytoFindAccessToken();
        claimsCOB835CrossoverProjections.stream().forEach(claimCOBCrossover -> {
            log.info("============><============="+claimCOBCrossover.getCrossoverCarrierPayerId());
            log.info("============><============="+token);
            ServiceOutcome<InsuranceMasterDTO> insuranceMasterCrossoverServiceOutcome;
            try {
                String url = "/settings/api/fetchInsuranceByCmsCrossoverInsuranceIdNo";
                log.info("====URL===InsuranceMaster=========>"+url);
                insuranceMasterCrossoverServiceOutcome = webClientBuilder.build().get()
                    .uri(uriBuilder -> uriBuilder.path(url).queryParam("cmsCrossoverInsuranceIdNo", claimCOBCrossover.getCrossoverCarrierPayerId()).build())
                    .header("Authorization", "Bearer " + token)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .accept(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
                    .retrieve()
//                    .onStatus(HttpStatus::isError, clientResponse -> )
                    .bodyToMono(new ParameterizedTypeReference<ServiceOutcome<InsuranceMasterDTO>>() {}).toFuture().get();
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }

            ServiceOutcome<SalesOrderInsuranceDetailsDTO> salesOrderInsuranceDetailsDTOServiceOutcome;
            if(insuranceMasterCrossoverServiceOutcome.getOutcome() && !Objects.isNull(insuranceMasterCrossoverServiceOutcome.getData())){
                log.info("===================>1. OUTPUT========getCmsCrossoverInsuranceIdNo==============>"+insuranceMasterCrossoverServiceOutcome.getData().getCmsCrossoverInsuranceIdNo());
                ClaimSubmissionStatus claimSubmissionStatus = claimSubmissionStatusRepository.getClaimSubmissionStatusByInternalClaimControlNumber(claimCOBCrossover.getPatientControlNumber());
                if(claimSubmissionStatus != null){
                    try {
                        log.info("===================>claimSubmissionStatus.getIntClaimNo()======================>"+claimSubmissionStatus.getIntClaimNo());
                        String url = "/salesorder/api/getSOInsuranceDetailsBySOID";
                        String finalUrl = url;
                        log.info("====URL===GET====SalesorderSecondaryInsurance=========>"+url);
                        salesOrderInsuranceDetailsDTOServiceOutcome = webClientBuilder.build().get()
                            .uri(uriBuilder -> uriBuilder.path(finalUrl).queryParam("salesOrderID", claimSubmissionStatus.getSalesOrderId()).build())
                            .header("Authorization", "Bearer "+token)
                            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .retrieve()
                            .bodyToMono(new ParameterizedTypeReference<ServiceOutcome<SalesOrderInsuranceDetailsDTO>>() {}).toFuture().get();

                        log.info("===================>2.OUTPUT=======getSalesOrderNo===============>"+salesOrderInsuranceDetailsDTOServiceOutcome.getData().getSalesOrderNo());
                        if(salesOrderInsuranceDetailsDTOServiceOutcome.getData().getSecondaryInsurerId() != null){
                            log.info("============SecondaryInsuranceId is NOT NULL================="+salesOrderInsuranceDetailsDTOServiceOutcome.getData().getSecondaryInsurerId());
                            log.info("============InsuranceId is NOT NULL================="+insuranceMasterCrossoverServiceOutcome.getData().getInsuranceId());
                            if(salesOrderInsuranceDetailsDTOServiceOutcome.getData().getSecondaryInsurerId() == insuranceMasterCrossoverServiceOutcome.getData().getInsuranceId()){
                                log.info("============SecondaryInsuranceId is SAME================= Fetch the Secondary Insurance Details then Return the Details.");
                                PatientInsuranceParameterMicroserviceDTO savePatientInsuranceParameters =
                                    createPatientInsuranceParameters(salesOrderInsuranceDetailsDTOServiceOutcome.getData(),
                                        salesOrderInsuranceDetailsDTOServiceOutcome.getData().getSecondaryInsurerId(),
                                        salesOrderInsuranceDetailsDTOServiceOutcome.getData().getSecondaryInsurerName());
                                url = "/patient/api/savePatientInsuranceForMicroservice";
                                log.info("====URL===PATCH====Save OR Get Patient Insurance=========>"+url);
                                ServiceOutcome<PatientInsuranceDTO> patientInsuranceServiceOutcome = webClientBuilder.build().patch()
                                    .uri(url)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization", "Bearer "+token)
                                    .body(Mono.just(savePatientInsuranceParameters), PatientInsuranceParameterMicroserviceDTO.class)
                                    .retrieve()
                                    .bodyToMono(new ParameterizedTypeReference<ServiceOutcome<PatientInsuranceDTO>>() {}).toFuture().get();
                                log.info("===================>PATIENT OUTPUT======================>"+patientInsuranceServiceOutcome.getOutcome()+"===="+patientInsuranceServiceOutcome.getMessage());
                                log.info("===================>3.OUTPUT====>PatientInsuranceInfo======>"+patientInsuranceServiceOutcome.getData().getInsuranceName());
                                //1.ADD Secondary Insurance Details on Invoices using Stored Procedure.
                                if(patientInsuranceServiceOutcome.getOutcome()) {
                                    log.info("===================>1.ADD Secondary Insurance Details on Invoices using Stored Procedure.===============");
                                    String secondaryClaimControlNumber = "CLS"+claimSubmissionStatus.getIntClaimNo().substring(3);
                                    log.info("===================>secondaryClaimControlNumber======================>"+secondaryClaimControlNumber);
                                    ServiceOutcome<AddSecondaryForPrimaryDTO> secondaryInsuranceServiceOutcome = addSecondaryDetailsForExistingPrimaryInsurance(salesOrderInsuranceDetailsDTOServiceOutcome.getData().getSalesOrderId(),
                                        claimCOBCrossover.getPatientControlNumber(), token);
                                    log.info("=====>Secondary Insurance====>"+secondaryInsuranceServiceOutcome.getOutcome());
                                    log.info("=====>Secondary Insurance====>"+secondaryInsuranceServiceOutcome.getMessage());
                                    log.info("=====>Secondary Insurance====>"+secondaryInsuranceServiceOutcome.getData().getSecondaryInvoiceNo());
                                    log.info("=====>Secondary Insurance====>"+secondaryInsuranceServiceOutcome.getData().getSecondaryInvoiceId());
                                    log.info("=====>Secondary Insurance====>"+secondaryInsuranceServiceOutcome.getData().getClaimControlNumber());
                                    updateInvoiceLineItem(claimSubmissionStatus, secondaryInsuranceServiceOutcome.getData());
                                    addClaim835CrossoverProcessed(claimCOBCrossover);
                                }
                            }
                            else{
                                log.info("============1. SecondaryInsuranceId are DIFFERENT=================Terminate the transaction");
                                Claims835CrossoverException claims835CrossoverException = new Claims835CrossoverException();
                                claims835CrossoverException.setClaimCob835MasterId(claimCOBCrossover.getClaimCob835MasterId());
                                claims835CrossoverException.setExceptionType("SALES-ORDER-INSURANCE");
                                claims835CrossoverException.setCrossoverCarrierName(claimCOBCrossover.getCrossoverCarrierName());
                                claims835CrossoverException.setCrossoverCarrierPayerId(claimCOBCrossover.getCrossoverCarrierPayerId());
                                claims835CrossoverException.setCrossoverCarrierPayerName(claimCOBCrossover.getCrossoverCarrierPayerName());
                                claims835CrossoverException.setPayerClaimControlNumber(claimCOBCrossover.getPayerClaimControlNumber());
                                claims835CrossoverException.setClaims835CrossoverExceptionUuid(UUID.randomUUID());
                                claims835CrossoverExceptionRepository.save(claims835CrossoverException);
                            }
                        }
                        else{
                            log.info("============SecondaryInsuranceId is NULL=================Update Secondary Insurance Details");
                            SalesOrderSecondaryInsuranceParameterExtendedDTO updateSecondaryInsurance = createSalesOrderSecondaryInsuranceParameters(salesOrderInsuranceDetailsDTOServiceOutcome.getData().getSalesOrderId(), insuranceMasterCrossoverServiceOutcome.getData());
                            url = "/salesorder/api/updateSOSecondaryInsuranceInfo";
                            log.info("====URL===PATCH====SalesorderSecondaryInsurance=========>"+url);
                            ServiceOutcome<SalesOrderInsuranceDetailsDTO> secondaryInsuranceDetailsServiceOutcome = webClientBuilder.build().patch()
                                .uri(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", "Bearer "+token)
                                .body(Mono.just(updateSecondaryInsurance), SalesOrderSecondaryInsuranceParameterExtendedDTO.class)
                                .retrieve()
                                .bodyToMono(new ParameterizedTypeReference<ServiceOutcome<SalesOrderInsuranceDetailsDTO>>() {}).toFuture().get();
                            log.info("===================>OUTPUT======================>"+secondaryInsuranceDetailsServiceOutcome.getOutcome()+"===="+secondaryInsuranceDetailsServiceOutcome.getMessage());
                            log.info("===================>3.OUTPUT====>SecondaryInsuranceInfo======>"+secondaryInsuranceDetailsServiceOutcome.getData().getSecondaryInsurerName());
                            if(secondaryInsuranceDetailsServiceOutcome.getOutcome()){
                                PatientInsuranceParameterMicroserviceDTO savePatientInsuranceParameters =
                                    createPatientInsuranceParameters(salesOrderInsuranceDetailsDTOServiceOutcome.getData(),
                                        secondaryInsuranceDetailsServiceOutcome.getData().getSecondaryInsurerId(),
                                        secondaryInsuranceDetailsServiceOutcome.getData().getSecondaryInsurerName());
                                url = "/patient/api/savePatientInsuranceForMicroservice";
                                log.info("====URL===PATCH====Save OR Get Patient Insurance=========>"+url);
                                ServiceOutcome<PatientInsuranceDTO> patientInsuranceServiceOutcome = webClientBuilder.build().patch()
                                    .uri(url)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .header("Authorization", "Bearer "+token)
                                    .body(Mono.just(savePatientInsuranceParameters), PatientInsuranceParameterMicroserviceDTO.class)
                                    .retrieve()
                                    .bodyToMono(new ParameterizedTypeReference<ServiceOutcome<PatientInsuranceDTO>>() {}).toFuture().get();
                                log.info("===================>OUTPUT======================>"+patientInsuranceServiceOutcome.getOutcome()+"===="+secondaryInsuranceDetailsServiceOutcome.getMessage());
                                log.info("===================>3.OUTPUT====>SecondaryInsuranceInfo======>"+patientInsuranceServiceOutcome.getData().getInsuranceName());
                                //2.ADD Secondary Insurance Details on Invoices using Stored Procedure.
                                if(patientInsuranceServiceOutcome.getOutcome()){
                                    if(patientInsuranceServiceOutcome.getData().getInsuranceId() == insuranceMasterCrossoverServiceOutcome.getData().getInsuranceId()){
                                        log.info("===================>2.ADD Secondary Insurance Details on Invoices using Stored Procedure.===============");
                                        ServiceOutcome<AddSecondaryForPrimaryDTO> secondaryInsuranceServiceOutcome = addSecondaryDetailsForExistingPrimaryInsurance(salesOrderInsuranceDetailsDTOServiceOutcome.getData().getSalesOrderId(),
                                            claimCOBCrossover.getPatientControlNumber(), token);
                                        log.info("=====>2.Secondary Insurance====>"+secondaryInsuranceServiceOutcome.getOutcome());
                                        log.info("=====>2.Secondary Insurance====>"+secondaryInsuranceServiceOutcome.getMessage());
                                        log.info("=====>2.Secondary Insurance====>"+secondaryInsuranceServiceOutcome.getData().getSecondaryInvoiceNo());
                                        log.info("=====>2.Secondary Insurance====>"+secondaryInsuranceServiceOutcome.getData().getSecondaryInvoiceId());
                                        log.info("=====>2.Secondary Insurance====>"+secondaryInsuranceServiceOutcome.getData().getClaimControlNumber());
                                        updateInvoiceLineItem(claimSubmissionStatus, secondaryInsuranceServiceOutcome.getData());
                                        addClaim835CrossoverProcessed(claimCOBCrossover);
                                    }
                                    else{
                                        log.info("============2. SecondaryInsuranceId are DIFFERENT=================Terminate the transaction");
                                        Claims835CrossoverException claims835CrossoverException = new Claims835CrossoverException();
                                        claims835CrossoverException.setClaimCob835MasterId(claimCOBCrossover.getClaimCob835MasterId());
                                        claims835CrossoverException.setExceptionType("PATIENT-INSURANCE");
                                        claims835CrossoverException.setCrossoverCarrierName(claimCOBCrossover.getCrossoverCarrierName());
                                        claims835CrossoverException.setCrossoverCarrierPayerId(claimCOBCrossover.getCrossoverCarrierPayerId());
                                        claims835CrossoverException.setCrossoverCarrierPayerName(claimCOBCrossover.getCrossoverCarrierPayerName());
                                        claims835CrossoverException.setPayerClaimControlNumber(claimCOBCrossover.getPayerClaimControlNumber());
                                        claims835CrossoverException.setClaims835CrossoverExceptionUuid(UUID.randomUUID());
                                        claims835CrossoverExceptionRepository.save(claims835CrossoverException);
                                    }
                                }
                            }
                        }
                    }
                    catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            else{
                log.info("===================>No Record Found in Insurance Master<==============>");
                Claims835CrossoverException claims835CrossoverException = new Claims835CrossoverException();
                claims835CrossoverException.setClaimCob835MasterId(claimCOBCrossover.getClaimCob835MasterId());
                claims835CrossoverException.setExceptionType("INSURANCE-NOT-EXISTS");
                claims835CrossoverException.setCrossoverCarrierName(claimCOBCrossover.getCrossoverCarrierName());
                claims835CrossoverException.setCrossoverCarrierPayerId(claimCOBCrossover.getCrossoverCarrierPayerId());
                claims835CrossoverException.setCrossoverCarrierPayerName(claimCOBCrossover.getCrossoverCarrierPayerName());
                claims835CrossoverException.setPayerClaimControlNumber(claimCOBCrossover.getPayerClaimControlNumber());
                claims835CrossoverException.setClaims835CrossoverExceptionUuid(UUID.randomUUID());
                claims835CrossoverExceptionRepository.save(claims835CrossoverException);
            }
        });
    }

    public void updateInvoiceLineItem(ClaimSubmissionStatus claimSubmissionStatus, AddSecondaryForPrimaryDTO secondaryForPrimaryDTO){
        List<InvoiceLineItemDetails> invoiceLineItemDetailsList = invoiceLineItemDetailsRepository.getPrimaryInvoiceLineItemDetailsOnPrimaryInvoiceNo(claimSubmissionStatus.getInvoiceNo());
        invoiceLineItemDetailsList.stream().forEach(invoiceList -> {
            invoiceList.setSecondaryInvoiceNo(secondaryForPrimaryDTO.getSecondaryInvoiceNo());
            invoiceList.setSecondaryInvoiceId(secondaryForPrimaryDTO.getSecondaryInvoiceId());
            invoiceLineItemDetailsRepository.save(invoiceList);
        });
    }
    private void addClaim835CrossoverProcessed(ClaimsCOB835CrossoverProjection claimCOBCrossover){
        Claims835CrossoverProcessed claims835CrossoverProcessed = new Claims835CrossoverProcessed();
        claims835CrossoverProcessed.setClaimCob835MasterId(claimCOBCrossover.getClaimCob835MasterId());
        claims835CrossoverProcessed.setProcessedDate(LocalDate.now());
        claims835CrossoverProcessed.setPatientControlNumber(claimCOBCrossover.getPatientControlNumber());
        claims835CrossoverProcessed.setClaims835CrossoverProcessed(UUID.randomUUID());
        claims835CrossoverProcessedRepository.save(claims835CrossoverProcessed);
    }
    private ServiceOutcome<AddSecondaryForPrimaryDTO> addSecondaryDetailsForExistingPrimaryInsurance(Long salesOrderId, String internalClaimControlNumber, String token) throws ExecutionException, InterruptedException {
        log.info("==============addSecondaryDetailsForExistingPrimaryInsurance==================="+internalClaimControlNumber+"==="+salesOrderId);
        String url = "/salesorder/api/addSecondaryForPrimary";
        log.info("====URL===POST====Save Secondary Insurance Details=========>"+url);
        ServiceOutcome<AddSecondaryForPrimaryDTO> salesOrderMasterDTOServiceOutcome = webClientBuilder.build().post()
            .uri(uriBuilder -> uriBuilder.path(url).queryParam("salesOrderId", salesOrderId)
                .queryParam("internalClaimControlNumber", internalClaimControlNumber).build())
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization", "Bearer "+token)
            .retrieve()
            .bodyToMono(new ParameterizedTypeReference<ServiceOutcome<AddSecondaryForPrimaryDTO>>() {}).toFuture().get();
        log.info("=======After POST======>"+salesOrderMasterDTOServiceOutcome.getOutcome());
        return salesOrderMasterDTOServiceOutcome;
    }

    private SalesOrderSecondaryInsuranceParameterExtendedDTO createSalesOrderSecondaryInsuranceParameters(Long salesOrderId, InsuranceMasterDTO insuranceMasterCrossoverServiceOutcome){
        SalesOrderSecondaryInsuranceParameterExtendedDTO updateSecondaryInsurance = new SalesOrderSecondaryInsuranceParameterExtendedDTO();
        updateSecondaryInsurance.setSalesOrderId(salesOrderId);
        updateSecondaryInsurance.setSecondaryInsurerId(insuranceMasterCrossoverServiceOutcome.getInsuranceId());
        updateSecondaryInsurance.setSecondaryInsurerName(insuranceMasterCrossoverServiceOutcome.getInsuranceName());
        updateSecondaryInsurance.setSecondaryInsuranceIndicatorCode(insuranceMasterCrossoverServiceOutcome.getInsuranceIdNo());
        updateSecondaryInsurance.setSecondaryInsurancePayerId(insuranceMasterCrossoverServiceOutcome.getInsurancePayerIdNo());
        updateSecondaryInsurance.setSecondaryInsurerAddressLine1(insuranceMasterCrossoverServiceOutcome.getAddressLine1());
        updateSecondaryInsurance.setSecondaryInsurerAddressLine2(insuranceMasterCrossoverServiceOutcome.getAddressLine2());
        updateSecondaryInsurance.setSecondaryInsurerCity(insuranceMasterCrossoverServiceOutcome.getCity());
        updateSecondaryInsurance.setSecondaryInsurerState(insuranceMasterCrossoverServiceOutcome.getState());
        updateSecondaryInsurance.setSecondaryInsurerZip(insuranceMasterCrossoverServiceOutcome.getZip());
        return updateSecondaryInsurance;
    }

    private PatientInsuranceParameterMicroserviceDTO createPatientInsuranceParameters(SalesOrderInsuranceDetailsDTO salesOrderInsuranceDetails,
                                                                                      Long secondaryInsuranceId, String secondaryInsuranceName){
        PatientInsuranceParameterMicroserviceDTO savePatientInsurance = new PatientInsuranceParameterMicroserviceDTO();
        savePatientInsurance.setPatientID(salesOrderInsuranceDetails.getPatientId());
        savePatientInsurance.setPayerLevel("secondary");
        savePatientInsurance.setInsuranceName(secondaryInsuranceName);
        savePatientInsurance.setInsuranceId(secondaryInsuranceId);
        savePatientInsurance.setPayPercentage(100d);
        savePatientInsurance.setRelationship("Self");
        return savePatientInsurance;
    }

    /*private Mono<ResponseDTO> handleResponse(ClientResponse clientResponse) {
        log.info("===================>handleResponse<======================>"+clientResponse);
        if (clientResponse.statusCode().isError()) {
            return clientResponse.bodyToMono(ResponseDTO.class)
                .flatMap(response -> Mono.error(new RuntimeException(response.getMessage())));
        }
        return clientResponse.bodyToMono(ResponseDTO.class);
    }*/

    @Override
    public List<ClaimsCOB835Data> getAllClaimsCOB835MasterData() {
        List<ClaimsCOB835Master> claimsCOB835Masters = claimsCOB835MasterRepository.findAll();
        List<ClaimsCOB835Data> claimsCOB835Data = new ArrayList<>();
        try {
            //BeanUtils.copyProperties(claimsCOB835Data, claimsCOB835Masters);
            for(ClaimsCOB835Master claimsCOB835 : claimsCOB835Masters) {
                ClaimsCOB835Data claimsCOBData = new ClaimsCOB835Data();
                BeanUtils.copyProperties(claimsCOB835, claimsCOBData);
                claimsCOB835Data.add(claimsCOBData);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return claimsCOB835Data;
    }

    @Override
    public List<ClaimsStatus277Data> getAllClaimsStatus277MasterData() {
        List<ClaimsStatus277Master> claimsStatus277Masters = claimsStatus277MasterRepository.findAll();
        List<ClaimsStatus277Data> claimsStatus277Data = new ArrayList<>();
        try {
            //BeanUtils.copyProperties(claimsStatus277Data, claimsStatus277Masters);
            for(ClaimsStatus277Master claimsStatus277 : claimsStatus277Masters) {
                ClaimsStatus277Data claimsStatusData = new ClaimsStatus277Data();
                BeanUtils.copyProperties(claimsStatus277, claimsStatusData);
                claimsStatus277Data.add(claimsStatusData);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return claimsStatus277Data;
    }

    public void saveOn835Exception(ClaimsCOB835Data claimRemittance) {
        Claim835277Exception claims835_277Exception = new Claim835277Exception();
        Optional<ClaimsCOB835Master> claimsCOB835Master = claimsCOB835MasterRepository.findById(claimRemittance.getClaimCob835MasterId());
        claims835_277Exception.setClaimCob835MasterId(claimsCOB835Master.get().getClaimCob835MasterId());
        claims835_277Exception.setFileName(claimRemittance.getFileName());
        claims835_277Exception.setIs835(true);
        claims835_277Exception.setPatientAccountNumber(null);
        claims835_277Exception.setPatientControlNumber(claimRemittance.getPatientControlNumber());
        claims835_277Exception.setPatientMemberId(null);
        claims835_277Exception.setPayerClaimControlNumber(claimRemittance.getPayerClaimControlNumber());
        claims835_277Exception.setRunDate(LocalDate.now());

        claims835_277Exception.setStatus(DefineStatus.Active.name());
        claims835_277Exception.setCreatedById(0L);
        claims835_277Exception.setCreatedByName("System");
        claims835_277Exception.setCreatedDate(LocalDate.now());
        claims835_277Exception.setUpdatedDate(LocalDate.now());
        claims835_277Exception.setUpdatedById(0L);
        claims835_277Exception.setUpdatedByName("System");
        claims835_277Exception.setClaim835277ExceptionUuid(UUID.randomUUID());
        claims835277ExceptionRepository.save(claims835_277Exception);
    }

    public void saveOn277Exception(ClaimsStatus277Data claimStatus) {
        Claim835277Exception claims835_277Exception = new Claim835277Exception();
        Optional<ClaimsStatus277Master> claimsStatus277Master = claimsStatus277MasterRepository.findById(claimStatus.getClaimStatus277MasterId());
        claims835_277Exception.setClaimStatus277MasterId(claimsStatus277Master.get().getClaimStatus277MasterId());
        claims835_277Exception.setFileName(claimStatus.getFileName());
        claims835_277Exception.setIs277(true);
        claims835_277Exception.setPatientAccountNumber(claimStatus.getPatientAccountNumber());
        claims835_277Exception.setPatientControlNumber(null);
        claims835_277Exception.setPatientMemberId(claimStatus.getPatientMemberId());
        claims835_277Exception.setPayerClaimControlNumber(null);
        claims835_277Exception.setRunDate(LocalDate.now());
        claims835_277Exception.setStatus(DefineStatus.Active.name());
        claims835_277Exception.setCreatedById(0L);
        claims835_277Exception.setCreatedByName("System");
        claims835_277Exception.setCreatedDate(LocalDate.now());
        claims835_277Exception.setUpdatedDate(LocalDate.now());
        claims835_277Exception.setUpdatedById(0L);
        claims835_277Exception.setUpdatedByName("System");
        claims835_277Exception.setClaim835277ExceptionUuid(UUID.randomUUID());
        claims835277ExceptionRepository.save(claims835_277Exception);
    }

    DepositMasterDetails createDepositOnClaimsCOB835(ClaimsCOB835Master saveClaimsCOB835Master) {
        System.out.println("====================createDepositOnClaimsCOB835===========================");
        Optional<DepositMasterDetails> depositMaster = Optional.ofNullable(depositMasterDetailsRepository.getDepositMasterDetailsOnReferenceNumber(saveClaimsCOB835Master.getCheckOrEFTTraceNumber()));
        System.out.println("====================saveClaimsCOB835Master.getCheckOrEFTTraceNumber()==========================="+saveClaimsCOB835Master.getCheckOrEFTTraceNumber());
        DepositMasterDetails depositMasterDetails = null;
        if (depositMaster.isEmpty()){
            System.out.println("====================IF===========================");
            depositMasterDetails = new DepositMasterDetails();
            depositMasterDetails.setCreatedById(0L);
            depositMasterDetails.setCreatedByName("System");
            depositMasterDetails.setCreatedDate(LocalDate.now());
            depositMasterDetails.setDepositDate(saveClaimsCOB835Master.getCheckIssueOrEFTEffectiveDate());
            String depositNumber = depositMasterDetailsRepository.getDepositNumber();
            System.out.println("Deposit Sequence Number=>" + depositNumber);
            depositMasterDetails.setDepositNo(depositNumber);
            depositMasterDetails.setDepositNotes(null);
            depositMasterDetails.setDepositReference(saveClaimsCOB835Master.getCheckOrEFTTraceNumber());
            depositMasterDetails.setPayorName(saveClaimsCOB835Master.getPayerName());
            depositMasterDetails.setStatus(DefineStatus.Active.name());
            depositMasterDetails.setUpdatedDate(LocalDate.now());
            depositMasterDetails.setUpdatedById(0L);
            depositMasterDetails.setUpdatedByName("System");
            depositMasterDetails.setClaimCob835MasterId(saveClaimsCOB835Master.getClaimCob835MasterId());
            depositMasterDetails.setDepositAmount(saveClaimsCOB835Master.getChequeEftAmount());
            depositMasterDetails.setDepositStatus(null);
            depositMasterDetails = depositMasterDetailsRepository.save(depositMasterDetails);
        }
        else{
            System.out.println("====================ELSE==========================="+depositMaster.get().getDepositNo());
            depositMasterDetails = depositMaster.get();
        }
        return depositMasterDetails;
    }

    ReceiptMasterDetails createReceiptOnClaimsCOB835(ClaimsCOB835Master saveClaimsCOB835Master, DepositMasterDetails depositMasterDetails) {
        System.out.println("====================createReceiptOnClaimsCOB835===========================");
        ReceiptMasterDetails receiptMaster = receiptMasterDetailsRepository.getReceiptDetailsOnDepositMaster(depositMasterDetails.getDepositId());
        ReceiptMasterDetails receiptMasterDetails = null;
        if(receiptMaster == null) {
            receiptMasterDetails = new ReceiptMasterDetails();
            receiptMasterDetails.setCreatedById(0L);
            receiptMasterDetails.setCreatedByName("System");
            receiptMasterDetails.setCreatedDate(LocalDate.now());
            receiptMasterDetails.setDepositId(depositMasterDetails.getDepositId());
            receiptMasterDetails.setDepositNo(depositMasterDetails.getDepositNo());
            receiptMasterDetails.setPaymentDate(saveClaimsCOB835Master.getCheckIssueOrEFTEffectiveDate());
            receiptMasterDetails.setPaymentMode(saveClaimsCOB835Master.getPaymentMethodCode());
            receiptMasterDetails.setReceiptAmount(saveClaimsCOB835Master.getChequeEftAmount());
            receiptMasterDetails.setReceiptNo(receiptMasterDetailsRepository.getReceiptNumber());
            receiptMasterDetails.setReceiptNotes(null);
            receiptMasterDetails.setReceiptReference(saveClaimsCOB835Master.getCheckOrEFTTraceNumber());
            receiptMasterDetails.setStatus(DefineStatus.Active.name());
            receiptMasterDetails.setUpdatedById(0L);
            receiptMasterDetails.setUpdatedByName("System");
            receiptMasterDetails.setUpdatedDate(LocalDate.now());
            receiptMasterDetails.setReceiptStatus(null);
            receiptMasterDetails = receiptMasterDetailsRepository.save(receiptMasterDetails);
        }
        else{
            System.out.println("====================ELSE==========================="+receiptMaster.getDepositNo());
            receiptMasterDetails = receiptMaster;
        }
        return receiptMasterDetails;
    }

    void createTransaction835(List<ClaimsCOB835Details> claimsCOB835DetailsList, ReceiptMasterDetails receiptMasterDetails) {
        System.out.println("====================createTransaction835===========================");
        claimsCOB835DetailsList.stream()
            .forEach(transaction -> {
                if(transaction.getProviderPaymentAmount() != 0) {
                    saveTransaction835(transaction, receiptMasterDetails, "Payment", "Payment", transaction.getProviderPaymentAmount());
                }
                if(transaction.getAdjustmentPrCode1() != null && transaction.getAdjustmentPrCode1().equalsIgnoreCase("1")) {
                    saveTransaction835(transaction, receiptMasterDetails, "Balance Transfer", "PR1-Deductable", transaction.getAdjustmentPrCode1Amount());
                }
                if(transaction.getAdjustmentPrCode1() != null && transaction.getAdjustmentPrCode1().equalsIgnoreCase("2")) {
                    saveTransaction835(transaction, receiptMasterDetails, "Balance Transfer", "CO-INS", transaction.getAdjustmentPrCode1Amount());
                }
                if(transaction.getAdjustmentPrCode2() != null && transaction.getAdjustmentPrCode2().equalsIgnoreCase("1")) {
                    saveTransaction835(transaction, receiptMasterDetails, "Balance Transfer", "PR1-Deductable", transaction.getAdjustmentPrCode2Amount());
                }
                if(transaction.getAdjustmentPrCode2() != null && transaction.getAdjustmentPrCode2().equalsIgnoreCase("2")) {
                    saveTransaction835(transaction, receiptMasterDetails, "Balance Transfer", "CO-INS", transaction.getAdjustmentPrCode2Amount());
                }
                if(transaction.getAdjustmentPrCode3() != null) {
                    saveTransaction835(transaction, receiptMasterDetails, "Balance Transfer", "CO-PAY", transaction.getAdjustmentPrCode3Amount());
                }
                if(transaction.getAdjustmentCoCode1() != null) {
                    System.out.println("====================transaction.getAdjustmentCoCode1()==========================="+transaction.getAdjustmentCoCode1());
                    saveTransaction835(transaction, receiptMasterDetails, "Adjustment", transaction.getAdjustmentCoCode1(), transaction.getAdjustmentCoCode1Amount());
                }
                if(transaction.getAdjustmentCoCode2() != null) {
                    System.out.println("====================transaction.getAdjustmentCoCode2()==========================="+transaction.getAdjustmentCoCode2());
                    saveTransaction835(transaction, receiptMasterDetails, "Adjustment", transaction.getAdjustmentCoCode2(), transaction.getAdjustmentCoCode2Amount());
                }
                if(transaction.getAdjustmentCoCode3() != null) {
                    System.out.println("====================transaction.getAdjustmentCoCode3()==========================="+transaction.getAdjustmentCoCode3());
                    saveTransaction835(transaction, receiptMasterDetails, "Adjustment", transaction.getAdjustmentCoCode3(), transaction.getAdjustmentCoCode3Amount());
                }
                if(transaction.getAdjustmentCoCode4() != null) {
                    System.out.println("====================transaction.getAdjustmentCoCode4()==========================="+transaction.getAdjustmentCoCode4());
                    saveTransaction835(transaction, receiptMasterDetails, "Adjustment", transaction.getAdjustmentCoCode4(), transaction.getAdjustmentCoCode4Amount());
                }
                if(transaction.getAdjustmentCrCode1() != null) {
                    saveTransaction835(transaction, receiptMasterDetails, "Adjustment", transaction.getAdjustmentCrCode1(), transaction.getAdjustmentCrCode1Amount());
                }
                if(transaction.getAdjustmentCrCode2() != null) {
                    saveTransaction835(transaction, receiptMasterDetails, "Adjustment", transaction.getAdjustmentCrCode2(), transaction.getAdjustmentCrCode2Amount());
                }
                if(transaction.getAdjustmentCrCode3() != null) {
                    saveTransaction835(transaction, receiptMasterDetails, "Adjustment", transaction.getAdjustmentCrCode3(), transaction.getAdjustmentCrCode3Amount());
                }
                if(transaction.getAdjustmentCrCode4() != null) {
                    saveTransaction835(transaction, receiptMasterDetails, "Adjustment", transaction.getAdjustmentCrCode4(), transaction.getAdjustmentCrCode4Amount());
                }
            });
    }

    void saveTransaction835(ClaimsCOB835Details transaction, ReceiptMasterDetails receiptMasterDetails, String type, String note, Double adjustmentAmount) {
        Optional<ClaimsCOB835Master> claimsCOB835Master = claimsCOB835MasterRepository.findById(transaction.getClaimCob835MasterId());
        Transaction835MasterDetails transaction835MasterDetails = new Transaction835MasterDetails();
        transaction835MasterDetails.setCreatedById(0L);
        transaction835MasterDetails.setCreatedByName("System");
        transaction835MasterDetails.setCreatedDate(LocalDate.now());
        transaction835MasterDetails.setItemModifiers(transaction.getAdjudicatedProcedureModifierCodes());
        transaction835MasterDetails.setItemProcCode(transaction.getAdjudicatedProcedureCode());
        transaction835MasterDetails.setItemQty(null);
        transaction835MasterDetails.setPatientControlNo(claimsCOB835Master.get().getPatientControlNumber());
        transaction835MasterDetails.setPayorClaimControlNo(claimsCOB835Master.get().getPayerClaimControlNumber());
        transaction835MasterDetails.setReceiptId(receiptMasterDetails.getReceiptId());
        transaction835MasterDetails.setReceiptNo(receiptMasterDetails.getReceiptNo());
        transaction835MasterDetails.setServiceDateFrom(transaction.getServiceDate());
        transaction835MasterDetails.setServiceDateTo(null);
        transaction835MasterDetails.setStatus(DefineStatus.Active.name());
        //transaction835MasterDetails.setTransactionAmount(transaction.getProviderPaymentAmount());
        transaction835MasterDetails.setTransactionAmount(adjustmentAmount);
        transaction835MasterDetails.setTransactionNotes(note);
        transaction835MasterDetails.setTransactionType(type);
        transaction835MasterDetails.setUpdatedById(0L);
        transaction835MasterDetails.setUpdatedByName("System");
        transaction835MasterDetails.setUpdatedDate(LocalDate.now());
        transaction835MasterDetails.setEdi835TransactionMasterDetailsUuid(UUID.randomUUID());
        transaction835MasterDetailsRepository.save(transaction835MasterDetails);
    }

    @Override
    public void preparePrimaryClaimSubmissionHealthInsuranceForm(String claimControlNumber) throws Exception {
        System.out.println("=============prepareHealthInsuranceForm==============");
        Optional<ClaimsSubmissionMaster> claimsSubmissionMaster = Optional.ofNullable(claimsSubmissionMasterRepository.getHealthInsuranceClaimDetailsOnClaimControlNumber(claimControlNumber));
        if(claimsSubmissionMaster != null) {
            List<ServiceLinesMaster> serviceLinesMasters = serviceLinesMasterRepository.getServiceLineMasterDataOnClaimSubmission(claimsSubmissionMaster.get().getChangeHealthPrimarySubmisionMasterId());
            preparePrimaryClaimSubmissionHealthInsuranceData(claimsSubmissionMaster, serviceLinesMasters);
        }
    }

    public void preparePrimaryClaimSubmissionHealthInsuranceData(Optional<ClaimsSubmissionMaster> claimsSubmissionMaster, List<ServiceLinesMaster> serviceLinesMasters) throws Exception {
        long serviceLineSize = serviceLinesMasters.size();
        List<ServiceLinesMaster> firstPage;
        List<ServiceLinesMaster> secondPage;
        Double totalChargeAmount = 0.00d;
        if(serviceLineSize >= 6){
            File file1 = new File(fileUploadConfigProperties.getTempClaimFormCMS1500Documents()+"/"+createNewClaimFormFile("first_"+claimsSubmissionMaster.get().getClaimControlNo(), "TEMP"));
            file1.getParentFile().mkdirs();

            File file2 = new File(fileUploadConfigProperties.getTempClaimFormCMS1500Documents()+"/"+createNewClaimFormFile("second_"+claimsSubmissionMaster.get().getClaimControlNo(), "TEMP"));
            file2.getParentFile().mkdirs();
            firstPage = new ArrayList<>();
            secondPage = new ArrayList<>();
            for (int i = 0; i < serviceLineSize - 1; i++) {
                firstPage.add(serviceLinesMasters.get(i));
                totalChargeAmount = totalChargeAmount + serviceLinesMasters.get(i).getChargeAmt();
            }
            for (int i = 2; i < serviceLineSize; i++) {
                secondPage.add(serviceLinesMasters.get(i));
                totalChargeAmount = totalChargeAmount + serviceLinesMasters.get(i).getChargeAmt();
            }

            PdfStamper stamper1 = fillPrimaryClaimSubmissionFormData(file1.getName(), "TEMP", claimsSubmissionMaster.get(), firstPage, 0.00d);
            stamper1.close();
            PdfStamper stamper2 = fillPrimaryClaimSubmissionFormData(file2.getName(), "TEMP", claimsSubmissionMaster.get(), secondPage, totalChargeAmount);
            stamper2.close();

            String finalFileName = createNewClaimFormFile(claimsSubmissionMaster.get().getClaimControlNo(), "PRIMARY");
            mergePdfs(file1.getName(), file2.getName(), finalFileName, "PRIMARY");
            if(finalFileName != null){
                claimsSubmissionMaster.get().setCms1500FormName(finalFileName);
                claimsSubmissionMasterRepository.save(claimsSubmissionMaster.get());
            }
        }
        else{
            String finalFileName = createNewClaimFormFile(claimsSubmissionMaster.get().getClaimControlNo(), "PRIMARY");
            for (int i = 0; i < serviceLineSize; i++) {
                totalChargeAmount = totalChargeAmount + serviceLinesMasters.get(i).getChargeAmt();
            }
            fillPrimaryClaimSubmissionFormData(finalFileName, "PRIMARY", claimsSubmissionMaster.get(), serviceLinesMasters, totalChargeAmount);
            if(finalFileName != null){
                claimsSubmissionMaster.get().setCms1500FormName(finalFileName);
                claimsSubmissionMasterRepository.save(claimsSubmissionMaster.get());
            }
        }
    }

    public void deleteTemporaryFiles(File file1, File file2){
        try {
            if(file1.exists()){
                FileInputStream fis1 = new FileInputStream(file1);
                fis1.close();
                file1.delete();
            }
            if(file2.exists()){
                FileInputStream fis2 = new FileInputStream(file2);
                fis2.close();
                file2.delete();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public PdfStamper fillPrimaryClaimSubmissionFormData(String dest, String type, ClaimsSubmissionMaster claimsSubmissionMaster, List<ServiceLinesMaster> serviceLinesMasters, Double totalChargeAmount) throws Exception{
        final DecimalFormat df = new DecimalFormat("0.00");
        PdfReader pdfReader = new PdfReader(fileUploadConfigProperties.getBaseFormCMS1500Documents().getLocation()+"/"+"form.pdf");
        //Create PdfStamper instance.
        String filePath = null;
        if(type.equalsIgnoreCase("TEMP"))
            filePath = fileUploadConfigProperties.getTempClaimFormCMS1500Documents().getLocation()+"/"+dest;
        if(type.equalsIgnoreCase("PRIMARY"))
            filePath = fileUploadConfigProperties.getPrimaryClaimFormCMS1500Documents().getLocation()+"/"+dest;
        PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(filePath));
        stamper.getAcroFields().setField("insurance_name", claimsSubmissionMaster.getReceiverOrganizationName().toUpperCase());
        if(Objects.isNull(claimsSubmissionMaster.getTradingPartnerAddressLine1()))
            stamper.getAcroFields().setField("insurance_address", "");
        else
            stamper.getAcroFields().setField("insurance_address", claimsSubmissionMaster.getTradingPartnerAddressLine1().toUpperCase());
        if(Objects.isNull(claimsSubmissionMaster.getInsuredAddressLine2()))
            stamper.getAcroFields().setField("insurance_address2", "");
        else
            stamper.getAcroFields().setField("insurance_address2", claimsSubmissionMaster.getInsuredAddressLine2().toUpperCase());
        StringBuffer insuranceCityStateZip = new StringBuffer();
        if(!Objects.isNull(claimsSubmissionMaster.getTradingPartnerCity()))
            insuranceCityStateZip.append(claimsSubmissionMaster.getTradingPartnerCity().toUpperCase());
        if(!Objects.isNull(claimsSubmissionMaster.getTradingPartnerState()))
            insuranceCityStateZip.append(claimsSubmissionMaster.getTradingPartnerState().toUpperCase());
        if(!Objects.isNull(claimsSubmissionMaster.getTradingPartnerZip()))
            insuranceCityStateZip.append(claimsSubmissionMaster.getTradingPartnerZip().toUpperCase());
        stamper.getAcroFields().setField("insurance_city_state_zip", insuranceCityStateZip.toString());

        if(claimsSubmissionMaster.getClaimFilingCode().equalsIgnoreCase("WC"))
            stamper.getAcroFields().setField("insurance_type", "Other", true);
        else
            stamper.getAcroFields().setField("insurance_type", claimsSubmissionMaster.getTradingPartnerName(), true);
        stamper.getAcroFields().setField("insurance_id", claimsSubmissionMaster.getSubscriberMemberIdNo());

        stamper.getAcroFields().setField("pt_name", claimsSubmissionMaster.getSubscriberFirstName().toUpperCase()+", "+claimsSubmissionMaster.getSubscriberLastName().toUpperCase());

        stamper.getAcroFields().setField("birth_mm", claimsSubmissionMaster.getSubscriberDob().getDayOfMonth() < 10 ? "0"+claimsSubmissionMaster.getSubscriberDob().getDayOfMonth() : String.valueOf(claimsSubmissionMaster.getSubscriberDob().getDayOfMonth()));
        stamper.getAcroFields().setField("birth_dd", claimsSubmissionMaster.getSubscriberDob().getMonthValue() < 10 ? "0"+claimsSubmissionMaster.getSubscriberDob().getMonthValue() : String.valueOf(claimsSubmissionMaster.getSubscriberDob().getMonthValue()));
        stamper.getAcroFields().setField("birth_yy", String.valueOf(claimsSubmissionMaster.getSubscriberDob().getYear()));
        stamper.getAcroFields().setField("sex", claimsSubmissionMaster.getSubscriberGender(), true);

        stamper.getAcroFields().setField("ins_name", claimsSubmissionMaster.getInsuredFirstName().toUpperCase()+" "+claimsSubmissionMaster.getInsuredLastName().toUpperCase());

        stamper.getAcroFields().setField("pt_street", claimsSubmissionMaster.getSubscriberAddressLine1().toUpperCase());
        stamper.getAcroFields().setField("pt_city", claimsSubmissionMaster.getSubscriberCity().toUpperCase());
        stamper.getAcroFields().setField("pt_state", claimsSubmissionMaster.getSubscriberState().toUpperCase());
        stamper.getAcroFields().setField("pt_zip", claimsSubmissionMaster.getSubscriberZipCode().toUpperCase());

        String patientRelationship = "";
        if(claimsSubmissionMaster.getPatientRelationshipInsured() != null) {
            if (claimsSubmissionMaster.getPatientRelationshipInsured().equalsIgnoreCase("18"))
                patientRelationship = "S";
            if (claimsSubmissionMaster.getPatientRelationshipInsured().equalsIgnoreCase("01"))
                patientRelationship = "M";
            if (claimsSubmissionMaster.getPatientRelationshipInsured().equalsIgnoreCase("19"))
                patientRelationship = "C";
            if (claimsSubmissionMaster.getPatientRelationshipInsured().equalsIgnoreCase("G8"))
                patientRelationship = "O";
        }
        stamper.getAcroFields().setField("rel_to_ins", patientRelationship, true);

        String insAddressLine2 = "";
        if(claimsSubmissionMaster.getInsuredAddressLine2() != null)
            insAddressLine2 = claimsSubmissionMaster.getInsuredAddressLine2();
        stamper.getAcroFields().setField("ins_street", claimsSubmissionMaster.getInsuredAddressLine1().toUpperCase()+" "+insAddressLine2.toUpperCase());
        stamper.getAcroFields().setField("ins_city", claimsSubmissionMaster.getInsuredCity().toUpperCase());
        stamper.getAcroFields().setField("ins_state", claimsSubmissionMaster.getInsuredState().toUpperCase());
        stamper.getAcroFields().setField("ins_zip", claimsSubmissionMaster.getInsuredZip().toUpperCase());
        stamper.getAcroFields().setField("ins_phone area", claimsSubmissionMaster.getInsuredContactNo().substring(0, 3));
        stamper.getAcroFields().setField("ins_phone", claimsSubmissionMaster.getInsuredContactNo().substring(3));

        if(claimsSubmissionMaster.getPatientConditionEmployment() != null && claimsSubmissionMaster.getPatientConditionEmployment().equalsIgnoreCase("YES"))
            stamper.getAcroFields().setField("employment", claimsSubmissionMaster.getPatientConditionEmployment(), true);
        else
            stamper.getAcroFields().setField("employment", "NO", true);
        if(claimsSubmissionMaster.getPatientConditionAutoAccident() != null && claimsSubmissionMaster.getPatientConditionAutoAccident().equalsIgnoreCase("YES"))
            stamper.getAcroFields().setField("pt_auto_accident", claimsSubmissionMaster.getPatientConditionAutoAccident(), true);
        else
            stamper.getAcroFields().setField("pt_auto_accident", "NO", true);
        if(claimsSubmissionMaster.getPatientConditionOtherAccident() != null && claimsSubmissionMaster.getPatientConditionOtherAccident().equalsIgnoreCase("YES"))
            stamper.getAcroFields().setField("other_accident", claimsSubmissionMaster.getPatientConditionOtherAccident(), true);
        else
            stamper.getAcroFields().setField("other_accident", "NO", true);

        stamper.getAcroFields().setField("ins_policy", claimsSubmissionMaster.getPrimaryInsurerPolicyNo());
        stamper.getAcroFields().setField("ins_dob_mm", claimsSubmissionMaster.getInsuredDob().getDayOfMonth() < 10 ? "0"+claimsSubmissionMaster.getInsuredDob().getDayOfMonth() : String.valueOf(claimsSubmissionMaster.getInsuredDob().getDayOfMonth()));
        stamper.getAcroFields().setField("ins_dob_dd", claimsSubmissionMaster.getInsuredDob().getMonthValue() < 10 ? "0"+claimsSubmissionMaster.getInsuredDob().getMonthValue() : String.valueOf(claimsSubmissionMaster.getInsuredDob().getMonthValue()));
        stamper.getAcroFields().setField("ins_dob_yy", String.valueOf(claimsSubmissionMaster.getInsuredDob().getYear()));
        if(claimsSubmissionMaster.getInsuredGender().equalsIgnoreCase("M"))
            stamper.getAcroFields().setField("ins_sex", "MALE", true);
        if(claimsSubmissionMaster.getInsuredGender().equalsIgnoreCase("F"))
            stamper.getAcroFields().setField("ins_sex", "FEMALE", true);
        stamper.getAcroFields().setField("ins_plan_name", "");
        if(claimsSubmissionMaster.getIsNextLevelInsurerPresentStatus() != null && claimsSubmissionMaster.getIsNextLevelInsurerPresentStatus().equalsIgnoreCase("YES"))
            stamper.getAcroFields().setField("ins_benefit_plan", "YES", true);
        else
            stamper.getAcroFields().setField("ins_benefit_plan", "NO", true);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        Date originalDos = new SimpleDateFormat("yyyy-MM-dd").parse(claimsSubmissionMaster.getOriginalDos().toString());
        if(claimsSubmissionMaster.getSignatureIndicator().equalsIgnoreCase("Y"))
            stamper.getAcroFields().setField("pt_signature", "Signature on file");

        if(claimsSubmissionMaster.getSignatureIndicator().equalsIgnoreCase("Y"))
            stamper.getAcroFields().setField("ins_signature", "Signature on file");
        stamper.getAcroFields().setField("pt_date", new SimpleDateFormat("dd/MM/yyyy").format(originalDos));

        stamper.getAcroFields().setField("ref_physician", claimsSubmissionMaster.getOrderingProviderFirstName().toUpperCase()+ " "+claimsSubmissionMaster.getOrderingProviderLastName().toUpperCase());
        stamper.getAcroFields().setField("id_physician", claimsSubmissionMaster.getOrderingProviderNpi());

        stamper.getAcroFields().setField("99icd", claimsSubmissionMaster.getDiagnosisCodeType());

        if(claimsSubmissionMaster.getIcd10diagnosisCode1() != null && !claimsSubmissionMaster.getIcd10diagnosisCode1().equals(""))
            stamper.getAcroFields().setField("diagnosis1", claimsSubmissionMaster.getIcd10diagnosisCode1().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode1().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode2() != null && !claimsSubmissionMaster.getIcd10diagnosisCode2().equals(""))
            stamper.getAcroFields().setField("diagnosis2", claimsSubmissionMaster.getIcd10diagnosisCode2().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode2().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode3() != null && !claimsSubmissionMaster.getIcd10diagnosisCode3().equals(""))
            stamper.getAcroFields().setField("diagnosis3", claimsSubmissionMaster.getIcd10diagnosisCode3().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode3().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode4() != null && !claimsSubmissionMaster.getIcd10diagnosisCode4().equals(""))
            stamper.getAcroFields().setField("diagnosis4", claimsSubmissionMaster.getIcd10diagnosisCode4().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode4().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode5() != null && !claimsSubmissionMaster.getIcd10diagnosisCode5().equals(""))
            stamper.getAcroFields().setField("diagnosis5", claimsSubmissionMaster.getIcd10diagnosisCode5().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode5().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode6() != null && !claimsSubmissionMaster.getIcd10diagnosisCode6().equals(""))
            stamper.getAcroFields().setField("diagnosis6", claimsSubmissionMaster.getIcd10diagnosisCode6().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode6().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode7() != null && !claimsSubmissionMaster.getIcd10diagnosisCode7().equals(""))
            stamper.getAcroFields().setField("diagnosis7", claimsSubmissionMaster.getIcd10diagnosisCode7().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode7().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode8() != null && !claimsSubmissionMaster.getIcd10diagnosisCode8().equals(""))
            stamper.getAcroFields().setField("diagnosis8", claimsSubmissionMaster.getIcd10diagnosisCode8().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode8().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode9() != null && !claimsSubmissionMaster.getIcd10diagnosisCode9().equals(""))
            stamper.getAcroFields().setField("diagnosis9", claimsSubmissionMaster.getIcd10diagnosisCode9().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode9().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode10() != null && !claimsSubmissionMaster.getIcd10diagnosisCode10().equals(""))
            stamper.getAcroFields().setField("diagnosis10", claimsSubmissionMaster.getIcd10diagnosisCode10().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode10().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode11() != null && !claimsSubmissionMaster.getIcd10diagnosisCode11().equals(""))
            stamper.getAcroFields().setField("diagnosis11", claimsSubmissionMaster.getIcd10diagnosisCode11().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode11().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode12() != null && !claimsSubmissionMaster.getIcd10diagnosisCode12().equals(""))
            stamper.getAcroFields().setField("diagnosis12", claimsSubmissionMaster.getIcd10diagnosisCode12().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode12().indexOf(',')));

        if(claimsSubmissionMaster.getParNo() != null)
            stamper.getAcroFields().setField("prior_auth", claimsSubmissionMaster.getParNo());

        stamper.getAcroFields().setField("tax_id", claimsSubmissionMaster.getBillingProviderEin());

//        PdfFormField ssn = form.getField("ssn");
//        String[] ssns = ssn.getAppearanceStates();
//        System.out.println(Arrays.toString(ssns));

        stamper.getAcroFields().setField("ssn", "EIN", true);
        stamper.getAcroFields().setField("physician_signature", claimsSubmissionMaster.getBillingProviderContactPersonName().toUpperCase());
        stamper.getAcroFields().setField("physician_date", new SimpleDateFormat("dd/MM/yyyy").format(originalDos));

        stamper.getAcroFields().setField("pt_account", claimsSubmissionMaster.getSubscriberMemberIdNo());
        if(claimsSubmissionMaster.getBenefitsAssignmentCertificationIndicator() != null && claimsSubmissionMaster.getBenefitsAssignmentCertificationIndicator().equalsIgnoreCase("Y"))
            stamper.getAcroFields().setField("assignment", "YES", true);
        else
            stamper.getAcroFields().setField("assignment", "NO", true);

        if(totalChargeAmount != 0)
            stamper.getAcroFields().setField("t_charge", df.format(totalChargeAmount));
        else
            stamper.getAcroFields().setField("t_charge", df.format(0.00));
        stamper.getAcroFields().setField("t_charge", df.format(totalChargeAmount));
        stamper.getAcroFields().setField("fac_name", claimsSubmissionMaster.getServiceProviderOrganisationName().toUpperCase());
        String servProvAddressLine2 = "";
        if(claimsSubmissionMaster.getServiceProviderAddressLine2() != null)
            servProvAddressLine2 = claimsSubmissionMaster.getServiceProviderAddressLine2().toUpperCase();
        stamper.getAcroFields().setField("fac_street", claimsSubmissionMaster.getServiceProviderAddressLine1().toUpperCase()+" "+servProvAddressLine2);
        stamper.getAcroFields().setField("fac_location", claimsSubmissionMaster.getServiceProviderCity().toUpperCase()+" "+claimsSubmissionMaster.getServiceProviderState().toUpperCase()+" "+claimsSubmissionMaster.getServiceProviderZipCode().toUpperCase());
        stamper.getAcroFields().setField("pin1", claimsSubmissionMaster.getServiceProviderNpi().toUpperCase());
        stamper.getAcroFields().setField("grp1", claimsSubmissionMaster.getServiceProviderTaxonomy().toUpperCase());


        stamper.getAcroFields().setField("doc_phone area", claimsSubmissionMaster.getBillingProviderContactNo().substring(0, 3));
        stamper.getAcroFields().setField("doc_phone", claimsSubmissionMaster.getBillingProviderContactNo().substring(3));
        stamper.getAcroFields().setField("doc_name", claimsSubmissionMaster.getBillingProviderContactPersonName().toUpperCase());
        String billProvAddressLine2 = "";
        if(claimsSubmissionMaster.getBillingProviderAddressLine2() != null)
            billProvAddressLine2 = claimsSubmissionMaster.getBillingProviderAddressLine2();
        stamper.getAcroFields().setField("doc_street", claimsSubmissionMaster.getBillingProviderAddressLine1().toUpperCase()+" "+billProvAddressLine2.toUpperCase());
        stamper.getAcroFields().setField("doc_location", claimsSubmissionMaster.getBillingProviderCity().toUpperCase()+" "+claimsSubmissionMaster.getBillingProviderState().toUpperCase()+" "+claimsSubmissionMaster.getBillingProviderZipCode().toUpperCase());
        stamper.getAcroFields().setField("pin", claimsSubmissionMaster.getBillingProviderNpi().toUpperCase());
        stamper.getAcroFields().setField("grp", claimsSubmissionMaster.getBillingProviderTaxonomy().toUpperCase());

        fillPrimaryClaimSubmissionServiceLineFormData(claimsSubmissionMaster, serviceLinesMasters, stamper);

        stamper.setFormFlattening(true);
        stamper.close();
        pdfReader.close();
        return stamper;
    }

    public void fillPrimaryClaimSubmissionServiceLineFormData(ClaimsSubmissionMaster claimsSubmissionMaster, List<ServiceLinesMaster> serviceLinesMasters, PdfStamper stamper){
        final DecimalFormat df = new DecimalFormat("0.00");
        char diagnosisPointer = '\0';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 1)
            diagnosisPointer = 'A';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 2)
            diagnosisPointer = 'B';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 3)
            diagnosisPointer = 'C';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 4)
            diagnosisPointer = 'D';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 5)
            diagnosisPointer = 'E';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 6)
            diagnosisPointer = 'F';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 7)
            diagnosisPointer = 'G';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 8)
            diagnosisPointer = 'H';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 9)
            diagnosisPointer = 'I';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 10)
            diagnosisPointer = 'J';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 11)
            diagnosisPointer = 'K';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 12)
            diagnosisPointer = 'L';
        var wrapper = new Object(){ int ordinal = 1; };
        char finalDiagnosisPointer = diagnosisPointer;

        serviceLinesMasters.stream()
            //.limit(2)
            .forEach(serviceLine -> {
                try {
                    stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_dd_from", serviceLine.getOriginalDos().getDayOfMonth() < 10 ? "0"+serviceLine.getOriginalDos().getDayOfMonth() : String.valueOf(serviceLine.getOriginalDos().getDayOfMonth()));
                    stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_mm_from", serviceLine.getOriginalDos().getMonthValue() < 10 ? "0"+serviceLine.getOriginalDos().getMonthValue() : String.valueOf(serviceLine.getOriginalDos().getMonthValue()));
                    stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_yy_from", String.valueOf(serviceLine.getOriginalDos().getYear()).substring(2, 4));
                    if (serviceLine.getDosTo() != null) {
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_dd_end", serviceLine.getOriginalDos().getDayOfMonth() < 10 ? "0"+serviceLine.getOriginalDos().getDayOfMonth() : String.valueOf(serviceLine.getOriginalDos().getDayOfMonth()));
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_mm_end", serviceLine.getOriginalDos().getMonthValue() < 10 ? "0"+serviceLine.getOriginalDos().getMonthValue() : String.valueOf(serviceLine.getOriginalDos().getMonthValue()));
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_yy_end", String.valueOf(serviceLine.getOriginalDos().getYear()).substring(2, 4));
                    }
                    else{
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_dd_end", serviceLine.getOriginalDos().getDayOfMonth() < 10 ? "0"+serviceLine.getOriginalDos().getDayOfMonth() : String.valueOf(serviceLine.getOriginalDos().getDayOfMonth()));
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_mm_end", serviceLine.getOriginalDos().getMonthValue() < 10 ? "0"+serviceLine.getOriginalDos().getMonthValue() : String.valueOf(serviceLine.getOriginalDos().getMonthValue()));
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_yy_end", String.valueOf(serviceLine.getOriginalDos().getYear()).substring(2, 4));
                    }
                    stamper.getAcroFields().setField("cpt" + wrapper.ordinal, serviceLine.getProcCode());
                    if (serviceLine.getModifier1() != null)
                        stamper.getAcroFields().setField("mod" + wrapper.ordinal, serviceLine.getModifier1());
                    if (serviceLine.getModifier2() != null)
                        stamper.getAcroFields().setField("mod" + wrapper.ordinal + "a", serviceLine.getModifier2());
                    if (serviceLine.getModifier3() != null)
                        stamper.getAcroFields().setField("mod" + wrapper.ordinal + "b", serviceLine.getModifier3());
                    if (serviceLine.getModifier4() != null)
                        stamper.getAcroFields().setField("mod" + wrapper.ordinal + "c", serviceLine.getModifier4());

                    stamper.getAcroFields().setField("ch" + wrapper.ordinal, df.format(serviceLine.getChargeAmt()));
                    stamper.getAcroFields().setField("day" + wrapper.ordinal, String.valueOf(serviceLine.getQty()));

                    stamper.getAcroFields().setField("diag"+wrapper.ordinal, String.valueOf(finalDiagnosisPointer));
//                    if(serviceLine.getOrderingProviderNpi() != null) {
//                        stamper.getAcroFields().setField("local"+wrapper.ordinal, serviceLine.getOrderingProviderNpi());
//                    }
//                    else{
//                        stamper.getAcroFields().setField("local"+wrapper.ordinal, "");
//                    }
                    wrapper.ordinal++;
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            });
    }

    public void mergePdfs(String file1, String file2, String finalFileName, String type){
        try {
            String[] files = {fileUploadConfigProperties.getTempClaimFormCMS1500Documents().getLocation() + "/" + file1, fileUploadConfigProperties.getTempClaimFormCMS1500Documents().getLocation() + "/" + file2};
            String path = null;
            if(type.equalsIgnoreCase("PRIMARY"))
                path = fileUploadConfigProperties.getPrimaryClaimFormCMS1500Documents().getLocation();
            else if(type.equalsIgnoreCase("SECONDARY"))
                path = fileUploadConfigProperties.getSecondaryClaimFormCMS1500Documents().getLocation();
            else if(type.equalsIgnoreCase("RECLAIM"))
                path = fileUploadConfigProperties.getReClaimFormCMS1500Documents().getLocation();

            Document pDFCombineUsingJava = new Document();
            PdfCopy copy = new PdfCopy(pDFCombineUsingJava , new FileOutputStream(path+"/"+finalFileName));
            pDFCombineUsingJava.open();
            PdfReader readInputPDF;
            //int number_of_pages;
            for (int i = 0; i < files.length; i++) {
                RandomAccessFile raf = new RandomAccessFile(files[i], "r");
                RandomAccessFileOrArray pdfFile = new RandomAccessFileOrArray(new RandomAccessSourceFactory().createSource(raf));
                readInputPDF = new PdfReader(pdfFile, new byte[0]);
                copy.addDocument(readInputPDF);
                copy.freeReader(readInputPDF);
                readInputPDF.close();
                Files.deleteIfExists(Paths.get(files[i]));
            }
            pDFCombineUsingJava.close();
            copy.close();
        }
        catch (Exception i) {
            System.out.println(i);
        }
    }

    public String createNewClaimFormFile(String fileName, String type){
        File finalFile = null;
        try {
            File file = new File(fileUploadConfigProperties.getBaseFormCMS1500Documents().getLocation()+"/"+"form.pdf");
            String[] files = {file.getAbsolutePath()};
            if(type.equalsIgnoreCase("TEMP"))
                finalFile = new File(fileUploadConfigProperties.getTempClaimFormCMS1500Documents().getLocation()+"/"+fileName+".pdf");
            else if(type.equalsIgnoreCase("PRIMARY"))
                finalFile = new File(fileUploadConfigProperties.getPrimaryClaimFormCMS1500Documents().getLocation()+"/"+fileName+".pdf");
            else if(type.equalsIgnoreCase("SECONDARY"))
                finalFile = new File(fileUploadConfigProperties.getSecondaryClaimFormCMS1500Documents().getLocation()+"/"+fileName+".pdf");
            else if(type.equalsIgnoreCase("RECLAIM"))
                finalFile = new File(fileUploadConfigProperties.getReClaimFormCMS1500Documents().getLocation()+"/"+fileName+".pdf");

            Document pDFCombineUsingJava = new Document();
            PdfCopy copy = new PdfCopy(pDFCombineUsingJava , new FileOutputStream(finalFile));
            pDFCombineUsingJava.open();
            PdfReader ReadInputPDF;
//            //int number_of_pages;
            for (int i = 0; i < files.length; i++) {
                ReadInputPDF = new PdfReader(files[i]);
                copy.addDocument(ReadInputPDF);
                copy.freeReader(ReadInputPDF);
            }
            pDFCombineUsingJava.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return finalFile.getName();
    }

    @Override
    @Transactional
    public void prepareSecondaryClaimSubmissionHealthInsuranceForm(String claimControlNumber) throws Exception {
        System.out.println("=============prepareSecondaryClaimHealthInsuranceForm==============");
        SecondaryClaimsSubmissionMaster claimsSubmissionMaster = secondaryClaimsSubmissionMasterRepository.getHealthInsuranceClaimDetailsOnClaimControlNumber(claimControlNumber);
        if(claimsSubmissionMaster != null) {
            List<SecondaryServiceLinesMaster> serviceLinesMasters = secondaryServiceLinesMasterRepository.getServiceLineMasterDataOnClaimSubmission(claimsSubmissionMaster.getChangeHealthSecondarySubmisionMasterId());
            prepareSecondaryClaimSubmissionHealthInsuranceData(claimsSubmissionMaster, serviceLinesMasters);
        }
    }

    public void prepareSecondaryClaimSubmissionHealthInsuranceData(SecondaryClaimsSubmissionMaster claimsSubmissionMaster, List<SecondaryServiceLinesMaster> serviceLinesMasters) throws Exception {
        log.info("===================claimsSubmissionMaster===========================>"+claimsSubmissionMaster.getClaimControlNo());
        long serviceLineSize = serviceLinesMasters.size();
        List<SecondaryServiceLinesMaster> firstPage;
        List<SecondaryServiceLinesMaster> secondPage;
        Double totalChargeAmount = 0.00d;
        if(serviceLineSize >= 3){
            File file1 = new File(fileUploadConfigProperties.getTempClaimFormCMS1500Documents()+"/"+createNewClaimFormFile("first_"+claimsSubmissionMaster.getClaimControlNo(), "TEMP"));
            file1.getParentFile().mkdirs();

            File file2 = new File(fileUploadConfigProperties.getTempClaimFormCMS1500Documents()+"/"+createNewClaimFormFile("second_"+claimsSubmissionMaster.getClaimControlNo(), "TEMP"));
            file2.getParentFile().mkdirs();
            firstPage = new ArrayList<>();
            secondPage = new ArrayList<>();
            for (int i = 0; i < serviceLineSize - 1; i++) {
                firstPage.add(serviceLinesMasters.get(i));
                totalChargeAmount = totalChargeAmount + serviceLinesMasters.get(i).getChargeAmt();
            }
            for (int i = 2; i < serviceLineSize; i++) {
                secondPage.add(serviceLinesMasters.get(i));
                totalChargeAmount = totalChargeAmount + serviceLinesMasters.get(i).getChargeAmt();
            }

            PdfStamper stamper1 = fillSecondaryClaimFormData(file1.getName(), "TEMP", claimsSubmissionMaster, firstPage, 0.00d);
            stamper1.close();
            PdfStamper stamper2 = fillSecondaryClaimFormData(file2.getName(), "TEMP", claimsSubmissionMaster, secondPage, totalChargeAmount);
            stamper2.close();

            String finalFileName = createNewClaimFormFile(claimsSubmissionMaster.getClaimControlNo(), "SECONDARY");
            mergePdfs(file1.getName(), file2.getName(), finalFileName, "SECONDARY");
            if(finalFileName != null){
                claimsSubmissionMaster.setCms1500FormName(finalFileName);
                secondaryClaimsSubmissionMasterRepository.save(claimsSubmissionMaster);
            }
        }
        else{
            String finalFileName = createNewClaimFormFile(claimsSubmissionMaster.getClaimControlNo(), "SECONDARY");
            for (int i = 0; i < serviceLineSize; i++) {
                totalChargeAmount = totalChargeAmount + serviceLinesMasters.get(i).getChargeAmt();
            }
            fillSecondaryClaimFormData(finalFileName, "SECONDARY", claimsSubmissionMaster, serviceLinesMasters, totalChargeAmount);
            if(finalFileName != null){
                claimsSubmissionMaster.setCms1500FormName(finalFileName);
                secondaryClaimsSubmissionMasterRepository.save(claimsSubmissionMaster);
            }
        }
    }

    public PdfStamper fillSecondaryClaimFormData(String dest, String type, SecondaryClaimsSubmissionMaster claimsSubmissionMaster, List<SecondaryServiceLinesMaster> serviceLinesMasters, Double totalChargeAmount) throws Exception{
        final DecimalFormat df = new DecimalFormat("0.00");
        PdfReader pdfReader = new PdfReader(fileUploadConfigProperties.getBaseFormCMS1500Documents().getLocation()+"/"+"form.pdf");
        String filePath = null;
        if(type.equalsIgnoreCase("TEMP"))
            filePath = fileUploadConfigProperties.getTempClaimFormCMS1500Documents().getLocation()+"/"+dest;
        if(type.equalsIgnoreCase("SECONDARY"))
            filePath = fileUploadConfigProperties.getSecondaryClaimFormCMS1500Documents().getLocation()+"/"+dest;
        PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(filePath));

        stamper.getAcroFields().setField("insurance_name", claimsSubmissionMaster.getReceiverOrganizationName().toUpperCase());


        if(Objects.isNull(claimsSubmissionMaster.getTradingPartnerAddressLine1()))
            stamper.getAcroFields().setField("insurance_address", "");
        else
            stamper.getAcroFields().setField("insurance_address", claimsSubmissionMaster.getTradingPartnerAddressLine1().toUpperCase());
        if(Objects.isNull(claimsSubmissionMaster.getInsuredAddressLine2()))
            stamper.getAcroFields().setField("insurance_address2", "");
        else
            stamper.getAcroFields().setField("insurance_address2", claimsSubmissionMaster.getInsuredAddressLine2().toUpperCase());
        StringBuffer insuranceCityStateZip = new StringBuffer();
        if(!Objects.isNull(claimsSubmissionMaster.getTradingPartnerCity()))
            insuranceCityStateZip.append(claimsSubmissionMaster.getTradingPartnerCity().toUpperCase());
        if(!Objects.isNull(claimsSubmissionMaster.getTradingPartnerState()))
            insuranceCityStateZip.append(claimsSubmissionMaster.getTradingPartnerState().toUpperCase());
        if(!Objects.isNull(claimsSubmissionMaster.getTradingPartnerZip()))
            insuranceCityStateZip.append(claimsSubmissionMaster.getTradingPartnerZip().toUpperCase());
        stamper.getAcroFields().setField("insurance_city_state_zip", insuranceCityStateZip.toString());

        stamper.getAcroFields().setField("insurance_type", claimsSubmissionMaster.getTradingPartnerName(), true);
        stamper.getAcroFields().setField("insurance_id", claimsSubmissionMaster.getSubscriberMemberIdNo());

        stamper.getAcroFields().setField("pt_name", claimsSubmissionMaster.getSubscriberFirstName().toUpperCase()+", "+claimsSubmissionMaster.getSubscriberLastName().toUpperCase());

        stamper.getAcroFields().setField("birth_mm", claimsSubmissionMaster.getSubscriberDob().getDayOfMonth() < 10 ? "0"+claimsSubmissionMaster.getSubscriberDob().getDayOfMonth() : String.valueOf(claimsSubmissionMaster.getSubscriberDob().getDayOfMonth()));
        stamper.getAcroFields().setField("birth_dd", claimsSubmissionMaster.getSubscriberDob().getMonthValue() < 10 ? "0"+claimsSubmissionMaster.getSubscriberDob().getMonthValue() : String.valueOf(claimsSubmissionMaster.getSubscriberDob().getMonthValue()));
        stamper.getAcroFields().setField("birth_yy", String.valueOf(claimsSubmissionMaster.getSubscriberDob().getYear()));
        stamper.getAcroFields().setField("sex", claimsSubmissionMaster.getSubscriberGender(), true);

        stamper.getAcroFields().setField("ins_name", claimsSubmissionMaster.getInsuredFirstName().toUpperCase()+" "+claimsSubmissionMaster.getInsuredLastName().toUpperCase());

        stamper.getAcroFields().setField("pt_street", claimsSubmissionMaster.getSubscriberAddressLine1().toUpperCase());
        stamper.getAcroFields().setField("pt_city", claimsSubmissionMaster.getSubscriberCity().toUpperCase());
        stamper.getAcroFields().setField("pt_state", claimsSubmissionMaster.getSubscriberState().toUpperCase());
        stamper.getAcroFields().setField("pt_zip", claimsSubmissionMaster.getSubscriberZipCode().toUpperCase());

        String patientRelationship = "";
        if(claimsSubmissionMaster.getPatientRelationshipInsured() != null) {
            if (claimsSubmissionMaster.getPatientRelationshipInsured().equalsIgnoreCase("18"))
                patientRelationship = "S";
            if (claimsSubmissionMaster.getPatientRelationshipInsured().equalsIgnoreCase("01"))
                patientRelationship = "M";
            if (claimsSubmissionMaster.getPatientRelationshipInsured().equalsIgnoreCase("19"))
                patientRelationship = "C";
            if (claimsSubmissionMaster.getPatientRelationshipInsured().equalsIgnoreCase("G8"))
                patientRelationship = "O";
        }
        stamper.getAcroFields().setField("rel_to_ins", patientRelationship, true);

        String insAddressLine2 = "";
        if(claimsSubmissionMaster.getInsuredAddressLine2() != null)
            insAddressLine2 = claimsSubmissionMaster.getInsuredAddressLine2();
        stamper.getAcroFields().setField("ins_street", claimsSubmissionMaster.getInsuredAddressLine1().toUpperCase()+" "+insAddressLine2.toUpperCase());
        stamper.getAcroFields().setField("ins_city", claimsSubmissionMaster.getInsuredCity().toUpperCase());
        stamper.getAcroFields().setField("ins_state", claimsSubmissionMaster.getInsuredState().toUpperCase());
        stamper.getAcroFields().setField("ins_zip", claimsSubmissionMaster.getInsuredZip().toUpperCase());
        stamper.getAcroFields().setField("ins_phone area", claimsSubmissionMaster.getInsuredContactNo().substring(0, 3));
        stamper.getAcroFields().setField("ins_phone", claimsSubmissionMaster.getInsuredContactNo().substring(3));

        stamper.getAcroFields().setField("other_ins_name", claimsSubmissionMaster.getOtherInsuredFirstName()+" "+claimsSubmissionMaster.getOtherInsuredLastName());
        stamper.getAcroFields().setField("other_ins_policy", claimsSubmissionMaster.getOtherPayerClaimControlNumber());
        stamper.getAcroFields().setField("other_ins_plan_name", claimsSubmissionMaster.getOtherPayerOrganizationName());


        if(claimsSubmissionMaster.getPatientConditionEmployment() != null && claimsSubmissionMaster.getPatientConditionEmployment().equalsIgnoreCase("YES"))
            stamper.getAcroFields().setField("employment", claimsSubmissionMaster.getPatientConditionEmployment(), true);
        else
            stamper.getAcroFields().setField("employment", "NO", true);
        if(claimsSubmissionMaster.getPatientConditionAutoAccident() != null && claimsSubmissionMaster.getPatientConditionAutoAccident().equalsIgnoreCase("YES"))
            stamper.getAcroFields().setField("pt_auto_accident", claimsSubmissionMaster.getPatientConditionAutoAccident(), true);
        else
            stamper.getAcroFields().setField("pt_auto_accident", "NO", true);
        if(claimsSubmissionMaster.getPatientConditionOtherAccident() != null && claimsSubmissionMaster.getPatientConditionOtherAccident().equalsIgnoreCase("YES"))
            stamper.getAcroFields().setField("other_accident", claimsSubmissionMaster.getPatientConditionOtherAccident(), true);
        else
            stamper.getAcroFields().setField("other_accident", "NO", true);

        stamper.getAcroFields().setField("ins_policy", claimsSubmissionMaster.getPrimaryInsurerPolicyNo());
        stamper.getAcroFields().setField("ins_dob_mm", claimsSubmissionMaster.getInsuredDob().getDayOfMonth() < 10 ? "0"+claimsSubmissionMaster.getInsuredDob().getDayOfMonth() : String.valueOf(claimsSubmissionMaster.getInsuredDob().getDayOfMonth()));
        stamper.getAcroFields().setField("ins_dob_dd", claimsSubmissionMaster.getInsuredDob().getMonthValue() < 10 ? "0"+claimsSubmissionMaster.getInsuredDob().getMonthValue() : String.valueOf(claimsSubmissionMaster.getInsuredDob().getMonthValue()));
        stamper.getAcroFields().setField("ins_dob_yy", String.valueOf(claimsSubmissionMaster.getInsuredDob().getYear()));
        if(claimsSubmissionMaster.getInsuredGender().equalsIgnoreCase("M"))
            stamper.getAcroFields().setField("ins_sex", "MALE", true);
        if(claimsSubmissionMaster.getInsuredGender().equalsIgnoreCase("F"))
            stamper.getAcroFields().setField("ins_sex", "FEMALE", true);
        stamper.getAcroFields().setField("ins_plan_name", claimsSubmissionMaster.getTradingPartnerName());
        if(claimsSubmissionMaster.getIsNextLevelInsurerPresentStatus() != null){
            if(claimsSubmissionMaster.getIsNextLevelInsurerPresentStatus().equalsIgnoreCase("Y"))
                stamper.getAcroFields().setField("ins_benefit_plan", "YES", true);
            if(claimsSubmissionMaster.getIsNextLevelInsurerPresentStatus().equalsIgnoreCase("N"))
                stamper.getAcroFields().setField("ins_benefit_plan", "NO", true);
        }


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        Date originalDos = new SimpleDateFormat("yyyy-MM-dd").parse(claimsSubmissionMaster.getOriginalDos().toString());
        if(claimsSubmissionMaster.getSignatureIndicator().equalsIgnoreCase("Y"))
            stamper.getAcroFields().setField("pt_signature", "Signature on file");

        if(claimsSubmissionMaster.getSignatureIndicator().equalsIgnoreCase("Y"))
            stamper.getAcroFields().setField("ins_signature", "Signature on file");
        stamper.getAcroFields().setField("pt_date", new SimpleDateFormat("dd/MM/yyyy").format(originalDos));

        stamper.getAcroFields().setField("ref_physician", claimsSubmissionMaster.getOrderingProviderFirstName().toUpperCase()+ " "+claimsSubmissionMaster.getOrderingProviderLastName().toUpperCase());
        stamper.getAcroFields().setField("id_physician", claimsSubmissionMaster.getOrderingProviderNpi());

        stamper.getAcroFields().setField("99icd", claimsSubmissionMaster.getDiagnosisCodeType());

        if(claimsSubmissionMaster.getIcd10diagnosisCode1() != null && !claimsSubmissionMaster.getIcd10diagnosisCode1().equals(""))
            stamper.getAcroFields().setField("diagnosis1", claimsSubmissionMaster.getIcd10diagnosisCode1().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode1().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode2() != null && !claimsSubmissionMaster.getIcd10diagnosisCode2().equals(""))
            stamper.getAcroFields().setField("diagnosis2", claimsSubmissionMaster.getIcd10diagnosisCode2().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode2().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode3() != null && !claimsSubmissionMaster.getIcd10diagnosisCode3().equals(""))
            stamper.getAcroFields().setField("diagnosis3", claimsSubmissionMaster.getIcd10diagnosisCode3().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode3().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode4() != null && !claimsSubmissionMaster.getIcd10diagnosisCode4().equals(""))
            stamper.getAcroFields().setField("diagnosis4", claimsSubmissionMaster.getIcd10diagnosisCode4().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode4().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode5() != null && !claimsSubmissionMaster.getIcd10diagnosisCode5().equals(""))
            stamper.getAcroFields().setField("diagnosis5", claimsSubmissionMaster.getIcd10diagnosisCode5().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode5().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode6() != null && !claimsSubmissionMaster.getIcd10diagnosisCode6().equals(""))
            stamper.getAcroFields().setField("diagnosis6", claimsSubmissionMaster.getIcd10diagnosisCode6().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode6().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode7() != null && !claimsSubmissionMaster.getIcd10diagnosisCode7().equals(""))
            stamper.getAcroFields().setField("diagnosis7", claimsSubmissionMaster.getIcd10diagnosisCode7().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode7().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode8() != null && !claimsSubmissionMaster.getIcd10diagnosisCode8().equals(""))
            stamper.getAcroFields().setField("diagnosis8", claimsSubmissionMaster.getIcd10diagnosisCode8().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode8().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode9() != null && !claimsSubmissionMaster.getIcd10diagnosisCode9().equals(""))
            stamper.getAcroFields().setField("diagnosis9", claimsSubmissionMaster.getIcd10diagnosisCode9().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode9().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode10() != null && !claimsSubmissionMaster.getIcd10diagnosisCode10().equals(""))
            stamper.getAcroFields().setField("diagnosis10", claimsSubmissionMaster.getIcd10diagnosisCode10().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode10().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode11() != null && !claimsSubmissionMaster.getIcd10diagnosisCode11().equals(""))
            stamper.getAcroFields().setField("diagnosis11", claimsSubmissionMaster.getIcd10diagnosisCode11().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode11().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode12() != null && !claimsSubmissionMaster.getIcd10diagnosisCode12().equals(""))
            stamper.getAcroFields().setField("diagnosis12", claimsSubmissionMaster.getIcd10diagnosisCode12().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode12().indexOf(',')));

        if(claimsSubmissionMaster.getParNo() != null)
            stamper.getAcroFields().setField("prior_auth", claimsSubmissionMaster.getParNo());

        stamper.getAcroFields().setField("tax_id", claimsSubmissionMaster.getBillingProviderEin());

        stamper.getAcroFields().setField("ssn", "EIN", true);
        stamper.getAcroFields().setField("physician_signature", claimsSubmissionMaster.getBillingProviderContactPersonName().toUpperCase());
        stamper.getAcroFields().setField("physician_date", new SimpleDateFormat("dd/MM/yyyy").format(originalDos));

        stamper.getAcroFields().setField("pt_account", claimsSubmissionMaster.getSubscriberMemberIdNo());
        if(claimsSubmissionMaster.getBenefitsAssignmentCertificationIndicator() != null && claimsSubmissionMaster.getBenefitsAssignmentCertificationIndicator().equalsIgnoreCase("Y"))
            stamper.getAcroFields().setField("assignment", "YES", true);
        else
            stamper.getAcroFields().setField("assignment", "NO", true);

        if(totalChargeAmount != 0)
            stamper.getAcroFields().setField("t_charge", df.format(totalChargeAmount));
        else
            stamper.getAcroFields().setField("t_charge", df.format(0.00));
        stamper.getAcroFields().setField("t_charge", df.format(totalChargeAmount));
        stamper.getAcroFields().setField("fac_name", claimsSubmissionMaster.getServiceProviderOrganisationName().toUpperCase());
        String servProvAddressLine2 = "";
        if(claimsSubmissionMaster.getServiceProviderAddressLine2() != null)
            servProvAddressLine2 = claimsSubmissionMaster.getServiceProviderAddressLine2().toUpperCase();
        stamper.getAcroFields().setField("fac_street", claimsSubmissionMaster.getServiceProviderAddressLine1().toUpperCase()+" "+servProvAddressLine2);
        stamper.getAcroFields().setField("fac_location", claimsSubmissionMaster.getServiceProviderCity().toUpperCase()+" "+claimsSubmissionMaster.getServiceProviderState().toUpperCase()+" "+claimsSubmissionMaster.getServiceProviderZipCode().toUpperCase());
        stamper.getAcroFields().setField("pin1", claimsSubmissionMaster.getServiceProviderNpi().toUpperCase());
        stamper.getAcroFields().setField("grp1", claimsSubmissionMaster.getServiceProviderTaxonomy().toUpperCase());


        stamper.getAcroFields().setField("doc_phone area", claimsSubmissionMaster.getBillingProviderContactNo().substring(0, 3));
        stamper.getAcroFields().setField("doc_phone", claimsSubmissionMaster.getBillingProviderContactNo().substring(3));
        stamper.getAcroFields().setField("doc_name", claimsSubmissionMaster.getBillingProviderContactPersonName().toUpperCase());
        String billProvAddressLine2 = "";
        if(claimsSubmissionMaster.getBillingProviderAddressLine2() != null)
            billProvAddressLine2 = claimsSubmissionMaster.getBillingProviderAddressLine2();
        stamper.getAcroFields().setField("doc_street", claimsSubmissionMaster.getBillingProviderAddressLine1().toUpperCase()+" "+billProvAddressLine2.toUpperCase());
        stamper.getAcroFields().setField("doc_location", claimsSubmissionMaster.getBillingProviderCity().toUpperCase()+" "+claimsSubmissionMaster.getBillingProviderState().toUpperCase()+" "+claimsSubmissionMaster.getBillingProviderZipCode().toUpperCase());
        stamper.getAcroFields().setField("pin", claimsSubmissionMaster.getBillingProviderNpi().toUpperCase());
        stamper.getAcroFields().setField("grp", claimsSubmissionMaster.getBillingProviderTaxonomy().toUpperCase());

        fillSecondaryClaimServiceLineFormData(claimsSubmissionMaster, serviceLinesMasters, stamper);

        stamper.setFormFlattening(true);
        stamper.close();
        pdfReader.close();
        return stamper;
    }
    public void fillSecondaryClaimServiceLineFormData(SecondaryClaimsSubmissionMaster claimsSubmissionMaster, List<SecondaryServiceLinesMaster> serviceLinesMasters, PdfStamper stamper){
        final DecimalFormat df = new DecimalFormat("0.00");
        char diagnosisPointer = '\0';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 1)
            diagnosisPointer = 'A';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 2)
            diagnosisPointer = 'B';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 3)
            diagnosisPointer = 'C';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 4)
            diagnosisPointer = 'D';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 5)
            diagnosisPointer = 'E';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 6)
            diagnosisPointer = 'F';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 7)
            diagnosisPointer = 'G';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 8)
            diagnosisPointer = 'H';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 9)
            diagnosisPointer = 'I';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 10)
            diagnosisPointer = 'J';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 11)
            diagnosisPointer = 'K';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 12)
            diagnosisPointer = 'L';
        var wrapper = new Object(){ int ordinal = 1; };
        char finalDiagnosisPointer = diagnosisPointer;

        serviceLinesMasters.stream()
            //.limit(2)
            .forEach(serviceLine -> {
                try {
                    stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_dd_from", serviceLine.getOriginalDos().getDayOfMonth() < 10 ? "0"+serviceLine.getOriginalDos().getDayOfMonth() : String.valueOf(serviceLine.getOriginalDos().getDayOfMonth()));
                    stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_mm_from", serviceLine.getOriginalDos().getMonthValue() < 10 ? "0"+serviceLine.getOriginalDos().getMonthValue() : String.valueOf(serviceLine.getOriginalDos().getMonthValue()));
                    stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_yy_from", String.valueOf(serviceLine.getOriginalDos().getYear()).substring(2, 4));
                    if (serviceLine.getDosTo() != null) {
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_dd_end", serviceLine.getOriginalDos().getDayOfMonth() < 10 ? "0"+serviceLine.getOriginalDos().getDayOfMonth() : String.valueOf(serviceLine.getOriginalDos().getDayOfMonth()));
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_mm_end", serviceLine.getOriginalDos().getMonthValue() < 10 ? "0"+serviceLine.getOriginalDos().getMonthValue() : String.valueOf(serviceLine.getOriginalDos().getMonthValue()));
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_yy_end", String.valueOf(serviceLine.getOriginalDos().getYear()).substring(2, 4));
                    }
                    else{
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_dd_end", serviceLine.getOriginalDos().getDayOfMonth() < 10 ? "0"+serviceLine.getOriginalDos().getDayOfMonth() : String.valueOf(serviceLine.getOriginalDos().getDayOfMonth()));
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_mm_end", serviceLine.getOriginalDos().getMonthValue() < 10 ? "0"+serviceLine.getOriginalDos().getMonthValue() : String.valueOf(serviceLine.getOriginalDos().getMonthValue()));
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_yy_end", String.valueOf(serviceLine.getOriginalDos().getYear()).substring(2, 4));
                    }
                    stamper.getAcroFields().setField("cpt" + wrapper.ordinal, serviceLine.getProcCode());
                    if (serviceLine.getModifier1() != null)
                        stamper.getAcroFields().setField("mod" + wrapper.ordinal, serviceLine.getModifier1());
                    if (serviceLine.getModifier2() != null)
                        stamper.getAcroFields().setField("mod" + wrapper.ordinal + "a", serviceLine.getModifier2());
                    if (serviceLine.getModifier3() != null)
                        stamper.getAcroFields().setField("mod" + wrapper.ordinal + "b", serviceLine.getModifier3());
                    if (serviceLine.getModifier4() != null)
                        stamper.getAcroFields().setField("mod" + wrapper.ordinal + "c", serviceLine.getModifier4());

                    stamper.getAcroFields().setField("ch" + wrapper.ordinal, df.format(serviceLine.getChargeAmt()));
                    stamper.getAcroFields().setField("day" + wrapper.ordinal, String.valueOf(serviceLine.getQty()));

                    stamper.getAcroFields().setField("diag"+wrapper.ordinal, String.valueOf(finalDiagnosisPointer));
//                    if(serviceLine.getOrderingProviderNpi() != null) {
//                        stamper.getAcroFields().setField("local"+wrapper.ordinal, serviceLine.getOrderingProviderNpi());
//                    }
//                    else{
//                        stamper.getAcroFields().setField("local"+wrapper.ordinal, "");
//                    }
                    wrapper.ordinal++;
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            });
    }

    @Override
    @Transactional
    public void preparePrimaryClaimReSubmissionHealthInsuranceForm(String claimControlNumber) throws Exception {
        System.out.println("=============prepareSecondaryClaimHealthInsuranceForm==============");
        PrimaryClaimsReSubmissionMaster claimsSubmissionMaster = primaryClaimsReSubmissionMasterRepository.getHealthInsuranceClaimDetailsOnClaimControlNumber(claimControlNumber);
        if(claimsSubmissionMaster != null) {
            List<PrimaryResubmissionServiceLinesMaster> serviceLinesMasters = primaryResubmissionServiceLinesMasterRepository.getServiceLineMasterDataOnClaimSubmission(claimsSubmissionMaster.getChangeHealthPrimaryResubmisionMasterId());
            preparePrimaryClaimResubmissionHealthInsuranceData(claimsSubmissionMaster, serviceLinesMasters);
        }
    }

    public void preparePrimaryClaimResubmissionHealthInsuranceData(PrimaryClaimsReSubmissionMaster claimsSubmissionMaster, List<PrimaryResubmissionServiceLinesMaster> serviceLinesMasters) throws Exception {
        long serviceLineSize = serviceLinesMasters.size();
        List<PrimaryResubmissionServiceLinesMaster> firstPage;
        List<PrimaryResubmissionServiceLinesMaster> secondPage;
        Double totalChargeAmount = 0.00d;
        if(serviceLineSize > 3){
            File file1 = new File(fileUploadConfigProperties.getTempClaimFormCMS1500Documents()+"/"+createNewClaimFormFile("first_"+claimsSubmissionMaster.getClaimControlNo(), "TEMP"));
            file1.getParentFile().mkdirs();

            File file2 = new File(fileUploadConfigProperties.getTempClaimFormCMS1500Documents()+"/"+createNewClaimFormFile("second_"+claimsSubmissionMaster.getClaimControlNo(), "TEMP"));
            file2.getParentFile().mkdirs();
            firstPage = new ArrayList<>();
            secondPage = new ArrayList<>();
            for (int i = 0; i < serviceLineSize - 1; i++) {
                firstPage.add(serviceLinesMasters.get(i));
                totalChargeAmount = totalChargeAmount + serviceLinesMasters.get(i).getChargeAmt();
            }
            for (int i = 2; i < serviceLineSize; i++) {
                secondPage.add(serviceLinesMasters.get(i));
                totalChargeAmount = totalChargeAmount + serviceLinesMasters.get(i).getChargeAmt();
            }

            PdfStamper stamper1 = fillPrimaryClaimResubmissionFormData(file1.getName(), "TEMP", claimsSubmissionMaster, firstPage, 0.00d);
            stamper1.close();
            PdfStamper stamper2 = fillPrimaryClaimResubmissionFormData(file2.getName(), "TEMP", claimsSubmissionMaster, secondPage, totalChargeAmount);
            stamper2.close();

            String finalFileName = createNewClaimFormFile(claimsSubmissionMaster.getClaimControlNo(), "RECLAIM");
            mergePdfs(file1.getName(), file2.getName(), finalFileName, "RECLAIM");
            if(finalFileName != null){
                claimsSubmissionMaster.setCms1500FormName(finalFileName);
                primaryClaimsReSubmissionMasterRepository.save(claimsSubmissionMaster);
            }
            deleteTemporaryFiles(file1, file2);
        }
        else{
            String finalFileName = createNewClaimFormFile(claimsSubmissionMaster.getClaimControlNo(), "RECLAIM");
            for (int i = 0; i < serviceLineSize; i++) {
                totalChargeAmount = totalChargeAmount + serviceLinesMasters.get(i).getChargeAmt();
            }
            fillPrimaryClaimResubmissionFormData(finalFileName, "RECLAIM", claimsSubmissionMaster, serviceLinesMasters, totalChargeAmount);
            if(finalFileName != null){
                claimsSubmissionMaster.setCms1500FormName(finalFileName);
                primaryClaimsReSubmissionMasterRepository.save(claimsSubmissionMaster);
            }
        }
    }

    public PdfStamper fillPrimaryClaimResubmissionFormData(String dest, String type, PrimaryClaimsReSubmissionMaster claimsSubmissionMaster, List<PrimaryResubmissionServiceLinesMaster> serviceLinesMasters, Double totalChargeAmount) throws Exception{
        final DecimalFormat df = new DecimalFormat("0.00");
        PdfReader pdfReader = new PdfReader(fileUploadConfigProperties.getBaseFormCMS1500Documents().getLocation()+"/"+"form.pdf");

        String filePath = null;
        if(type.equalsIgnoreCase("TEMP"))
            filePath = fileUploadConfigProperties.getTempClaimFormCMS1500Documents().getLocation()+"/"+dest;
        if(type.equalsIgnoreCase("RECLAIM"))
            filePath = fileUploadConfigProperties.getReClaimFormCMS1500Documents().getLocation()+"/"+dest;
        PdfStamper stamper = new PdfStamper(pdfReader, new FileOutputStream(filePath));
        stamper.getAcroFields().setField("insurance_name", claimsSubmissionMaster.getReceiverOrganizationName().toUpperCase());

        if(Objects.isNull(claimsSubmissionMaster.getTradingPartnerAddressLine1()))
            stamper.getAcroFields().setField("insurance_address", "");
        else
            stamper.getAcroFields().setField("insurance_address", claimsSubmissionMaster.getTradingPartnerAddressLine1().toUpperCase());
        if(Objects.isNull(claimsSubmissionMaster.getInsuredAddressLine2()))
            stamper.getAcroFields().setField("insurance_address2", "");
        else
            stamper.getAcroFields().setField("insurance_address2", claimsSubmissionMaster.getInsuredAddressLine2().toUpperCase());
        StringBuffer insuranceCityStateZip = new StringBuffer();
        if(!Objects.isNull(claimsSubmissionMaster.getTradingPartnerCity()))
            insuranceCityStateZip.append(claimsSubmissionMaster.getTradingPartnerCity().toUpperCase());
        if(!Objects.isNull(claimsSubmissionMaster.getTradingPartnerState()))
            insuranceCityStateZip.append(claimsSubmissionMaster.getTradingPartnerState().toUpperCase());
        if(!Objects.isNull(claimsSubmissionMaster.getTradingPartnerZip()))
            insuranceCityStateZip.append(claimsSubmissionMaster.getTradingPartnerZip().toUpperCase());
        stamper.getAcroFields().setField("insurance_city_state_zip", insuranceCityStateZip.toString());

        stamper.getAcroFields().setField("insurance_type", claimsSubmissionMaster.getTradingPartnerName(), true);
        stamper.getAcroFields().setField("insurance_id", claimsSubmissionMaster.getSubscriberMemberIdNo());

        stamper.getAcroFields().setField("pt_name", claimsSubmissionMaster.getSubscriberFirstName().toUpperCase()+", "+claimsSubmissionMaster.getSubscriberLastName().toUpperCase());

        stamper.getAcroFields().setField("birth_mm", claimsSubmissionMaster.getSubscriberDob().getDayOfMonth() < 10 ? "0"+claimsSubmissionMaster.getSubscriberDob().getDayOfMonth() : String.valueOf(claimsSubmissionMaster.getSubscriberDob().getDayOfMonth()));
        stamper.getAcroFields().setField("birth_dd", claimsSubmissionMaster.getSubscriberDob().getMonthValue() < 10 ? "0"+claimsSubmissionMaster.getSubscriberDob().getMonthValue() : String.valueOf(claimsSubmissionMaster.getSubscriberDob().getMonthValue()));
        stamper.getAcroFields().setField("birth_yy", String.valueOf(claimsSubmissionMaster.getSubscriberDob().getYear()));
        stamper.getAcroFields().setField("sex", claimsSubmissionMaster.getSubscriberGender(), true);

        stamper.getAcroFields().setField("ins_name", claimsSubmissionMaster.getInsuredFirstName().toUpperCase()+" "+claimsSubmissionMaster.getInsuredLastName().toUpperCase());

        stamper.getAcroFields().setField("pt_street", claimsSubmissionMaster.getSubscriberAddressLine1().toUpperCase());
        stamper.getAcroFields().setField("pt_city", claimsSubmissionMaster.getSubscriberCity().toUpperCase());
        stamper.getAcroFields().setField("pt_state", claimsSubmissionMaster.getSubscriberState().toUpperCase());
        stamper.getAcroFields().setField("pt_zip", claimsSubmissionMaster.getSubscriberZipCode().toUpperCase());

        String patientRelationship = "";
        if(claimsSubmissionMaster.getPatientRelationshipInsured() != null) {
            if (claimsSubmissionMaster.getPatientRelationshipInsured().equalsIgnoreCase("18"))
                patientRelationship = "S";
            if (claimsSubmissionMaster.getPatientRelationshipInsured().equalsIgnoreCase("01"))
                patientRelationship = "M";
            if (claimsSubmissionMaster.getPatientRelationshipInsured().equalsIgnoreCase("19"))
                patientRelationship = "C";
            if (claimsSubmissionMaster.getPatientRelationshipInsured().equalsIgnoreCase("G8"))
                patientRelationship = "O";
        }
        stamper.getAcroFields().setField("rel_to_ins", patientRelationship, true);

        String insAddressLine2 = "";
        if(claimsSubmissionMaster.getInsuredAddressLine2() != null)
            insAddressLine2 = claimsSubmissionMaster.getInsuredAddressLine2();
        stamper.getAcroFields().setField("ins_street", claimsSubmissionMaster.getInsuredAddressLine1().toUpperCase()+" "+insAddressLine2.toUpperCase());
        stamper.getAcroFields().setField("ins_city", claimsSubmissionMaster.getInsuredCity().toUpperCase());
        stamper.getAcroFields().setField("ins_state", claimsSubmissionMaster.getInsuredState().toUpperCase());
        stamper.getAcroFields().setField("ins_zip", claimsSubmissionMaster.getInsuredZip().toUpperCase());
        stamper.getAcroFields().setField("ins_phone area", claimsSubmissionMaster.getInsuredContactNo().substring(0, 3));
        stamper.getAcroFields().setField("ins_phone", claimsSubmissionMaster.getInsuredContactNo().substring(3));

        if(claimsSubmissionMaster.getPatientConditionEmployment() != null && claimsSubmissionMaster.getPatientConditionEmployment().equalsIgnoreCase("YES"))
            stamper.getAcroFields().setField("employment", claimsSubmissionMaster.getPatientConditionEmployment(), true);
        else
            stamper.getAcroFields().setField("employment", "NO", true);
        if(claimsSubmissionMaster.getPatientConditionAutoAccident() != null && claimsSubmissionMaster.getPatientConditionAutoAccident().equalsIgnoreCase("YES"))
            stamper.getAcroFields().setField("pt_auto_accident", claimsSubmissionMaster.getPatientConditionAutoAccident(), true);
        else
            stamper.getAcroFields().setField("pt_auto_accident", "NO", true);
        if(claimsSubmissionMaster.getPatientConditionOtherAccident() != null && claimsSubmissionMaster.getPatientConditionOtherAccident().equalsIgnoreCase("YES"))
            stamper.getAcroFields().setField("other_accident", claimsSubmissionMaster.getPatientConditionOtherAccident(), true);
        else
            stamper.getAcroFields().setField("other_accident", "NO", true);

        stamper.getAcroFields().setField("ins_policy", claimsSubmissionMaster.getPrimaryInsurerPolicyNo());
        stamper.getAcroFields().setField("ins_dob_mm", claimsSubmissionMaster.getInsuredDob().getDayOfMonth() < 10 ? "0"+claimsSubmissionMaster.getInsuredDob().getDayOfMonth() : String.valueOf(claimsSubmissionMaster.getInsuredDob().getDayOfMonth()));
        stamper.getAcroFields().setField("ins_dob_dd", claimsSubmissionMaster.getInsuredDob().getMonthValue() < 10 ? "0"+claimsSubmissionMaster.getInsuredDob().getMonthValue() : String.valueOf(claimsSubmissionMaster.getInsuredDob().getMonthValue()));
        stamper.getAcroFields().setField("ins_dob_yy", String.valueOf(claimsSubmissionMaster.getInsuredDob().getYear()));
        if(claimsSubmissionMaster.getInsuredGender().equalsIgnoreCase("M"))
            stamper.getAcroFields().setField("ins_sex", "MALE", true);
        if(claimsSubmissionMaster.getInsuredGender().equalsIgnoreCase("F"))
            stamper.getAcroFields().setField("ins_sex", "FEMALE", true);

        stamper.getAcroFields().setField("other_claim_id", claimsSubmissionMaster.getPayerClaimControlNumber());
        stamper.getAcroFields().setField("ins_plan_name", "");
        System.out.println("claimsSubmissionMaster.getIsNextLevelInsurerPresentStatus()====>>>"+claimsSubmissionMaster.getIsNextLevelInsurerPresentStatus());
        if(claimsSubmissionMaster.getIsNextLevelInsurerPresentStatus() != null && claimsSubmissionMaster.getIsNextLevelInsurerPresentStatus().equalsIgnoreCase("YES"))
            stamper.getAcroFields().setField("ins_benefit_plan", "YES", true);
        else
            stamper.getAcroFields().setField("ins_benefit_plan", "NO", true);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
        Date originalDos = new SimpleDateFormat("yyyy-MM-dd").parse(claimsSubmissionMaster.getOriginalDos().toString());
        if(claimsSubmissionMaster.getSignatureIndicator().equalsIgnoreCase("Y"))
            stamper.getAcroFields().setField("pt_signature", "Signature on file");

        if(claimsSubmissionMaster.getSignatureIndicator().equalsIgnoreCase("Y"))
            stamper.getAcroFields().setField("ins_signature", "Signature on file");
        stamper.getAcroFields().setField("pt_date", new SimpleDateFormat("dd/MM/yyyy").format(originalDos));

        stamper.getAcroFields().setField("ref_physician", claimsSubmissionMaster.getOrderingProviderFirstName().toUpperCase()+ " "+claimsSubmissionMaster.getOrderingProviderLastName().toUpperCase());
        stamper.getAcroFields().setField("id_physician", claimsSubmissionMaster.getOrderingProviderNpi());

        stamper.getAcroFields().setField("99icd", claimsSubmissionMaster.getDiagnosisCodeType());

        if(claimsSubmissionMaster.getIcd10diagnosisCode1() != null && !claimsSubmissionMaster.getIcd10diagnosisCode1().equals(""))
            stamper.getAcroFields().setField("diagnosis1", claimsSubmissionMaster.getIcd10diagnosisCode1().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode1().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode2() != null && !claimsSubmissionMaster.getIcd10diagnosisCode2().equals(""))
            stamper.getAcroFields().setField("diagnosis2", claimsSubmissionMaster.getIcd10diagnosisCode2().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode2().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode3() != null && !claimsSubmissionMaster.getIcd10diagnosisCode3().equals(""))
            stamper.getAcroFields().setField("diagnosis3", claimsSubmissionMaster.getIcd10diagnosisCode3().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode3().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode4() != null && !claimsSubmissionMaster.getIcd10diagnosisCode4().equals(""))
            stamper.getAcroFields().setField("diagnosis4", claimsSubmissionMaster.getIcd10diagnosisCode4().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode4().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode5() != null && !claimsSubmissionMaster.getIcd10diagnosisCode5().equals(""))
            stamper.getAcroFields().setField("diagnosis5", claimsSubmissionMaster.getIcd10diagnosisCode5().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode5().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode6() != null && !claimsSubmissionMaster.getIcd10diagnosisCode6().equals(""))
            stamper.getAcroFields().setField("diagnosis6", claimsSubmissionMaster.getIcd10diagnosisCode6().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode6().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode7() != null && !claimsSubmissionMaster.getIcd10diagnosisCode7().equals(""))
            stamper.getAcroFields().setField("diagnosis7", claimsSubmissionMaster.getIcd10diagnosisCode7().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode7().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode8() != null && !claimsSubmissionMaster.getIcd10diagnosisCode8().equals(""))
            stamper.getAcroFields().setField("diagnosis8", claimsSubmissionMaster.getIcd10diagnosisCode8().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode8().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode9() != null && !claimsSubmissionMaster.getIcd10diagnosisCode9().equals(""))
            stamper.getAcroFields().setField("diagnosis9", claimsSubmissionMaster.getIcd10diagnosisCode9().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode9().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode10() != null && !claimsSubmissionMaster.getIcd10diagnosisCode10().equals(""))
            stamper.getAcroFields().setField("diagnosis10", claimsSubmissionMaster.getIcd10diagnosisCode10().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode10().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode11() != null && !claimsSubmissionMaster.getIcd10diagnosisCode11().equals(""))
            stamper.getAcroFields().setField("diagnosis11", claimsSubmissionMaster.getIcd10diagnosisCode11().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode11().indexOf(',')));
        if(claimsSubmissionMaster.getIcd10diagnosisCode12() != null && !claimsSubmissionMaster.getIcd10diagnosisCode12().equals(""))
            stamper.getAcroFields().setField("diagnosis12", claimsSubmissionMaster.getIcd10diagnosisCode12().substring(0, claimsSubmissionMaster.getIcd10diagnosisCode12().indexOf(',')));


        AcroFields form = stamper.getAcroFields();
        form.setFieldProperty("medicaid_resub", "textsize", Float.valueOf(10), null);
        form.setField("medicaid_resub", claimsSubmissionMaster.getClaimFrequencyCode());
        stamper.getAcroFields().setField("original_ref", claimsSubmissionMaster.getPayerClaimControlNumber());

        if(claimsSubmissionMaster.getParNo() != null)
            stamper.getAcroFields().setField("prior_auth", claimsSubmissionMaster.getParNo());

        stamper.getAcroFields().setField("tax_id", claimsSubmissionMaster.getBillingProviderEin());

        stamper.getAcroFields().setField("ssn", "EIN", true);
        stamper.getAcroFields().setField("physician_signature", claimsSubmissionMaster.getBillingProviderContactPersonName().toUpperCase());
        stamper.getAcroFields().setField("physician_date", new SimpleDateFormat("dd/MM/yyyy").format(originalDos));

        stamper.getAcroFields().setField("pt_account", claimsSubmissionMaster.getSubscriberMemberIdNo());
        if(claimsSubmissionMaster.getBenefitsAssignmentCertificationIndicator() != null && claimsSubmissionMaster.getBenefitsAssignmentCertificationIndicator().equalsIgnoreCase("Y"))
            stamper.getAcroFields().setField("assignment", "YES", true);
        else
            stamper.getAcroFields().setField("assignment", "NO", true);

        if(totalChargeAmount != 0)
            stamper.getAcroFields().setField("t_charge", df.format(totalChargeAmount));
        else
            stamper.getAcroFields().setField("t_charge", df.format(0.00));
        stamper.getAcroFields().setField("t_charge", df.format(totalChargeAmount));
        stamper.getAcroFields().setField("fac_name", claimsSubmissionMaster.getServiceProviderOrganisationName().toUpperCase());
        String servProvAddressLine2 = "";
        if(claimsSubmissionMaster.getServiceProviderAddressLine2() != null)
            servProvAddressLine2 = claimsSubmissionMaster.getServiceProviderAddressLine2().toUpperCase();
        stamper.getAcroFields().setField("fac_street", claimsSubmissionMaster.getServiceProviderAddressLine1().toUpperCase()+" "+servProvAddressLine2);
        stamper.getAcroFields().setField("fac_location", claimsSubmissionMaster.getServiceProviderCity().toUpperCase()+" "+claimsSubmissionMaster.getServiceProviderState().toUpperCase()+" "+claimsSubmissionMaster.getServiceProviderZipCode().toUpperCase());
        stamper.getAcroFields().setField("pin1", claimsSubmissionMaster.getServiceProviderNpi().toUpperCase());
        stamper.getAcroFields().setField("grp1", claimsSubmissionMaster.getServiceProviderTaxonomy().toUpperCase());


        stamper.getAcroFields().setField("doc_phone area", claimsSubmissionMaster.getBillingProviderContactNo().substring(0, 3));
        stamper.getAcroFields().setField("doc_phone", claimsSubmissionMaster.getBillingProviderContactNo().substring(3));
        stamper.getAcroFields().setField("doc_name", claimsSubmissionMaster.getBillingProviderContactPersonName().toUpperCase());
        String billProvAddressLine2 = "";
        if(claimsSubmissionMaster.getBillingProviderAddressLine2() != null)
            billProvAddressLine2 = claimsSubmissionMaster.getBillingProviderAddressLine2();
        stamper.getAcroFields().setField("doc_street", claimsSubmissionMaster.getBillingProviderAddressLine1().toUpperCase()+" "+billProvAddressLine2.toUpperCase());
        stamper.getAcroFields().setField("doc_location", claimsSubmissionMaster.getBillingProviderCity().toUpperCase()+" "+claimsSubmissionMaster.getBillingProviderState().toUpperCase()+" "+claimsSubmissionMaster.getBillingProviderZipCode().toUpperCase());
        stamper.getAcroFields().setField("pin", claimsSubmissionMaster.getBillingProviderNpi().toUpperCase());
        stamper.getAcroFields().setField("grp", claimsSubmissionMaster.getBillingProviderTaxonomy().toUpperCase());

        fillPrimaryClaimResubmissionServiceLineFormData(claimsSubmissionMaster, serviceLinesMasters, stamper);

        stamper.setFormFlattening(true);
        stamper.close();
        pdfReader.close();
        return stamper;
    }

    public void fillPrimaryClaimResubmissionServiceLineFormData(PrimaryClaimsReSubmissionMaster claimsSubmissionMaster, List<PrimaryResubmissionServiceLinesMaster> serviceLinesMasters, PdfStamper stamper){
        final DecimalFormat df = new DecimalFormat("0.00");
        char diagnosisPointer = '\0';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 1)
            diagnosisPointer = 'A';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 2)
            diagnosisPointer = 'B';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 3)
            diagnosisPointer = 'C';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 4)
            diagnosisPointer = 'D';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 5)
            diagnosisPointer = 'E';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 6)
            diagnosisPointer = 'F';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 7)
            diagnosisPointer = 'G';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 8)
            diagnosisPointer = 'H';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 9)
            diagnosisPointer = 'I';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 10)
            diagnosisPointer = 'J';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 11)
            diagnosisPointer = 'K';
        if(Integer.parseInt(claimsSubmissionMaster.getPrimaryDiagnosis()) == 12)
            diagnosisPointer = 'L';
        var wrapper = new Object(){ int ordinal = 1; };
        char finalDiagnosisPointer = diagnosisPointer;

        serviceLinesMasters.stream()
            //.limit(2)
            .forEach(serviceLine -> {
                try {
                    stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_dd_from", serviceLine.getOriginalDos().getDayOfMonth() < 10 ? "0"+serviceLine.getOriginalDos().getDayOfMonth() : String.valueOf(serviceLine.getOriginalDos().getDayOfMonth()));
                    stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_mm_from", serviceLine.getOriginalDos().getMonthValue() < 10 ? "0"+serviceLine.getOriginalDos().getMonthValue() : String.valueOf(serviceLine.getOriginalDos().getMonthValue()));
                    stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_yy_from", String.valueOf(serviceLine.getOriginalDos().getYear()).substring(2, 4));
                    if (serviceLine.getDosTo() != null) {
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_dd_end", serviceLine.getOriginalDos().getDayOfMonth() < 10 ? "0"+serviceLine.getOriginalDos().getDayOfMonth() : String.valueOf(serviceLine.getOriginalDos().getDayOfMonth()));
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_mm_end", serviceLine.getOriginalDos().getMonthValue() < 10 ? "0"+serviceLine.getOriginalDos().getMonthValue() : String.valueOf(serviceLine.getOriginalDos().getMonthValue()));
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_yy_end", String.valueOf(serviceLine.getOriginalDos().getYear()).substring(2, 4));
                    }
                    else{
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_dd_end", serviceLine.getOriginalDos().getDayOfMonth() < 10 ? "0"+serviceLine.getOriginalDos().getDayOfMonth() : String.valueOf(serviceLine.getOriginalDos().getDayOfMonth()));
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_mm_end", serviceLine.getOriginalDos().getMonthValue() < 10 ? "0"+serviceLine.getOriginalDos().getMonthValue() : String.valueOf(serviceLine.getOriginalDos().getMonthValue()));
                        stamper.getAcroFields().setField("sv" + wrapper.ordinal + "_yy_end", String.valueOf(serviceLine.getOriginalDos().getYear()).substring(2, 4));
                    }
                    stamper.getAcroFields().setField("cpt" + wrapper.ordinal, serviceLine.getProcCode());
                    if (serviceLine.getModifier1() != null)
                        stamper.getAcroFields().setField("mod" + wrapper.ordinal, serviceLine.getModifier1());
                    if (serviceLine.getModifier2() != null)
                        stamper.getAcroFields().setField("mod" + wrapper.ordinal + "a", serviceLine.getModifier2());
                    if (serviceLine.getModifier3() != null)
                        stamper.getAcroFields().setField("mod" + wrapper.ordinal + "b", serviceLine.getModifier3());
                    if (serviceLine.getModifier4() != null)
                        stamper.getAcroFields().setField("mod" + wrapper.ordinal + "c", serviceLine.getModifier4());

                    stamper.getAcroFields().setField("ch" + wrapper.ordinal, df.format(serviceLine.getChargeAmt()));
                    stamper.getAcroFields().setField("day" + wrapper.ordinal, String.valueOf(serviceLine.getQty()));

                    stamper.getAcroFields().setField("diag"+wrapper.ordinal, String.valueOf(finalDiagnosisPointer));
//                    if(serviceLine.getOrderingProviderNpi() != null) {
//                        stamper.getAcroFields().setField("local"+wrapper.ordinal, serviceLine.getOrderingProviderNpi());
//                    }
//                    else{
//                        stamper.getAcroFields().setField("local"+wrapper.ordinal, "");
//                    }
                    wrapper.ordinal++;
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            });
    }
}
















