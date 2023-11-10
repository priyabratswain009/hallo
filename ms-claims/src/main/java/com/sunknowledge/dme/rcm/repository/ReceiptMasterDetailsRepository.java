package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ReceiptMasterDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ReceiptMasterDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReceiptMasterDetailsRepository extends JpaRepository<ReceiptMasterDetails, Long> {}
