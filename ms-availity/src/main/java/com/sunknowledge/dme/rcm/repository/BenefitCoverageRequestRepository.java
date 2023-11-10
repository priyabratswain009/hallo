package com.sunknowledge.dme.rcm.repository;

import com.sunknowledge.dme.rcm.domain.BenefitCoverageRequest;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the BenefitCoverageRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BenefitCoverageRequestRepository extends JpaRepository<BenefitCoverageRequest, Long> {}
