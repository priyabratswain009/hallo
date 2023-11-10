package com.sunknowledge.dme.rcm.service.claimservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.core.ServiceOutcome;
import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import com.sunknowledge.dme.rcm.repository.primaryclaimrepository.ClaimSubmissionStatusRepo;

@Service
public class ClaimSubmissionServiceImpl implements ClaimSubmissionService {

	@Autowired
	private ClaimSubmissionStatusRepo claimSubmissionStatusRepo;
	@Autowired
	private PrimaryClaimSubmissionService primaryClaimSubmissionService;
	@Autowired
	private PrimaryClaimReSubmissionService primaryClaimReSubmissionService;
	@Autowired
	private SecondaryClaimSubmissionService secondaryClaimSubmissionService;

	@Override
	public ServiceOutcome<List<ClaimSubmissionStatus>> getSalesOrderForClaimsSubmission() {
		// TODO Auto-generated method stub

		List<ClaimSubmissionStatus> objList = claimSubmissionStatusRepo.getAllClaimsForSubmission();
		ServiceOutcome<List<ClaimSubmissionStatus>> outCome = new ServiceOutcome<List<ClaimSubmissionStatus>>();
		String claimType = "";

		for (ClaimSubmissionStatus obj : objList) {

			claimType = obj.getIntClaimNo().substring(0, 3);

			System.out.println("Type of Claim: " + claimType);

			if (claimType.equalsIgnoreCase("CLP")) {
				primaryClaimSubmissionService.accessProfessionalClaimsSubmission(String.valueOf(obj.getSalesOrderId()),obj.getIntClaimNo());
			} else if (claimType.equalsIgnoreCase("CLS")) {
				primaryClaimReSubmissionService
						.accessProfessionalClaimsSubmission(String.valueOf(obj.getSalesOrderId()),obj.getIntClaimNo());
			} else if (claimType.equalsIgnoreCase("CLR")) {
				secondaryClaimSubmissionService.accessProfessionalClaimsSubmission(obj.getSalesOrderId(),
						obj.getIntClaimNo());
			}

		}

		if (objList == null) {
			outCome.setData(null);
			outCome.setMessage("Data is not available");
			outCome.setOutcome(true);
		} else {
			outCome.setData(objList);
			outCome.setMessage("Data Retrieved Successfully");
			outCome.setOutcome(true);
		}

		return outCome;
	}

}
