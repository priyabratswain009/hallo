package com.sunknowledge.dme.rcm.web.rest.claimcontroller;

import java.util.List;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import com.sunknowledge.dme.rcm.pojo.common.ResultClaimSubmissionOutcome;
import com.sunknowledge.dme.rcm.service.claimservice.ClaimSubmissionService;
import com.sunknowledge.dme.rcm.service.claimservice.InvoiceService;
import com.sunknowledge.dme.rcm.service.claimservice.PrimaryClaimReSubmissionService;
import com.sunknowledge.dme.rcm.service.claimservice.PrimaryClaimSubmissionService;
import com.sunknowledge.dme.rcm.service.claimservice.SecondaryClaimSubmissionService;
import com.sunknowledge.dme.rcm.service.dto.InvoiceLineItemDetailsDTO;
import com.sunknowledge.dme.rcm.web.rest.ClaimsSubmissionMasterResource;

@RestController
@RequestMapping("/api")
public class ClaimSubmission {

	private final Logger log = LoggerFactory.getLogger(ClaimsSubmissionMasterResource.class);

	@Value("${jhipster.clientApp.name}")
	private String applicationName;

	@Autowired
	private ClaimSubmissionService claimSubmissionService;
	@Autowired
	private PrimaryClaimSubmissionService primaryClaimSubmissionService;
	@Autowired
	private PrimaryClaimReSubmissionService claimReSubmissionService;
	@Autowired
	private SecondaryClaimSubmissionService secondaryClaimSubmissionService;
	@Autowired
	private InvoiceService invoiceService;

	@PostMapping("/claimSubmission")
	public ServiceOutcome<List<ClaimSubmissionStatus>> claimSubmission() {
		ServiceOutcome<List<ClaimSubmissionStatus>> routcome = new ServiceOutcome<List<ClaimSubmissionStatus>>();
		try {
			routcome = claimSubmissionService.getSalesOrderForClaimsSubmission();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return routcome;
	}

	@PostMapping("/primaryClaimSubmission")
	public String primaryClaimSubmission(@RequestParam("salesOrderId") String salesOrderId,
			@RequestParam("claimControlNo") String claimControlNo) {
		String resultOutcomeJson = "";
		ServiceOutcome<ResultClaimSubmissionOutcome> routcome = new ServiceOutcome<ResultClaimSubmissionOutcome>();
		try {
			routcome = primaryClaimSubmissionService.accessProfessionalClaimsSubmission(salesOrderId, claimControlNo);
			if (routcome != null) {
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				resultOutcomeJson = ow.writeValueAsString(routcome);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultOutcomeJson;
	}

	@PostMapping("/primaryClaimReSubmission")
	public String primaryClaimReSubmission(@RequestParam("salesOrderNumber") String salesOrderNumber,
			@RequestParam("claimControlNo") String claimControlNo) {
		String resultOutcomeJson = "";
		ServiceOutcome<ResultClaimSubmissionOutcome> routcome = new ServiceOutcome<ResultClaimSubmissionOutcome>();
		try {
			routcome = claimReSubmissionService.accessProfessionalClaimsSubmission(salesOrderNumber, claimControlNo);
			if (routcome != null) {
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				resultOutcomeJson = ow.writeValueAsString(routcome);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultOutcomeJson;
	}

	@PostMapping("/secondaryClaimSubmission")
	public String secondaryClaimSubmission(@RequestParam("salesOrderId") Long salesOrderId,
			@RequestParam("claimControlNo") String claimControlNo) {
		String resultOutcomeJson = "";
		ServiceOutcome<ResultClaimSubmissionOutcome> routcome = new ServiceOutcome<ResultClaimSubmissionOutcome>();
		try {
			routcome = secondaryClaimSubmissionService.accessProfessionalClaimsSubmission(salesOrderId, claimControlNo);
			if (routcome != null) {
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				resultOutcomeJson = ow.writeValueAsString(routcome);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultOutcomeJson;
	}

	@PostMapping("/updateInvoicePostingDetails")
	public String updateInvoicePostingDetails(@RequestParam("secondaryInvoiceNo") String secondaryInvoiceNo,
			@RequestParam("secondaryInvoiceId") Long secondaryInvoiceId,
			@RequestParam("primaryInvoiceNo") String primaryInvoiceNo) {
		String resultOutcomeJson = "";
		ServiceOutcome<List<InvoiceLineItemDetailsDTO>> routcome = new ServiceOutcome<List<InvoiceLineItemDetailsDTO>>();
		try {
			routcome = invoiceService.updateInvoicePostingDetails(secondaryInvoiceNo, secondaryInvoiceId,primaryInvoiceNo);
			if (routcome != null) {
				ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
				resultOutcomeJson = ow.writeValueAsString(routcome);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultOutcomeJson;
	}
}
