package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.PrimaryClaimsReSubmissionMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the PrimaryClaimsReSubmissionMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrimaryClaimsReSubmissionMasterRepository extends JpaRepository<PrimaryClaimsReSubmissionMaster, Long> {}
