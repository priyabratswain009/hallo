package com.sunknowledge.dme.rcm.web.rest.claimssubmissiondata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.dto.claims.AddSecondaryForPrimaryDTO;
import com.sunknowledge.dme.rcm.service.claimssubmissiondata.ClaimsSubmissionDataServiceExtended;
import com.sunknowledge.dme.rcm.service.dto.SalesOrderMasterDTO;

import reactor.core.publisher.Mono;

@Validated
@RestController
@RequestMapping("/api")
public class ClaimsSubmissionDataResourceExtended {

	@Value("${jhipster.clientApp.name}")
	private String applicationName;
	@Autowired
	private ClaimsSubmissionDataServiceExtended claimsSubmissionDataService;

	@PostMapping("/getClaimsSubmissionData")
	public void getClaimsSubmissionData(@RequestParam("salesOrderId") String salesOrderId) {
		claimsSubmissionDataService.getClaimsSubmissionData(salesOrderId);
	}

	@PostMapping("/addSecondaryForPrimary")
	public Mono<ServiceOutcome<AddSecondaryForPrimaryDTO>> addSecondaryForPrimary(
			@RequestParam("salesOrderId") Long salesOrderId,
			@RequestParam("internalClaimControlNumber") String internalClaimControlNumber) {

		return claimsSubmissionDataService.addSecondaryForPrimary(salesOrderId, internalClaimControlNumber)
				.map(v -> { System.out.println(v);
					return new ServiceOutcome<AddSecondaryForPrimaryDTO>(v, true, "Data Inserted Successfully");
							})
				.switchIfEmpty(Mono.just(new ServiceOutcome<AddSecondaryForPrimaryDTO>(null, false, "Data Insertion Failed")));

	}

}
