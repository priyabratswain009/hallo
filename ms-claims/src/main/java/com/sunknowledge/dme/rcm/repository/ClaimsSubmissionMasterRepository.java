package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ClaimsSubmissionMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ClaimsSubmissionMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimsSubmissionMasterRepository extends JpaRepository<ClaimsSubmissionMaster, Long> {}
