package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.ClaimSubmissionStatus;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ClaimSubmissionStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClaimSubmissionStatusRepository extends JpaRepository<ClaimSubmissionStatus, Long> {}
