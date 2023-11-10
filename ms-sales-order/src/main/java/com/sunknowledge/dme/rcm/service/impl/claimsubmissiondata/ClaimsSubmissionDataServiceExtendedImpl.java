package com.sunknowledge.dme.rcm.service.impl.claimsubmissiondata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunknowledge.dme.rcm.dto.claims.AddSecondaryForPrimaryDTO;
import com.sunknowledge.dme.rcm.repository.ClaimsSubmissionMasterDataRepoExtended;
import com.sunknowledge.dme.rcm.repository.salesorder.SalesOrderMasterRepositoryExtended;
import com.sunknowledge.dme.rcm.service.claimssubmissiondata.ClaimsSubmissionDataServiceExtended;
import com.sunknowledge.dme.rcm.service.mapper.SalesOrderMasterMapper;

import reactor.core.publisher.Mono;

@Service
public class ClaimsSubmissionDataServiceExtendedImpl implements ClaimsSubmissionDataServiceExtended {

	@Autowired
	private ClaimsSubmissionMasterDataRepoExtended claimsSubmissionDataRepository;
	@Autowired
	private SalesOrderMasterRepositoryExtended salesOrderMasterRepositoryExtended;
	@Autowired
	private SalesOrderMasterMapper salesOrderMasterMapper;

	@Override
	public void getClaimsSubmissionData(String soId) {
		// TODO Auto-generated method stub
		try {
			claimsSubmissionDataRepository.getClaimsSubmissionData(Long.valueOf(soId)).collectList().toFuture().get()
					.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Mono<AddSecondaryForPrimaryDTO> addSecondaryForPrimary(Long salesOrderId,
			String internalClaimControlNumber) {
		// TODO Auto-generated method stub

		return salesOrderMasterRepositoryExtended.addSecondaryForPrimary(salesOrderId, internalClaimControlNumber)
				.map(v -> {
					System.out.println(v);
					return v;
				});

	}

}
