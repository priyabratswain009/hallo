package com.sunknowledge.dme.rcm.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import com.sunknowledge.dme.rcm.repository.primaryclaimrepository.ClaimSubmissionStatusRepo;
import com.sunknowledge.dme.rcm.service.claimservice.PrimaryClaimSubmissionService;
import com.sunknowledge.dme.rcm.service.claimservice.SecondaryClaimSubmissionService;

@Component
public class ClaimSubmissionScheduler {

	@Autowired
	private ClaimSubmissionStatusRepo claimSubmissionStatusRepository;
	@Autowired
	private PrimaryClaimSubmissionService primaryClaimSubmissionService;
	@Autowired
	private SecondaryClaimSubmissionService secondaryClaimSubmissionService;

	@Scheduled(cron = "0 42 17 ? * *")
	public void claimSubmissionScheduler() {

		/*
		 * List<ClaimSubmissionStatus> cList =
		 * claimSubmissionStatusRepository.getAllClaimsForSubmission(); for
		 * (ClaimSubmissionStatus a : cList) { System.out.println(a.getSalesOrderId());
		 * if (a.getPayorLevel().equalsIgnoreCase("P")) {
		 * primaryClaimSubmissionService.accessProfessionalClaimsSubmission(String.
		 * valueOf(a.getSalesOrderId())); } else if
		 * (a.getPayorLevel().equalsIgnoreCase("S")) {
		 * secondaryClaimSubmissionService.accessProfessionalClaimsSubmission(String.
		 * valueOf(a.getSalesOrderId())); } }
		 */
	}

}
