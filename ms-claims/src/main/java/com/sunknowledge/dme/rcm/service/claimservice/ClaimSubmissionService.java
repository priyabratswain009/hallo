package com.sunknowledge.dme.rcm.service.claimservice;

import java.util.List;

import com.sunknowledge.dme.rcm.application.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;

public interface ClaimSubmissionService {

	ServiceOutcome<List<ClaimSubmissionStatus>> getSalesOrderForClaimsSubmission();

}
