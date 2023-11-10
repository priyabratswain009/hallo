package com.sunknowledge.dme.rcm.service.availity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageResponse;
import com.sunknowledge.dme.rcm.repository.Availity.BenefitCoverageResponseRepo;

@Service
public class PatientCoverageDetailsServiceImpl implements PatientCoverageDetailsService {


	@Autowired
	BenefitCoverageResponseRepo benefitCoverageResponseRepository;
	
	@Override
	public List<BenefitCoverageResponse> getpatientCoverageDetails(String memberId) {

		List<BenefitCoverageResponse> coverageList = new ArrayList<>();
		
		coverageList = benefitCoverageResponseRepository.getcoverageDetailswithmemberID(memberId);
		
		return coverageList;
	}

}
