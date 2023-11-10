package com.sunknowledge.dme.rcm.repository.primaryclaimrepository;

import com.sunknowledge.dme.rcm.domain.ReceiptMasterDetails;
import org.springframework.data.jpa.repository.Query;

import com.sunknowledge.dme.rcm.repository.ReceiptMasterDetailsRepository;
import org.springframework.data.repository.query.Param;

public interface ReceiptMasterDetailsRepo extends ReceiptMasterDetailsRepository {
	@Query(value = "SELECT concat('RP', nextval('claims.receipt_master_details_s_receipt_no'))", nativeQuery = true)
	String getReceiptNumberNextVal();

    @Query(value = "SELECT * FROM get_receipt_master_details_s_receipt_no()", nativeQuery = true)
    String getReceiptNumber();

    @Query("FROM ReceiptMasterDetails WHERE depositId = :depositId")
    ReceiptMasterDetails getReceiptDetailsOnDepositMaster(@Param("depositId") Long depositId);
}
