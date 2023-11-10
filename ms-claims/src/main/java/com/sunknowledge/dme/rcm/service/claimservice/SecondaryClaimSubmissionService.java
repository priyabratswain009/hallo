package com.sunknowledge.dme.rcm.service.claimservice;

import com.sunknowledge.dme.rcm.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.pojo.common.ResultClaimSubmissionOutcome;
import com.sunknowledge.dme.rcm.pojo.secondarytertiary.ClaimSubmissionInput;

public interface SecondaryClaimSubmissionService {
	
	ServiceOutcome<ResultClaimSubmissionOutcome> accessProfessionalClaimsSubmission(Long soId, String claimControlNo);
	ServiceOutcome<ResultClaimSubmissionOutcome> professionalClaimSubmission(String token, ClaimSubmissionInput claimSubmissionInput);
	ServiceOutcome<ResultClaimSubmissionOutcome> professionalClaimHealthCheck(String token);
}
