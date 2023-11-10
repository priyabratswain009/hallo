package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.SecondaryClaimsSubmissionMaster;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SecondaryClaimsSubmissionMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecondaryClaimsSubmissionMasterRepository extends JpaRepository<SecondaryClaimsSubmissionMaster, Long> {}
