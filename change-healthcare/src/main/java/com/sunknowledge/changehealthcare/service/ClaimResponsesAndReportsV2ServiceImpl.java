package com.sunknowledge.changehealthcare.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sunknowledge.changehealthcare.application.utils.ApplicationConstants;
import com.sunknowledge.changehealthcare.core.ServiceOutcome;
import com.sunknowledge.changehealthcare.core.TokenOutCome;
import com.sunknowledge.changehealthcare.pojo.claimreports.ActualClaimResponseReport;
import com.sunknowledge.changehealthcare.pojo.claimreports.ClaimReports;
import com.sunknowledge.changehealthcare.pojo.claimreports.ClaimsTransactionReport;
import com.sunknowledge.changehealthcare.pojo.claimreports.X12EDIReportContents;
import com.sunknowledge.changehealthcare.pojo.claimreports.claimsremittance835.ClaimsRemittanceTransaction;
import com.sunknowledge.changehealthcare.pojo.claimreports.claimstatusresponse277.ClaimStatusResponseTransaction;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClaimResponsesAndReportsV2ServiceImpl implements ClaimResponsesAndReportsV2Service {
	@Autowired
	private TokenGenerationServiceImpl tokenGenerationServiceImpl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public ServiceOutcome<ArrayList<ActualClaimResponseReport>> getActualClaimResponsesReports() {
		log.info("=======================POST(SERVICE) Get Medical Network Claims Responses and Reports V2 API(Change Healthcare)==========================");
		ServiceOutcome<ClaimReports> routcome = new ServiceOutcome<ClaimReports>();
		ServiceOutcome<ArrayList<ActualClaimResponseReport>> claimResponseReportOutcome = new ServiceOutcome<ArrayList<ActualClaimResponseReport>>();
		ArrayList<ActualClaimResponseReport> claimResponseReport = new ArrayList<ActualClaimResponseReport>();
		TokenOutCome tokenOutCome = new TokenOutCome();
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
		boolean response = false;
		int type = 1;
		String fileName = "";
		ServiceOutcome<ClaimReports> routcome = new ServiceOutcome<ClaimReports>();
		ClaimReports claimOutcome = new ClaimReports();
		ResponseEntity<String> responseEntity = generateResponse(accessToken, fileName, type);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();
			ClaimReports claimReports;
			try {
				claimReports = mapper.readValue(responseEntity.getBody(), ClaimReports.class);
				if(claimReports.getReports() != null) {
					response = true;
					routcome.setData(claimReports);
					routcome.setMessage("Successfully accessed complete available customer document list.");
					routcome.setOutcome(response);
				}
				else {
					response = false;
					routcome.setData(claimOutcome);
					routcome.setMessage("Failed to access complete available customer document list.");
					routcome.setOutcome(response);
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
		boolean response = false;
		int type = 2;
		ServiceOutcome<X12EDIReportContents> routcome = new ServiceOutcome<X12EDIReportContents>();
		ResponseEntity<String> responseEntity = generateResponse(accessToken, fileName, type);
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();
			X12EDIReportContents contents;
			try {
				contents = mapper.readValue(responseEntity.getBody(), X12EDIReportContents.class);
				if(contents.getReportContent() != null) {
					response = true;
					routcome.setData(contents);
					routcome.setMessage("Successfully retrived the X12 EDI report contents.");
					routcome.setOutcome(response);
				}
				else {
					response = false;
					routcome.setData(contents);
					routcome.setMessage("Failed to retrived the X12 EDI report contents.");
					routcome.setOutcome(response);
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
		boolean response = false;
		ServiceOutcome<ClaimStatusResponseTransaction> csrtOutcome = new ServiceOutcome<ClaimStatusResponseTransaction>();
		ServiceOutcome<ClaimsRemittanceTransaction> crtOutcome = new ServiceOutcome<ClaimsRemittanceTransaction>();
		ServiceOutcome<ClaimsTransactionReport> ctrOutcome = new ServiceOutcome<ClaimsTransactionReport>();
		ClaimsTransactionReport claimsTransactionReport = new ClaimsTransactionReport();
		
		ResponseEntity<String> responseEntity = generateResponse(accessToken, fileName, type);
		String reportType = fileName.substring(0, 2);
		
		if(responseEntity.getStatusCode() == HttpStatus.OK) {
			ObjectMapper mapper = new ObjectMapper();
			ClaimStatusResponseTransaction claimStatusResponseTransaction;
			ClaimsRemittanceTransaction claimsRemittanceTransaction;
			try {
				if(reportType.equals("X3")) {
					claimStatusResponseTransaction = mapper.readValue(responseEntity.getBody(), ClaimStatusResponseTransaction.class);
					if(claimStatusResponseTransaction.getTransactions() != null) {
						response = true;
						csrtOutcome.setData(claimStatusResponseTransaction);
						csrtOutcome.setMessage("Successfully retrived the actual report contents.");
						csrtOutcome.setOutcome(response);
						
						claimsTransactionReport.setClaimStatusResponseTransaction(csrtOutcome.getData());
						ctrOutcome.setData(claimsTransactionReport);
					}
					else {
						response = false;
						csrtOutcome.setData(claimStatusResponseTransaction);
						csrtOutcome.setMessage("Failed to retrived the actual report contents.");
						csrtOutcome.setOutcome(response);
					}
				}
				if(reportType.equals("R5")) {
					claimsRemittanceTransaction = mapper.readValue(responseEntity.getBody(), ClaimsRemittanceTransaction.class);
					if(claimsRemittanceTransaction.getTransactions() != null) {
						response = true;
						crtOutcome.setData(claimsRemittanceTransaction);
						crtOutcome.setMessage("Successfully retrived the actual report contents.");
						crtOutcome.setOutcome(response);
						
						claimsTransactionReport.setClaimsRemittanceTransaction(crtOutcome.getData());
						ctrOutcome.setData(claimsTransactionReport);
					}
					else {
						response = false;
						crtOutcome.setData(claimsRemittanceTransaction);
						crtOutcome.setMessage("Failed to retrived the actual report contents.");
						crtOutcome.setOutcome(response);
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
			url = ApplicationConstants.AVAILABLE_CUSTOMER_DOCUMENT_URL;
		if(type == 2)
			url = ApplicationConstants.AVAILABLE_CUSTOMER_DOCUMENT_URL+"/"+fileName;
		if(type == 3) {
			String reportType = fileName.substring(0, 2);
			int responseType = 0;
			if(reportType.equals("X3")) {
				responseType = 277;
				url = ApplicationConstants.AVAILABLE_CUSTOMER_DOCUMENT_URL+"/"+fileName+"/"+responseType;
			}
			if(reportType.equals("R5")) {
				responseType = 835;
				url = ApplicationConstants.AVAILABLE_CUSTOMER_DOCUMENT_URL+"/"+fileName+"/"+responseType;
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
}
