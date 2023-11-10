package com.sunknowledge.dme.rcm.repository.primaryclaimrepository;

import com.sunknowledge.dme.rcm.domain.DepositMasterDetails;
import com.sunknowledge.dme.rcm.repository.DepositMasterDetailsRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepositMasterDetailsRepo extends DepositMasterDetailsRepository {
	@Query(value = "SELECT concat('CP', nextval('claims.deposit_master_details_s_deposit_no'))", nativeQuery = true)
	String getDepositNumberNextVal();

    @Query(value = "SELECT * FROM get_deposit_master_details_s_deposit_no()", nativeQuery = true)
    String getDepositNumber();

    @Query("FROM DepositMasterDetails WHERE claimCob835MasterId = :claimCob835MasterId")
    DepositMasterDetails getDepositeDetailsOnClaimCOB835Master(@Param("claimCob835MasterId") Long claimCob835MasterId);

    @Query(value = "select * from t_deposit_master_details where deposit_reference = :checkOrEFTTraceNumber and deposit_status is null or deposit_status = '' and status = 'Active'", nativeQuery = true)
    DepositMasterDetails getDepositMasterDetailsOnReferenceNumber(@Param("checkOrEFTTraceNumber") String checkOrEFTTraceNumber);
}
