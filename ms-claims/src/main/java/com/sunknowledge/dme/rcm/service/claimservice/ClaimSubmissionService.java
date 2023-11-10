package com.sunknowledge.dme.rcm.service.claimservice;

import java.util.List;

import com.sunknowledge.dme.rcm.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import com.sunknowledge.dme.rcm.pojo.common.ResultClaimSubmissionOutcome;

public interface ClaimSubmissionService {

	ServiceOutcome<List<ClaimSubmissionStatus>> getSalesOrderForClaimsSubmission();
	
}
