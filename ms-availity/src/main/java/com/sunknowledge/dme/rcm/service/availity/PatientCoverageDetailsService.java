package com.sunknowledge.dme.rcm.service.availity;

import java.util.List;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;

public interface PatientCoverageDetailsService {

	List<BenefitCoverageResponse> getpatientCoverageDetails(String memberId);
}
