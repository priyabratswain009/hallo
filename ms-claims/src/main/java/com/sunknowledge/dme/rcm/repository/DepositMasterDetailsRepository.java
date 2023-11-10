package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.DepositMasterDetails;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the DepositMasterDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepositMasterDetailsRepository extends JpaRepository<DepositMasterDetails, Long> {}
