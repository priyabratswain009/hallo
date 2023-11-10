package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SoItemTransactionDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SoItemTransactionDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoItemTransactionDetailsRepository extends JpaRepository<SoItemTransactionDetails, Long> {}
